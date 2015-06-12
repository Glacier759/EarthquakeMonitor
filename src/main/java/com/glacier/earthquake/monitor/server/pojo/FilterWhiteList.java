package com.glacier.earthquake.monitor.server.pojo;

import java.util.Date;

/**
 * Created by glacier on 15-5-1.
 */
public class FilterWhiteList {

    private String url;
    private int id;
    private Date date;
    private String submiter;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubmiter() {
        return submiter;
    }

    public void setSubmiter(String submiter) {
        this.submiter = submiter;
    }

    @Override
    public String toString() {
        return "FilterWhiteList{" +
                "url='" + url + '\'' +
                ", id=" + id +
                ", date=" + date +
                ", submiter='" + submiter + '\'' +
                '}';
    }
}
