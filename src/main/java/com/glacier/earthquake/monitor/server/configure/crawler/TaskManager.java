package com.glacier.earthquake.monitor.server.configure.crawler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by glacier on 15-5-28.
 */
public class TaskManager implements ServletContextListener {

    private Timer timer_baidu_search = null;
    private Timer timer_bbs_search = null;
    private Timer timer_bing_search = null;
    private Timer timer_tieba_search = null;
    private Timer timer_weibo_search = null;
    private Timer timer_weixin_search = null;

    private static final long SECOND = 1000;
    private static final long MINUTE = SECOND * 60;
    private static final long HOUR = MINUTE * 60;

    /**
     * 在Web应用启动时初始化任务
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //创建一个新计时器，其相关的线程具有指定的名称，并且可以指定作为守护程序运行。
        timer_baidu_search = new Timer("百度搜索 爬虫定时任务", true);
        timer_bbs_search = new Timer("BBS搜索 爬虫定时任务", true);
        timer_bing_search = new Timer("必应搜索 爬虫定时任务", true);
        timer_tieba_search = new Timer("贴吧搜索 爬虫定时任务", true);
        timer_weibo_search = new Timer("微博搜索 爬虫定时任务", true);
        timer_weixin_search = new Timer("微信搜索 爬虫定时任务", true);

        //task - 所要安排的任务。
        //delay - 执行任务前的延迟时间，单位是毫秒。
        //period - 执行各后续任务之间的时间间隔，单位是毫秒。

//        timer_baidu_search.schedule(new TaskBaiduSearch(), MINUTE * 5, HOUR * 2);
//        timer_bing_search.schedule(new TaskBingSearch(), MINUTE * 30, HOUR * 2);
//        timer_bbs_search.schedule(new TaskBBSearch(), HOUR, HOUR * 3);
//        timer_tieba_search.schedule(new TaskTiebaSearch(), MINUTE * 45, HOUR * 3);
//        timer_weibo_search.schedule(new TaskWeiboSearch(), MINUTE * 70, HOUR * 3);
//        timer_weixin_search.schedule(new TaskWeixinSearch(), HOUR * 2, HOUR * 12);
    }

    /**
     * 在Web应用结束时停止任务
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        timer_baidu_search.cancel(); // 定时器销毁
        timer_bbs_search.cancel(); // 定时器销毁
        timer_bing_search.cancel(); // 定时器销毁
        timer_tieba_search.cancel(); // 定时器销毁
        timer_weibo_search.cancel(); // 定时器销毁
        timer_weixin_search.cancel(); // 定时器销毁
    }

}
