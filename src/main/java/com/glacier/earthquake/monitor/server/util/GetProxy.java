package com.glacier.earthquake.monitor.server.util;

import com.glacier.earthquake.monitor.server.crawler.core.Downloader;
import com.glacier.earthquake.monitor.server.pojo.SpiderProxy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.xml.crypto.Data;

/**
 * Created by glacier on 15-6-1.
 */
public class GetProxy {

    private static Logger logger = Logger.getLogger(GetProxy.class.getName());
    private static final String seedURL = "http://www.youdaili.net/Daili/http/";

    public void getProxy() {
        DefaultHttpClient defaultHttpClient = MyHttpConnectionManager.getNewHttpClient();
        Downloader downloader = new Downloader();
        downloader.setClient(defaultHttpClient);
        Document document = downloader.document(seedURL, Downloader.HTTP_GET);
        Element element = document.select("ul[class=newslist_line]").select("li").select("a[href]").first();
        document = downloader.document(element.attr("abs:href"), Downloader.HTTP_GET);
        element = document.select("div[class=newsdetail_cont]").select("p").first();

        String[] proxy_array = element.html().split("<br />");
        for ( String proxy : proxy_array ) {
            logger.info("[代理] - 自动获取到代理 " + proxy);
            String remark = proxy.substring(proxy.indexOf("@"));
            proxy = proxy.replace(remark, "");
            String proxy_ip = proxy.split(":")[0];
            String proxy_port = proxy.split(":")[1];

            SpiderProxy spider_proxy = new SpiderProxy();
            spider_proxy.setProxy_ip(proxy_ip);
            spider_proxy.setProxy_port(Integer.parseInt(proxy_port));
            spider_proxy.setRemark(remark);
            Object2Data.insertProxy(spider_proxy);
        }
    }

    public static void main(String[] args) {
        GetProxy getProxy = new GetProxy();
        SpiderProxy proxy = Data2Object.getProxy();
        System.out.println(proxy);
        Object2Data.updateProxy(proxy);
    }

}
