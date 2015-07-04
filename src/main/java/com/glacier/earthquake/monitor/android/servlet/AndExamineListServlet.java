package com.glacier.earthquake.monitor.android.servlet;

import com.glacier.earthquake.monitor.browser.util.SpiderInfoUtils;
import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.SpiderInfo;
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
public class AndExamineListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        JSONArray jsonArray = new JSONArray();
        if ( UserMonitor.getUserMonitor(request).isAdministor() ) {
            List<SpiderInfo> spiderInfos = UserMonitor.getUserMonitor(request).getSpiderInfoMonitor().getSpiderInfo_Status(0);
            if (spiderInfos != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                int index = 1;
                for (SpiderInfo spiderInfo : spiderInfos) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("number", index ++);
                    jsonObject.put("id", spiderInfo.getId());
                    jsonObject.put("url", spiderInfo.getUrl());
                    jsonObject.put("source", spiderInfo.getSource());
                    jsonObject.put("title", SpiderInfoUtils.titleByOrigin(spiderInfo.getOrigin(), spiderInfo.getTitle()));
                    jsonObject.put("type", spiderInfo.getType());
                    jsonObject.put("crawldate", format.format(spiderInfo.getCreate_date()));
                    jsonObject.put("pagedate", format.format(spiderInfo.getPage_date()));
                    jsonObject.put("origin", SpiderInfoUtils.originToString(spiderInfo.getOrigin()));
                    jsonObject.put("status", SpiderInfoUtils.statusToString(spiderInfo.getStatus()));
                    jsonArray.put(jsonObject);
                }
            }
        }
        response.getWriter().print(jsonArray.toString());
    }
}
