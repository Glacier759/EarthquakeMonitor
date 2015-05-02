package com.glacier.earthquake.monitor.server.configure.crawler;

import com.glacier.earthquake.monitor.server.pojo.SpiderInfo;
import com.glacier.earthquake.monitor.server.util.Object2Data;

/**
 * Created by glacier on 15-5-2.
 */
public class SpiderInfoManager {

    public static void insertSpiderInfo(SpiderInfo spiderInfo) {
        Object2Data.insertSpiderInfo(spiderInfo);
    }

    public static void deleteSpiderInfo(SpiderInfo spiderInfo) {
        Object2Data.deleteSpiderInfo(spiderInfo);
    }

}
