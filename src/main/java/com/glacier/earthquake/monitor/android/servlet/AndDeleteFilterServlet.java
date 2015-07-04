package com.glacier.earthquake.monitor.android.servlet;

import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
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
public class AndDeleteFilterServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(AndDeleteFilterServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        String type = request.getParameter("type");
        String users = UserMonitor.getUserMonitor(request).getUsername();
        JSONObject jsonObject = new JSONObject();

        String filter_id = request.getParameter("filter_id");
        if ( type != null && type.equals("disaster") ) {
            if (UserMonitor.getUserMonitor(request).getFilterRuleMonitor().delRuleDisaster(Integer.parseInt(filter_id))) {
                logger.info("[安卓端][删除规则] - " + users + " 删除disaster规则ID: " + filter_id);
                jsonObject.put("status", "success");
            } else {
                logger.info("[安卓端][删除失败] - " + users + " 删除disaster规则ID: " + filter_id);
                jsonObject.put("status", "failed");
                jsonObject.put("explain", "permission denied");
            }
        } else if ( type != null && type.equals("public") ) {
            if (UserMonitor.getUserMonitor(request).getFilterRuleMonitor().delRulePubSentiment(Integer.parseInt(filter_id))) {
                logger.info("[安卓端][删除规则] - " + users + " 删除public规则ID: " + filter_id);
                jsonObject.put("status", "success");
            } else {
                logger.info("[安卓端][删除失败] - " + users + " 删除public规则ID: " + filter_id);
                jsonObject.put("status", "failed");
                jsonObject.put("explain", "permission denied");
            }
        } else if ( type != null && type.equals("whitelist") ) {
            if (UserMonitor.getUserMonitor(request).getFilterRuleMonitor().delRuleWhiteList(Integer.parseInt(filter_id))) {
                logger.info("[安卓端][删除规则] - " + users + " 删除whitelist规则ID: " + filter_id);
                jsonObject.put("status", "success");
            } else {
                logger.info("[安卓端][删除失败] - " + users + " 删除whitelist规则ID: " + filter_id);
                jsonObject.put("status", "failed");
                jsonObject.put("explain", "permission denied");
            }
        }
        response.getWriter().print(jsonObject.toString());
    }

}