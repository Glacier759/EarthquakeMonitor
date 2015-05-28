package com.glacier.earthquake.monitor.server.configure.crawler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;

/**
 * Created by glacier on 15-5-28.
 */
public class TaskManager implements ServletContextListener {

    private Timer timer = null;

    /**
     * 在Web应用启动时初始化任务
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //创建一个新计时器，其相关的线程具有指定的名称，并且可以指定作为守护程序运行。
        timer = new Timer("爬虫任务执行", true);
        //task - 所要安排的任务。
        //delay - 执行任务前的延迟时间，单位是毫秒。
        //period - 执行各后续任务之间的时间间隔，单位是毫秒。
        timer.schedule(new TaskCrawler(), 1000*60*10, 1000*60*10);

    }

    /**
     * 在Web应用结束时停止任务
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        timer.cancel(); // 定时器销毁
    }

}
