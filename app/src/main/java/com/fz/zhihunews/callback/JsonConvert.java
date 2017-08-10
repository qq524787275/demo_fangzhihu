package com.fz.zhihunews.callback;

import android.app.ListActivity;
import android.util.Log;

import com.fz.zhihunews.utils.Convert;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;

import java.io.Reader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by feizhuo on 2017/4/25.
 */

public class JsonConvert<T> extends AbsCallback<T> {
    private static final String TAG = "JsonConvert";

    @Override
    public void onSuccess(com.lzy.okgo.model.Response<T> response) {
        Log.i(TAG, "onSuccess: ");
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        Reader reader = response.body().charStream();
        Type genericSuperclass = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        Type type = params[0];
        return Convert.fromJson(reader, type);
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        Log.i(TAG, "onStart: ");
    }

    @Override
    public void onFinish() {
        super.onFinish();
        Log.i(TAG, "onFinish: ");
    }
    //
//    @Override
//    public void onSuccess(T t, Call call, Response response) {
//        Log.i(TAG, "onSuccess: ");
//    }
//
//    @Override
//    public T convertSuccess(Response response) throws Exception {
//        Log.i(TAG, "convertSuccess: ");
//        Reader reader = response.body().charStream();
//        Type genericSuperclass = getClass().getGenericSuperclass();
//        Type[] params = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
//        Type type = params[0];
//        return Convert.fromJson(reader, type);
//    }
//
//    @Override
//    public void onBefore(BaseRequest request) {
//        super.onBefore(request);
//        Log.i(TAG, "onBefore: ");
//
//    }
//
//    @Override
//    public void onAfter(T t, Exception e) {
//        Log.i(TAG, "onAfter: ");
//        super.onAfter(t, e);
//
//    }
//
//    @Override
//    public void onCacheError(Call call, Exception e) {
//        super.onCacheError(call, e);
//        Log.i(TAG, "onCacheError: 来自缓存数据！！");
//    }
//
//    @Override
//    public void onCacheSuccess(T t, Call call) {
//        super.onCacheSuccess(t, call);
//        Log.i(TAG, "onCacheSuccess: 来自缓存数据！！");
//    }

    @Override
    public void onCacheSuccess(com.lzy.okgo.model.Response<T> response) {
        super.onCacheSuccess(response);
        Log.i(TAG, "onCacheSuccess: ");
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        Log.i(TAG, "onError: ");
    }
}
