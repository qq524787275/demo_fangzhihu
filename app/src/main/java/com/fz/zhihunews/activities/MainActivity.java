package com.fz.zhihunews.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.model.LatLng;
import com.feeljoy.library.annotation.view.ViewInject;
import com.feeljoy.library.base.BaseActivity;
import com.feeljoy.library.widget.dialog.FzDialog;
import com.fz.zhihunews.R;
import com.fz.zhihunews.fragment.HotFragment;
import com.fz.zhihunews.fragment.RiBaoFragment;
import com.fz.zhihunews.fragment.WeiXinFragment;
import com.fz.zhihunews.fragment.ZhuanLanFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    @ViewInject(id = R.id.tablayout)
    private TabLayout mTablayout;
    @ViewInject(id = R.id.viewpage)
    private ViewPager mViewpage;
    private List<Fragment> mFragments;

    private String currentLocation;
    //    private String[] mTitles = {"日报", "专栏", "微信", "热门"};
    private String[] mTitles;
    @ViewInject(id = R.id.floatButton)
    private FloatingActionButton mFloatButton;

    private LocationClient mLocationClient;
    private MyLocationListener myLocationListener;


    @Override
    protected void fzOnCreate(Bundle bundle) {

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        mFloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FzDialog fzDialog = new FzDialog(MainActivity.this);
                fzDialog.setTitle("定位结果:");
                if (currentLocation != null) {
                    fzDialog.setMessage(currentLocation);
                }else{
                    fzDialog.setMessage("请重新定位!");
                }
                fzDialog.setCommitText("重新定位当前位置");
                fzDialog.setCancleText("Go百度定位");
                fzDialog.setOnButtonClickListener(new FzDialog.OnButtonClickListener() {
                    @Override
                    public void onCancleClick() {
                        Intent intent=new Intent(MainActivity.this,BaiduActivity.class);
                        startActivity(intent);

                    }


                    @Override
                    public void onCommitClick(String inputtext) {
                       mLocationClient.start();
                    }
                });
                fzDialog.show();
            }
        });
    }


    private void showDizlog() {
        final FzDialog fzDialog = new FzDialog(MainActivity.this);
        fzDialog.setTitle("中二");
        fzDialog.setMessage("弹指遮天!");
        fzDialog.show();
    }

    @Override
    protected void initView() {
        myLocationListener = new MyLocationListener();
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myLocationListener);
        initLocation();
        mLocationClient.start();


        mTitles = getResources().getStringArray(R.array.select_tab_item);
        mFragments = new ArrayList<>();
        mFragments.add(RiBaoFragment.newInstance());
        mFragments.add(ZhuanLanFragment.newInstance());
        mFragments.add(WeiXinFragment.newInstance());
        mFragments.add(HotFragment.newInstance());

        mViewpage.setOffscreenPageLimit(mTitles.length);
        mTablayout.setupWithViewPager(mViewpage);


        mViewpage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });

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
                currentLocation = location.getLocationDescribe();
                Toast.makeText(MainActivity.this, currentLocation, Toast.LENGTH_SHORT).show();

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
            MapStatus mapStatus = new MapStatus.Builder()
                    .target(latLng)
                    .build();
           if(mLocationClient.isStarted()){
               mLocationClient.stop();
           }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }
}
