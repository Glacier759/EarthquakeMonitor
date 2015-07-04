package com.glacier.earthquake.monitor.android.servlet;

import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.crawler.core.Scheduler;
import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.FilterPublicSentiment;
import com.glacier.earthquake.monitor.server.pojo.SpiderInfo;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by glacier on 15-7-4.
 */
public class AndExamineFilterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id");
        if ( id != null ) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SpiderInfo spiderInfo = UserMonitor.getUserMonitor(request).getSpiderInfoMonitor().getSpiderInfoByID(Integer.parseInt(id));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", spiderInfo.getId());
            jsonObject.put("url", spiderInfo.getUrl());
            jsonObject.put("title", spiderInfo.getTitle());
            jsonObject.put("crawldate", format.format(spiderInfo.getCreate_date()));
            if ( spiderInfo.getOrigin() == Scheduler.SERVICE_WEIBO_SEARCH ) {
                jsonObject.put("source", spiderInfo.getSource());
            }
            jsonObject.put("status", spiderInfo.getStatus());

            if ( SpiderInfo.FILTER_DISASTER == spiderInfo.getType() ) {
                FilterDisaster filter = UserMonitor.getUserMonitor(request).getFilterRuleMonitor().getFilterDisasterByID(spiderInfo.getRule_id());
                jsonObject.put("type", "disaster");
                jsonObject.put("rule", filter.getFilterRule());
            } else if ( SpiderInfo.FILTER_PUBSENTIMENT == spiderInfo.getType() ) {
                FilterPublicSentiment filter = UserMonitor.getUserMonitor(request).getFilterRuleMonitor().getFilterPubSentimentByID(spiderInfo.getRule_id());
                jsonObject.put("type", "public");
                jsonObject.put("name", filter.getName());
                jsonObject.put("matcher", filter.getMatcher());
                jsonObject.put("unexist", filter.getUnexist());
            }
            response.getWriter().print(jsonObject.toString());
        }
    }
}
