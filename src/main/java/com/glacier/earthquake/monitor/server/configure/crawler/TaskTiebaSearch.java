package com.glacier.earthquake.monitor.server.configure.crawler;

import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.crawler.Crawler;
import com.glacier.earthquake.monitor.server.crawler.module.baidu_search.BaiduSearchCrawler;
import com.glacier.earthquake.monitor.server.pojo.SystemConfig;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.TimerTask;

/**
 * Created by glacier on 15-6-1.
 */
public class TaskTiebaSearch extends TimerTask {

    private static Logger logger = Logger.getLogger(TaskTiebaSearch.class.getName());
    private static boolean isRunning = false;

    @Override
    public void run() {
        if ( !isRunning && UserMonitor.getConfigStatusByType(SystemConfig.CONFIG_TYPE_SYSTEM) == SystemConfig.SYSTEM_START ) {
            isRunning = true;
            logger.info("[定时任务] - 开始执行 【贴吧搜索】");

            Crawler crawler = new BaiduSearchCrawler();
            try {
                crawler.start();
            } catch (Exception e) {
                logger.error("[爬虫异常] - " + crawler.toString());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                e.printStackTrace(new PrintStream(baos));
                logger.error(baos.toString());
            }

            logger.info("[定时任务] - 执行完毕 【贴吧搜索】");
            isRunning = false;
        } else if ( UserMonitor.getConfigStatusByType(SystemConfig.CONFIG_TYPE_SYSTEM) == SystemConfig.SYSTEM_STOP ) {
            logger.info("[定时任务] - 系统处于关闭状态");
        }
    }

}
