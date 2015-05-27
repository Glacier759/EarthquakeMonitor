package com.glacier.earthquake.monitor.server.pojo;

import java.util.Date;

/**
 * Created by glacier on 15-5-27.
 */
public class SystemStatus {

    public static final int SYSTEM_STOP = 0;
    public static final int SYSTEM_START = 1;

    private int id;
    private Date create_date;
    private int status;

    public SystemStatus() {

    }

    public SystemStatus(int id, Date create_date, int status) {
        this.id = id;
        this.create_date = create_date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "SystemStatus{" +
                "id=" + id +
                ", create_date=" + create_date +
                ", status=" + status +
                '}';
    }
}
