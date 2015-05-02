package com.glacier.earthquake.monitor.server.util;

import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

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

    public static DefaultHttpClient getHttpsClient() {

        DefaultHttpClient defaultHttpClient = getNewHttpClient();

        //创建TrustManager
        X509TrustManager xtm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        //这个好像是HOST验证
        X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
            public void verify(String arg0, SSLSocket arg1) throws IOException {}
            public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
            public void verify(String arg0, X509Certificate arg1) throws SSLException {}
        };
        try {

            //TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
            SSLContext ctx = SSLContext.getInstance("TLS");
            //使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
            ctx.init(null, new TrustManager[]{xtm}, null);
            //创建SSLSocketFactory
            org.apache.http.conn.ssl.SSLSocketFactory socketFactory = new org.apache.http.conn.ssl.SSLSocketFactory(ctx);
            socketFactory.setHostnameVerifier(hostnameVerifier);
            //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
            defaultHttpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));
            return defaultHttpClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}