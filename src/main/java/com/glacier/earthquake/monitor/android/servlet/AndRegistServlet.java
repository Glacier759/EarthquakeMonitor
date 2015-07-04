package com.glacier.earthquake.monitor.android.servlet;

import com.glacier.earthquake.monitor.browser.util.UserUtils;
import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.User;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by glacier on 15-7-4.
 */
public class AndRegistServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(AndRegistServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        JSONObject jsonObject = new JSONObject();

        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");
        String realname =  req.getParameter("realname");
        String qqnumber = req.getParameter("qqnumber");
        String workplace = req.getParameter("workplace");
        String position = req.getParameter("position");

        if ( email == null || mobile == null || password == null || repassword == null
                || realname == null || qqnumber == null || workplace == null || position == null ) {
            jsonObject.put("status", "failed");
            jsonObject.put("explain", "has null field");
        }
        else if ( email.length() == 0 || mobile.length() == 0 || password.length() == 0
                || repassword.length() == 0 || realname.length() == 0 || qqnumber.length() == 0
                || workplace.length() == 0 || position.length() == 0 ) {
            jsonObject.put("status", "failed");
            jsonObject.put("explain", "has null field");
        }
        else if ( !password.equals(repassword) ) {
            jsonObject.put("status", "failed");
            jsonObject.put("explain", "password is not equal");
            logger.info("[安卓端][注册] - " + realname + " 两次密码不一致");
        }
        else {
            User user_mail = UserMonitor.getUserInfoByEmail(email);
            User user_mobile = UserMonitor.getUserInfoByMobile(mobile);
            if ( user_mail != null ) {
                jsonObject.put("status", "failed");
                jsonObject.put("explain", "email is existed");
                logger.info("[安卓端][注册] - " + email + " 已存在该用户");
            }
            else if ( user_mobile != null ) {
                jsonObject.put("status", "failed");
                jsonObject.put("explain", "mobile is existed");
                logger.info("[安卓端][注册] - " + mobile + " 已存在该用户");
            }
            else {
                User user = new User();
                int flag = 0;
                if ( UserUtils.isEmail(email) ) {
                    user.setEmail(email);
                }
                else {
                    flag = -1;
                    jsonObject.put("status", "failed");
                    jsonObject.put("explain", "mail format error");
                    logger.info("[安卓端][注册] - " + realname + " 输入的邮箱格式有误");
                }
                if ( UserUtils.isMobile(mobile) ) {
                    user.setMobile(mobile);
                }
                else {
                    flag = -2;
                    jsonObject.put("status", "failed");
                    jsonObject.put("explain", "mobile format error");
                    logger.info("[安卓端][注册] - " + realname + " 输入的手机格式有误");
                }
                if ( flag == 0 ) {
                    user.setPassword(password);
                    user.setPosition(position);
                    user.setQqnumber(qqnumber);
                    user.setRealname(realname);
                    user.setWorkplace(workplace);

                    UserMonitor.registUser(user);
                    jsonObject.put("status", "success");
                    logger.info("[安卓端][注册] - " + realname + " 注册成功");
                }
            }
        }
        resp.getWriter().print(jsonObject.toString());
    }
}
