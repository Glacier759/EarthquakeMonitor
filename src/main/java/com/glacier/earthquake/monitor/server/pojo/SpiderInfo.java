package com.glacier.earthquake.monitor.server.pojo;

import java.util.Date;

/**
 * Created by glacier on 15-5-2.
 */
public class SpiderInfo {

    public static final int FILTER_DISASTER = 0;
    public static final int FILTER_PUBSENTIMENT = 1;

    public static final int INFO_APPROVED = 1;
    public static final int INFO_NOTAPPROVED = 0;

    private int id, rule_id, type, status;
    private String url, title, source;
    private Date create_date, page_date;
    private int origin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRule_id() {
        return rule_id;
    }

    public void setRule_id(int rule_id) {
        this.rule_id = rule_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public Date getPage_date() {
        return page_date;
    }

    public void setPage_date(Date page_date) {
        this.page_date = page_date;
    }

    @Override
    public String toString() {
        return "SpiderInfo{" +
                "id=" + id +
                ", rule_id=" + rule_id +
                ", type=" + type +
                ", status=" + status +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", create_date=" + create_date +
                ", page_date=" + page_date +
                ", origin=" + origin +
                '}';
    }
}
