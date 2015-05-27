<%@ page import="com.glacier.earthquake.monitor.browser.util.UserUtils" %>
<%@ page import="com.glacier.earthquake.monitor.server.configure.user.UserMonitor" %>
<%@ page import="com.glacier.earthquake.monitor.server.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if ( session.getAttribute("login") == null || session.getAttribute("login").equals("false") ) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    String username = cookie.getValue();
                    session.setAttribute("login", "true");
                    if (UserUtils.isMobile(username)) {
                        session.setAttribute("login_user", UserMonitor.getUserInfoByMobile(username));
                    } else if (UserUtils.isEmail(username)) {
                        session.setAttribute("login_user", UserMonitor.getUserInfoByEmail(username));
                    }
                    User this_user = (User) (session.getAttribute("login_user"));
                    if (this_user.getPrivilege() == 1) {
                        session.setAttribute("privilege", "admin");
                    } else if (this_user.getPrivilege() == 0) {
                        session.setAttribute("privilege", "user");
                    }
                    if ( UserMonitor.getUserMonitor(this_user).hasFullInfo() ) {
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
