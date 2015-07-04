package com.glacier.earthquake.monitor.android.servlet;

import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by glacier on 15-7-4.
 */
public class AndUserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        JSONArray jsonArray = new JSONArray();
        List<User> userList = UserMonitor.getUserMonitor(request).getUserList();
        if ( userList != null ) {
            for (int index = 0; index < userList.size(); index++) {
                User user = userList.get(index);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("uid", user.getUid());
                jsonObject.put("email", user.getEmail());
                jsonObject.put("mobile", user.getMobile());
                jsonObject.put("position", user.getPosition());
                jsonObject.put("qqnumber", user.getQqnumber());
                jsonObject.put("realname", user.getRealname());
                jsonObject.put("workplace", user.getWorkplace());
                jsonObject.put("privilege", user.getPrivilege());
                jsonArray.put(jsonObject);
            }
        }
        response.getWriter().print(jsonArray.toString());
    }

}
