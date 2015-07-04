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
public class AndDeleteUserServlet extends HttpServlet {

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
            User del_user = UserMonitor.getUserInfoByUID(Integer.parseInt(uid));
            if ( del_user != null ) {
                //root用户不允许删除
                if ( del_user.getPrivilege() == UserMonitor.USER_ROOT ) {
                    jsonObject.put("status", "failed");
                    jsonObject.put("explain", "root user is not allowed to delete");
                    //如果登录用户是root 那么非root用户都可以删除
                } else if ( del_user.getPrivilege() <= UserMonitor.USER_ADMINISTATOR && UserMonitor.getUserMonitor(request).isROOT() ) {
                    UserMonitor.getUserMonitor(request).delUser(del_user);
                    jsonObject.put("status", "success");
                    jsonObject.put("explain", "user will be delete");
                    //如果登录用户是普通管理员 并且 待删用户为普通用户 则可以删除
                } else if ( UserMonitor.getUserMonitor(request).isAdministor() && del_user.getPrivilege() < UserMonitor.USER_ADMINISTATOR ) {
                    UserMonitor.getUserMonitor(request).delUser(del_user);
                    jsonObject.put("status", "success");
                    jsonObject.put("explain", "user will be delete");
                } else if ( !UserMonitor.getUserMonitor(request).isAdministor() ) {
                    jsonObject.put("status", "failed");
                    jsonObject.put("explain", "permission denied");
                } else if ( del_user.getPrivilege() == UserMonitor.USER_ADMINISTATOR ) {
                    jsonObject.put("status", "failed");
                    jsonObject.put("explain", "permission denied");
                } else {
                    jsonObject.put("status", "failed");
                    jsonObject.put("explain", "wrong operate");
                }
            }
        }
        response.getWriter().print(jsonObject.toString());
    }
}
