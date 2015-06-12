package com.glacier.earthquake.monitor.server.pojo;

import java.util.Date;

/**
 * Created by glacier on 15-5-1.
 */
public class FilterPublicSentiment {

    private String name, matcher, unexist;
    private int id;
    private Date createDate;
    private String submiter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatcher() {
        return matcher;
    }

    public void setMatcher(String matcher) {
        this.matcher = matcher;
    }

    public String getUnexist() {
        return unexist;
    }

    public void setUnexist(String unexist) {
        this.unexist = unexist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSubmiter() {
        return submiter;
    }

    public void setSubmiter(String submiter) {
        this.submiter = submiter;
    }

    @Override
    public String toString() {
        return "FilterPublicSentiment{" +
                "name='" + name + '\'' +
                ", matcher='" + matcher + '\'' +
                ", unexist='" + unexist + '\'' +
                ", id=" + id +
                ", createDate=" + createDate +
                ", submiter='" + submiter + '\'' +
                '}';
    }
}
