package com.glacier.earthquake.monitor.server.crawler.module.tieba_search;

import com.glacier.earthquake.monitor.server.crawler.core.Downloader;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;

/**
 * Created by glacier on 15-5-3.
 */
public class TiebaDownloader extends Downloader {

    public void setHttpGet() {
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("Cache-Control", "max-age=0");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Cookie", "BAIDUPSID=F045B66BFCB5BB92759C9A2D3A870DB8; TIEBA_USERTYPE=91d9d8a2c4ded86bb627ff09; bdshare_firstime=1420731058423; rpln_guide=1; BAIDUID=08E9AE184C0AA17353DA17B51E094BE5:FG=1; MCITY=-%3A; BDUSS=F5QzRJYTRtQnIxSjBaT2Vlb2ZwSzNCU3Fnb2R6dmxEREdSdzdHRDNnckZYMWhWQVFBQUFBJCQAAAAAAAAAAAEAAACUxIIXVGltZaHMZ29lc6HMYnkAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMXSMFXF0jBVV2; TIEBAUID=52dc7439f3e72559bb48a912; BIDUPSID=08E9AE184C0AA17353DA17B51E094BE5; cflag=65151%3A3; locale=zh; BDRCVFR[w2jhEs_Zudc]=mbxnW11j9Dfmh7GuZR8mvqV; wise_device=0; BDRCVFR[feWj1Vr5u3D]=I67x6TjHwwYf0; H_PS_PSSID=13470_13139_13636_1438_13074_12824_10812_12867_13323_12692_13691_10562_12723_13210_13761_13257_13780_11919_13610_13085_8498");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36");

        httpGet.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
        httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
        httpGet.getParams().setParameter(CoreProtocolPNames.WAIT_FOR_CONTINUE, 60000);
        httpGet.getParams().setBooleanParameter("http.tcp.nodelay", true);
        httpGet.getParams().setParameter("http.connection.stalecheck", false);
        httpGet.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
    }

}
