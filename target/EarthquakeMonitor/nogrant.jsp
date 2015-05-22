<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Student Manager</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/head.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/content.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/foot.css">
</head>
<style type="text/css">
   #nogrant{
        margin-top: 20px;
        width:980px;
        height: 410px;
        background-color: #84b3ff;
       margin: 0 auto;
       text-align: center;
       line-height: 100px;
       background: url("<%=request.getContextPath()%>/resource/img/nogrant-bg.jpg")no-repeat scroll 0px 20px transparent;
       background-size:100% 100%;
       filter:alpha(opacity=60);
       -moz-opacity:0.7;
       -moz-opacity:0.7;
       opacity: 0.7;
    }
   #nogrant  div{
        font-size: 26px;
       color: #3dcaff;
       /*font-weight: bold;*/
       padding-top: 100px;
    }
</style>
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
        <div id="nogrant">
            <div  style="color: #fff7fb">
                对不起，您无权访问该模块，请先登录！<br>
                <br>
                <a href="<%=request.getContextPath()%>/index.jsp" style="color:#fcffe8;background-color: #2EAFBB;border-radius: 5px;padding-left: 10px;padding-right: 10px;padding-top: 5px;padding-bottom: 5px;border: 2px solid #3398a3;">返回登录</a>
            </div>

        </div>
    </div>

<footer class="footer"><h4>Copyright © 软件工程实践综合教学平台</h4></footer>
</body>
</html>