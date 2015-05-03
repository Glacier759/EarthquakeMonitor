package com.glacier.earthquake.monitor.server.crawler.module.baidu_search;

import com.glacier.earthquake.monitor.server.crawler.core.Downloader;
import com.glacier.earthquake.monitor.server.util.MyHttpConnectionManager;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;


/**
 * Created by glacier on 15-5-2.
 */
public class BaiduSearchDownloader extends Downloader {

    public static Logger logger = Logger.getLogger(BaiduSearchDownloader.class.getName());

    public static String trueLink(String url) {
        MyHttpConnectionManager.setHandleRedirect(httpClient, false);
        HttpResponse response = response(url, HTTP_GET);
        String locationURL = null;
        if ( response.getFirstHeader("Location") != null ) {
            locationURL = response.getFirstHeader("Location").getValue();
        }
        MyHttpConnectionManager.setHandleRedirect(httpClient, true);
        return locationURL;
    }

}
