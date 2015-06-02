package com.glacier.earthquake.monitor.browser.servlet;

import com.glacier.earthquake.monitor.browser.util.UserUtils;
import com.glacier.earthquake.monitor.server.configure.user.FilterRuleMonitor;
import com.glacier.earthquake.monitor.server.configure.user.UserMonitor;
import com.glacier.earthquake.monitor.server.pojo.*;
import com.glacier.earthquake.monitor.server.util.Data2Object;
import com.glacier.earthquake.monitor.server.util.Object2Data;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
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
        request.setCharacterEncoding("utf-8");
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
                } else if ( type != null && type.equals("whitelist") ) {
                    List<FilterWhiteList> filters = UserMonitor.getUserMonitor(request).getFilterRuleMonitor().getRuleWhiteLists();
                    if ( filters != null ) {
                        for ( int index = 0; index < filters.size(); index ++ ) {
                            FilterWhiteList filter = filters.get(index);
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("create_time", format.format(filter.getDate()));
                            jsonObject.put("url", filter.getUrl());
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
                } else if ( type != null && type.equals("whitelist") ) {
                    if (UserMonitor.getUserMonitor(request).getFilterRuleMonitor().delRuleWhiteList(Integer.parseInt(filter_id))) {
                        logger.info("[删除规则] - 删除whitelist规则ID: " + filter_id);
                    } else {
                        logger.info("[删除失败] - 删除whitelist规则ID: " + filter_id);
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
                                f = new String(f.getBytes("iso-8859-1"),"UTF-8");
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
                                String filter_name = new String(filter_names[index].getBytes("iso-8859-1"),"UTF-8");
                                String filter_matcher = new String(filter_matchers[index].getBytes("iso-8859-1"),"UTF-8");
                                String filter_unexist = new String(filter_unexists[index].getBytes("iso-8859-1"),"UTF-8");
                                try {
                                    if (filter_name.length() < 1 || filter_matcher.length() < 1 || filter_unexist.length() < 1) {
                                        logger.error("[插入规则] - 插入规则失败: " + filter_name + "\t" + filter_matcher + "\t" + filter_unexist + " [FilterPubSentiment]");
                                        continue;
                                    }
                                    FilterPublicSentiment filterPublicSentiment = new FilterPublicSentiment();
                                    filterPublicSentiment.setName(filter_name);
                                    filterPublicSentiment.setMatcher(filter_matcher);
                                    filterPublicSentiment.setUnexist(filter_unexist);
                                    UserMonitor.getUserMonitor(request).getFilterRuleMonitor().addRulePubSentiment(filterPublicSentiment);
                                    logger.error("[插入规则] - 插入规则成功: " + filter_name + "\t" + filter_matcher + "\t" + filter_unexist + " [FilterPubSentiment]");
                                } catch (Exception e) {
                                    logger.error("[插入规则] - 插入规则成功: " + filter_name + "\t" + filter_matcher + "\t" + filter_unexist + " [FilterPubSentiment]");
                                }
                            }
                        }
                    } else if ( type != null && type.equals("whitelist") ) {
                        String filters_whitelists[] = request.getParameterValues("whitelist");
                        if ( filters_whitelists != null ) {
                            for (String f : filters_whitelists) {
                                f = new String(f.getBytes("iso-8859-1"),"UTF-8");
                                try {
                                    if (f.length() < 1) {
                                        logger.error("[插入规则] - 插入规则失败: " + f + " [FilterWhiteList]");
                                        continue;
                                    }
                                    FilterWhiteList filterWhiteList = new FilterWhiteList();
                                    filterWhiteList.setUrl(f);
                                    UserMonitor.getUserMonitor(request).getFilterRuleMonitor().addRuleWhiteList(filterWhiteList);
                                    logger.info("[插入规则] - 插入规则成功: " + f + "[FilterWhiteList]");
                                } catch (Exception e) {
                                    logger.error("[插入规则] - 插入规则失败: " + f + " [FilterWhiteList]");
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
                //如果用户没有设置过邮箱
                if ( user.getEmail() == null || user.getEmail().length() == 0) {
                    //首先判断用户传入的是不是邮箱格式
                    if ( UserUtils.isEmail(email) ) {
                        //如果是邮箱 先判断邮箱是否已经被占用
                        if ( UserMonitor.getUserInfoByEmail(email) != null ) {
                            response.getWriter().print("email had");
                            return;
                        } else {
                            //没有被占用的时候才能进行邮箱设置
                            new_user.setEmail(email);
                        }
                    }
                    //如果不是邮箱格式就告知返回
                    else {
                        response.getWriter().print("email format error");
                        return;
                    }
                }
                //如果用户设置过邮箱并且和本次传递进来的参数一致
                else {
                    if ( user.getEmail().equals(email) ) {
                        new_user.setEmail(email);
                    }
                    else {
                        //说明用户已经绑定过邮箱
                        response.getWriter().print("email binding");
                        return;
                    }
                }

                String mobile = request.getParameter("mobile");
                System.out.println("UserMobile = " + user.getMobile());
                if ( user.getMobile() == null || user.getMobile().length() == 0) {
                    //首先判断用户传入的是不是手机格式
                    if ( UserUtils.isMobile(mobile) ) {
                        //如果是手机 先判断手机是否已经被占用
                        if ( UserMonitor.getUserInfoByMobile(mobile) != null ) {
                            response.getWriter().print("mobile had");
                            return;
                        } else {
                            //没有被占用的时候才能进行手机设置
                            new_user.setMobile(mobile);
                        }
                    }
                    //如果不是手机格式就告知返回
                    else {
                        response.getWriter().print("mobile format error");
                        return;
                    }
                }
                //如果用户设置过手机并且和本次传递进来的参数一致
                else {
                    if ( user.getMobile().equals(mobile) ) {
                        new_user.setMobile(mobile);
                    }
                    else {
                        //说明用户已经绑定过手机
                        response.getWriter().print("mobile binding");
                        return;
                    }
                }
                new_user.setNickname(request.getParameter("nickname"));
                new_user.setRealname(request.getParameter("realname"));
                new_user.setQqnumber(request.getParameter("qqnumber"));
                new_user.setPosition(request.getParameter("position"));
                new_user.setWorkplace(request.getParameter("workplace"));

                if ( new_user.getEmail().equals("null") || new_user.getNickname().equals("null") || new_user.getMobile().equals("null")
                        || new_user.getQqnumber().equals("null") || new_user.getPosition().equals("null") || new_user.getRealname().equals("null")
                        || new_user.getWorkplace().equals("null") ) {
                    response.getWriter().print("userinfo not full");
                    return;
                }

                new_user.setUid(user.getUid());
                new_user.setPassword(user.getPassword());
                new_user.setPrivilege(user.getPrivilege());
                UserMonitor.getUserMonitor(user).modifyUserInfo(new_user);
                logger.info("[修改资料] - " + new_user.toString());
                request.getSession().setAttribute("login_user", new_user);
                response.getWriter().print("success");
                if ( UserMonitor.getUserMonitor(request).hasFullInfo() ) {
                    request.getSession().setAttribute("userinfo", "y");
                }
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
                        request.getSession().setAttribute("login_user", UserMonitor.getUserInfoByUID(user.getUid()));
                        response.getWriter().print("success");
                    }
                }
            } else if ( operate.equals("examine") ) {
                JSONArray jsonArray = new JSONArray();
                if ( UserMonitor.getUserMonitor(request).isAdministor() ) {
                    List<SpiderInfo> spiderInfos = UserMonitor.getUserMonitor(request).getSpiderInfoMonitor().getSpiderInfo_Status(0);
                    if (spiderInfos != null) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        for (SpiderInfo spiderInfo : spiderInfos) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("id", spiderInfo.getId());
                            jsonObject.put("url", spiderInfo.getUrl());
                            jsonObject.put("title", spiderInfo.getTitle());
                            jsonObject.put("crawldate", format.format(spiderInfo.getCreate_date()));
                            jsonArray.put(jsonObject);
                        }
                    }
                }
                response.getWriter().print(jsonArray.toString());
            } else if ( operate.equals("spiderinfo") ) {
                String id = request.getParameter("id");
                if ( id != null ) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    SpiderInfo spiderInfo = UserMonitor.getUserMonitor(request).getSpiderInfoMonitor().getSpiderInfoByID(Integer.parseInt(id));
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", spiderInfo.getId());
                    jsonObject.put("url", spiderInfo.getUrl());
                    jsonObject.put("title", spiderInfo.getTitle());
                    jsonObject.put("crawldate", format.format(spiderInfo.getCreate_date()));
                    //jsonObject.put("source", spiderInfo.getSource());
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
            } else if ( operate.equals("examine-ok") ) {
                if ( !UserMonitor.getUserMonitor(request).isAdministor() ) {
                    response.getWriter().print("permission denied");
                }
                String[] id_array = request.getParameterValues("check");
                if ( id_array != null ) {
                    int flag = 1;
                    for ( String id : id_array ) {
                        try {
                            if (UserMonitor.getUserMonitor(request).getSpiderInfoMonitor().approvedThrough(Integer.parseInt(id))) {
                                flag *= 1;
                                logger.info("[审核通过] - id: " + id);
                            } else {
                                flag *= 0;
                                logger.error("[审核失败] - id: " + id);
                            }
                        }catch (Exception e) {
                            flag *= 0;
                            logger.error("[审核失败] - id: " + id);
                        }
                    }
                    if ( flag == 1 ) {
                        response.getWriter().print("success");
                    } else {
                        response.getWriter().print("wrong");
                    }
                }
            } else if ( operate.equals("system") ) {
                int status = UserMonitor.getConfigStatusByType(SystemConfig.CONFIG_TYPE_SYSTEM);
                if ( status == SystemConfig.SYSTEM_START ) {
                    response.getWriter().print("starting");
                } else if ( status == SystemConfig.SYSTEM_STOP ) {
                    response.getWriter().print("stoping");
                }
            } else if ( operate.equals("system-stop") ) {
                if ( UserMonitor.getUserMonitor(request).isAdministor() ) {
                    UserMonitor.setConfigStatusByType(SystemConfig.CONFIG_TYPE_SYSTEM, SystemConfig.SYSTEM_STOP);
                    logger.info("[关闭系统]");
                    response.getWriter().print("success");
                } else {
                    response.getWriter().print("permission denied");
                }
            } else if ( operate.equals("system-start") ) {
                if ( UserMonitor.getUserMonitor(request).isAdministor() ) {
                    UserMonitor.setConfigStatusByType(SystemConfig.CONFIG_TYPE_SYSTEM, SystemConfig.SYSTEM_START);
                    logger.info("[开启系统]");
                    response.getWriter().print("success");
                } else {
                    response.getWriter().print("permission denied");
                }
            } else if ( operate.equals("showdata") ) {
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

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for ( SpiderInfo spiderInfo : spiderInfos ) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", spiderInfo.getId());
                    jsonObject.put("url", spiderInfo.getUrl());
                    jsonObject.put("title", spiderInfo.getTitle());
                    jsonObject.put("type", spiderInfo.getType());
                    jsonObject.put("crawldate", format.format(spiderInfo.getCreate_date()));
                    jsonArray.put(jsonObject);
                }
                response.getWriter().print(jsonArray.toString());
            } else if ( operate.equals("switch") ) {
                String value = request.getParameter("value");
                if ( value != null ) {
                    if ( UserMonitor.getUserMonitor(request).isAdministor() ) {     //判断权限
                        if ( UserMonitor.getConfigStatusByType(SystemConfig.CONFIG_TYPE_EXAMINE) == SystemConfig.EXAMINE_START ) {  //判断当前状态
                            if ( value.equals("0") ) {  //判断用户操作 0表示用户想要关闭系统
                                UserMonitor.setConfigStatusByType(SystemConfig.CONFIG_TYPE_EXAMINE, SystemConfig.EXAMINE_STOP);
                                logger.info("[审核模块] - 审核模块已被关闭");
                                response.getWriter().print("examine stop");
                            }
                            else {
                                logger.info("[审核模块] - 非法操作");
                                response.getWriter().print("wrong start");
                            }
                        } else if ( UserMonitor.getConfigStatusByType(SystemConfig.CONFIG_TYPE_EXAMINE) == SystemConfig.EXAMINE_STOP ) {
                            if ( value.equals("1") ) {
                                UserMonitor.setConfigStatusByType(SystemConfig.CONFIG_TYPE_EXAMINE, SystemConfig.EXAMINE_START);
                                logger.info("[审核模块] - 审核模块已被开启");
                                response.getWriter().print("examine start");
                            } else {
                                logger.info("[审核模块] - 非法操作");
                                response.getWriter().print("wrong stop");
                            }
                        }
                    } else {
                        response.getWriter().print("permission denied");
                        logger.info("[审核模块] - 没有权限");
                    }
                }
            } else if ( operate.equals("deluser") ) {
                String uid = request.getParameter("uid");
                if ( uid != null ) {
                    User del_user = UserMonitor.getUserInfoByUID(Integer.parseInt(uid));
                    if ( del_user != null ) {
                        //root用户不允许删除
                        if ( del_user.getPrivilege() == UserMonitor.USER_ROOT ) {
                            response.getWriter().print("not allow");
                            //如果登录用户是root 那么非root用户都可以删除
                        } else if ( del_user.getPrivilege() <= UserMonitor.USER_ADMINISTATOR && UserMonitor.getUserMonitor(request).isROOT() ) {
                            UserMonitor.getUserMonitor(request).delUser(del_user);
                            response.getWriter().print("success");
                            //如果登录用户是普通管理员 并且 待删用户为普通用户 则可以删除
                        } else if ( UserMonitor.getUserMonitor(request).isAdministor() && del_user.getPrivilege() < UserMonitor.USER_ADMINISTATOR ) {
                            UserMonitor.getUserMonitor(request).delUser(del_user);
                            response.getWriter().print("success");
                        } else if ( !UserMonitor.getUserMonitor(request).isAdministor() ) {
                            response.getWriter().print("permission denied");
                        } else if ( del_user.getPrivilege() == UserMonitor.USER_ADMINISTATOR ) {
                            response.getWriter().print("permission denied");
                        }
                        else {
                            response.getWriter().print("wrong");
                        }
                    }
                }
            } else if ( operate.equals("manage") ) {
                String uid = request.getParameter("uid");
                if ( uid != null ) {
                    User manage_user = UserMonitor.getUserInfoByUID(Integer.parseInt(uid));
                    if ( manage_user != null ) {
                        if ( UserMonitor.getUserMonitor(request).isROOT() ) {
                            if ( manage_user.getPrivilege() == UserMonitor.USER_ORDINARY ) {
                                if ( UserMonitor.getUserMonitor(request).setManage(manage_user) ) {
                                    response.getWriter().print("success");
                                } else {
                                    response.getWriter().print("permission denied");
                                }
                            }
                            else if ( manage_user.getPrivilege() == UserMonitor.USER_ADMINISTATOR ) {
                                response.getWriter().print("is admin");
                            }
                            else if ( manage_user.getPrivilege() == UserMonitor.USER_ROOT ) {
                                response.getWriter().print("not allow");
                            }
                        }
                        else {
                            response.getWriter().print("permission denied");
                        }
                    }
                }
            }
        }
    }
}
