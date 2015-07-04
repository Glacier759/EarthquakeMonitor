package com.glacier.earthquake.monitor.android.servlet;

import com.glacier.earthquake.monitor.server.configure.user.FilterRuleMonitor;
import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.FilterPublicSentiment;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by glacier on 15-7-4.
 */
public class AndModifyFilterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        String type = request.getParameter("type");
        JSONObject jsonObject = new JSONObject();

        if ( !UserMonitor.getUserMonitor(request).isAdministor() ) {
            jsonObject.put("status", "failed");
            jsonObject.put("explain", "permission denied");
        }
        else if ( type != null ) {
            if (type.equals("public")) {
                String name = request.getParameter("filter-name");
                String matcher = request.getParameter("filter-matcher");
                String unexist = request.getParameter("filter-unexist");
                String id = request.getParameter("filter-id");
                FilterRuleMonitor monitor = UserMonitor.getUserMonitor(request).getFilterRuleMonitor();
                if (name != null && matcher != null && unexist != null && id != null) {
                    FilterPublicSentiment filter = monitor.getFilterPubSentimentByID(Integer.parseInt(id));
                    if (filter == null) {
                        jsonObject.put("status", "failed");
                        jsonObject.put("explain", "filter is null");
                    } else {
                        filter.setName(name);
                        filter.setUnexist(unexist);
                        filter.setMatcher(matcher);
                        if (monitor.setRulePubSentiment(filter)) {
                            jsonObject.put("status", "success");
                        } else {
                            jsonObject.put("status", "failed");
                            jsonObject.put("explain", "update error");
                        }
                    }
                } else {
                    jsonObject.put("status", "failed");
                    jsonObject.put("explain", "has null field");
                }
            } else if (type.equals("disaster")) {
                String name = request.getParameter("filter-name");
                String id = request.getParameter("filter-id");
                FilterRuleMonitor monitor = UserMonitor.getUserMonitor(request).getFilterRuleMonitor();
                if (name != null && id != null) {
                    FilterDisaster filter = monitor.getFilterDisasterByID(Integer.parseInt(id));
                    if (filter == null) {
                        jsonObject.put("status", "failed");
                        jsonObject.put("explain", "filter is null");
                    } else {
                        filter.setFilterRule(name);
                        if (monitor.setRuleDisaster(filter)) {
                            jsonObject.put("status", "success");
                        } else {
                            jsonObject.put("status", "failed");
                            jsonObject.put("explain", "update error");
                        }
                    }
                }
            }
        }
        response.getWriter().print(jsonObject.toString());
    }

}
