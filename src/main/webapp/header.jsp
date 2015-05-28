<%@ page import="com.glacier.earthquake.monitor.browser.util.UserUtils" %>
<%@ page import="com.glacier.earthquake.monitor.server.configure.user.UserMonitor" %>
<%@ page import="com.glacier.earthquake.monitor.server.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if ( session.getAttribute("login") == null || session.getAttribute("login").equals("false") || session.getAttribute("login_user") == null ) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    UserMonitor.logger.info("[Cookie] - 执行到Cookie这里了");
                    String username = cookie.getValue();
                    if (UserUtils.isMobile(username)) {
                        session.setAttribute("login_user", UserMonitor.getUserInfoByMobile(username));
                    } else if (UserUtils.isEmail(username)) {
                        session.setAttribute("login_user", UserMonitor.getUserInfoByEmail(username));
                    }
                    User this_user = (User) (session.getAttribute("login_user"));
                    if ( this_user != null ) {
                        session.setAttribute("login", "true");
                    } else {
                        session.setAttribute("login", "false");
                    }
                    if (this_user != null && this_user.getPrivilege() == 1) {
                        session.setAttribute("privilege", "admin");
                    } else if (this_user != null && this_user.getPrivilege() == 0) {
                        session.setAttribute("privilege", "user");
                    } else if ( this_user != null && this_user.getPrivilege() == 2 ) {
                        session.setAttribute("privilege", "root");
                    }
                    if ( this_user != null && UserMonitor.getUserMonitor(this_user).hasFullInfo() ) {
                        session.setAttribute("userinfo", "y");
                    } else {
                        session.setAttribute("userinfo", "n");
                    }
                }
            }
        }
    }
%>
<div class="button-container" id="menu">
    <span class="top" id="span-top" key="menu"></span>
    <span class="middle"></span>
    <span class="bottom"></span>
</div>
<%
    if (session.getAttribute("login") == null || session.getAttribute("login").equals("false")) {
%>
<div class="overlay" id="overlay">
    <nav class="overlay-menu">
        <ul>
            <li><a href="<%=request.getContextPath()%>/index.jsp">主页</a></li>
            <li><a href="#" class="unlogin">系统</a></li>
            <li><a href="#" class="unlogin">设置</a></li>
            <li><a href="#" class="unlogin">用户管理</a></li>
            <li><a href="#" class="unlogin">关于</a></li>
        </ul>
    </nav>
</div>
<%} else {%>
<div class="overlay" id="overlay">
    <nav class="overlay-menu">
        <ul>
            <li><a href="<%=request.getContextPath()%>/index.jsp">主页</a></li>
            <li><a href="#" id="system">系统</a></li>
            <li><a href="<%=request.getContextPath()%>/setting.jsp">设置</a></li>
            <li><a href="<%=request.getContextPath()%>/manager.jsp">用户管理</a></li>
            <li><a href="<%=request.getContextPath()%>/about.jsp">关于</a></li>
        </ul>
    </nav>
</div>
<%}%>
