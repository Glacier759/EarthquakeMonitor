package com.glacier.earthquake.monitor.server.crawler.core;

import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.SpiderFilter;
import com.glacier.earthquake.monitor.server.pojo.SystemConfig;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.Object2Data;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public static void insertRecord(int sign, String sign_value, int service, int ruleID, int type) {
        SpiderFilter filter = getRecordBySignVale(sign_value, ruleID, type);

        if ( filter == null ) {
            filter = new SpiderFilter();
            filter.setOrigin(service);
            filter.setSign(sign);
            filter.setSign_value(sign_value);
            filter.setType(type);

            String rule_json = new JSONArray().put(new JSONObject().put("rule_id", ruleID)).toString();
            filter.setRule_json(rule_json);
            Object2Data.insertRecord(filter);
        }
        else {
            String rule_json = filter.getRule_json();
            if ( rule_json == null || rule_json.length() == 0 ) {
                rule_json = new JSONArray().put(new JSONObject().put("rule_id", ruleID)).toString();
                filter.setRule_json(rule_json);
                Object2Data.updateRecord(filter);
            }
            else {
                JSONArray jsonArray = new JSONArray(rule_json);
                for ( int index = 0; index < jsonArray.length(); index ++ ) {
                    JSONObject jsonObject = jsonArray.getJSONObject(index);
                    if ( jsonObject.get("rule_id").equals(ruleID) ) {
                        return;
                    }
                }
                rule_json = jsonArray.put(new JSONObject().put("rule_id", ruleID)).toString();
                filter.setRule_json(rule_json);
                Object2Data.updateRecord(filter);
            }
        }
    }

    public static SpiderFilter getRecordBySignVale(String sign_value, int ruleID, int type) {
        SpiderFilter filter = Data2Object.getRecordBySignValue(sign_value, type);
        String rule_json = filter.getRule_json();
        if ( rule_json != null) {
            JSONArray jsonArray = new JSONArray(rule_json);
            for ( int index = 0; index < jsonArray.length(); index ++ ) {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                if ( jsonObject.get("rule_id").equals(ruleID) ) {
                    return filter;
                }
            }
        }
        return null;
    }

}
