package com.feeljoy.library.http;

/**
 * Created by Cate on 2016/3/25.
 */
public abstract class FzConfig {
    public static int NETWORK_NULL = 0;
    public  static String FAILURE = "1";
    public  static int SUCCESS = 200;
    public  static String STATUS = "flag", DATA = "data", MSG = "msg";
    public static String VERSION = "";


    /**
     * log配置类
     * @author cate 2015-2-12
     */
    public static class Log
    {
        /**
         * 是否开启debug
         */
        public static boolean isDubug = true;
        /**
         * 是否输出到文件
         */
        public static boolean isPoint = false;

        public static String LogPath = "";
    }

    /**
     * 网络请求配置类
     * @author Cate
     *
     */
    public static class Request
    {
        /**
         *  http请求最大并发连接数
         */
        public static int maxConnections = 10;
        /**
         *  超时时间，默认15秒
         */
        public static int socketTimeout = 15 * 1000;
        public static int maxRetries = 5;// 错误尝试次数，错误异常表请在RetryHandler添加
        public static int httpThreadCount = 3;// http线程池数量
        public static String charset = "utf-8";  //默认编码
    }


}
