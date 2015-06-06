package com.glacier.earthquake.monitor.server.pojo;

import java.util.Date;

/**
 * Created by glacier on 15-6-1.
 */
public class SpiderFilter {

    private int id, sign;
    private String sign_value;
    private int origin;
    private Date create_date;
    private String rule_json;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public String getSign_value() {
        return sign_value;
    }

    public void setSign_value(String sign_value) {
        this.sign_value = sign_value;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getRule_json() {
        return rule_json;
    }

    public void setRule_json(String rule_json) {
        this.rule_json = rule_json;
    }

    @Override
    public String toString() {
        return "SpiderFilter{" +
                "id=" + id +
                ", sign=" + sign +
                ", sign_value='" + sign_value + '\'' +
                ", origin=" + origin +
                ", create_date=" + create_date +
                ", rule_json='" + rule_json + '\'' +
                '}';
    }
}
