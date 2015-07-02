package com.glacier.earthquake.monitor.browser.util;

import com.glacier.earthquake.monitor.server.crawler.core.Scheduler;
import com.glacier.earthquake.monitor.server.pojo.SpiderInfo;
import com.glacier.earthquake.monitor.server.pojo.SystemConfig;

/**
 * Created by glacier on 15-6-12.
 */
public class SpiderInfoUtils {

    public static String originToString(int origin) {
        if ( origin == SystemConfig.CONFIG_TYPE_BAIDU_SEARCH) {
            return "百度";
        }
        else if ( origin == SystemConfig.CONFIG_TYPE_WEIXIN_SEARCH ) {
            return "微信";
        }
        else if ( origin == SystemConfig.CONFIG_TYPE_BBS_SEARCH ) {
            return "论坛";
        }
        else if ( origin == SystemConfig.CONFIG_TYPE_BING_SEARCH ) {
            return "必应";
        }
        else if ( origin == SystemConfig.CONFIG_TYPE_SINA_WEIBO ) {
            return "微博";
        }
        else if ( origin == SystemConfig.CONFIG_TYPE_TIEBA_SEARCH ) {
            return "贴吧";
        }
        return "未知";
    }

    public static String statusToString(int status) {
        if ( status == SpiderInfo.INFO_APPROVED ) {
            return "已审核";
        }
        else if ( status == SpiderInfo.INFO_NOTAPPROVED ) {
            return "未审核";
        }
        return "未知";
    }

    public static String titleByOrigin(int origin, String title) {
        if ( origin == Scheduler.SERVICE_WEIBO_SEARCH ) {
            return title.substring(1, 30) + "...";
        }
        else {
            return title;
        }
    }

    public static String examinerToString(String examiner) {
        if ( examiner == null ) {
            return "未知";
        }
        else {
            return examiner;
        }
    }

}
