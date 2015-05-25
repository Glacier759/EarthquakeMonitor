<%--
  Created by IntelliJ IDEA.
  User: glacier
  Date: 15-5-25
  Time: 下午4:02
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
        <%
            if ( session.getAttribute("login") == null || session.getAttribute("login").equals("false") ) {
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
        <div class="overlay" id="overlay-form">
            <nav class="overlay-menu">
                <form action="<%=request.getContextPath()%>/LoginServlet" method="post">
                    <ul>
                        <li>
                            <div class="input-group">
                                <span class="glyphicon glyphicon-user icon-class" aria-hidden="true">  </span>
                                <input type="text" id="username" name="username" placeholder="用户名/手机号/邮箱" class="input-class" value="" required="required" />
                            </div>
                        </li>
                        <br />
                        <li>
                            <div class="input-group">
                                <span class="glyphicon glyphicon-lock icon-class" aria-hidden="true" >  </span>
                                <input type="password" id="password" name="password" placeholder="登录密码" class="input-class" value="" required="required" />
                            </div>
                        </li>
                        <br />
                        <li>
                            <button class="btn btn-submit animated fadeInUp" type="submit" value="login">登录</button>
                        </li>
                    </ul>
                </form>
            </nav>
        </div>
        <div class="modal fade" id="confirm" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4>系统</h4>
                    </div>
                    <div class="modal-body">这里可以对这个功能点做以说明。比如说启动是什么作用，停止是什么作用，退出又是什么作用。</div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success">启动</button>
                        <button type="button" class="btn btn-danger">停止</button>
                        <button type="button" class="btn btn-warning">退出</button>
                    </div>
                </div>
            </div>
        </div>
        <header class="container-fluid intro-lg bkg">
            <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-8 col-md-offset-2">
                <h3 id="supra" class="animated fadeInUp">地震灾情获取与舆情监控</h3>
                <h1 id="title" class="animated fadeInUp">Earthquake Eye</h1>
                <h3 id="sub" class="animated bounceIn">一个<span class="hidden-xs">多维度</span><span class="hidden-xs hidden-sm hidden-md">的地震信息监测报警系统</span></h3>
                <div class="divider divider-intro animated bounceIn"></div>
                <%if ( session.getAttribute("login") == null || session.getAttribute("login").equals("false") ) {%>
                <a id="login" href="#" class="btn btn-custom animated fadeInUp">SIGN IN</a>
                <%} else {%>
                <a id="lookup" href="<%=request.getContextPath()%>/setting.jsp" class="btn btn-custom animated fadeInUp">LOOK UP</a>
                <%}%>
            </div>
        </header>
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
            $(function() {
               $("#system").click(function() {
                  document.getElementById("menu").click();
                   $("#confirm").modal("toggle");
               });
            });
        </script>
    </body>
</html>
