package com.feeljoy.library.http;

import com.feeljoy.library.utils.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Cate on 2016/5/26.
 */
public class LoggingInterceptor implements Interceptor {
    private final String TAG = "Http-->";
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        LogUtils.e(TAG,String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));
//        logger.info(String.format("Sending request %s on %s%n%s",
//                request.url(), chain.connection(), request.headers()));
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        LogUtils.e(TAG,String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
//        logger.info(String.format("Received response for %s in %.1fms%n%s",
//                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        return response;
    }
}
