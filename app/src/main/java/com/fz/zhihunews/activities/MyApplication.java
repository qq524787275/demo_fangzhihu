package com.fz.zhihunews.activities;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.feeljoy.library.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

/**
 * Created by feizhuo on 2017/4/27.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpHeaders headers = new HttpHeaders();
        HttpParams params = new HttpParams();
        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers)                      //全局公共头
                .addCommonParams(params);                       //全局公共参数

        ToastUtils.init(this);


        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);

        SDKInitializer.initialize(getApplicationContext());
    }
}
