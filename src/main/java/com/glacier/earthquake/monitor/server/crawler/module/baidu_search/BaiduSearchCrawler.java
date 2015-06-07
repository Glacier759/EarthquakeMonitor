package com.glacier.earthquake.monitor.server.crawler.module.baidu_search;

import com.glacier.earthquake.monitor.server.configure.crawler.SpiderInfoManager;
import com.glacier.earthquake.monitor.server.crawler.Crawler;
import com.glacier.earthquake.monitor.server.crawler.core.Downloader;
import com.glacier.earthquake.monitor.server.crawler.core.Scheduler;
import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.SpiderFilter;
import com.glacier.earthquake.monitor.server.pojo.SpiderInfo;
import com.glacier.earthquake.monitor.server.pojo.SystemConfig;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.JudgeFilter;
import com.glacier.earthquake.monitor.server.util.MyHttpConnectionManager;
import com.glacier.earthquake.monitor.server.util.PublicSentimentUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by glacier on 15-5-2.
 */
public class BaiduSearchCrawler extends Crawler {

    public static Logger logger = Logger.getLogger(BaiduSearchCrawler.class.getName());
    public static BaiduSearchDownloader downloader = new BaiduSearchDownloader();

    @Override
    public void start() {

        //获取一个可以抓取HTTPS协议的浏览器
        DefaultHttpClient defaultHttpClient = MyHttpConnectionManager.getHttpsClient();
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
            logger.info("[百度搜索] - 搜索关键字为 " + searchKey);
            searchKey = searchKey.replace(" ", "%20");
            searchKey = "https://www.baidu.com/s?wd=" + searchKey + "&pn=0";
            return downloader.document(searchKey, Downloader.HTTP_GET);
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

            int count = 0;
            do {
                try {
                    count++;
                    logger.info("[解析] - 正在获取第 " + count + "页");
                    //由于存在换页情况，url发生变化，在此处增加白名单过滤
                    if (JudgeFilter.isMeetWhiteList(document.baseUri())) {
                        continue;
                    }

                    //遍历所有的搜索结果
                    Elements elements = document.select("div[tpl]");
                    logger.info("[解析] - 本页获得搜索结果 " + elements.size() + " 条(粗略结果)");
                    for (Element element : elements) {
                        try {
                            //带有id属性的搜索结果为正确的
                            if (element.attr("id").length() > 0) {
                                //提取标签中蕴含的搜索结果的地址
                                String resultLink = element.select("h3").select("a[href]").attr("abs:href");
                                String locationURL = downloader.trueLink(resultLink);
                                if (locationURL != null) {
                                    resultLink = locationURL;
                                }
                                logger.info("[解析] - 得到一条搜索结果链接 " + resultLink);

                                //由于搜索引擎的数据来源很多，所以再次对搜索结果进行白名单过滤
                                if (JudgeFilter.isMeetWhiteList(resultLink)) {
                                    continue;
                                }

                                if ( Scheduler.getRecordBySignVale(resultLink, ruleID, SpiderInfo.FILTER_DISASTER) != null ) {
                                    logger.info("[去重] - " + resultLink + " 已经抓取过");
                                    continue;
                                }

                                //获得搜索结果链接对应的文档树
                                Document document_result = downloader.document(resultLink, Downloader.HTTP_GET);
                                Scheduler.insertRecord(Scheduler.SIGN_URL, resultLink, Scheduler.SERVICE_BAIDU_SEARCH, ruleID, SpiderInfo.FILTER_DISASTER);

                                logger.info("[判断] - 正在对搜索结果进行正确新判断");
                                //进行过滤条件判断
                                boolean ans = true;
                                String base = "(.*)";
                                for (String keyword : keywords) {
                                    base += keyword + "(.*)";
                                    ans = ans && document_result.text().contains(keyword);
                                }

                                Pattern pattern = Pattern.compile(base);
                                Matcher matcher = pattern.matcher(document_result.toString());
                                if ( matcher.find() ) {
                                    logger.info("[正则匹配] - 正则匹配成功 " + document_result.baseUri());
                                } else {
                                    logger.info("[正则匹配] - 正则匹配失败 " + document_result.baseUri());
                                    ans = false;
                                }

                                //如果ans为true则表示当前网页符合过滤条件
                                if (ans) {
                                    //FileUtils.writeStringToFile(new File("Data", System.currentTimeMillis() + ".xml"), document_result.toString());
                                    SpiderInfo spiderInfo = new SpiderInfo();
                                    spiderInfo.setType(type);
                                    spiderInfo.setUrl(document_result.baseUri());
                                    spiderInfo.setTitle(document_result.title());
                                    //改为iframe后不需要保存原文信息
                                    //spiderInfo.setSource(document_result.select("p").text());
                                    spiderInfo.setOrigin(SystemConfig.CONFIG_TYPE_BAIDU_SEARCH);
                                    spiderInfo.setRule_id(ruleID);
                                    //设置好属性后插入数据库
                                    SpiderInfoManager.insertSpiderInfo(spiderInfo);
                                    logger.info("[匹配成功] - 获得一条新数据");

                                    /**
                                     * 匹配成功一条灾情获取信息之后, 首先进行当前规则id对应的舆情监控抓取记录的判重工作
                                     * 如果没有进行过对应的舆情监控的过滤, 则对当前页面进行相应规则的二次过滤（舆情监控定制的规则）
                                     * */
                                    SpiderFilter filter = Scheduler.getRecordBySignVale(resultLink, ruleID, SpiderInfo.FILTER_PUBSENTIMENT);
                                    if ( filter == null ) {
                                        PublicSentimentUtils.publicSentimentJudge(document_result, ruleID);

                                    }
                                }
                            }
                        } catch (Exception e) {
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            e.printStackTrace(new PrintStream(baos));
                            logger.error(baos.toString());
                        }
                    }
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
            if ( document.select("a[class=n]").text().contains("下一页") ) {
                String nextPage = document.baseUri();
                Integer page = Integer.parseInt(nextPage.substring(nextPage.lastIndexOf("&pn=") + 4)) + 10;
                nextPage = nextPage.substring( 0, nextPage.lastIndexOf("&pn=")+4 ) + page;
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
        return "BaiduSearchCrawler";
    }
}