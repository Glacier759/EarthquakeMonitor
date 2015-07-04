package com.glacier.earthquake.monitor.android.servlet;

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
public class AndModifyPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        JSONObject jsonObject = new JSONObject();

        User user = (User)request.getSession().getAttribute("login_user");

        String password = request.getParameter("password");
        if ( !user.getPassword().equals(password) ) {
            jsonObject.put("status", "failed");
            jsonObject.put("explain", "wrong password");
        }
        else {
            String new_password = request.getParameter("new-password");
            String re_password = request.getParameter("re-password");
            if ( !new_password.equals(re_password) ) {
                jsonObject.put("status", "failed");
                jsonObject.put("explain", "password not equal");
            } else {
                User new_user = new User();
                new_user.setPassword(new_password);
                new_user.setUid(user.getUid());
                UserMonitor.getUserMonitor(user).changePassword(new_user);
                request.getSession().setAttribute("login_user", UserMonitor.getUserInfoByUID(user.getUid()));
                jsonObject.put("status", "success");
            }
        }
        response.getWriter().print(jsonObject.toString());
    }

}
