package com.glacier.earthquake.monitor.server.crawler.module.baidu_search;

import com.glacier.earthquake.monitor.server.crawler.core.Downloader;
import com.glacier.earthquake.monitor.server.util.MyHttpConnectionManager;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;


/**
 * Created by glacier on 15-5-2.
 */
public class BaiduSearchDownloader extends Downloader {

    public static Logger logger = Logger.getLogger(BaiduSearchDownloader.class.getName());

    public String trueLink(String url) {
        MyHttpConnectionManager.setHandleRedirect(httpClient, false);
        HttpResponse response = response(url, HTTP_GET);
        String locationURL = null;
        if ( response.getFirstHeader("Location") != null ) {
            locationURL = response.getFirstHeader("Location").getValue();
        }
        MyHttpConnectionManager.setHandleRedirect(httpClient, true);
        return locationURL;
    }

    public void setHttpGet() {
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.120 Safari/537.36");
        httpGet.setHeader("Cookie", "BAIDUPSID=F045B66BFCB5BB92759C9A2D3A870DB8; BAIDUID=08E9AE184C0AA17353DA17B51E094BE5:FG=1; MCITY=-%3A; BDUSS=F5QzRJYTRtQnIxSjBaT2Vlb2ZwSzNCU3Fnb2R6dmxEREdSdzdHRDNnckZYMWhWQVFBQUFBJCQAAAAAAAAAAAEAAACUxIIXVGltZaHMZ29lc6HMYnkAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMXSMFXF0jBVV2; BIDUPSID=08E9AE184C0AA17353DA17B51E094BE5; cflag=65151%3A3; locale=zh; BDRCVFR[w2jhEs_Zudc]=mbxnW11j9Dfmh7GuZR8mvqV; BDRCVFR[feWj1Vr5u3D]=I67x6TjHwwYf0; ispeed_lsm=10; BDRCVFR[M7pOaqtZgJR]=I67x6TjHwwYf0; BD_CK_SAM=1; H_PS_645EC=5dc4QN5pcICKpOAl%2BCDdLLlofT%2BD5WOUiyMiMmt5%2FvBj6oTxaKobO73uKopGhyQ; BD_HOME=1; H_PS_PSSID=13470_13139_13636_1438_13074_12824_10812_12867_13323_12692_13691_10562_12723_13210_13761_13257_13780_11919_13610_13085_8498; BD_UPN=12314353");

        httpGet.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
        httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
        httpGet.getParams().setParameter(CoreProtocolPNames.WAIT_FOR_CONTINUE, 60000);
        httpGet.getParams().setBooleanParameter("http.tcp.nodelay", true);
        httpGet.getParams().setParameter("http.connection.stalecheck", false);
        httpGet.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
    }

}
