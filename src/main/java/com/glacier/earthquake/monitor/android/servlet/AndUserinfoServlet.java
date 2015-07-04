package com.glacier.earthquake.monitor.android.servlet;

import com.glacier.earthquake.monitor.browser.util.UserUtils;
import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by glacier on 15-7-4.
 */
public class AndUserinfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        JSONObject jsonObject = new JSONObject();

        String uid = req.getParameter("uid");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        if ( uid != null || email != null || mobile != null ) {
            User user = null;
            if ( uid != null ) {
                user = UserMonitor.getUserInfoByUID(Integer.parseInt(uid));
            }
            else if ( email != null && UserUtils.isEmail(email) ) {
                user = UserMonitor.getUserInfoByEmail(email);
            }
            else if ( mobile != null && UserUtils.isMobile(mobile) ) {
                user = UserMonitor.getUserInfoByMobile(mobile);
            }
            if ( user != null ) {
                jsonObject.put("status", "success");
                jsonObject.put("user_uid", user.getUid());
                jsonObject.put("user_email", user.getEmail());
                jsonObject.put("user_mobile", user.getMobile());
                jsonObject.put("user_privilege", UserUtils.privilegeToString(user.getPrivilege()));
                jsonObject.put("user_create_date", UserUtils.dateFormat(user.getCreateDate()));
                jsonObject.put("user_name", user.getRealname());
                jsonObject.put("user_workplace", user.getWorkplace());
                jsonObject.put("user_position", user.getPosition());
                jsonObject.put("user_qqnumber", user.getQqnumber());
            }
            else {
                jsonObject.put("status", "failed");
                jsonObject.put("explain", "user is null");
            }
        }
        else {
            User user = (User)req.getSession().getAttribute("login_user");
            if ( user != null ) {
                jsonObject.put("status", "success");
                jsonObject.put("user_uid", user.getUid());
                jsonObject.put("user_email", user.getEmail());
                jsonObject.put("user_mobile", user.getMobile());
                jsonObject.put("user_privilege", UserUtils.privilegeToString(user.getPrivilege()));
                jsonObject.put("user_create_date", UserUtils.dateFormat(user.getCreateDate()));
                jsonObject.put("user_name", user.getRealname());
                jsonObject.put("user_workplace", user.getWorkplace());
                jsonObject.put("user_position", user.getPosition());
                jsonObject.put("user_qqnumber", user.getQqnumber());
            }
            else {
                jsonObject.put("status", "failed");
                jsonObject.put("explain", "parameter error");
            }
        }
        resp.getWriter().print(jsonObject.toString());
    }

}
