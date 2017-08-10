package com.fz.zhihunews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feeljoy.library.annotation.view.ViewInject;
import com.feeljoy.library.base.BaseFragment;
import com.feeljoy.library.utils.ToastUtils;
import com.fz.zhihunews.Constants;
import com.fz.zhihunews.R;
import com.fz.zhihunews.activities.NewsActivity;
import com.fz.zhihunews.adapter.LatestAdapter;
import com.fz.zhihunews.bean.Latest;
import com.fz.zhihunews.callback.JsonConvert;
import com.fz.zhihunews.utils.GlideImageLoader;
import com.fz.zhihunews.view.EmptyView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feizhuo on 2017/4/27.
 */

public class RiBaoFragment extends BaseFragment {
    private static final String TAG = "RiBaoFragment";
    @ViewInject(id = R.id.recyclerview)
    private RecyclerView mRecyclerview;


    private LatestAdapter mLatestAdapter;
    private Banner mBanner;
    @ViewInject(id = R.id.swipefrefresh)
    private SwipeRefreshLayout mSwipefrefresh;
    @ViewInject(id = R.id.emptyview)
    private EmptyView mEmptyview;


    public static RiBaoFragment newInstance() {

        Bundle args = new Bundle();

        RiBaoFragment fragment = new RiBaoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_ribao, container, false);
    }

    @Override
    protected void initView(View view) {


        mSwipefrefresh.setSize(40);
        mSwipefrefresh.setColorSchemeResources(R.color.white);
        mSwipefrefresh.setProgressBackgroundColorSchemeResource(R.color.green_27b443);

        mLatestAdapter = new LatestAdapter();
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerview.setAdapter(mLatestAdapter);

        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.view_header, null);
        mBanner = (Banner) inflate.findViewById(R.id.banner);
        //        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //        //设置自动轮播，默认为true
        //        mBanner.isAutoPlay(true);
        //        //设置轮播时间
        mBanner.setDelayTime(3000);
        //        //设置指示器位置（当banner模式中有指示器时）
        //        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mLatestAdapter.addHeaderView(inflate);

    }

    @Override
    protected void initEvent() {
        mSwipefrefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                downLoadData();
            }
        });
    }

    @Override
    protected void initData() {
        downLoadData();
    }

    private void downLoadData() {
        OkGo.<Latest>get(Constants.URL_RIBAO)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .execute(new JsonConvert<Latest>() {
                    @Override
                    public void onSuccess(Response<Latest> response) {
                        super.onSuccess(response);
                        final Latest latest=response.body();
                        mLatestAdapter.setNewData(latest.getStories());
                        List<String> strings = new ArrayList<>();
                        List<String> titles = new ArrayList<>();
                        for (int i = 0; i < latest.getTop_stories().size(); i++) {
                            String image = latest.getTop_stories().get(i).getImage();
                            Log.i(TAG, "onSuccess: -----------------" + image);
                            strings.add(latest.getTop_stories().get(i).getImage());
                            titles.add(latest.getTop_stories().get(i).getTitle());
                        }


                        mBanner.setBannerTitles(titles);
                        mBanner.setImages(strings);
                        mBanner.start();
                        mBanner.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {

                                Intent intent = new Intent(getActivity(), NewsActivity.class);
                                intent.putExtra("id", latest.getTop_stories().get(position).getId());
                                startActivity(intent);
                            }
                        });
                        mSwipefrefresh.setRefreshing(false);
                        mEmptyview.setVisibility(View.GONE);
                        ToastUtils.showShortToast("刷新成功~~!");
                    }

//                    @Override
//                    public void onSuccess(final Latest latest, Call call, Response response) {
//                        super.onSuccess(latest, call, response);
//                        mLatestAdapter.setNewData(latest.getStories());
//                        List<String> strings = new ArrayList<>();
//                        List<String> titles = new ArrayList<>();
//                        for (int i = 0; i < latest.getTop_stories().size(); i++) {
//                            String image = latest.getTop_stories().get(i).getImage();
//                            Log.i(TAG, "onSuccess: -----------------" + image);
//                            strings.add(latest.getTop_stories().get(i).getImage());
//                            titles.add(latest.getTop_stories().get(i).getTitle());
//                        }
//
//
//                        mBanner.setBannerTitles(titles);
//                        mBanner.setImages(strings);
//                        mBanner.start();
//                        mBanner.setOnBannerListener(new OnBannerListener() {
//                            @Override
//                            public void OnBannerClick(int position) {
//
//                                Intent intent = new Intent(getActivity(), NewsActivity.class);
//                                intent.putExtra("id", latest.getTop_stories().get(position).getId());
//                                startActivity(intent);
//                            }
//                        });
//                        mSwipefrefresh.setRefreshing(false);
//                        mEmptyview.setVisibility(View.GONE);
//                        ToastUtils.showShortToast("刷新成功~~!");
//                    }

//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        ToastUtils.showLongToast("数据加载失败!");
//                        mEmptyview.setMode(EmptyView.MODE_ERROR);
//                        mEmptyview.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                downLoadData();
//                            }
//                        });
//                    }

                    @Override
                    public void onError(Response<Latest> response) {
                        super.onError(response);
                        ToastUtils.showLongToast("数据加载失败!");
                        mEmptyview.setMode(EmptyView.MODE_ERROR);
                        mEmptyview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                downLoadData();
                            }
                        });
                    }
                });


    }

}
