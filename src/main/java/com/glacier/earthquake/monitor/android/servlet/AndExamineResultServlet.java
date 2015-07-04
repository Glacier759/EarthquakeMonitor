package com.glacier.earthquake.monitor.android.servlet;

import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.FilterWhiteList;
import com.glacier.earthquake.monitor.server.pojo.SpiderInfo;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by glacier on 15-7-4.
 */
public class AndExamineResultServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(AndExamineResultServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        String users = UserMonitor.getUserMonitor(request).getUsername();
        JSONArray jsonArray = new JSONArray();

        if ( !UserMonitor.getUserMonitor(request).isAdministor() ) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", "failed");
            jsonObject.put("explain", "permission denied");
            response.getWriter().print(jsonObject.toString());
            return;
        }
        String[] id_array = request.getParameterValues("check");
        String op = request.getParameter("type");
        if ( id_array != null && op != null ) {
            int flag = 1;
            UserMonitor monitor = UserMonitor.getUserMonitor(request);
            for ( String id : id_array ) {
                JSONObject jsonObject = new JSONObject();
                try {
                    SpiderInfo spiderInfo = monitor.getSpiderInfoMonitor().getSpiderInfoByID(Integer.parseInt(id));
                    spiderInfo.setExaminer(monitor.getUsername());
                    spiderInfo.setExamine_date(new Date());
                    if (op.equals("0")) {
                        FilterWhiteList whiteList = new FilterWhiteList();
                        whiteList.setUrl(spiderInfo.getUrl());
                        whiteList.setSubmiter(monitor.getUsername());
                        monitor.getFilterRuleMonitor().addRuleWhiteList(whiteList);
                        if (monitor.getSpiderInfoMonitor().deleteSpiderInfo(Integer.parseInt(id))) {
                            flag *= 1;
                            logger.info("[审核淘汰] - " + users + "  id: " + id);
                            jsonObject.put("status", "success");
                            jsonObject.put("id", id);
                            jsonObject.put("explain", "weed out");
                        } else {
                            flag *= 0;
                            logger.error("[审核失败] - " + users + "  id: " + id);
                            jsonObject.put("status", "failed");
                            jsonObject.put("id", id);
                            jsonObject.put("explain", "delete error");
                        }
                    } else if (op.equals("1")) {
                        if (monitor.getSpiderInfoMonitor().approvedThrough(spiderInfo)) {
                            flag *= 1;
                            logger.info("[审核通过] - " + users + "  id: " + id);
                            jsonObject.put("status", "success");
                            jsonObject.put("id", id);
                            jsonObject.put("explain", "pass");
                        } else {
                            flag *= 0;
                            logger.error("[审核失败] - " + users + "  id: " + id);
                            jsonObject.put("status", "failed");
                            jsonObject.put("id", id);
                            jsonObject.put("explain", "update error");
                        }
                    }
                } catch (Exception e) {
                    flag *= 0;
                    logger.info("[审核异常] - " + users + " " + id + " 审核时出现异常");
                    jsonObject.put("status", "failed");
                    jsonObject.put("id", id);
                    jsonObject.put("explain", e.toString());
                } finally {
                    jsonArray.put(jsonObject);
                }
            }
        }
        response.getWriter().print(jsonArray.toString());
    }

}
