<%@ page import="com.glacier.earthquake.monitor.browser.util.UserUtils" %>
<%@ page import="com.glacier.earthquake.monitor.server.configure.user.UserMonitor" %>
<%@ page import="com.glacier.earthquake.monitor.server.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int flag = 0;
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
                    session.setAttribute("username", this_user.getRealname());
                    if ( this_user != null ) {
                        session.setAttribute("login", "true");
                        session.setAttribute("username", this_user.getRealname());
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
                    flag = 1;
                }
            }
        }
    }
    else {
        flag = 1;
    }
//    if ( !request.getServletPath().contains("/index") ) {
%>
<div id="header-pic" align="center">
    <img src="<%=request.getContextPath()%>/resource/img/pic.jpg" style="height: 22%; width: 100%; border: 0;" />
</div>
<%if ( flag == 0 && !request.getServletPath().equals("/index.jsp") && !request.getServletPath().equals("/about.jsp") ) {
    request.getRequestDispatcher("/index.jsp").forward(request, response);
}%>