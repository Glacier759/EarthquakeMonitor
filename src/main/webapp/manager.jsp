<%--
  Created by IntelliJ IDEA.
  User: glacier
  Date: 15-5-25
  Time: 下午4:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.glacier.earthquake.monitor.server.pojo.User" %>
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
        <%@include file="system.jsp"%>
        <%@include file="navbar.jsp"%>
        <%User user = (User)session.getAttribute("login_user");%>
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="alert alert-info alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4>个人资料<a class="anchorjs-link" href="#"><span class="anchorjs-icon"></span></a></h4>
                    <form id="form-userinfo" method="post">
                        <br />
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon">名称</span>
                                    <input type="text" class="form-control" placeholder="<%=user.getRealname()%>" name="realname" value="<%=user.getRealname()%>" />
                                </div>
                            </div>
                            <div class="col-md-2"></div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon">邮箱</span>
                                    <input type="text" class="form-control" placeholder="<%=user.getEmail()%>" name="email" value="<%=user.getEmail()%>" />
                                </div>
                            </div>
                            <div class="col-md-4"></div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon">手机</span>
                                    <input type="text" class="form-control" placeholder="<%=user.getMobile()%>" name="mobile" value="<%=user.getMobile()%>" />
                                </div>
                            </div>
                            <div class="col-md-4"></div>
                        </div>
                        <br /><br />
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <span class="input-group-addon">QQ</span>
                                    <input type="text" class="form-control" placeholder="<%=user.getQqnumber()%>" name="qqnumber" value="<%=user.getQqnumber()%>" />
                                </div>
                            </div>
                            <div class="col-md-4"></div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-5">
                                <div class="input-group">
                                    <span class="input-group-addon">工作单位</span>
                                    <input type="text" class="form-control" placeholder="<%=user.getWorkplace()%>" name="workplace" value="<%=user.getWorkplace()%>" />
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <span class="input-group-addon">职位</span>
                                    <input type="text" class="form-control" placeholder="<%=user.getPosition()%>" name="position" value="<%=user.getPosition()%>" />
                                </div>
                            </div>
                            <div class="col-md-4"></div>
                        </div>
                        <br /><br />
                        <div class="row">
                            <div class="col-md-12" align="right">
                                <button class="btn btn-info" type="submit">更新资料</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <br />
        <div class="row" align="center">
            <button type="button" class="btn btn-warning btn-lg" onclick="show()">修改密码</button>
        </div>

        <div class="modal fade" id="show-div" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <form id="form-password" method="post">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4>修改密码</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-8">
                                    <div class="input-group">
                                        <span class="input-group-addon">初始密码</span>
                                        <input type="password" class="form-control" name="password" value="" />
                                    </div>
                                </div>
                            </div>
                            <br /><br />
                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <span class="input-group-addon">新的密码</span>
                                        <input type="password" class="form-control" name="new-password" value="" />
                                    </div>
                                </div>
                                <div class="col-lg-4"></div>
                            </div>
                            <br />
                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <span class="input-group-addon">重复密码</span>
                                        <input type="password" class="form-control" name="re-password" value="" />
                                    </div>
                                </div>
                                <div class="col-lg-4"></div>
                            </div>
                            <br /><br />
                        </div>
                        <div class="modal-footer">
                            <div class="row">
                                <div class="col-lg-12" align="right">
                                    <button class="btn btn-danger" type="submit">修改密码</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script>
            $(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
            function show() {
                $("#show-div").modal("toggle");
            }
            $("#form-userinfo").submit(function() {
                var ajax_url = "<%=request.getContextPath()%>/SettingServlet?operate=userinfo";
                var ajax_type = $(this).attr('method');
                var ajax_data = $(this).serialize();
                $.ajax({
                    type: ajax_type,
                    url: ajax_url,
                    data: ajax_data,
                    success: function(msg) {    //msg是后台调用action时，你传过来的参数
                        if ( msg == "email format error" ) {
                            alert("邮箱格式有误");
                        } else if ( msg == "mobile format error" ) {
                            alert("手机号码格式有误");
                        } else if ( msg == "email binding" ) {
                            alert("您已绑定邮箱,无法修改. (如需帮助请咨询管理员)");
                        } else if ( msg == "mobile binding" ) {
                            alert("您已绑定手机号,无法修改. (如需帮助请咨询管理员)");
                        } else if ( msg == "success" ) {
                            alert("资料修改成功");
                            location.reload();
                        } else if ( msg == "mobile had" ) {
                            alert("该手机号已被绑定, 请更换手机号");
                        } else if ( msg == "email had" ) {
                            alert("该邮箱已被绑定, 请更换邮箱");
                        } else if ( msg == "userinfo not full" ) {
                            alert("信息不完整,请完善后提交");
                        }
                    }
                });
                return false;   //阻止表单的默认提交事件
            });
            $("#form-password").submit(function() {
                var ajax_url = "<%=request.getContextPath()%>/SettingServlet?operate=password";
                var ajax_type = $(this).attr('method');
                var ajax_data = $(this).serialize();
                $.ajax({
                    type: ajax_type,
                    url: ajax_url,
                    data: ajax_data,
                    success: function(msg) {    //msg是后台调用action时，你传过来的参数
                        if ( msg == "wrong password" ) {
                            alert("原始密码有误");
                        } else if ( msg == "password not equal" ) {
                            alert("两次密码不一致");
                        } else if ( msg == "success" ) {
                            alert("密码修改成功");
                            location.reload();
                        }
                    }
                });
                return false;   //阻止表单的默认提交事件
            });
            function no() {
                alert("您没有权限进行此操作");
            }
        </script>
        <script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
        <%@include file="footer.jsp"%>
    </body>
</html>
