package com.glacier.earthquake.monitor.server.crawler.module.weibo_sina;

import com.glacier.earthquake.monitor.server.crawler.core.Downloader;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by glacier on 15-5-2.
 */
public class WeiboDownloader extends Downloader {

    public static Logger logger = Logger.getLogger(WeiboDownloader.class.getName());

    public static Document document_method(Document document) {
        if ( document.title().equals("微博广场") ) {
            logger.warn("[重登陆] - 访问频繁导致出现重定向，正在重新登录...");
            document = reLogin();
        }
        return document;
    }

    public static Document reLogin() {
        try {
            //更换账号登陆并设置WeiboDownloader浏览器
            setClient(WeiboCrawler.login.login(Data2Object.account("weibo")));
            return document(last_url, HTTP_GET);
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

}
