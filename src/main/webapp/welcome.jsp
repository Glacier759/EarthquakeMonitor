<%@ page import="com.glacier.earthquake.monitor.server.pojo.User" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    if ( session.getAttribute("login") == null || session.getAttribute("login").equals("false") ) {
        response.sendRedirect(request.getContextPath() + "/nogrant.jsp");
    }
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/main.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/head.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/content.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/foot.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery-2.1.1.min.js"></script>
    </head>
<body>
<%
    if ( session.getAttribute("login") != null && session.getAttribute("login").equals("true") ) {
%>
    <span class="quick">
        <a href="LogoutServlet">退出登录</a>
    </span>
<%
    }
%>
<header id="header">
    <h1>软件工程实践综合教学平台</h1>
    <nav >
        <ul>
            <li><a href="<%=request.getContextPath()%>/student.jsp">实验安排</a></li>
            <li><a href="<%=request.getContextPath()%>/select.jsp">成绩查询</a></li>
            <li><a href="<%=request.getContextPath()%>/test.jsp">在线测试</a></li>
        </ul>
    </nav>
</header>
<div id="content">
    <div class="asideL-w-1">
        <%
            if ( session.getAttribute("login").equals("true") ) {
                User loginUser = (User)session.getAttribute("login_user");
        %>
            <div>
                <p>welcome,<%=loginUser.getNickname()%>!</p>
                <p>欢迎您进入软件工程实践综合教学平台！</p><br /><br />
                <p >请在顶部导航栏选择您要选择的操作。</p>
            </div>
        <%
            }
        %>
    </div>
</div>
<footer class="footer"><h4>Copyright © 软件工程实践综合教学平台</h4></footer>
</body>
</html>