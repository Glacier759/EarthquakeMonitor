package com.glacier.earthquake.monitor.browser.servlet;

import com.glacier.earthquake.monitor.server.configure.user.FilterRuleMonitor;
import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.User;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.Object2Data;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by glacier on 15-5-26.
 */
@WebServlet(name = "SettingServlet")
public class SettingServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(SettingServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        response.setContentType("text/html;charset=utf-8");
        if ( type != null ) {
            if ( type.equals("disaster") ) {
                JSONArray jsonArray = new JSONArray();
                List<FilterDisaster> filters = UserMonitor.getUserMonitor(request).getFilterRuleMonitor().getRuleDisasterList();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if ( filters != null ) {
                    for (int index = 0; index < filters.size(); index++) {
                        FilterDisaster filter = filters.get(index);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("create_time", format.format(filter.getCreateDate()));
                        jsonObject.put("rule", filter.getFilterRule());
                        jsonObject.put("id", filter.getId());
                        jsonArray.put(jsonObject);
                    }
                }
                response.getWriter().print(jsonArray.toString());
            }
            else if ( type.equals("delete") ) {
                String filter_id = request.getParameter("filter_id");
                if (UserMonitor.getUserMonitor(request).getFilterRuleMonitor().delRuleDisaster(Integer.parseInt(filter_id))) {
                    logger.info("[删除规则] - 删除规则ID: " + filter_id);
                } else {
                    logger.info("[删除失败] - 删除规则ID: " + filter_id);
                    response.getWriter().print("permission denied");
                }
            }
            else if ( type.equals("addfilter") ) {
                String filters[] = request.getParameterValues("filter");
                if ( !UserMonitor.getUserMonitor(request).isAdministor() ) {
                    response.getWriter().print("permission denied");
                }
                else {
                    for ( String f : filters ) {
                        try {
                            if ( f.length() < 1 ) {
                                continue;
                            }
                            FilterDisaster filterDisaster = new FilterDisaster();
                            filterDisaster.setFilterRule(f);
                            UserMonitor.getUserMonitor(request).getFilterRuleMonitor().addRuleDisaster(filterDisaster);
                            logger.info("[插入规则] - 插入规则成功: " + f + "[FilterDisaster]");
                        }catch (Exception e) {
                            logger.error("[插入规则] - 插入规则失败: " + f + " [FilterDisaster]");
                        }
                    }
                }
            }
            else if ( type.equals("user") ) {
                JSONArray jsonArray = new JSONArray();
                List<User> userList = UserMonitor.getUserMonitor(request).getUserList();
                if ( userList != null ) {
                    for (int index = 0; index < userList.size(); index++) {
                        User user = userList.get(index);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("uid", user.getUid());
                        jsonObject.put("nickname", user.getNickname());
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
    }
}
