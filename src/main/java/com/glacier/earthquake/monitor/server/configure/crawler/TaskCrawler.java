package com.glacier.earthquake.monitor.server.configure.crawler;

import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.SystemStatus;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.TimerTask;

/**
 * Created by glacier on 15-5-28.
 */
public class TaskCrawler extends TimerTask {

    private static Logger logger = Logger.getLogger(TaskCrawler.class.getName());
    private static boolean isRunning = false;

    @Override
    public void run() {
        if ( !isRunning && UserMonitor.getSystemStatus().getStatus() == SystemStatus.SYSTEM_START ) {
            isRunning = true;
            logger.info("[定时任务] - 开始执行");
            //do something...
            logger.info("没错我就是任务！！ - " + new Date());
            logger.info("[定时任务] - 执行完毕");
            isRunning = false;
        } else if ( UserMonitor.getSystemStatus().getStatus() == SystemStatus.SYSTEM_STOP ) {
            logger.info("[定时任务] - 系统处于关闭状态");
        }
    }
}
