<%--
  Created by IntelliJ IDEA.
  User: glacier
  Date: 15-6-30
  Time: 下午3:32
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
        <script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%@include file="system.jsp" %>
        <div id="header-pic" align="center">
            <img src="<%=request.getContextPath()%>/resource/img/pic.jpg" style="height: 25%; width: 100%; border: 0;"/>
        </div>
        <%@include file="navbar.jsp" %>
        <%if (session.getAttribute("login") == null || session.getAttribute("login").equals("false")) {%>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <form id="form-login" method="post" class="form-submit">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3>用户登陆</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-8">
                                    <div class="input-group">
                                        <span class="input-group-addon">账户</span>
                                        <input type="text" class="form-control" name="username" value="" placeholder="手机号/邮箱" required autofocus/>
                                        <input type="hidden" name="choice" value="login"/>
                                    </div>
                                </div>
                            </div>
                            <br/><br/>

                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <span class="input-group-addon">密码</span>
                                        <input type="password" class="form-control" name="password" value="" placeholder="登录密码" required/>
                                    </div>
                                </div>
                                <div class="col-lg-4"></div>
                            </div>
                            <br/><br/>
                        </div>
                        <div class="modal-footer">
                            <div class="row">
                                <div class="col-lg-12" align="right">
                                    <button class="btn btn-success btn-lg" type="button" onclick="register()">注册</button>
                                    <button class="btn btn-info btn-lg" type="submit">登陆</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="modal fade" id="register-div" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <form id="form-register" method="post" class="form-submit">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span
                                    aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h3>用户注册</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-5">
                                    <div class="input-group">
                                        <span class="input-group-addon">名称</span>
                                        <input type="text" class="form-control" name="realname" value="" placeholder="真实姓名" required autofocus/>
                                    </div>
                                </div>
                            </div>
                            <br/>

                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-5">
                                    <div class="input-group">
                                        <span class="input-group-addon">邮箱</span>
                                        <input type="text" class="form-control" name="email" value="" placeholder="邮箱" required/>
                                        <input type="hidden" name="choice" value="register"/>
                                    </div>
                                </div>
                            </div>
                            <br/>

                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-5">
                                    <div class="input-group">
                                        <span class="input-group-addon">手机</span>
                                        <input type="text" class="form-control" name="mobile" value="" placeholder="手机号" required/>
                                    </div>
                                </div>
                            </div>
                            <br/><br/>

                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <span class="input-group-addon">密码</span>
                                        <input type="password" class="form-control" name="password" value="" placeholder="登录密码" required/>
                                    </div>
                                </div>
                                <div class="col-lg-4"></div>
                            </div>
                            <br/>

                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <span class="input-group-addon">确认密码</span>
                                        <input type="password" class="form-control" name="repassword" value="" placeholder="确认密码" required/>
                                    </div>
                                </div>
                                <div class="col-lg-4"></div>
                            </div>
                            <br/><br/>

                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-4">
                                    <div class="input-group">
                                        <span class="input-group-addon">QQ</span>
                                        <input type="text" class="form-control" name="qqnumber" value="" placeholder="QQ号" required/>
                                    </div>
                                </div>
                            </div>
                            <br/>

                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-5">
                                    <div class="input-group">
                                        <span class="input-group-addon">工作单位</span>
                                        <input type="text" class="form-control" placeholder="工作单位" name="workplace" value="" required/>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="input-group">
                                        <span class="input-group-addon">职位</span>
                                        <input type="text" class="form-control" placeholder="职位" name="position" value="" required/>
                                    </div>
                                </div>
                                <div class="col-md-4"></div>
                            </div>
                            <br/><br/>
                        </div>
                        <div class="modal-footer">
                            <div class="row">
                                <div class="col-lg-12" align="right">
                                    <button class="btn btn-info btn-lg" type="submit">注册</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <%} else if (session.getAttribute("login") != null && session.getAttribute("login").equals("true")) {%>
        <br /><br />
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <h2 align="center">您好, <%=session.getAttribute("username")%></h2>
                <br />
                <p align="center">
                    <a class="btn btn-lg btn-info" href="<%=request.getContextPath()%>/showdata.jsp">查看记录</a>
                    <a class="btn btn-lg btn-danger" href="#" id="logout">退出登录</a>
                </p>
            </div>
        </div>
        <%}%>
        <script>
            $(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
            function register() {
                $("#register-div").modal("toggle");
            }
            $(".form-submit").submit(function () {
                var ajax_url = "<%=request.getContextPath()%>/LoginServlet";
                var ajax_type = $(this).attr('method');
                var ajax_data = $(this).serialize();
                $.ajax({
                    type: ajax_type,
                    url: ajax_url,
                    data: ajax_data,
                    success: function (msg) {    //msg是后台调用action时，你传过来的参数
                        if (msg == "login success") {
                            location.href = "<%=request.getContextPath()%>/index.jsp";
                        } else if (msg == "login failed") {
                            alert("登录失败 - 账户密码不匹配");
                        } else if (msg == "password not equal") {
                            alert("两次密码不一致");
                        } else if (msg == "mail is existed") {
                            alert("该邮箱已被注册");
                        } else if (msg == "mobile is existed") {
                            alert("该手机已被注册");
                        } else if (msg == "has null") {
                            alert("不能有空字段")
                        } else if (msg == "input mail error") {
                            alert("邮箱格式错误");
                        } else if (msg == "input mobile error") {
                            alert("手机格式错误");
                        } else if (msg == "register success") {
                            alert("注册成功");
                            register();
                        } else if (msg == "userinfo is not full") {
                            alert("用户信息不完整, 请完善信息后使用该系统");
                            location.href = "<%=request.getContextPath()%>/manager.jsp";
                        }
                    }
                });
                return false;   //阻止表单的默认提交事件
            });
            $("#logout").click(function () {
                $.ajax({
                    type: "post",
                    url: "<%=request.getContextPath()%>/LogoutServlet",
                    data: "logout",
                    success: function (msg) {    //msg是后台调用action时，你传过来的参数
                        location.reload();
                    }
                });
                return false;   //阻止表单的默认提交事件
            });
        </script>
        <%@include file="footer.jsp" %>
    </body>
</html>
