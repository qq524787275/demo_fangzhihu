package com.fz.zhihunews.activities;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.utils.DistanceUtil;
import com.feeljoy.library.annotation.view.ViewInject;
import com.feeljoy.library.base.BaseActivity;
import com.fz.zhihunews.R;

import java.util.List;

public class BaiduActivity extends BaseActivity implements View.OnClickListener {

    private static final int BAIDU_READ_PHONE_STATE = 100;
    private LocationClient mLocationClient;
    private MyLocationListener mBdLocationListener = new MyLocationListener();
    private BaiduMap mBaiduMap;


    @ViewInject(id = R.id.baidu_openlocation)
    private Button mBaiduOpenlocation;

    @ViewInject(id = R.id.baidu_closelocation)
    private Button mBaiduCloselocation;

    @ViewInject(id = R.id.baidu_mapview)
    private MapView mBaiduMapview;
    private LatLng pt1;

    private LatLng currentLatLng;

    @ViewInject(id = R.id.baidu_keyword)
    private EditText mBaiduKeyword;
    @ViewInject(id = R.id.baidu_query)
    private Button mBaiduQuery;
    private PoiSearch mPoiSearch;

    @Override
    protected void fzOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_baidu);
    }

    @Override
    protected void initView() {

        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult == null || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                    Toast.makeText(BaiduActivity.this, "未搜索到相应的结果!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {// 检索结果正常返回
                    mBaiduMap.clear();
                    MyPoiOverlay poiOverlay = new MyPoiOverlay(mBaiduMap);
                    poiOverlay.setData(poiResult);// 设置POI数据
                    mBaiduMap.setOnMarkerClickListener(poiOverlay);
                    poiOverlay.addToMap();// 将所有的overlay添加到地图上
                    poiOverlay.zoomToSpan();
//                    totalPage = poiResult.getTotalPageNum();// 获取总分页数
//                    Toast.makeText(
//                            PoiSearchActivity.this,
//                            "总共查到" + poiResult.getTotalPoiNum() + "个兴趣点, 分为"
//                                    + totalPage + "页", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
                if (poiDetailResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(BaiduActivity.this, "抱歉，未找到结果",
                            Toast.LENGTH_SHORT).show();
                } else {// 正常返回结果的时候，此处可以获得很多相关信息
                    Toast.makeText(
                            BaiduActivity.this,
                            poiDetailResult.getName() + ": "
                                    + poiDetailResult.getAddress(),
                            Toast.LENGTH_LONG).show();
                    String url = poiDetailResult.getDetailUrl();
                    alertDialog(url);

                }
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        });

        mBaiduMap = mBaiduMapview.getMap();

        mBaiduOpenlocation.setOnClickListener(this);
        mBaiduCloselocation.setOnClickListener(this);
        mBaiduQuery.setOnClickListener(this);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(mBdLocationListener);

        initLocation();
// 天安门坐标
        double mLat1 = 39.915291;
        double mLon1 = 116.403857;
        pt1 = new LatLng(mLat1, mLon1);
        currentLatLng = pt1;
    }

    private void alertDialog(String url) {

        AlertDialog.Builder builder = new AlertDialog.Builder(BaiduActivity.this, R.style.Dialog_Fullscreen);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.alertimg);
        window.setWindowAnimations(R.style.dialogWindowAnim);
        WebView view = (WebView) window.findViewById(R.id.dialog_webview);
        View viewById = window.findViewById(R.id.dialog_back);

        WebSettings settings = view.getSettings();
        settings.setJavaScriptEnabled(true);//支持JavaScript脚本语言
        settings.setAllowContentAccess(true);//允许访问文件
        settings.setBuiltInZoomControls(true);//设置显示缩放按钮
        settings.setSupportZoom(true);//设置支持缩放
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);//屏幕自适应
        settings.setLoadWithOverviewMode(true);



        view.loadUrl(url);
        view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(String.valueOf(request));
                return true;
            }
        });
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.baidu_openlocation:
                Toast.makeText(this, "开启", Toast.LENGTH_SHORT).show();
                mLocationClient.start();
                break;
            case R.id.baidu_closelocation:
                Toast.makeText(this, "关闭", Toast.LENGTH_SHORT).show();
                mLocationClient.stop();
                break;
            case R.id.baidu_query:
                search();
                break;
            default:
                break;
        }
    }

    private void search() {
        PoiNearbySearchOption poiNearbySearchOption = new PoiNearbySearchOption();
        poiNearbySearchOption.keyword(mBaiduKeyword.getText().toString().trim());
        poiNearbySearchOption.location(currentLatLng);
        poiNearbySearchOption.radius(5000);
        mPoiSearch.searchNearby(poiNearbySearchOption);
    }


    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }


    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            currentLatLng = latLng;
            MapStatus mapStatus = new MapStatus.Builder()
                    .target(latLng)
                    .build();
            mBaiduMap.clear();
            Log.i("aaaa", "onReceiveLocation: " + DistanceUtil.getDistance(pt1, latLng) / 1000 + "km");
            mBaiduMap.addOverlay(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(mapStatus));

        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

    private class MyPoiOverlay extends PoiOverlay {

        /**
         * 构造函数
         *
         * @param baiduMap 该 PoiOverlay 引用的 BaiduMap 对象
         */
        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int i) {
            super.onPoiClick(i);
            PoiInfo poiInfo = getPoiResult().getAllPoi().get(i);
            // 检索poi详细信息
            mPoiSearch.searchPoiDetail(new PoiDetailSearchOption()
                    .poiUid(poiInfo.uid));
            return true;
        }
    }
}
