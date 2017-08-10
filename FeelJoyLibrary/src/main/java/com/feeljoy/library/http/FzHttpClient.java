package com.feeljoy.library.http;

import android.os.Handler;
import android.os.Looper;
import com.feeljoy.library.utils.LogUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



/**
 * 网络请求
 * Created by Cate on 2016/3/25.
 */
public class FzHttpClient {

    private final static String TAG = "FzHttpClient";

    private OkHttpClient mClient;
    private Handler mDelivery;

    public FzHttpClient(){
//        mClient = new OkHttpClient();
         mClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();
        mDelivery = new Handler(Looper.getMainLooper());


    }

//    /**
//     * 设置主机读取数据超时
//     * @param readTimeOut 读取超时时间（单位 毫秒）
//     */
//    public void setReadTimeOut(long readTimeOut ){
//        setReadTimeout(readTimeOut,TimeUnit.MILLISECONDS);
//    }
//
//    /**
//     * 设置主机读取数据超时
//     * @param timeout 超时时间
//     * @param unit 时间单位
//     */
//    public void setReadTimeout(long timeout, TimeUnit unit) {
//        setReadTimeout(timeout, TimeUnit.MILLISECONDS);
//    }
//
//    /**
//     * 设置写超时时间
//     * @param writeTimeOut 写超时时间（单位 毫秒）
//     */
//    public void setWriteTimeout(long writeTimeOut ){
//        setWriteTimeout(writeTimeOut, TimeUnit.MILLISECONDS);
//    }
//    /**
//     * 设置写数据超时
//     * @param timeout 超时时间
//     * @param unit 时间单位
//     */
//    public void setWriteTimeout(long timeout, TimeUnit unit) {
////       mClient.setWriteTimeout(timeout, unit);
////        mClient.writeTimeout(timeout, unit);
//        mClient.newBuilder().writeTimeout(timeout, unit);
//    }
//
//    /**
//     * 设置连接超时
//     * @param connectTimeout 写超时时间（单位 毫秒）
//     */
//    public void setConnectTimeout(long connectTimeout ){
//        setWriteTimeout(connectTimeout, TimeUnit.MILLISECONDS);
//    }
//    /**
//     * 设置连接超时
//     * @param timeout 超时时间
//     * @param unit 时间单位
//     */
//    public void setConnectTimeout(long timeout, TimeUnit unit) {
////        mClient.setConnectTimeout(timeout, unit);
//        mClient =  mClient.newBuilder().connectTimeout(timeout,unit).build();
//    }


    /**
     * 同步GET方式获取网络数据
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public Response getSync(String url , FzParams params) throws IOException {
        Request request;
        if (params==null || params.getSize()==0){
            request =  new Request.Builder().url(url).build();
        }else{
            RequestBody body =  params.getRequest();
            request = new Request.Builder().url(url+"?"+params.getParamString()).get().build();
        }

        Call call = mClient.newCall(request);
        Response response = call.execute();



        return response ;
    }

    /**
     * 同步GET方式获取网络数据
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public String getSyncString(String url ,FzParams params) throws IOException{
        return   getSyncString(url, params, null);
    }


    /**
     * 同步GET方式获取网络数据
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public String getSyncString(String url ,FzParams params,HashMap<String, String> headers) throws IOException{
        Request request;
        Request.Builder builder = new Request.Builder();
        appendHeaders(builder,headers);
        if (params==null || params.getSize()==0){
            request =  builder.url(url).get().build();
        }else{
            request = builder.url(url + "?" + params.getParamString()).get().build();
        }
        Call call = mClient.newCall(request);
        Response response = call.execute();
        return response.body().string() ;
    }

    /**
     * 同步GET方式获取网络数据 以字符串的方式返回
     * @param url
     * @return
     * @throws IOException
     */
    public String getSyncString(String url ) throws IOException{
        return getSyncString(url, null, null) ;
    }

    /**
     * 同步GET方式获取网络数据
     * @param url
     * @return
     * @throws IOException
     */
    public Response getSync(String url ) throws IOException{
        return getSync(url, null) ;
    }

    /**
     * 异步获取网络数据
     * @param url
     */
    public void get(String url){
        get(url, null);
    }

    /**
     * 异步获取网络数据
     * @param url
     * @param callBack 回调接口（返回数据执行在主线程）
     */
    public void get(String url ,FzCallBack callBack){
        get(url, null,null,callBack);
    }


    /**
     * 异步获取网络数据
     * @param url  地址
     * @param params
     * @param callBack  回调接口（返回数据执行在主线程）
     */
    public void get(String url ,FzParams params,final FzCallBack callBack){
        get(url, params, null, null, callBack);
    }

    /**
     * 异步获取网络数据
     * @param url  地址
     * @param params
     * @param tag  本次请求的标签，可以根据标签取消本次请求
     * @param callBack  回调接口（返回数据执行在主线程）
     */
    public void get(String url ,FzParams params, Object tag ,final FzCallBack callBack){
       get(url, params, null, tag, callBack);
    }


