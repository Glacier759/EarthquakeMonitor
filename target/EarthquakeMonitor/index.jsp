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
        <script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <div class="overlay" id="overlay-form">
            <nav class="overlay-menu">
                <form method="post" id="form-login" class="form-submit">
                    <ul>
                        <li>
                            <div class="input-group">
                                <span class="glyphicon glyphicon-user icon-class" aria-hidden="true">  </span>
                                <input type="text" id="username" name="username" placeholder="手机号/邮箱" class="input-class" value="" required="required" />
                                <input type="hidden" name="choice" value="login" />
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
                            <button class="btn btn-submit animated fadeInUp" type="submit" name="choice" value="login">登录</button>
                        </li>
                    </ul>
                </form>
            </nav>
        </div>
        <div class="overlay" id="overlay-form-register">
            <nav class="overlay-menu">
                <form method="post" id="form-register" class="form-submit">
                    <ul>
                        <li>
                            <div class="input-group">
                                <span class="glyphicon glyphicon-user icon-class" aria-hidden="true">  </span>
                                <input type="text" id="username-register" name="username" placeholder="手机号/邮箱" class="input-class" value="" required="required" />
                            </div>
                        </li>
                        <br />
                        <li>
                            <div class="input-group">
                                <span class="glyphicon glyphicon-lock icon-class" aria-hidden="true" >  </span>
                                <input type="password" id="password-register" name="password" placeholder="登录密码" class="input-class" value="" required="required" />
                                <input type="hidden" name="choice" value="register" />
                            </div>
                        </li>
                        <li>
                            <div class="input-group">
                                <span class="glyphicon glyphicon-hdd icon-class" aria-hidden="true" >  </span>
                                <input type="password" id="repassword-register" name="repassword" placeholder="确认密码" class="input-class" value="" required="required" />
                            </div>
                        </li>
                        <br />
                        <li>
                            <button class="btn btn-submit animated fadeInUp" type="submit">注册</button>
                        </li>
                    </ul>
                </form>
            </nav>
        </div>
        <%@include file="system.jsp"%>
        <header class="container-fluid intro-lg bkg">
            <%if ( session.getAttribute("login") != null && session.getAttribute("login").equals("true") ) {%>
            <a id="logout" href="#" class="btn btn-custom animated fadeInUp" data-toggle="tooltip" data-placement="bottom" title="退出" style="position:absolute;top:0px;right:30px">LOGOUT</a>
            <%}%>
            <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-8 col-md-offset-2">
                <h3 id="supra" class="animated fadeInUp">地震灾情获取与舆情监控</h3>
                <h1 id="title" class="animated fadeInUp">Earthquake Eye</h1>
                <h3 id="sub" class="animated bounceIn">一个<span class="hidden-xs">多维度</span><span class="hidden-xs hidden-sm hidden-md">的地震信息监测报警系统</span></h3>
                <div class="divider divider-intro animated bounceIn"></div>
                <%%>
                <%if ( session.getAttribute("login") == null || session.getAttribute("login").equals("false") ) {%>
                <button type="button" id="login" class="btn btn-custom animated fadeInUp"  data-toggle="tooltip" data-placement="top" title="登录">SIGN IN</button>
                &nbsp;
                <button type="button" id="register" class="btn btn-custom animated fadeInUp"  data-toggle="tooltip" data-placement="top" title="注册">SIGN UP</button>
                <%} else if ( session.getAttribute("login") != null ) {%>
                <a id="lookup" href="<%=request.getContextPath()%>/setting.jsp" class="btn btn-custom animated fadeInUp" data-toggle="tooltip" data-placement="bottom" title="查看">LOOK UP</a>
                <%}%>
            </div>
        </header>
        <script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
        <script>
            $(".form-submit").submit(function() {
                var ajax_url = "<%=request.getContextPath()%>/LoginServlet";
                var ajax_type = $(this).attr('method');
                var ajax_data = $(this).serialize();
                $.ajax({
                    type: ajax_type,
                    url: ajax_url,
                    data: ajax_data,
                    success: function(msg) {    //msg是后台调用action时，你传过来的参数
                        if ( msg == "login success" ) {
                            location.reload();
                        } else if ( msg == "login failed" ) {
                            alert("登录失败");
                        } else if ( msg == "password not equal" ) {
                            alert("两次密码不一致");
                        } else if ( msg == "user is existed" ) {
                            alert("该用户已被注册");
                        } else if ( msg == "input error" ) {
                            alert("请输入【邮箱】或【手机号】");
                        } else if ( msg == "register success" ) {
                            document.getElementById("menu").click();
                            document.getElementById("login").click();
                        }
                    }
                });
                return false;   //阻止表单的默认提交事件
            });
            $("#logout").click(function() {
                $.ajax({
                    type: "post",
                    url: "<%=request.getContextPath()%>/LogoutServlet",
                    data: "logout",
                    success: function(msg) {    //msg是后台调用action时，你传过来的参数
                        location.reload();
                    }
                });
                return false;   //阻止表单的默认提交事件
            });
        </script>
        <script>
            $(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
        <%@include file="footer.jsp"%>
    </body>
</html>
