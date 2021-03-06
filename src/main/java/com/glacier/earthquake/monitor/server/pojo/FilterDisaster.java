package com.glacier.earthquake.monitor.server.pojo;

import java.util.Date;

/**
 * Created by glacier on 15-5-1.
 */
public class FilterDisaster {

    private String filterRule;
    private int id;
    private Date createDate;
    private String submiter;

    public String getFilterRule() {
        return filterRule;
    }

    public void setFilterRule(String filterRule) {
        this.filterRule = filterRule;
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
        return "FilterDisaster{" +
                "filterRule='" + filterRule + '\'' +
                ", id=" + id +
                ", createDate=" + createDate +
                ", submiter='" + submiter + '\'' +
                '}';
    }
}
