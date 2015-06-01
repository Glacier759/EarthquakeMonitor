package com.glacier.earthquake.monitor.server.pojo;

import java.util.Date;

/**
 * Created by glacier on 15-5-28.
 */
public class SystemConfig {

    public static final int CONFIG_TYPE_SYSTEM = 0;
    public static final int CONFIG_TYPE_EXAMINE = 1;
    public static final int CONFIG_TYPE_BAIDU_SEARCH = 2;
    public static final int CONFIG_TYPE_BBS_SEARCH = 3;
    public static final int CONFIG_TYPE_TIEBA_SEARCH = 4;
    public static final int CONFIG_TYPE_SINA_WEIBO = 5;
    public static final int CONFIG_TYPE_WEIXIN_SEARCH = 6;
    public static final int CONFIG_TYPE_BING_SEARCH = 7;

    public static final int SYSTEM_STOP = 0;
    public static final int SYSTEM_START = 1;

    public static final int EXAMINE_STOP = 0;
    public static final int EXAMINE_START = 1;


    private int id, type, status;
    private Date createDate;

    public SystemConfig() {

    }

    public SystemConfig(int type, int status) {
        this.type = type;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "SystemConfig{" +
                "id=" + id +
                ", type=" + type +
                ", status=" + status +
                ", createDate=" + createDate +
                '}';
    }
}
