package com.glacier.earthquake.monitor.server.util;

import com.glacier.earthquake.monitor.server.pojo.Account;
import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.FilterPublicSentiment;
import com.glacier.earthquake.monitor.server.pojo.FilterWhiteList;

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

    public void delFilterRulesDisaster(FilterDisaster filterDisaster);

    public void addFilterRulesDisaster(FilterDisaster filterDisaster);

}
