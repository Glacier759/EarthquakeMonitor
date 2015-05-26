package com.glacier.earthquake.monitor.browser.servlet;

import com.glacier.earthquake.monitor.browser.util.UserUtils;
import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.User;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.Object2Data;
import com.glacier.earthquake.monitor.server.util.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by glacier on 15-5-22.
 */
public class LoginServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(LoginServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String choice = request.getParameter("choice");     //判断是登陆还是注册
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ( choice.equals("login") ) {
            User user = null;
            if ( UserUtils.isEmail(username) ) {
                user = UserMonitor.getUserInfoByEmail(username);
            }
            else if ( UserUtils.isMobile(username) ) {
                user = UserMonitor.getUserInfoByMobile(username);
            }
            if ( user != null && user.getPassword().equals(password) ) {
                request.getSession().setAttribute("login_user", user);
                request.getSession().setAttribute("login", "true");
                response.getWriter().print("login success");
                logger.info("[登陆] - " + username + " 登录成功");
            }
            else {
                request.getSession().setAttribute("login", "false");
                response.getWriter().print("login failed");
                logger.info("[登陆] - " + username  + " 登录失败");
            }
        }
        else if ( choice.equals("register") ) {
            String repassword = request.getParameter("repassword");
            if ( !password.equals(repassword) ) {
                response.getWriter().print("password not equal");
                logger.info("[注册] - " + username + " 两次密码不一致");
                return;
            }
            User user = null;
            User user_mail = UserMonitor.getUserInfoByEmail(username);
            User user_mobile = UserMonitor.getUserInfoByMobile(username);
            if ( user_mail != null ) {  user = user_mail; }
            else if ( user_mobile != null ) {   user = user_mobile; }
            if ( user != null ) {
                response.getWriter().print("user is existed");
                logger.info("[注册] - " + username + " 已存在该用户");
            }
            else {
                user = new User();
                if (UserUtils.isEmail(username)) {
                    user.setEmail(username);
                }
                else if ( UserUtils.isMobile(username) ) {
                    user.setMobile(username);
                }
                else {
                    response.getWriter().print("input error");
                    logger.info("[注册] - " + username + " 输入的信息既不是邮箱也不是手机号");
                    return;
                }
                user.setPassword(password);
                UserMonitor.registUser(user);
                response.getWriter().print("register success");
                logger.info("[注册] - " + username + " 注册成功");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
