package com.glacier.earthquake.monitor.server.configure.user;

import com.glacier.earthquake.monitor.server.pojo.SpiderInfo;
import com.glacier.earthquake.monitor.server.pojo.User;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.Object2Data;

import java.util.List;

/**
 * Created by glacier on 15-5-2.
 */
public class SpiderInfoMonitor {

    private User user;

    public SpiderInfoMonitor(User user) {
        this.user = user;
    }

    public List<SpiderInfo> getSpiderInfo_Type(int type) {
        return Data2Object.getSpiderInfo_Type(type);
    }

    public List<SpiderInfo> getSpiderInfo_TypeAndStatus(SpiderInfo spiderInfo) {
        return Data2Object.getSpiderInfo_TypeAndStatus(spiderInfo);
    }

    public List<SpiderInfo> getSpiderInfo_Status(int status) {
        return Data2Object.getSpiderInfo_Status(status);
    }

    public boolean approvedThrough(Integer id) {
        if ( user.getPrivilege() >= UserMonitor.USER_ADMINISTATOR ) {
            Object2Data.approvedThrough(id);
            return true;
        }
        return false;
    }

    public boolean insertSpiderInfo(SpiderInfo spiderInfo) {
        if ( user.getPrivilege() >= UserMonitor.USER_ADMINISTATOR ) {
            Object2Data.insertSpiderInfo(spiderInfo);
            return true;
        }
        return false;
    }

    public boolean deleteSpiderInfo(int id) {
        SpiderInfo spiderInfo = new SpiderInfo();
        spiderInfo.setId(id);
        return deleteSpiderInfo(spiderInfo);
    }

    public boolean deleteSpiderInfo(SpiderInfo spiderInfo) {
        if ( user.getPrivilege() >= UserMonitor.USER_ADMINISTATOR ) {
            Object2Data.deleteSpiderInfo(spiderInfo);
            return true;
        }
        return false;
    }

    public SpiderInfo getSpiderInfoByID(Integer id) {
        return Data2Object.getSpiderInfoByID(id);
    }

}
