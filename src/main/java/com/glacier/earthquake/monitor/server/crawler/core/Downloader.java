package com.glacier.earthquake.monitor.server.crawler.core;

import com.glacier.earthquake.monitor.server.util.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by glacier on 15-5-1.
 */
public class Downloader {

    private static Logger logger = Logger.getLogger(Downloader.class.getName());
    public static DefaultHttpClient httpClient;
    public static final int HTTP_GET = 0;
    public static final int HTTP_POST = 1;

    public static String last_url = null;

    /**
     * 设置Downloader模块所需的HttpClient
     * @param client 经过登陆操作返回的HttpClient
     * */
    public static void setClient(DefaultHttpClient client) {
        httpClient = client;

    }

    /**
     * 对相应地址使用相应方法返回抓取得到的dom树
     * @param url 需要获取的地址
     * @param method 访问该地址需要使用的HTTP请求方法
     * @return 返回获取得到的Document文档树
     * */
    public static Document document(String url, int method) {
        try {
            last_url = url;
            HttpResponse response = null;
            if ( method == HTTP_GET ) {
                HttpGet httpGet = new HttpGet(url);

                httpGet.setHeader("Connection", "keep-alive");
                httpGet.setHeader("Cache-Control", "max-age=0");
                httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36");

                httpGet.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
                httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
                httpGet.getParams().setParameter(CoreProtocolPNames.WAIT_FOR_CONTINUE, 60000);
                httpGet.getParams().setBooleanParameter("http.tcp.nodelay", true);
                httpGet.getParams().setParameter("http.connection.stalecheck", false);
                httpGet.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
                response = httpClient.execute(httpGet);
            }
            else if ( method == HTTP_POST ) {
                HttpPost httpPost = new HttpPost(url);

                httpPost.setHeader("Connection", "keep-alive");
                httpPost.setHeader("Cache-Control", "max-age=0");
                httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                httpPost.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36");

                httpPost.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
                httpPost.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
                httpPost.getParams().setParameter(CoreProtocolPNames.WAIT_FOR_CONTINUE, 60000);
                httpPost.getParams().setBooleanParameter("http.tcp.nodelay", true);
                httpPost.getParams().setParameter("http.connection.stalecheck", false);
                httpPost.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
                response = httpClient.execute(httpPost);
            }
            HttpEntity entity = response.getEntity();

            //默认采用UTF-8编码
            Document document = Jsoup.parse(getContent(entity, "UTF-8"));
            String baseStr = document.select("meta[content]").toString();
            String encode = null;
            try {
                if ( baseStr.contains("charset=") ) {
                    encode = baseStr.substring(baseStr.indexOf("charset=") + 8);
                    encode = encode.substring(0, encode.indexOf('"'));
                }
            }catch (Exception e) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                e.printStackTrace(new PrintStream(baos));
                logger.error(baos.toString());
            }

            //如果匹配到网页meta标签中声明本网页并非utf-8编码，则采用当前网页编码形式重新编码
            if ( encode != null && !StringUtils.startsWithIgnoreCase(encode, "utf-8")
                                && !StringUtils.startsWithIgnoreCase(encode, "utf8" )) {
                try {
                    document = Jsoup.parse(getContent(response(url, HTTP_GET).getEntity(), encode));
                }catch (Exception e) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    e.printStackTrace(new PrintStream(baos));
                    logger.error(baos.toString());
                }
            }
            document.setBaseUri(url);   //设置document的来源地址

            document = document_method(document);

            return document;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    public static HttpResponse response(String url, int method) {
        try {
            HttpResponse response = null;
            if (method == HTTP_GET) {
                HttpGet httpGet = new HttpGet(url);

                httpGet.setHeader("Connection", "keep-alive");
                httpGet.setHeader("Cache-Control", "max-age=0");
                httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36");

                httpGet.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
                httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
                httpGet.getParams().setParameter(CoreProtocolPNames.WAIT_FOR_CONTINUE, 60000);
                httpGet.getParams().setBooleanParameter("http.tcp.nodelay", true);
                httpGet.getParams().setParameter("http.connection.stalecheck", false);
                httpGet.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
                response = httpClient.execute(httpGet);
            } else if (method == HTTP_POST) {
                HttpPost httpPost = new HttpPost(url);

                httpPost.setHeader("Connection", "keep-alive");
                httpPost.setHeader("Cache-Control", "max-age=0");
                httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                httpPost.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36");

                httpPost.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
                httpPost.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
                httpPost.getParams().setParameter(CoreProtocolPNames.WAIT_FOR_CONTINUE, 60000);
                httpPost.getParams().setBooleanParameter("http.tcp.nodelay", true);
                httpPost.getParams().setParameter("http.connection.stalecheck", false);
                httpPost.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
                response = httpClient.execute(httpPost);
            }
            return response;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    /**
     * 为保证文档树不产生乱码情况
     * @param entity HTTP请求后得到的HttpEntity
     * @param encode 需要指定的最终文字编码
     * @return 返回按照指定编码转码后的页面源码, 已进行全角转半角处理
     * */
    private static String getContent(HttpEntity entity, String encode) {
        BufferedReader reader = null;
        StringBuffer buffer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(entity.getContent(), encode));
            buffer = new StringBuffer();
            String temp = null;
            while( (temp = reader.readLine()) != null ) {
                buffer.append(temp);
            }
            reader.close();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return StringUtils.full2half(buffer.toString());
    }

    /**
     * 为保证文档树不产生乱码情况
     * @param source 需要处理的HTML网页源码
     * @param encode 需要指定的最终文字编码
     * @return 返回按照指定编码转码后的页面源码, 已进行全角转半角处理
     * */
    private static String getContent(InputStream source, String encode) {
        BufferedReader reader = null;
        StringBuffer buffer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(source, encode));
            buffer = new StringBuffer();
            String temp = null;
            while( (temp = reader.readLine()) != null ) {
                buffer.append(temp);
            }
            reader.close();
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return StringUtils.full2half(buffer.toString());
    }

    public static Document document_method(Document document) {
        return document;
    }

}
