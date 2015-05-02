package com.glacier.earthquake.monitor.server.crawler.module.baidu_search;

import com.glacier.earthquake.monitor.server.crawler.Crawler;
import com.glacier.earthquake.monitor.server.crawler.core.Downloader;
import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.SpiderInfo;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.JudgeFilter;
import com.glacier.earthquake.monitor.server.util.MyHttpConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;



/**
 * Created by glacier on 15-5-2.
 */
public class BaiduSearchCrawler extends Crawler {

    public static Logger logger = Logger.getLogger(BaiduSearchCrawler.class.getName());

    @Override
    public void start() {

        //获取一个可以抓取HTTPS协议的浏览器
        DefaultHttpClient defaultHttpClient = MyHttpConnectionManager.getHttpsClient();
        //设置BaiduSearchDownloader
        BaiduSearchDownloader.setClient(defaultHttpClient);

        //获取得到所有的过滤规则
        List<FilterDisaster> disasters = Data2Object.filterRulesDisaster();
        //遍历过滤规则
        for (FilterDisaster disaster : disasters) {
            //取得过滤规则, 处理为搜索引擎中需要输入的key
            String filterRule = disaster.getFilterRule();
            String searchKey = filterRule.replace('*', ' ');
            //使用该key在搜索引擎中搜索
            Document document = search(searchKey);
            //判断抓取的地址是否在白名单中, 在的话就不管了continue
            if (JudgeFilter.isMeetWhiteList(document.baseUri())) {
                continue;
            }
            //以此取出搜索引擎搜索结果提取正文确认是否满足过滤规则
            //将满足条件的信息存储在数据库中
            logger.info("[Filter] - 当前过滤规则为 " + disaster.getFilterRule());
            parse(document, filterRule.split("\\*"), disaster.getId(), SpiderInfo.FILTER_DISASTER);
        }
    }

    private Document search(String searchKey) {
        try {
            logger.info("[百度搜索] - 搜索关键字为 " + searchKey);
            searchKey = searchKey.replace(" ", "%20");
            searchKey = "https://www.baidu.com/s?wd=" + searchKey + "&pn=0";
            return BaiduSearchDownloader.document(searchKey, Downloader.HTTP_GET);
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    private void parse(Document document, String[] keywords, int ruleID, int type) {
        try {
            logger.info("[解析] - 正在获取搜索结果...");
            Elements elements = document.select("div[tpl]").select("div[id]");
            System.out.println(elements.size());
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }
}
