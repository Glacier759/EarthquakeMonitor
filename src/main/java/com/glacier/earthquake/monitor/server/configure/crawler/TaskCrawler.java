package com.glacier.earthquake.monitor.server.configure.crawler;

import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.crawler.Crawler;
import com.glacier.earthquake.monitor.server.crawler.module.baidu_search.BaiduSearchCrawler;
import com.glacier.earthquake.monitor.server.crawler.module.bbs_search.BBSCrawler;
import com.glacier.earthquake.monitor.server.crawler.module.bing_search.BingCrawler;
import com.glacier.earthquake.monitor.server.crawler.module.tieba_search.TiebaCrawler;
import com.glacier.earthquake.monitor.server.crawler.module.weibo_sina.WeiboCrawler;
import com.glacier.earthquake.monitor.server.crawler.module.weixin_search.WeixinCrawler;
import com.glacier.earthquake.monitor.server.pojo.SystemConfig;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by glacier on 15-5-28.
 */
public class TaskCrawler extends TimerTask {

    private static Logger logger = Logger.getLogger(TaskCrawler.class.getName());
    private static boolean isRunning = false;

    @Override
    public void run() {
        if ( !isRunning && UserMonitor.getConfigStatusByType(SystemConfig.CONFIG_TYPE_SYSTEM) == SystemConfig.SYSTEM_START ) {
            isRunning = true;
            logger.info("[定时任务] - 开始执行");

//            List<Crawler> crawlerList = new ArrayList<Crawler>();
//            crawlerList.add(new BaiduSearchCrawler());
//            crawlerList.add(new BBSCrawler());
//            crawlerList.add(new BingCrawler());
//            crawlerList.add(new TiebaCrawler());
//            crawlerList.add(new WeixinCrawler());
//            crawlerList.add(new WeiboCrawler());
//            for ( Crawler crawler : crawlerList ) {
//                try {
//                    crawler.start();
//                }catch (Exception e) {
//                    logger.error("[爬虫异常] - " + crawler.toString());
//                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                    e.printStackTrace(new PrintStream(baos));
//                    logger.error(baos.toString());
//                }
//            }

            logger.info("没错我就是任务！！ - " + new Date());
            logger.info("[定时任务] - 执行完毕");
            isRunning = false;
        } else if ( UserMonitor.getConfigStatusByType(SystemConfig.CONFIG_TYPE_SYSTEM) == SystemConfig.SYSTEM_STOP ) {
            logger.info("[定时任务] - 系统处于关闭状态");
        }
    }
}
