package com.glacier.earthquake.monitor.server.crawler.core;

import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.SpiderFilter;
import com.glacier.earthquake.monitor.server.pojo.SystemConfig;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.Object2Data;

/**
 * Created by glacier on 15-5-3.
 */
public class Scheduler {

    public static final int SIGN_URL = 0;
    public static final int SIGN_ID = 1;
    public static final int SERVICE_BAIDU_SEARCH = SystemConfig.CONFIG_TYPE_BAIDU_SEARCH;
    public static final int SERVICE_TIEBA_SEARCH = SystemConfig.CONFIG_TYPE_TIEBA_SEARCH;
    public static final int SERVICE_WEIBO_SEARCH = SystemConfig.CONFIG_TYPE_SINA_WEIBO;
    public static final int SERVICE_WEIXIN_SERACH = SystemConfig.CONFIG_TYPE_WEIXIN_SEARCH;
    public static final int SERVICE_BING_SEARCH = SystemConfig.CONFIG_TYPE_BING_SEARCH;
    public static final int SERVICE_BBS_SEARCH = SystemConfig.CONFIG_TYPE_BBS_SEARCH;

    public static void insertRecord(int sign, String sign_value, int service) {
        SpiderFilter filter = new SpiderFilter();
        filter.setOrigin(service);
        filter.setSign(sign);
        filter.setSign_value(sign_value);
        Object2Data.insertRecord(filter);
    }

    public static SpiderFilter getRecordBySignVale(String sign_value) {
        SpiderFilter filter = Data2Object.getRecordBySignValue(sign_value);
        return filter;
    }

}
