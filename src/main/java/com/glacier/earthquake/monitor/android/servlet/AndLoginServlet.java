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
public class AndLoginServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(AndLoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = null;
        JSONObject jsonObject = new JSONObject();
        if ( username != null && password != null ) {
            if (UserUtils.isEmail(username)) {
                user = UserMonitor.getUserInfoByEmail(username);
            } else if (UserUtils.isMobile(username)) {
                user = UserMonitor.getUserInfoByMobile(username);
            }
            if (user != null && user.getPassword().equals(password)) {
                req.getSession().setAttribute("login_user", user);

                String privilege = UserUtils.privilegeToString(user.getPrivilege());
                jsonObject.put("status", "success");
                jsonObject.put("user_name", user.getRealname());
                jsonObject.put("user_id", user.getUid());
                jsonObject.put("user_privilege", privilege);
                jsonObject.put("user_email", user.getEmail());
                jsonObject.put("user_mobile", user.getMobile());
                logger.info("[安卓端][登陆] - " + username + " 登录成功");
            }
        }
        else {
            jsonObject.put("status", "failed");
            logger.info("[安卓端][登陆] - " + username + " 登录失败");
        }
        resp.getWriter().print(jsonObject.toString());
    }

}
