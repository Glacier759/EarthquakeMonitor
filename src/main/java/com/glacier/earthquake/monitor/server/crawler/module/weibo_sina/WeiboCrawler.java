package com.glacier.earthquake.monitor.server.crawler.module.weibo_sina;

import com.glacier.earthquake.monitor.server.configure.crawler.SpiderInfoManager;
import com.glacier.earthquake.monitor.server.crawler.Crawler;
import com.glacier.earthquake.monitor.server.crawler.core.Downloader;
import com.glacier.earthquake.monitor.server.crawler.core.Scheduler;
import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.SpiderInfo;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.JudgeFilter;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by glacier on 15-5-2.
 */
public class WeiboCrawler extends Crawler {

    public static Logger logger = Logger.getLogger(WeiboCrawler.class.getName());
    public static Login login = new Login();
    public static WeiboDownloader downloader = new WeiboDownloader();

    @Override
    public void start() {

        //登陆模块
        //使用微博账号进行登陆. 并将登陆后维护的HttpClient设置给WeiboDownloader
        downloader.setClient(login.login(Data2Object.account("weibo")));

        //获取得到所有的过滤规则
        List<FilterDisaster> disasters = Data2Object.filterRulesDisaster();
        //遍历过滤规则
        for ( FilterDisaster disaster : disasters ) {
            //取得过滤规则，处理为搜索key
            String filterRule = disaster.getFilterRule();
            String searchKey = filterRule.replace('*', ' ');
            //使用该key在微博搜索中使用
            Document document = search(searchKey);
            //判断抓取的地址是否在白名单中，在的就不管了continue
            if (JudgeFilter.isMeetWhiteList(document.baseUri())) {
                continue;
            }
            //以此取出微博正文内容再次确认是否满足过滤规则
            //满足条件的将直接存储在数据库中
            logger.info("[Filter] - 当前过滤规则为 " + filterRule);
            parse(document, filterRule.split("\\*"), disaster.getId(), SpiderInfo.FILTER_DISASTER);
        }
    }

    /**
     * 使用微博搜索功能检索关键字，返回的文档树检索首页，包含有所有包含该关键字的相关微博
     * @param key 需要检索的关键字
     * @return 经过微博搜索该关键字得到的document文档树
     * */
    private Document search(String key) {
        try {
            logger.info("[微博搜索] - 搜索关键字为 " + key);
            key = key.replace(" ", "%20");
            String search = "http://weibo.cn/search/mblog?hideSearchFrame=&keyword=" + key + "&page=1";
            return downloader.document(search, Downloader.HTTP_GET);
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    private void parse(Document document, String[] keywords, int ruleID, int type) {
        try {
            logger.info("[解析] 正在获取搜索结果微博...");
            Element maxPageEle = document.select("input[type=hidden]").first();
            String maxPage;
            if ( maxPageEle != null )
                maxPage = maxPageEle.attr("value");
            else
                maxPage = "1";

            int count = 1;
            do {
                //由于在此处会存在换页情况，url会发生变化，在此处增加白名单判断
                if ( JudgeFilter.isMeetWhiteList(document.baseUri()) ) {
                    continue;
                }
                try {
                    logger.info("[解析] 正在获取第 " + count + " 页 / " + maxPage + " 页");
                    Elements weiboDivs = document.select("div[class=c]").select("div[id]");
                    logger.info("[解析] 当前页解析得到 " + weiboDivs.size() + " 条微博");

                    int times = 0;
                    //处理访问频繁被封禁问题, 一个页面连续三次出现这种问题放弃
                    while ( weiboDivs.size() == 0 && times < 3 ) {
                        logger.info("[解析] 当前页面微博数为0, 更换账号重访问中...");
                        //调用reLogin方法重登陆并获得上次访问地址的Document
                        document = downloader.reLogin();
                        times ++;
                        try {
                            weiboDivs = document.select("div[class=c]").select("div[id]");
                        }catch (Exception e) {
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            e.printStackTrace(new PrintStream(baos));
                            logger.error(baos.toString());
                        }
                    }
                    if ( times == 3 ) {
                        continue;
                    }

                    //遍历到每一条微博
                    for (Element weiboDiv : weiboDivs) {
                        //获得微博所在的Element
                        Element weiboText = weiboDiv.select("span[class=ctt]").first();
                        if (weiboText == null)
                            continue;

                        Scheduler.insertRecord(Scheduler.SIGN_ID, weiboDiv.attr("id"), Scheduler.SERVICE_WEIBO_SEARCH);

                        //进行过滤条件判断
                        boolean ans = true;
                        for ( String keyword : keywords ) {
                            ans = ans && weiboText.text().contains(keyword);
                        }
                        //如果ans为true则表示当前微博符合过滤规则
                        if ( ans ) {
                            SpiderInfo spiderInfo = new SpiderInfo();
                            spiderInfo.setUrl(document.baseUri());
                            spiderInfo.setRule_id(ruleID);
                            spiderInfo.setSource(weiboDiv.toString());
                            spiderInfo.setTitle(weiboText.text());
                            spiderInfo.setType(type);
                            //设置好属性后插入数据库
                            SpiderInfoManager.insertSpiderInfo(spiderInfo);
                            logger.info("[匹配成功] - 获得一条新数据 ID: " + weiboDiv.attr("id"));
                        }
                    }
                    count++;   //当前页标
                }catch (Exception e) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    e.printStackTrace(new PrintStream(baos));
                    logger.error(baos.toString());
                }
                //多页情况进行翻页
            }while((document = next(document)) != null);

        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
    }

    /**
     * 对于搜索结果不能在一页进行展示的情况，利用该方法得到下一页的文档树
     * @param document 存在该情况的当前document文档树
     * @return 执行翻页操作后的document文档树
     * */
    private Document next( Document document ) {
        try {
            Elements pageListTags = document.select("div[id=pagelist]").select("a");
            Element nextEle = pageListTags.first();
            if ( nextEle == null ) {
                return null;
            }
            if ( nextEle.text().equals("下页") ) {
                return downloader.document(nextEle.attr("abs:href"), Downloader.HTTP_GET);
            }
        }catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            logger.error(baos.toString());
        }
        return null;
    }

    public String toString() {
        return "WeiboCrawler";
    }

}
