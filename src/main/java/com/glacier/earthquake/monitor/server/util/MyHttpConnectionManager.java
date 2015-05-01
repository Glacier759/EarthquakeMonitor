package com.glacier.earthquake.monitor.server.util;

import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * Created by glacier on 15-5-1.
 */
public class MyHttpConnectionManager {
    public static HttpParams httpParams;
    public static ClientConnectionManager connectionManager;

    //数据设置
    //最大链接数
    public static int max_connection = 80;
    //获取链接的最大等待时间
    public static int wait_connection_timeout = 15000;
    //连接超时时间
    public static int connection_timeout = 15000;
    //读取超时
    public static int read_timeout = 50000;

    //取得DefaultHttpClient
    public static DefaultHttpClient defaultHttpClient;
    static {
        httpParams = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(httpParams, max_connection);
        ConnManagerParams.setTimeout(httpParams, wait_connection_timeout);

        //每个路由的最大连接个数，标志对同一站点的并发请求
        ConnPerRouteBean connPerRoute = new ConnPerRouteBean(100);
        ConnManagerParams.setMaxConnectionsPerRoute(httpParams, connPerRoute);

        HttpConnectionParams.setConnectionTimeout(httpParams, connection_timeout);
        HttpConnectionParams.setSoTimeout(httpParams, read_timeout);
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        registry.register(new Scheme("https", org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory(), 443));

        connectionManager = new ThreadSafeClientConnManager(httpParams, registry);
        defaultHttpClient = new DefaultHttpClient(connectionManager, httpParams);
    }

    public static DefaultHttpClient getHttpClient() {
        return defaultHttpClient;
    }

    public static DefaultHttpClient getNewHttpClient() {
        return defaultHttpClient;
    }

    //设置是否重由httpclient自动管理跳转
    public static void setHandleRedirect(DefaultHttpClient defaultHttpClient, boolean isAuto) {
        if (isAuto) {
            defaultHttpClient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, true);
        }
        else {
            defaultHttpClient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false);
        }
    }
}