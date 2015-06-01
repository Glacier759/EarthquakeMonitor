package com.glacier.earthquake.monitor.server.pojo;

import java.util.Date;

/**
 * Created by glacier on 15-6-1.
 */
public class SpiderProxy {

    private int id, proxy_port;
    private String proxy_username, proxy_password, proxy_ip;
    private Date create_date, last_using;
    private int status;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProxy_ip() {
        return proxy_ip;
    }

    public void setProxy_ip(String proxy_ip) {
        this.proxy_ip = proxy_ip;
    }

    public int getProxy_port() {
        return proxy_port;
    }

    public void setProxy_port(int proxy_port) {
        this.proxy_port = proxy_port;
    }

    public String getProxy_username() {
        return proxy_username;
    }

    public void setProxy_username(String proxy_username) {
        this.proxy_username = proxy_username;
    }

    public String getProxy_password() {
        return proxy_password;
    }

    public void setProxy_password(String proxy_password) {
        this.proxy_password = proxy_password;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getLast_using() {
        return last_using;
    }

    public void setLast_using(Date last_using) {
        this.last_using = last_using;
    }

    @Override
    public String toString() {
        return "SpiderProxy{" +
                "id=" + id +
                ", proxy_ip='" + proxy_ip + '\'' +
                ", proxy_port=" + proxy_port +
                ", proxy_username='" + proxy_username + '\'' +
                ", proxy_password='" + proxy_password + '\'' +
                ", create_date=" + create_date +
                ", last_using=" + last_using +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }
}
