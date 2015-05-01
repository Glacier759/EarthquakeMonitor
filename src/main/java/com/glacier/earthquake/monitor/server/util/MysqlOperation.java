package com.glacier.earthquake.monitor.server.util;

import com.glacier.earthquake.monitor.server.pojo.Account;
import com.glacier.earthquake.monitor.server.pojo.Filter;

import java.util.List;

/**
 * Created by glacier on 15-5-1.
 */
public interface MysqlOperation {

    public Account getAccount(String type);

    public void updateAccount(Account accounts);

    public List<String> getFilterRules();

}
