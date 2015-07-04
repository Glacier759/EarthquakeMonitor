package com.glacier.earthquake.monitor.android.servlet;

import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.FilterPublicSentiment;
import com.glacier.earthquake.monitor.server.pojo.FilterWhiteList;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by glacier on 15-7-4.
 */
public class AndAddFilterServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(AndAddFilterServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        String type = request.getParameter("type");
        String users = UserMonitor.getUserMonitor(request).getUsername();
        JSONArray jsonArray = new JSONArray();

        if ( !UserMonitor.getUserMonitor(request).isAdministor() ) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", "failed");
            jsonObject.put("explain", "permission denied");
            jsonArray.put(jsonObject);
        }
        else if ( type != null ) {
            if ( type.equals("disaster") ) {
                String filters[] = request.getParameterValues("filter");
                if ( filters != null ) {
                    for (String f : filters) {
                        JSONObject jsonObject = new JSONObject();
                        //f = new String(f.getBytes("iso-8859-1"),"UTF-8");
                        try {
                            if (f.length() < 1) {
                                jsonObject.put("status", "failed");
                                jsonObject.put("filter", f);
                                jsonObject.put("explain", "length < 1");
                                logger.error("[安卓端][插入规则] - 插入规则失败: " + f + " [FilterDisaster]");
                                continue;
                            }
                            FilterDisaster filterDisaster = new FilterDisaster();
                            filterDisaster.setFilterRule(f);
                            filterDisaster.setSubmiter(UserMonitor.getUserMonitor(request).getUsername());
                            UserMonitor.getUserMonitor(request).getFilterRuleMonitor().addRuleDisaster(filterDisaster);
                            logger.info("[安卓端][插入规则] - " + users + " 插入规则成功: " + f + "[FilterDisaster]");
                            jsonObject.put("status", "success");
                            jsonObject.put("filter", f);
                        } catch (Exception e) {
                            jsonObject.put("status", "failed");
                            jsonObject.put("filter", f);
                            jsonObject.put("explain", e.toString());
                            logger.error("[安卓端][插入规则] - " + users + " 插入规则失败: " + f + " [FilterDisaster]");
                        } finally {
                            jsonArray.put(jsonObject);
                        }
                    }
                }
            } else if ( type.equals("public") ) {
                String[] filter_names = request.getParameterValues("filter-name");
                String[] filter_matchers = request.getParameterValues("filter-matcher");
                String[] filter_unexists = request.getParameterValues("filter-unexist");
                if ( filter_names != null && filter_matchers != null && filter_unexists != null ) {
                    for (int index = 0; index < filter_names.length; index++) {
                        JSONObject jsonObject = new JSONObject();
//                                String filter_name = new String(filter_names[index].getBytes("iso-8859-1"),"UTF-8");
//                                String filter_matcher = new String(filter_matchers[index].getBytes("iso-8859-1"),"UTF-8");
//                                String filter_unexist = new String(filter_unexists[index].getBytes("iso-8859-1"),"UTF-8");
                        String filter_name = filter_names[index];
                        String filter_matcher = filter_matchers[index];
                        String filter_unexist = filter_unexists[index];
                        try {
                            if (filter_name.length() < 1 || filter_matcher.length() < 1 || filter_unexist.length() < 1) {
                                jsonObject.put("status", "failed");
                                jsonObject.put("filter_name", filter_name);
                                jsonObject.put("filter_matcher", filter_matcher);
                                jsonObject.put("filter_unexist", filter_unexist);
                                jsonObject.put("explain", "length < 1");
                                logger.error("[安卓端][插入规则] - " + users + " 插入规则失败: " + filter_name + "\t" + filter_matcher + "\t" + filter_unexist + " [FilterPubSentiment]");
                                continue;
                            }
                            FilterPublicSentiment filterPublicSentiment = new FilterPublicSentiment();
                            filterPublicSentiment.setName(filter_name);
                            filterPublicSentiment.setMatcher(filter_matcher);
                            filterPublicSentiment.setUnexist(filter_unexist);
                            filterPublicSentiment.setSubmiter(UserMonitor.getUserMonitor(request).getUsername());
                            UserMonitor.getUserMonitor(request).getFilterRuleMonitor().addRulePubSentiment(filterPublicSentiment);
                            logger.info("[安卓端][插入规则] - " + users + " 插入规则成功: " + filter_name + "\t" + filter_matcher + "\t" + filter_unexist + " [FilterPubSentiment]");
                            jsonObject.put("status", "success");
                            jsonObject.put("filter_name", filter_name);
                            jsonObject.put("filter_matcher", filter_matcher);
                            jsonObject.put("filter_unexist", filter_unexist);
                        } catch (Exception e) {
                            jsonObject.put("status", "failed");
                            jsonObject.put("filter_name", filter_name);
                            jsonObject.put("filter_matcher", filter_matcher);
                            jsonObject.put("filter_unexist", filter_unexist);
                            jsonObject.put("explain", e.toString());
                            logger.error("[安卓端][插入规则] - " + users + " 插入规则成功: " + filter_name + "\t" + filter_matcher + "\t" + filter_unexist + " [FilterPubSentiment]");
                        } finally {
                            jsonArray.put(jsonObject);
                        }
                    }
                }
            } else if ( type.equals("whitelist") ) {
                String filters_whitelists[] = request.getParameterValues("whitelist");
                if ( filters_whitelists != null ) {
                    for (String f : filters_whitelists) {
                        JSONObject jsonObject = new JSONObject();
                        //f = new String(f.getBytes("iso-8859-1"),"UTF-8");
                        try {
                            if (f.length() < 1) {
                                jsonObject.put("status", "failed");
                                jsonObject.put("filter", f);
                                jsonObject.put("explain", "length < 1");
                                logger.error("[安卓端][插入规则] - " + users + " 插入规则失败: " + f + " [FilterWhiteList]");
                                continue;
                            }
                            FilterWhiteList filterWhiteList = new FilterWhiteList();
                            filterWhiteList.setUrl(f);
                            filterWhiteList.setSubmiter(UserMonitor.getUserMonitor(request).getUsername());
                            UserMonitor.getUserMonitor(request).getFilterRuleMonitor().addRuleWhiteList(filterWhiteList);
                            logger.info("[安卓端][插入规则] - " + users + " 插入规则成功: " + f + "[FilterWhiteList]");
                            jsonObject.put("status", "success");
                            jsonObject.put("filter", f);
                        } catch (Exception e) {
                            jsonObject.put("status", "failed");
                            jsonObject.put("filter", f);
                            jsonObject.put("explain", e.toString());
                            logger.error("[安卓端][插入规则] - " + users + " 插入规则失败: " + f + " [FilterWhiteList]");
                        } finally {
                            jsonArray.put(jsonObject);
                        }
                    }
                }
            }
        }
        response.getWriter().print(jsonArray.toString());
    }
}
