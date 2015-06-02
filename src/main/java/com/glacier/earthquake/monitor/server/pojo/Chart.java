package com.glacier.earthquake.monitor.server.pojo;

import com.glacier.earthquake.monitor.server.crawler.core.Scheduler;
import com.glacier.earthquake.monitor.server.util.Data2Object;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by glacier on 15-6-1.
 */
public class Chart {

    public static int infoCountAll, infoCountDis, infoCountPub;
    public static int infoCountToday, infoCountPass, infoCountNoPass;
    public static int infoCountTodayDis, infoCountTodayPub;
    public static HashMap<String,Integer> timeMap, originMap, ruleMap;

    static {
        List<SpiderInfo> infoList = Data2Object.getSpiderInfoList();
        infoCountAll = infoList.size();
        infoCountDis = 0; infoCountPub = 0; infoCountToday = 0;
        infoCountPass = 0; infoCountNoPass = 0;
        infoCountTodayDis = 0; infoCountTodayPub = 0;
        timeMap = new HashMap<String, Integer>();
        originMap = new HashMap<String, Integer>();
        ruleMap = new HashMap<String, Integer>();
        originMap.put("百度搜索", 0);
        originMap.put("必应搜索", 0);
        originMap.put("百度贴吧", 0);
        originMap.put("BBS论坛搜索", 0);
        originMap.put("新浪微博", 0);
        originMap.put("微信文章", 0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for ( SpiderInfo spiderInfo : infoList ) {
            if ( spiderInfo.getType() == SpiderInfo.FILTER_DISASTER ) {
                infoCountDis ++;
            }
            else if( spiderInfo.getType() == SpiderInfo.FILTER_PUBSENTIMENT ) {
                infoCountPub ++;
            }
            if ( format.format(new Date()).equals(format.format(spiderInfo.getCreate_date())) ) {
                if ( spiderInfo.getType() == SpiderInfo.FILTER_DISASTER ) {
                    infoCountTodayDis ++;
                }
                else if ( spiderInfo.getType() == SpiderInfo.FILTER_PUBSENTIMENT ){
                    infoCountTodayPub ++;
                }
                infoCountToday ++;
            }
            if ( spiderInfo.getStatus() == SpiderInfo.INFO_APPROVED ) {
                infoCountPass ++;
            }
            else if ( spiderInfo.getStatus() == SpiderInfo.INFO_NOTAPPROVED ) {
                infoCountNoPass ++;
            }
            if ( !timeMap.containsKey(format.format(spiderInfo.getCreate_date())) ) {
                timeMap.put(format.format(spiderInfo.getCreate_date()), 1);
            } else {
                timeMap.put(format.format(spiderInfo.getCreate_date()), timeMap.get(format.format(spiderInfo.getCreate_date()))+1);
            }
            switch (spiderInfo.getOrigin()) {
                case Scheduler.SERVICE_BBS_SEARCH:
                    originMap.put("BBS论坛搜索", originMap.get("BBS论坛搜索")+1);
                    break;
                case Scheduler.SERVICE_BAIDU_SEARCH:
                    originMap.put("百度搜索", originMap.get("百度搜索")+1);
                    break;
                case Scheduler.SERVICE_BING_SEARCH:
                    originMap.put("必应搜索", originMap.get("必应搜索")+1);
                    break;
                case Scheduler.SERVICE_TIEBA_SEARCH:
                    originMap.put("百度贴吧", originMap.get("百度贴吧")+1);
                    break;
                case Scheduler.SERVICE_WEIBO_SEARCH:
                    originMap.put("新浪微博", originMap.get("新浪微博")+1);
                    break;
                case Scheduler.SERVICE_WEIXIN_SERACH:
                    originMap.put("微信文章", originMap.get("微信文章")+1);
                    break;
            }
            if ( !ruleMap.containsKey(spiderInfo.getRule_id()+"") ) {
                ruleMap.put(spiderInfo.getRule_id()+"", 1);
            }
            else {
                ruleMap.put(spiderInfo.getRule_id()+"", ruleMap.get(spiderInfo.getRule_id()+"")+1);
            }
        }
    }

    public String today() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 星期E HH:mm:ss");
        return format.format(new Date());
    }

//    "SELECT count(*) FROM spider_information"; //系统收录信息
//    "SELECT count(*) FROM spider_information WHERE type = 0"; //灾情获取匹配
//    "SELECT count(*) FROM spider_information WHERE type = 1"; //舆情监测匹配
//    "SELECT count(*) FROM spider_information WHERE date_format(create_date, '%Y-%m-%d') = '2015-06-01'"; //今日获取数据
//    "SELECT count(*) FROM spider_information WHERE status = 1";     //已通过审核信息
//    "SELECT count(*) FROM spider_information WHERE status = 0";     //未通过审核信息
//    "SELECT count(create_date),create_date FROM spider_information GROUP BY date_format(create_date, '%Y-%m-%d');"  //近期数据采集情况
//    "SELECT count(*),origin FROM spider_information GROUP BY origin";   //信息来源分布
//    "SELECT count(*),rule_id FROM spider_information GROUP BY rule_id";     //过滤规则分布情况
}
