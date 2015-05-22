<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>Student Manager</title>
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
    <nav id="nav">
        <ul>
            <li><a href="<%=request.getContextPath()%>/index.jsp">首页</a></li>
            <li><a>后台管理</a>
                <ul>
                    <li ><a>实验查询</a></li>
                    <li><a>小组登录</a></li>
                    <li><a>作业提交</a></li>
                    <li><a>成绩查询</a></li>
                    <li><a>在线考试</a></li>
                </ul>
            </li>
            <li><a>实验资源管理</a>
                <ul>
                    <li ><a>实验查询</a></li>
                    <li><a>小组登录</a></li>
                    <li><a>作业提交</a></li>
                    <li><a>成绩查询</a></li>
                    <li><a>在线考试</a></li>
                </ul>
            </li>
            <li><a>教师端</a>
                <ul>
                    <li ><a>实验查询</a></li>
                    <li><a>小组登录</a></li>
                    <li><a>作业提交</a></li>
                    <li><a>成绩查询</a></li>
                    <li><a>在线考试</a></li>
                </ul>
            </li>
            <li><a href="<%=request.getContextPath()%>/welcome.jsp" target="_blank">学生端</a>
                <ul >
                    <li ><a>实验查询</a></li>
                    <li><a href="<%=request.getContextPath()%>/groupLogin.jsp">小组登录</a></li>
                    <li><a>作业提交</a></li>
                    <li><a>成绩查询</a></li>
                    <li><a>在线考试</a></li>
                </ul>
            </li>
            <li><a>在线测试</a>
                <ul>
                    <li ><a>实验查询</a></li>
                    <li><a>小组登录</a></li>
                    <li><a>作业提交</a></li>
                    <li><a>成绩查询</a></li>
                    <li><a>在线考试</a></li>
                </ul>
            </li>
            <li><a>作业判定</a>
                <ul>
                    <li ><a>实验查询</a></li>
                    <li><a>小组登录</a></li>
                    <li><a>作业提交</a></li>
                    <li><a>成绩查询</a></li>
                    <li><a>在线考试</a></li>
                </ul>
            </li>
            <li><a>学生Android端</a>
                <ul>
                    <li ><a>实验查询</a></li>
                    <li><a>小组登录</a></li>
                    <li><a>作业提交</a></li>
                    <li><a>成绩查询</a></li>
                    <li><a>在线考试</a></li>
                </ul>
            </li>

        </ul>
    </nav>
</header>
<div id="content">
    <div id="groupLogin">
        <form id="login">
            <h4 class="login">小组登录</h4>
            <ul class="user">
                <li class="logo"></li>
                <li>
                    <input id="username" name="username" class="input_text" placeholder="小组编号"  type="text">
                </li>
            </ul>
            <ul class="user">
                <li class="logoPass"></li>
                <li>
                    <input id="pass" name="pass" class="input_text" placeholder="密码"  type="password">
                </li>
            </ul>
            <input type="submit" class="input_login" value="登录"/><br><br>
            <input name="zhuce" class="input_login" type="button" value="注册" />

        </form>
    </div>
</div>
<footer class="footer"><h4>Copyright © 软件工程实践综合教学平台</h4></footer>
</body>
</html>