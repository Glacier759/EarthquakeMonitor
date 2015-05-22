package com.glacier.earthquake.monitor.server.pojo;

import java.util.Date;

/**
 * Created by glacier on 15-5-1.
 */
public class User {

    private String nickname, email, mobile;
    private int uid, privilege;
    private Date createDate;
    private String password;
    private String realname, workplace, position, qqnumber;

    public User() {

    }

    public User(String nickname, String email, String mobile, String password) {
        this.nickname = nickname;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getQqnumber() {
        return qqnumber;
    }

    public void setQqnumber(String qqnumber) {
        this.qqnumber = qqnumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", uid=" + uid +
                ", privilege=" + privilege +
                ", createDate=" + createDate +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", workplace='" + workplace + '\'' +
                ", position='" + position + '\'' +
                ", qqnumber='" + qqnumber + '\'' +
                '}';
    }
}
