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
    </head>
    <body>
        <%@include file="header.jsp"%>
        <%@include file="system.jsp"%>
        <div id="header-div">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="glyphicon glyphicon-chevron-down" aria-hidden="true" ></span>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                            <%if ( session.getAttribute("privilege").equals("admin") ) {%>
                            <li><a href="<%=request.getContextPath()%>/settings/manage-user.jsp"><h4>用户管理</h4></a></li>
                            <%} else {%>
                            <li><a href="#" onclick="no()"><h4>用户管理</h4></a></li>
                            <%}%>
                            <li><a href="#"><h4>修改资料</h4></a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
        </div>
        <%User user = (User)session.getAttribute("login_user");%>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="alert alert-info alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4>个人资料<a class="anchorjs-link" href="#"><span class="anchorjs-icon"></span></a></h4>
                    <form id="form-userinfo" method="post">
                        <br />
                        <div class="row">
                            <div class="col-lg-2"></div>
                            <div class="col-lg-8">
                                <div class="input-group">
                                    <span class="input-group-addon">昵称</span>
                                    <input type="text" class="form-control" placeholder="<%=user.getNickname()%>" name="nickname" value="" />
                                </div>
                            </div>
                            <div class="col-lg-2"></div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="col-lg-2"></div>
                            <div class="col-lg-6">
                                <div class="input-group">
                                    <span class="input-group-addon">邮箱</span>
                                    <input type="text" class="form-control" placeholder="<%=user.getEmail()%>" name="email" value="" />
                                </div>
                            </div>
                            <div class="col-lg-4"></div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="col-lg-2"></div>
                            <div class="col-lg-6">
                                <div class="input-group">
                                    <span class="input-group-addon">手机</span>
                                    <input type="text" class="form-control" placeholder="<%=user.getMobile()%>" name="mobile" value="" />
                                </div>
                            </div>
                            <div class="col-lg-4"></div>
                        </div>
                        <br /><br />
                        <div class="row">
                            <div class="col-lg-2"></div>
                            <div class="col-lg-3">
                                <div class="input-group">
                                    <span class="input-group-addon">姓名</span>
                                    <input type="text" class="form-control" placeholder="<%=user.getRealname()%>" name="realname" value="" />
                                </div>
                            </div>
                            <div class="col-lg-1"></div>
                            <div class="col-lg-4">
                                <div class="input-group">
                                    <span class="input-group-addon">QQ</span>
                                    <input type="text" class="form-control" placeholder="<%=user.getQqnumber()%>" name="qqnumber" value="" />
                                </div>
                            </div>
                            <div class="col-lg-4"></div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="col-lg-2"></div>
                            <div class="col-lg-5">
                                <div class="input-group">
                                    <span class="input-group-addon">工作单位</span>
                                    <input type="text" class="form-control" placeholder="<%=user.getWorkplace()%>" name="workplace" value="" />
                                </div>
                            </div>
                            <div class="col-lg-3">
                                <div class="input-group">
                                    <span class="input-group-addon">职位</span>
                                    <input type="text" class="form-control" placeholder="<%=user.getPosition()%>" name="position" value="" />
                                </div>
                            </div>
                            <div class="col-lg-4"></div>
                        </div>
                        <br /><br />
                        <div class="row">
                            <div class="col-lg-9"></div>
                            <div class="col-lg-2">
                                <button class="btn btn-info" onclick="uploadFile()">更新资料</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4>修改密码<a class="anchorjs-link" href="#"><span class="anchorjs-icon"></span></a></h4>
                    <form id="form-password" method="post">
                        <br />
                        <div class="row">
                            <div class="col-lg-2"></div>
                            <div class="col-lg-6">
                                <div class="input-group">
                                    <span class="input-group-addon">初始密码</span>
                                    <input type="password" class="form-control" name="password" value="" />
                                </div>
                            </div>
                            <div class="col-lg-4"></div>
                        </div>
                        <br /><br />
                        <div class="row">
                            <div class="col-lg-2"></div>
                            <div class="col-lg-6">
                                <div class="input-group">
                                    <span class="input-group-addon">新的密码</span>
                                    <input type="password" class="form-control" name="new-password" value="" />
                                </div>
                            </div>
                            <div class="col-lg-4"></div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="col-lg-2"></div>
                            <div class="col-lg-6">
                                <div class="input-group">
                                    <span class="input-group-addon">重复密码</span>
                                    <input type="password" class="form-control" name="re-password" value="" />
                                </div>
                            </div>
                            <div class="col-lg-4"></div>
                        </div>
                        <br /><br />
                        <div class="row">
                            <div class="col-lg-9"></div>
                            <div class="col-lg-2">
                                <button class="btn btn-danger" onclick="uploadFile()">修改密码</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
        <script>
            function no() {
                alert("您没有权限进行此操作");
            }
        </script>
        <script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
        <%@include file="footer.jsp"%>
    </body>
</html>
