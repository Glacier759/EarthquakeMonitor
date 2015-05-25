<%--
  Created by IntelliJ IDEA.
  User: glacier
  Date: 15-5-25
  Time: 下午4:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Earthquake Eye</title>
    <link href="<%=request.getContextPath()%>/resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resource/css/animate.css">
    <link href="<%=request.getContextPath()%>/resource/css/style.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resource/fonts/google_api.css?family=Montserrat|Varela+Round"
          rel="stylesheet">
    <script src="<%=request.getContextPath()%>/resource/js/pace.js"></script>
    <link href="<%=request.getContextPath()%>/resource/css/pace-theme-flash.min.css" rel="stylesheet">
</head>
<body>
<div class="button-container" id="toggle">
    <span class="top" id="span-top" key="toggle"></span>
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
            <li><a href="<%=request.getContextPath()%>/system.jsp">系统</a></li>
            <li><a href="<%=request.getContextPath()%>/setting.jsp">设置</a></li>
            <li><a href="<%=request.getContextPath()%>/manager.jsp">用户管理</a></li>
            <li><a href="<%=request.getContextPath()%>/about.jsp">关于</a></li>
        </ul>
    </nav>
</div>
<%}%>
<header class="container-fluid intro-lg bkg">
    <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-8 col-md-offset-2">
        <h3 id="supra" class="animated fadeInUp">ceshi</h3>

        <h1 id="title" class="animated fadeInUp">Earthquake Eye</h1>

        <h3 id="sub" class="animated bounceIn">一个<span class="hidden-xs">多维ss度</span><span
                class="hidden-xs hidden-sm hidden-md">sadasd</span></h3>

        <div class="divider divider-intro animated bounceIn"></div>
        <a id="login" href="#" class="btn btn-custom animated fadeInUp">sada in</a>
    </div>
</header>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
<script>
    $(function () {
        $(".unlogin").click(function () {
            document.getElementById("toggle").click();
            document.getElementById("login").click();
        });
    });
</script>
</body>
</html>
