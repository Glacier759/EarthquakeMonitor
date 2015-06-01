package com.glacier.earthquake.monitor.server.util;

import com.glacier.earthquake.monitor.server.pojo.*;

import java.util.List;

/**
 * Created by glacier on 15-5-1.
 */
public interface MysqlOperation {

    public Account getAccount(String type);

    public void updateAccount(Account accounts);

    public List<FilterDisaster> getFilterRulesDisaster();

    public List<FilterPublicSentiment> getFilterRulesPubSentiment();

    public List<FilterWhiteList> getFilterRulesWhiteList();

    public void setFilterRulesWhite(FilterWhiteList filterRulesWhite);

    public void delFilterRulesWhite(Integer id);

    public void addFilterRulesWhite(FilterWhiteList filterWhiteList);

    public void setFilterRulesPubSentiment(FilterPublicSentiment filterRulesPubSentiment);

    public void delFilterRulesPubSentiment(Integer id);

    public void addFilterRulesPubSentiment(FilterPublicSentiment filterRulesPubSentiment);

    public void setFilterRulesDisaster(FilterDisaster filterDisaster);

    public void delFilterRulesDisaster(Integer id);

    public void addFilterRulesDisaster(FilterDisaster filterDisaster);

    public User getUserInfoByEmail(String email);

    public User getUserInfoByMobile(String mobile);

    public User getUserInfoByUID(int uid);

    public User checkPassword(User user);

    public void modifyUserInfo(User user);

    public void addUser(User user);

    public void delUser(User user);

    public User isExistUser(User user);

    public List<SpiderInfo> getSpiderInfoList();

    public List<SpiderInfo> getSpiderInfo_Type(int type);

    public List<SpiderInfo> getSpiderInfo_Status(int status);

    public List<SpiderInfo> getSpiderInfo_TypeAndStatus(SpiderInfo spiderInfo);

    public void approvedThrough(Integer id);

    public void insertSpiderInfo(SpiderInfo spiderInfo);

    public void deleteSpiderInfo(SpiderInfo spiderInfo);

    public List<User> getUserList();

    public void changePassword(User user);

    public SpiderInfo getSpiderInfoByID(Integer id);

    public FilterDisaster getFilterDisasterByID(Integer id);

    public FilterPublicSentiment getFilterPubSentimentByID(Integer id);

    public FilterWhiteList getFilterWhiteListByID(Integer id);

    public void setConfigStatusByType(SystemConfig config);

    public SystemConfig getConfigStatusByType(Integer type);

    public void insertRecord(SpiderFilter filter);

    public SpiderFilter getRecordBySignValue(String sign_value);

    public void insertProxy(SpiderProxy proxy);

    public SpiderProxy getProxy();

    public void updateProxy(SpiderProxy proxy);

    public void setManage(User user);

}
