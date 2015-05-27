package com.glacier.earthquake.monitor.browser.servlet;

import com.glacier.earthquake.monitor.browser.util.UserUtils;
import com.glacier.earthquake.monitor.server.configure.user.FilterRuleMonitor;
import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.FilterDisaster;
import com.glacier.earthquake.monitor.server.pojo.FilterPublicSentiment;
import com.glacier.earthquake.monitor.server.pojo.User;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.Object2Data;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
        String operate = request.getParameter("operate");
        String type = request.getParameter("type");
        response.setContentType("text/html;charset=utf-8");
        if ( operate != null ) {
            if ( operate.equals("table") ) {
                JSONArray jsonArray = new JSONArray();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if ( type != null && type.equals("disaster") ) {
                    List<FilterDisaster> filters = UserMonitor.getUserMonitor(request).getFilterRuleMonitor().getRuleDisasterList();
                    if (filters != null) {
                        for (int index = 0; index < filters.size(); index++) {
                            FilterDisaster filter = filters.get(index);
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("create_time", format.format(filter.getCreateDate()));
                            jsonObject.put("rule", filter.getFilterRule());
                            jsonObject.put("id", filter.getId());
                            jsonArray.put(jsonObject);
                        }
                    }
                } else if ( type != null && type.equals("public") ) {
                    List<FilterPublicSentiment> filters = UserMonitor.getUserMonitor(request).getFilterRuleMonitor().getRulePubSentimentList();
                    if ( filters != null ) {
                        for ( int index = 0; index < filters.size(); index ++ ) {
                            FilterPublicSentiment filter = filters.get(index);
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("create_time", format.format(filter.getCreateDate()));
                            jsonObject.put("name", filter.getName());
                            jsonObject.put("matcher", filter.getMatcher());
                            jsonObject.put("unexist", filter.getUnexist());
                            jsonObject.put("id", filter.getId());
                            jsonArray.put(jsonObject);
                        }
                    }
                }
                response.getWriter().print(jsonArray.toString());
            }
            else if ( operate.equals("delete") ) {
                String filter_id = request.getParameter("filter_id");
                if ( type != null && type.equals("disaster") ) {
                    if (UserMonitor.getUserMonitor(request).getFilterRuleMonitor().delRuleDisaster(Integer.parseInt(filter_id))) {
                        logger.info("[删除规则] - 删除disaster规则ID: " + filter_id);
                    } else {
                        logger.info("[删除失败] - 删除disaster规则ID: " + filter_id);
                        response.getWriter().print("permission denied");
                    }
                } else if ( type != null && type.equals("public") ) {
                    if (UserMonitor.getUserMonitor(request).getFilterRuleMonitor().delRulePubSentiment(Integer.parseInt(filter_id))) {
                        logger.info("[删除规则] - 删除public规则ID: " + filter_id);
                    } else {
                        logger.info("[删除失败] - 删除public规则ID: " + filter_id);
                        response.getWriter().print("permission denied");
                    }
                }
            }
            else if ( operate.equals("addfilter") ) {
                if ( !UserMonitor.getUserMonitor(request).isAdministor() ) {
                    response.getWriter().print("permission denied");
                }
                else {
                    if ( type != null && type.equals("disaster") ) {
                        String filters[] = request.getParameterValues("filter");
                        if ( filters != null ) {
                            for (String f : filters) {
                                try {
                                    if (f.length() < 1) {
                                        logger.error("[插入规则] - 插入规则失败: " + f + " [FilterDisaster]");
                                        continue;
                                    }
                                    FilterDisaster filterDisaster = new FilterDisaster();
                                    filterDisaster.setFilterRule(f);
                                    UserMonitor.getUserMonitor(request).getFilterRuleMonitor().addRuleDisaster(filterDisaster);
                                    logger.info("[插入规则] - 插入规则成功: " + f + "[FilterDisaster]");
                                } catch (Exception e) {
                                    logger.error("[插入规则] - 插入规则失败: " + f + " [FilterDisaster]");
                                }
                            }
                        }
                    } else if ( type != null && type.equals("public") ) {
                        String[] filter_names = request.getParameterValues("filter-name");
                        String[] filter_matchers = request.getParameterValues("filter-matcher");
                        String[] filter_unexists = request.getParameterValues("filter-unexist");
                        if ( filter_names != null && filter_matchers != null && filter_unexists != null ) {
                            for (int index = 0; index < filter_names.length; index++) {
                                String filter_name = filter_names[index];
                                String filter_matcher = filter_matchers[index];
                                String filter_unexist = filter_unexists[index];
                                try {
                                    if (filter_name.length() < 1 || filter_matcher.length() < 1 || filter_unexist.length() < 1) {
                                        logger.error("[插入规则] - 插入规则失败: " + filter_name + "\t" + filter_matcher + "\t" + filter_unexist + " [FilterDisaster]");
                                        continue;
                                    }
                                    FilterPublicSentiment filterPublicSentiment = new FilterPublicSentiment();
                                    filterPublicSentiment.setName(filter_name);
                                    filterPublicSentiment.setMatcher(filter_matcher);
                                    filterPublicSentiment.setUnexist(filter_unexist);
                                    UserMonitor.getUserMonitor(request).getFilterRuleMonitor().addRulePubSentiment(filterPublicSentiment);
                                    logger.error("[插入规则] - 插入规则成功: " + filter_name + "\t" + filter_matcher + "\t" + filter_unexist + " [FilterDisaster]");
                                } catch (Exception e) {
                                    logger.error("[插入规则] - 插入规则成功: " + filter_name + "\t" + filter_matcher + "\t" + filter_unexist + " [FilterDisaster]");
                                }
                            }
                        }
                    }
                }
            }
            else if ( operate.equals("user") ) {
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
            else if ( operate.equals("userinfo") ) {
                User user = (User)request.getSession().getAttribute("login_user");
                User new_user = new User();

                String email = request.getParameter("email");
                if ( user.getEmail() == null || user.getEmail().length() == 0 || user.getEmail().equals(email) ) {
                    if (UserUtils.isEmail(email)) {
                        new_user.setEmail(request.getParameter("email"));
                    } else {
                        response.getWriter().print("email format error");
                        return;
                    }
                } else {
                    new_user.setEmail(user.getEmail());
                    response.getWriter().print("email binding");
                    return;
                }

                String mobile = request.getParameter("mobile");
                if ( user.getMobile() == null || user.getEmail().length() == 0 || user.getMobile().equals(mobile) ) {
                    if ( UserUtils.isMobile(mobile) ) {
                        new_user.setMobile(mobile);
                    } else {
                        response.getWriter().print("mobile format error");
                        return;
                    }
                } else {
                    new_user.setMobile(user.getMobile());
                    response.getWriter().print("mobile binding");
                    return;
                }
                new_user.setNickname(request.getParameter("nickname"));
                new_user.setRealname(request.getParameter("realname"));
                new_user.setQqnumber(request.getParameter("qqnumber"));
                new_user.setPosition(request.getParameter("position"));
                new_user.setWorkplace(request.getParameter("workplace"));
                new_user.setUid(user.getUid());
                new_user.setPassword(user.getPassword());
                new_user.setPrivilege(user.getPrivilege());
                UserMonitor.getUserMonitor(user).modifyUserInfo(new_user);
                logger.info("[修改资料] - " + new_user.toString());
                request.getSession().setAttribute("login_user", new_user);
                response.getWriter().print("success");
            }
            else if ( operate.equals("password") ) {
                User user = (User)request.getSession().getAttribute("login_user");

                String password = request.getParameter("password");
                if ( !user.getPassword().equals(password) ) {
                    response.getWriter().print("wrong password");
                }
                else {
                    String new_password = request.getParameter("new-password");
                    String re_password = request.getParameter("re-password");
                    if ( !new_password.equals(re_password) ) {
                        response.getWriter().print("password not equal");
                    } else {
                        User new_user = new User();
                        new_user.setPassword(new_password);
                        new_user.setUid(user.getUid());
                        UserMonitor.getUserMonitor(user).changePassword(new_user);
                        response.getWriter().print("success");
                    }
                }
            }
        }
    }
}
