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
public class AndManagerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        JSONObject jsonObject = new JSONObject();

        String uid = request.getParameter("uid");
        if ( uid != null ) {
            User manage_user = UserMonitor.getUserInfoByUID(Integer.parseInt(uid));
            if ( manage_user != null ) {
                if ( UserMonitor.getUserMonitor(request).isROOT() ) {
                    if ( manage_user.getPrivilege() == UserMonitor.USER_ORDINARY ) {
                        if ( UserMonitor.getUserMonitor(request).setManage(manage_user) ) {
                            jsonObject.put("status", "success");
                        } else {
                            jsonObject.put("status", "failed");
                            jsonObject.put("explain", "permission denied");
                        }
                    }
                    else if ( manage_user.getPrivilege() == UserMonitor.USER_ADMINISTATOR ) {
                        jsonObject.put("status", "failed");
                        jsonObject.put("explain", "it was admin");
                    }
                    else if ( manage_user.getPrivilege() == UserMonitor.USER_ROOT ) {
                        jsonObject.put("status", "failed");
                        jsonObject.put("explain", "root user is not allowed to operated");
                    }
                }
                else {
                    jsonObject.put("status", "failed");
                    jsonObject.put("explain", "permission denied");
                }
            }
        }
        response.getWriter().print(jsonObject.toString());
    }
}
