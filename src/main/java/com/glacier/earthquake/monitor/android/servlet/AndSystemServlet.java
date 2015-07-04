package com.glacier.earthquake.monitor.android.servlet;

import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.SystemConfig;
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
public class AndSystemServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(AndSystemServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        String operate = request.getParameter("operate");
        String status = request.getParameter("status");
        String users = UserMonitor.getUserMonitor(request).getUsername();
        JSONObject jsonObject = new JSONObject();

        if ( status != null ) {
            if (UserMonitor.getConfigStatusByType(SystemConfig.CONFIG_TYPE_SYSTEM) == SystemConfig.SYSTEM_START) {
                jsonObject.put("system_status", "start");
            }
            else if (UserMonitor.getConfigStatusByType(SystemConfig.CONFIG_TYPE_SYSTEM) == SystemConfig.SYSTEM_STOP ) {
                jsonObject.put("system_status", "stop");
            }
        }
        if ( operate != null ) {
            if ( operate.equals("system-stop") ) {
                if ( UserMonitor.getUserMonitor(request).isAdministor() ) {
                    UserMonitor.setConfigStatusByType(SystemConfig.CONFIG_TYPE_SYSTEM, SystemConfig.SYSTEM_STOP);
                    logger.info("[安卓端][关闭系统] - " + users);
                    jsonObject.put("operate_status", "success");
                }
                else {
                    jsonObject.put("operate_status", "failed");
                    jsonObject.put("explain", "permission denied");
                }
            }
            else if ( operate.equals("system-start") ) {
                if ( UserMonitor.getUserMonitor(request).isAdministor() ) {
                    UserMonitor.setConfigStatusByType(SystemConfig.CONFIG_TYPE_SYSTEM, SystemConfig.SYSTEM_START);
                    logger.info("[安卓端][开启系统] - " + users);
                    jsonObject.put("operate_status", "success");
                } else {
                    jsonObject.put("operate_status", "failed");
                    jsonObject.put("explain", "permission denied");
                }
            }
        }
        response.getWriter().print(jsonObject.toString());
    }
}
