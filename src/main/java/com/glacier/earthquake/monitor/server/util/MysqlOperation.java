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

    public void delFilterRulesWhite(FilterWhiteList filterWhiteList);

    public void addFilterRulesWhite(FilterWhiteList filterWhiteList);

    public void setFilterRulesPubSentiment(FilterPublicSentiment filterRulesPubSentiment);

    public void delFilterRulesPubSentiment(FilterPublicSentiment filterRulesPubSentiment);

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

    public List<SpiderInfo> getSpiderInfos_Type(int type);

    public List<SpiderInfo> getSpiderInfos_TypeAndStatus(SpiderInfo spiderInfo);

    public void approvedThrough(SpiderInfo spiderInfo);

    public void insertSpiderInfo(SpiderInfo spiderInfo);

    public void deleteSpiderInfo(SpiderInfo spiderInfo);

    public List<User> getUserList();

}