    /**
     * 异步获取网络数据
     * @param url  地址
     * @param params
     * @param headers 头文件
     * @param tag  本次请求的标签，可以根据标签取消本次请求
     * @param callBack  回调接口（返回数据执行在主线程）
     */
    public void get(String url ,FzParams params,HashMap<String, String> headers,Object tag, final FzCallBack callBack){
        Request request;
        Request.Builder builder = new Request.Builder();
        appendHeaders(builder,headers);
        if(tag!=null){
            builder.tag(tag);
        }
        if (params==null || params.getSize()==0){

            request =  builder.url(url).build();
        }else{
            request = builder.url(url + "?" + params.getParamString()).get().build();
        }
        Call call = mClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handFailure(call, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handSuccess(call,response, callBack);
            }
        });
//        call.enqueue(new Callback() {
//
//            @Override
//            public void onFailure(Request request, IOException e) {
//                handFailure(request, e, callBack);
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                handSuccess(response, callBack);
//
//            }
//        });
    }



    /**
     * 同步POST提交网络数据
     * @param url
     * @param params
     * @return Response
     * @throws IOException
     */
    public  Response postSync(String url ,FzParams params, HashMap<String, String> headers) throws IOException {
        Request request;
        Request.Builder builder = new Request.Builder();
        appendHeaders(builder,headers);
        if (params==null || params.getSize()==0){
            request =  builder.url(url).build();
        }else{
            RequestBody body = params.getRequest();
            request = builder.url(url).post(body).build();
        }
        Call call = mClient.newCall(request);
        Response response = call.execute();
        return response ;
    }

    /**
     * 同步POST提交网络数据
     * @param url
     * @return Response
     * @throws IOException
     */
    public  Response postSync(String url) throws IOException {
        return  postSync(url, null, null);
    }

    /**
     * 同步POST提交网络数据 （直接返回字符串）
     * @param url
     * @return
     * @throws IOException
     */
    public  String postSyncString(String url) throws IOException {
        return  postSync(url,null,null).body().string();
    }

    /**
     * 同步POST提交网络数据 （直接返回字符串）
     * @param url
     * @param params
     * @return String
     * @throws IOException
     */
    public  String postSyncString(String url ,FzParams params) throws IOException {
        return  postSync(url,params,null).body().string();
    }

    /**
     * 异步提交网络数据
     * @param url
     */
    public void post(String url){
        post(url,null,null);
    }
    /**
     * 异步提交网络数据
     * @param url
     * @param callBack
     */
    public void post(String url, FzCallBack callBack){
        post(url,null,null,callBack);
    }
    /**
     * 异步提交网络数据
     * @param url
     * @param params
     */
    public void post(String url,FzParams params){
        post(url,params,null,null);
    }

    /**
     * 异步提交网络数据
     * @param url
     * @param params
     * @param callBack
     */
    public void post(String url,FzParams params,FzCallBack callBack){
        post(url, params, null,callBack);
    }

    /**
     * 异步提交网络数据
     * @param url
     * @param params
     * @param tag  本次请求的标签，可以根据标签取消本次请求
     * @param callBack
     */
    public void post(String url,FzParams params, Object tag,FzCallBack callBack){
        post(url, params, null, tag,callBack);
    }

    /**
     * 异步提交网络数据
     * @param url
     * @param params
     * @param headers  头文件
     * @param tag  本次请求的标签，可以根据标签取消本次请求
     * @param callBack
     */
    public void post(String url,FzParams params, HashMap<String, String> headers,Object tag,final FzCallBack callBack){
        Request request;
        Request.Builder builder = new Request.Builder();
        appendHeaders(builder,headers);
        if (tag!=null){
            builder.tag(tag);
        }
        if (params==null || params.getSize()==0){
            request =  builder.url(url).build();
        }else{
            RequestBody body = params.getRequest();
            request = builder.url(url).post(body).build();
        }
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handFailure(call, e, callBack);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handSuccess(call, response,callBack);
            }
        });

//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                handFailure(request, e, callBack);
//            }
//            @Override
//            public void onResponse(Response response) throws IOException {
//                handSuccess(response, callBack);
//            }
//        });
        if (callBack!=null){
            callBack.onStart();
        }

    }

    private void handSuccess(final Call call,final Response response, final FzCallBack callBack){
        try {
            if (response.isSuccessful()){
                final  String result =  response.body().string();
                LogUtils.e(TAG,result);
                mDelivery.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (callBack != null)
                            callBack.onSuccess(call,result);
                    }
                });
            }else{
                handFailure(call,new IOException("Unexpected code " + response),callBack);
            }

        } catch (IOException e) {
            e.printStackTrace();
            handFailure(call,new IOException("Unexpected code " + response),callBack);
        }


    }
    private void handFailure(final Call call, final IOException e, final FzCallBack callBack){
        mDelivery.post(new Runnable()
        {
            @Override
            public void run()
            {
                if (callBack != null)
                    if (call.isCanceled()){
                        callBack.onCancle();
                    }else{
                        callBack.onFailure(call,e);
                    }
            }
        });
    }


    /**
     * 取消所有请求
     *
     */
    public void cancleAll(){
//        mClient.getDispatcher().cancel(tag);
        if (mClient!=null){
            mClient.dispatcher().cancelAll();
        }
    }

    protected void appendHeaders(Request.Builder builder,Map<String, String> headers)
    {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet())
        {
            headerBuilder.add(key, headers.get(key));
        }


        headerBuilder.add("version",FzConfig.VERSION);
        headerBuilder.add("model", android.os.Build.MODEL + "-"+android.os.Build.VERSION.SDK+"-"+android.os.Build.VERSION.RELEASE);

        builder.headers(headerBuilder.build());
    }



}
