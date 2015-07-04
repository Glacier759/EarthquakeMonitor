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
public class AndModifyUserInfoServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(AndModifyUserInfoServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        String users = UserMonitor.getUserMonitor(request).getUsername();
        JSONObject jsonObject = new JSONObject();

        User user = (User)request.getSession().getAttribute("login_user");
        User new_user = new User();

        String email = request.getParameter("email");
        //如果用户没有设置过邮箱
        if ( user.getEmail() == null || user.getEmail().length() == 0) {
            //首先判断用户传入的是不是邮箱格式
            if ( UserUtils.isEmail(email) ) {
                //如果是邮箱 先判断邮箱是否已经被占用
                if ( UserMonitor.getUserInfoByEmail(email) != null ) {
                    jsonObject.put("status", "failed");
                    jsonObject.put("explain", "email existed");
                    response.getWriter().print(jsonObject.toString());
                    return;
                } else {
                    //没有被占用的时候才能进行邮箱设置
                    new_user.setEmail(email);
                }
            }
            //如果不是邮箱格式就告知返回
            else {
                jsonObject.put("status", "failed");
                jsonObject.put("explain", "email format error");
                response.getWriter().print(jsonObject.toString());
                return;
            }
        }
        //如果用户设置过邮箱并且和本次传递进来的参数一致
        else {
            if ( user.getEmail().equals(email) ) {
                new_user.setEmail(email);
            }
            else {
                //说明用户已经绑定过邮箱
                jsonObject.put("status", "failed");
                jsonObject.put("explain", "email binding");
                response.getWriter().print(jsonObject.toString());
                return;
            }
        }

        String mobile = request.getParameter("mobile");
        if ( user.getMobile() == null || user.getMobile().length() == 0) {
            //首先判断用户传入的是不是手机格式
            if ( UserUtils.isMobile(mobile) ) {
                //如果是手机 先判断手机是否已经被占用
                if ( UserMonitor.getUserInfoByMobile(mobile) != null ) {
                    jsonObject.put("status", "failed");
                    jsonObject.put("explain", "mobile had");
                    response.getWriter().print(jsonObject.toString());
                    return;
                } else {
                    //没有被占用的时候才能进行手机设置
                    new_user.setMobile(mobile);
                }
            }
            //如果不是手机格式就告知返回
            else {
                jsonObject.put("status", "failed");
                jsonObject.put("explain", "mobile format error");
                response.getWriter().print(jsonObject.toString());
                return;
            }
        }
        //如果用户设置过手机并且和本次传递进来的参数一致
        else {
            if ( user.getMobile().equals(mobile) ) {
                new_user.setMobile(mobile);
            }
            else {
                //说明用户已经绑定过手机
                jsonObject.put("status", "failed");
                jsonObject.put("explain", "mobile binding");
                response.getWriter().print(jsonObject.toString());
                return;
            }
        }
        new_user.setRealname(request.getParameter("realname"));
        new_user.setQqnumber(request.getParameter("qqnumber"));
        new_user.setPosition(request.getParameter("position"));
        new_user.setWorkplace(request.getParameter("workplace"));

        if ( new_user.getEmail().equals("null") || new_user.getMobile().equals("null")
                || new_user.getQqnumber().equals("null") || new_user.getPosition().equals("null") || new_user.getRealname().equals("null")
                || new_user.getWorkplace().equals("null") ) {
            jsonObject.put("status", "failed");
            jsonObject.put("explain", "userinfo not full");
            response.getWriter().print(jsonObject.toString());
            return;
        }

        new_user.setUid(user.getUid());
        new_user.setPassword(user.getPassword());
        new_user.setPrivilege(user.getPrivilege());
        UserMonitor.getUserMonitor(user).modifyUserInfo(new_user);
        logger.info("[修改资料] - " + users + " " + new_user.toString());
        request.getSession().setAttribute("login_user", new_user);
        jsonObject.put("status", "success");
        response.getWriter().print(jsonObject.toString());
    }

}
