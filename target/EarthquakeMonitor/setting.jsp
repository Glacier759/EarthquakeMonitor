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
        <link href="<%=request.getContextPath()%>/resource/fonts/google_api.css?family=Montserrat|Varela+Round" rel="stylesheet">
        <script src="<%=request.getContextPath()%>/resource/js/pace.js"></script>
        <link href="<%=request.getContextPath()%>/resource/css/pace-theme-flash.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="button-container" id="menu">
            <span class="top" id="span-top" key="menu"></span>
            <span class="middle"></span>
            <span class="bottom"></span>
        </div>
        <%if ( session.getAttribute("login") == null || session.getAttribute("login").equals("false") ) {%>
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
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="glyphicon glyphicon-chevron-down" aria-hidden="true" ></span>
                        <%--<span class="icon-bar"></span>--%>
                        <%--<span class="icon-bar"></span>--%>
                        <%--<span class="icon-bar"></span>--%>
                    </button>
                </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><h4>系统设置<span class="caret"></span></h4></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">灾情获取匹配式管理</a></li>
                                <li class="divider"></li>
                                <li><a href="#">舆情监测匹配式管理</a></li>
                                <li class="divider"></li>
                                <li><a href="#">白名单管理</a></li>
                                <li class="divider"></li>
                                <li><a href="#">报警设置</a></li>
                                <li class="divider"></li>
                                <li><a href="#">审核管理</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

        <script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
        <script>
            $(function() {
                $(".unlogin").click(function(){
                    document.getElementById("menu").click();
                    document.getElementById("login").click();
                });
            });
        </script>
    </body>
</html>