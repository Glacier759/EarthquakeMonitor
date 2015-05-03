package com.glacier.earthquake.monitor.server.crawler.core;

/**
 * Created by glacier on 15-5-3.
 */
public class Scheduler {

    public static final int SIGN_URL = 0;
    public static final int SIGN_ID = 1;
    public static final int SERVICE_BAIDU_SEARCH = 10;
    public static final int SERVICE_TIEBA_SEARCH = 11;
    public static final int SERVICE_WEIBO_SEARCH = 12;
    public static final int SERVICE_WEIXIN_SERACH = 13;
    public static final int SERVICE_BING_SEARCH = 14;
    public static final int SERVICE_BBS_SEARCH = 15;

    public static void insertRecord(int sign, String sign_value, int service) {

    }

}
