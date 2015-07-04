package com.glacier.earthquake.monitor.android.servlet;

import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.SystemConfig;
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
public class AndExamineSwitchServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(AndExamineSwitchServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        String users = UserMonitor.getUserMonitor(request).getUsername();
        JSONObject jsonObject = new JSONObject();

        String operate = request.getParameter("operate");
        if ( operate != null ) {
            if ( UserMonitor.getUserMonitor(request).isAdministor() ) {     //判断权限
                if ( UserMonitor.getConfigStatusByType(SystemConfig.CONFIG_TYPE_EXAMINE) == SystemConfig.EXAMINE_START ) {  //判断当前状态
                    if ( operate.equals("0") ) {  //判断用户操作 0表示用户想要关闭系统
                        UserMonitor.setConfigStatusByType(SystemConfig.CONFIG_TYPE_EXAMINE, SystemConfig.EXAMINE_STOP);
                        logger.info("[安卓端][审核模块] - " + users + " 审核模块已被关闭");
                        jsonObject.put("status", "success");
                        jsonObject.put("explain", "examine is stoping");
                    }
                    else {
                        logger.info("[安卓端][审核模块] - " + users + " 非法操作");
                        jsonObject.put("status", "failed");
                        jsonObject.put("explain", "examine is stoped");
                    }
                } else if ( UserMonitor.getConfigStatusByType(SystemConfig.CONFIG_TYPE_EXAMINE) == SystemConfig.EXAMINE_STOP ) {
                    if ( operate.equals("1") ) {
                        UserMonitor.setConfigStatusByType(SystemConfig.CONFIG_TYPE_EXAMINE, SystemConfig.EXAMINE_START);
                        logger.info("[安卓端][审核模块] - " + users + " 审核模块已被开启");
                        jsonObject.put("status", "success");
                        jsonObject.put("explain", "examine is starting");
                    } else {
                        logger.info("[安卓端][审核模块] - " + users + " 非法操作");
                        jsonObject.put("status", "failed");
                        jsonObject.put("explain", "examine is started");
                    }
                }
            } else {
                jsonObject.put("status", "failed");
                jsonObject.put("explain", "permission denied");
                logger.info("[安卓端][审核模块] - " + users + " 没有权限");
            }
        }
        response.getWriter().print(jsonObject.toString());
    }
}
