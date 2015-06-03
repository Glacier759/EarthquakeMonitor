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
import javax.servlet.http.Cookie;
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

        if ( choice.equals("login") ) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = null;
            if ( UserUtils.isEmail(username) ) {
                user = UserMonitor.getUserInfoByEmail(username);
            }
            else if ( UserUtils.isMobile(username) ) {
                user = UserMonitor.getUserInfoByMobile(username);
            }
            if ( user != null && user.getPassword().equals(password) ) {
                String privilege = null;
                if (user.getPrivilege() == 1) {
                    privilege = "admin";
                } else if (user.getPrivilege() == 0) {
                    privilege = "user";
                } else if (user.getPrivilege() == 2) {
                    privilege = "root";
                }
                request.getSession().setAttribute("login_user", user);
                request.getSession().setAttribute("login", "true");
                request.getSession().setAttribute("privilege", privilege);

                Cookie cookie_user = new Cookie("username", username);
                cookie_user.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(cookie_user);
                if ( UserMonitor.getUserMonitor(request).hasFullInfo() ) {
                    response.getWriter().print("login success");
                    logger.info("[登陆] - " + username + " 登录成功");
                } else {
                    request.getSession().setAttribute("userinfo", "n");
                    response.getWriter().print("userinfo is not full");
                    logger.info("[登陆] - " + username + " 登录成功 待完善个人信息");
                }
            }
            else {
                request.getSession().setAttribute("login", "false");
                response.getWriter().print("login failed");
                logger.info("[登陆] - " + username  + " 登录失败");
            }
        }
        else if ( choice.equals("register") ) {
            String nickname = request.getParameter("nickname");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String password = request.getParameter("password");
            String repassword = request.getParameter("repassword");
            String realname = request.getParameter("realname");
            String qqnumber = request.getParameter("qqnumber");
            String workplace = request.getParameter("workplace");
            String position = request.getParameter("position");

            if ( nickname == null || email == null || mobile == null || password == null || repassword == null
                    || realname == null || qqnumber == null || workplace == null || position == null ) {
                response.getWriter().print("has null");
                return;
            }
            if ( nickname.length() == 0 || email.length() == 0 || mobile.length() == 0 || password.length() == 0
                    || repassword.length() == 0 || realname.length() == 0 || qqnumber.length() == 0
                    || workplace.length() == 0 || position.length() == 0 ) {
                response.getWriter().print("has null");
                return;
            }

            if ( !password.equals(repassword) ) {
                response.getWriter().print("password not equal");
                logger.info("[注册] - " + nickname + " 两次密码不一致");
                return;
            }
            User user = null;
            User user_mail = UserMonitor.getUserInfoByEmail(email);
            User user_mobile = UserMonitor.getUserInfoByMobile(mobile);
            if ( user_mail != null ) {
                response.getWriter().print("mail is existed");
                logger.info("[注册] - " + email + " 已存在该用户");
                return;
            }
            if ( user_mobile != null ) {
                response.getWriter().print("mobile is existed");
                logger.info("[注册] - " + mobile + " 已存在该用户");
                return;
            }
            user = new User();
            if (UserUtils.isEmail(email)) {
                user.setEmail(email);
            }
            else {
                response.getWriter().print("input mail error");
                logger.info("[注册] - " + nickname + " 输入的邮箱格式有误");
                return;
            }
            if ( UserUtils.isMobile(mobile) ) {
                user.setMobile(mobile);
            }
            else {
                response.getWriter().print("input mobile error");
                logger.info("[注册] - " + nickname + " 输入的手机格式有误");
                return;
            }
            user.setPassword(password);
            user.setNickname(nickname);
            user.setPosition(position);
            user.setQqnumber(qqnumber);
            user.setRealname(realname);
            user.setWorkplace(workplace);

            UserMonitor.registUser(user);
            response.getWriter().print("register success");
            logger.info("[注册] - " + nickname + " 注册成功");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
