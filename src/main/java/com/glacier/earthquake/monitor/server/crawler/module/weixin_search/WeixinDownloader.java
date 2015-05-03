package com.glacier.earthquake.monitor.server.crawler.module.weixin_search;

import com.glacier.earthquake.monitor.server.crawler.core.Downloader;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;

/**
 * Created by glacier on 15-5-3.
 */
public class WeixinDownloader extends Downloader {

    public void setHttpGet() {
        httpGet.setHeader("Cache-Control", "max-agr=0");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.120 Safari/537.36");
        httpGet.setHeader("Cookie", "ssuid=2809541882; SUID=42568C711524900A0000000054866DB1; SUV=008E79B2718C564254A228CE96D50889; IPLOC=CN6101; ABTEST=7|1430653862|v1; PHPSESSID=vnn57brnhul8a2vtcfdejdu663; SUIR=1430653863; SNUID=FB31C99CE8EDFCC4FFC1E241E94EF22D; LSTMV=989%2C482; LCLKINT=698006");

        httpGet.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
        httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
        httpGet.getParams().setParameter(CoreProtocolPNames.WAIT_FOR_CONTINUE, 60000);
        httpGet.getParams().setBooleanParameter("http.tcp.nodelay", true);
        httpGet.getParams().setParameter("http.connection.stalecheck", false);
        httpGet.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
    }

}
