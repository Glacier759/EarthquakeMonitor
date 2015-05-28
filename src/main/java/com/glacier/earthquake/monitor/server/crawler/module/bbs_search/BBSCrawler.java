package com.glacier.earthquake.monitor.server.crawler.module.bbs_search;

import com.glacier.earthquake.monitor.server.configure.crawler.SpiderInfoManager;
import com.glacier.earthquake.monitor.server.crawler.Crawler;
import com.glacier.earthquake.monitor.server.crawler.core.Downloader;
import com.glacier.earthquake.monitor.server.crawler.core.Scheduler;
import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.SpiderInfo;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.JudgeFilter;
import com.glacier.earthquake.monitor.server.util.MyHttpConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by glacier on 15-5-3.
 */
public class BBSCrawler extends Crawler {

    public static Logger logger = Logger.getLogger(BBSCrawler.class.getName());
    public static BBSDownloader downloader = new BBSDownloader();

    @Override
    public void start() {
        //获取一个可以抓取HTTPS协议的浏览器
        DefaultHttpClient defaultHttpClient = MyHttpConnectionManager.getHttpClient();
        //设置BaiduSearchDownloader的浏览器
        downloader.setClient(defaultHttpClient);

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
            logger.info("[论坛搜索] - 搜索关键字为 " + searchKey);
            searchKey = searchKey.replace(" ", "+");
            searchKey = "http://www.sogou.com/web?query=" + searchKey + "&interation=196648&ie=utf-8&page=1";
            logger.info("[论坛搜索] - 搜索地址为 " + searchKey);
            return downloader.document(searchKey, Downloader.HTTP_GET);
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    private void parse(Document document, String keywords[], int rule_id, int type) {
        try {
            logger.info("[解析] - 正在获取搜索结果...");
            do {
                try {
                    //由于存在换页情况，url发生变化，在此处增加白名单过滤
                    if (JudgeFilter.isMeetWhiteList(document.baseUri())) {
                        continue;
                    }
                    //遍历所有的搜索结果
                    Elements elements = document.select("div[class=results]").first().children();
                    logger.info("[解析] - 本页获得搜索结果 " + elements.size() + " 条(粗略结果)");
                    for (Element element : elements) {
                        try {
                            String resultLink = element.select("h3").select("a[href]").attr("abs:href");
                            logger.info("[解析] - 得到一条搜索结果链接 " + resultLink);

                            //再次依据白名单做以筛选
                            if (JudgeFilter.isMeetWhiteList(resultLink)) {
                                continue;
                            }
                            //获得搜索结果对应的文档树
                            Document document_post = downloader.document(resultLink, Downloader.HTTP_GET);
                            Scheduler.insertRecord(Scheduler.SIGN_URL, resultLink, Scheduler.SERVICE_BBS_SEARCH);

                            //进行过滤条件判断
                            boolean ans = true;
                            for (String keyword : keywords) {
                                ans = ans && document_post.text().contains(keyword);
                            }
                            //如果ans为true则表示当前网页符合过滤条件
                            if (ans) {
                                SpiderInfo spiderInfo = new SpiderInfo();
                                spiderInfo.setRule_id(rule_id);
                                spiderInfo.setType(type);
                                spiderInfo.setTitle(document_post.title());
                                spiderInfo.setUrl(document_post.baseUri());
                                spiderInfo.setSource(document_post.select("p").text());
                                //设置好属性后插入数据库
                                SpiderInfoManager.insertSpiderInfo(spiderInfo);
                                logger.info("[匹配成功] - 获得一条新数据");
                            }
                        } catch (Exception e) {
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            e.printStackTrace(new PrintStream(baos));
                            logger.error(baos.toString());
                        }
                    }
                    return;
                }catch (Exception e) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    e.printStackTrace(new PrintStream(baos));
                    logger.error(baos.toString());
                }
            }while ((document = next(document)) != null);
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    private Document next(Document document) {
        try {
            if ( document.select("a[id=sogou_next]").size() > 0 && document.select("a[id=sogou_next]").text().contains("下一页") ) {
                String nextPage = document.baseUri();
                Integer page = Integer.parseInt(nextPage.substring(nextPage.lastIndexOf("&page=") + 6)) + 1;
                nextPage = nextPage.substring( 0, nextPage.lastIndexOf("&page=")+6 ) + page;
                logger.info("[翻页] - 获取到下一页: " + nextPage);
                return downloader.document(nextPage, Downloader.HTTP_GET);
            }
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    public String toString() {
        return "BBSCrawler";
    }
}
