package com.glacier.earthquake.monitor.android.servlet;

import com.glacier.earthquake.monitor.browser.util.SpiderInfoUtils;
import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.SpiderInfo;
import com.glacier.earthquake.monitor.server.pojo.SystemConfig;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by glacier on 15-7-4.
 */
public class AndDataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        JSONArray jsonArray = new JSONArray();

        //0表示未通过审核 1表示已通过审核;
        List<SpiderInfo> spiderInfos_0 = UserMonitor.getUserMonitor(request).getSpiderInfoMonitor().getSpiderInfo_Status(0);
        List<SpiderInfo> spiderInfos_1 = UserMonitor.getUserMonitor(request).getSpiderInfoMonitor().getSpiderInfo_Status(1);

        List<SpiderInfo> spiderInfos = new LinkedList<SpiderInfo>();
        //如果审核模块关闭或者当前用户是管理员的话  可以查看未通过审核的信息
        if ( spiderInfos_0 != null && (UserMonitor.getConfigStatusByType(SystemConfig.CONFIG_TYPE_EXAMINE) == SystemConfig.EXAMINE_STOP
                || UserMonitor.getUserMonitor(request).isAdministor()) ) {
            spiderInfos.addAll(spiderInfos_0);
        }
        if ( spiderInfos_1 != null ) {
            spiderInfos.addAll(spiderInfos_1);
        }

        Collections.sort(spiderInfos, new Comparator<SpiderInfo>() {
            public int compare(SpiderInfo o1, SpiderInfo o2) {
                return new Long(o2.getExamine_date().getTime() - o1.getExamine_date().getTime()).intValue();
            }
        });
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int index = 1;
        for ( SpiderInfo spiderInfo : spiderInfos ) {
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
            jsonObject.put("examiner", SpiderInfoUtils.examinerToString(spiderInfo.getExaminer()));
            jsonObject.put("examinedate", format.format(spiderInfo.getExamine_date()));
            jsonArray.put(jsonObject);
        }
        response.getWriter().print(jsonArray.toString());
    }
}
