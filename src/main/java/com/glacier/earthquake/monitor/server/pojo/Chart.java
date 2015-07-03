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

    private int infoCountAll, infoCountDis, infoCountPub;
    private int infoCountToday, infoCountPass, infoCountNoPass;
    private int infoCountTodayDis, infoCountTodayPub;
    private HashMap<String,Integer> timeMap, originMap, ruleMap;

    public Chart() {
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 E HH:mm:ss");
        return format.format(new Date());
    }

    public Integer infoCountAll() {
        return infoCountAll;
    }

    public Integer infoCountDis() {
        return infoCountDis;
    }

    public Integer infoCountPub() {
        return infoCountPub;
    }

    public Integer infoCountToday() {
        return infoCountToday;
    }

    public Integer infoCountTodayDis() {
        return infoCountTodayDis;
    }

    public Integer infoCountTodayPub() {
        return infoCountTodayPub;
    }

    public Integer infoCountPass() {
        return infoCountPass;
    }

    public Integer infoCountNoPass() {
        return infoCountNoPass;
    }

    public HashMap<String, Integer> timeMap() {
        return timeMap;
    }

    public HashMap<String, Integer> originMap() {
        return originMap;
    }

    public HashMap<String, Integer> ruleMap() {
        return ruleMap;
    }

}
