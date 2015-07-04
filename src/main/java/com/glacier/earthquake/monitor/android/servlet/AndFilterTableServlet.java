package com.glacier.earthquake.monitor.android.servlet;

import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.FilterPublicSentiment;
import com.glacier.earthquake.monitor.server.pojo.FilterWhiteList;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by glacier on 15-7-4.
 */
public class AndFilterTableServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        String type = request.getParameter("type");

        if ( type != null ) {
            JSONArray jsonArray = new JSONArray();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if ( type.equals("disaster") ) {
                List<FilterDisaster> filters = UserMonitor.getUserMonitor(request).getFilterRuleMonitor().getRuleDisasterList();
                if (filters != null) {
                    for (FilterDisaster filter : filters) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("create_time", format.format(filter.getCreateDate()));
                        jsonObject.put("rule", filter.getFilterRule());
                        jsonObject.put("id", filter.getId());
                        if (filter.getSubmiter() == null) {
                            jsonObject.put("submiter", "未知");
                        } else {
                            jsonObject.put("submiter", filter.getSubmiter());
                        }
                        jsonArray.put(jsonObject);
                    }
                }
            } else if ( type.equals("public") ) {
                List<FilterPublicSentiment> filters = UserMonitor.getUserMonitor(request).getFilterRuleMonitor().getRulePubSentimentList();
                if ( filters != null ) {
                    for (FilterPublicSentiment filter : filters) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("create_time", format.format(filter.getCreateDate()));
                        jsonObject.put("name", filter.getName());
                        jsonObject.put("matcher", filter.getMatcher());
                        jsonObject.put("unexist", filter.getUnexist());
                        jsonObject.put("id", filter.getId());
                        if (filter.getSubmiter() == null) {
                            jsonObject.put("submiter", "未知");
                        } else {
                            jsonObject.put("submiter", filter.getSubmiter());
                        }
                        jsonArray.put(jsonObject);
                    }
                }
            } else if ( type.equals("whitelist") ) {
                List<FilterWhiteList> filters = UserMonitor.getUserMonitor(request).getFilterRuleMonitor().getRuleWhiteLists();
                if ( filters != null ) {
                    for (FilterWhiteList filter : filters) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("create_time", format.format(filter.getDate()));
                        jsonObject.put("url", filter.getUrl());
                        jsonObject.put("id", filter.getId());
                        if (filter.getSubmiter() == null) {
                            jsonObject.put("submiter", "未知");
                        } else {
                            jsonObject.put("submiter", filter.getSubmiter());
                        }
                        jsonArray.put(jsonObject);
                    }
                }
            }
            response.getWriter().print(jsonArray.toString());
        }
    }
}
