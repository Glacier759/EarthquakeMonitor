package com.glacier.earthquake.monitor.server.crawler.module.weibo_sina.login;

import com.glacier.earthquake.monitor.server.pojo.Account;
import com.glacier.earthquake.monitor.server.util.MyHttpConnectionManager;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glacier on 15-5-1.
 */
public class Login {

    private String loginURL = "http://login.weibo.cn/login/";                   //登陆微博入口地址
    private String homePage;

    private static Logger logger = Logger.getLogger(Login.class.getName());

    /**
     * 执行登录操作
     * @param account 登陆所需要的账号密码，封装为Accounts类
     * @return 返回登陆后维护有cookie等信息的HttpClient
     * */
    public DefaultHttpClient login(Account account) {
        return login(account.getUsername(), account.getPassword());
    }

    /**
     * 执行登陆操作
     * @param username 登陆需要的用户名
     * @param password 用户名对应的密码
     * @return 返回登录后维护有cookie等信息的HttpClient
     * */
    public DefaultHttpClient login(String username, String password) {
        try {
            logger.info("[Login] '" + username + "' 正在登陆...");
            Document document = Jsoup.connect(loginURL)
                    .userAgent("Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .get();

            //从登陆页面中提取一次登录请求所需要发送的数据字段
            String loginBackURL = document.select("input[name=backURL]").attr("value");
            String loginBackTitle = document.select("input[name=backTitle]").attr("value");
            String loginVK = document.select("input[name=vk]").attr("value");
            String loginSubmit = document.select("input[name=submit]").attr("value");

            //构造传输参数集合
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("mobile", username));
            nvps.add(new BasicNameValuePair("password_" + loginVK.substring(0, 4), password));
            nvps.add(new BasicNameValuePair("remember", "on"));
            nvps.add(new BasicNameValuePair("backURL", loginBackURL));
            nvps.add(new BasicNameValuePair("backTitle", loginBackTitle));
            nvps.add(new BasicNameValuePair("tryCount", ""));
            nvps.add(new BasicNameValuePair("vk", loginVK));
            nvps.add(new BasicNameValuePair("submit", loginSubmit));

            //执行Post请求以进行登陆
            HttpPost httpPost = new HttpPost(loginURL);
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            DefaultHttpClient httpClient =MyHttpConnectionManager.getNewHttpClient();
            HttpResponse response = httpClient.execute(httpPost);

            //URL重定向
            String locationURL = null;
            while( response.getFirstHeader("Location") != null) {
                locationURL = response.getFirstHeader("Location").getValue();
                logger.debug("[login] " + response.getStatusLine() + "\t" + locationURL);
                HttpGet httpGet = new HttpGet(locationURL);
                MyHttpConnectionManager.setHandleRedirect(httpClient, false);
                response = httpClient.execute(httpGet);
                MyHttpConnectionManager.setHandleRedirect(httpClient, true);
            }

            if ( locationURL == null )
                return null;
            //取得重定向后的首页地址，登陆成功
            HttpGet httpGet = new HttpGet(locationURL);
            MyHttpConnectionManager.setHandleRedirect(httpClient, false);
            response = httpClient.execute(httpGet);
            MyHttpConnectionManager.setHandleRedirect(httpClient, true);
            homePage = EntityUtils.toString(response.getEntity());
            logger.info("[login] '" + username + "' 登陆成功!");

            //设置HttpClient相关参数，返回并维护该HttpClient
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
            httpClient.getParams().setParameter(CoreProtocolPNames.WAIT_FOR_CONTINUE, 60000);
            httpClient.getParams().setBooleanParameter("http.tcp.nodelay", true);
            httpClient.getParams().setParameter("http.connection.stalecheck", false);
            httpClient.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);

            return httpClient;
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    /**
     * @return 返回登陆后首页的源码
     * */
    public String getHomePage(){    return homePage;    }

}
