package com.feeljoy.library.http;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Cate on 2016/3/25.
 */
public abstract class FzParams {
    private static String ENCODING = "UTF-8";

    protected ConcurrentHashMap<String, String> urlParams;
    protected ConcurrentHashMap<String, File> fileParams;


    public FzParams()
    {
        init();
    }

    public FzParams(Map<String, String> source)
    {
        init();
        for (Map.Entry<String, String> entry : source.entrySet())
        {
            put(entry.getKey(), entry.getValue());
        }
    }

    public FzParams(String key, String value)
    {
        init();
        put(key, value);
    }

    public FzParams(Object... keysAndValues)
    {
        init();
        int len = keysAndValues.length;
        if (len % 2 != 0) throw new IllegalArgumentException("Supplied arguments must be even");
        for (int i = 0; i < len; i += 2)
        {
            String key = String.valueOf(keysAndValues[i]);
            String val = String.valueOf(keysAndValues[i + 1]);
            put(key, val);
        }
    }

    public void put(String key, String value)
    {
        if (key != null && value != null)
        {
            urlParams.put(key, value);
        }
    }

    public void put(String key, File file) throws FileNotFoundException
    {
        if (key != null && file != null)
        {
           fileParams.put(key,file);
        }
    }

    public  String getMimeType(String fileUrl) throws IOException
    {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(fileUrl);
        return type;
    }

    public void remove(String key)
    {

        urlParams.remove(key);
        fileParams.remove(key);
    }

    @Override
    public String toString()
    {

        doSomething();

        StringBuilder result = new StringBuilder();
        for(ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            if(result.length() > 0)
                result.append("&");
            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }
        for(ConcurrentHashMap.Entry<String, File> entry : fileParams.entrySet()) {
            if(result.length() > 0)
                result.append("&");
            result.append(entry.getKey());
            result.append("=");
            result.append("FILE");
        }

        return result.toString();
    }


    public String getParamString()
    {
        doSomething();
        StringBuilder result = new StringBuilder();
        for(ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            if(result.length() > 0)
                result.append("&");
            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }
        for(ConcurrentHashMap.Entry<String, File> entry : fileParams.entrySet()) {
            if(result.length() > 0)
                result.append("&");
            result.append(entry.getKey());
            result.append("=");
            result.append("FILE");
        }
        return result.toString();
    }


    public RequestBody getRequest(){
        doSomething();
        if (!fileParams.isEmpty()){
            MultipartBody.Builder builder = new  MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
//            MultipartBuilder builder = new MultipartBuilder()
//                    .type(MultipartBuilder.FORM);

//            builder

//            FormBody.Builder formBuilder = new FormBody.Builder();
//            for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet())
//            {
//                formBuilder.add(entry.getKey(), entry.getValue());
//            }
//            RequestBody formBody = formBuilder.build();
//            builder.addPart(formBody);
            for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet())
            {
//                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
//                        RequestBody.create(null, entry.getValue()));


//                builder.addPart( entry.getKey(), entry.getValue());
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
            for (ConcurrentHashMap.Entry<String, File> entry : fileParams.entrySet())
            {
                File file = entry.getValue();
                RequestBody fileBody = null;
                try {
                    fileBody = RequestBody.create(MediaType.parse( getMimeType(file.getAbsolutePath())), file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                //TODO 根据文件名设置contentType
//                builder.addPart(Headers.of("Content-Disposition",
//                                    "form-data; name=\"" + entry.getKey() + "\"; filename=\"" + file.getName() + "\""),
//                            fileBody);


                builder.addFormDataPart(entry.getKey(),file.getName(),fileBody);
            }
            RequestBody requestBody = builder.build();

            return requestBody;
        }else{
            FormBody.Builder builder = new FormBody.Builder();
            for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet())
            {
                builder.add(entry.getKey(), entry.getValue());
            }


            RequestBody requestBody = builder.build();
            return  requestBody;
        }
    }

    //获取额外的字段
    public  abstract void doSomething();


    private void init()
    {
        urlParams = new ConcurrentHashMap<String, String>();
        fileParams = new ConcurrentHashMap<String, File>();
    }

    public ConcurrentHashMap<String, File> getFileParams() {
        return fileParams;
    }

    public void setFileParams(ConcurrentHashMap<String, File> fileParams) {
        this.fileParams = fileParams;
    }
    public ConcurrentHashMap<String, String> getUrlParams() {
        return urlParams;
    }

    public void setUrlParams(ConcurrentHashMap<String, String> urlParams) {
        this.urlParams = urlParams;
    }

    public int getSize(){
        return  urlParams.size()+fileParams.size();
    }


    private static final String APPLICATION_JSON = "application/json";

    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

}
