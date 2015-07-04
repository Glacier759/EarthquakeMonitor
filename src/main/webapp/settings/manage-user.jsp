<%--
  Created by IntelliJ IDEA.
  User: glacier
  Date: 15-5-27
  Time: 上午12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>Earthquake Eye</title>
        <link href="<%=request.getContextPath()%>/resource/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/resource/css/style.min.css" rel="stylesheet">
        <script src="<%=request.getContextPath()%>/resource/js/pace.js"></script>
        <link href="<%=request.getContextPath()%>/resource/css/pace-theme-flash.min.css" rel="stylesheet">
        <script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <%@include file="../system.jsp" %>
        <%@include file="../navbar.jsp"%>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3>警告<a class="anchorjs-link" href="#"><span class="anchorjs-icon"></span></a></h3>
                    <br />
                    <div class="row">
                        <div class="col-lg-2"></div>
                        <div class="col-lg-8" align="center">
                            <h3>您可以对以下用户进行删除操作</h3>
                            <p>&nbsp;</p>
                        </div>
                        <div class="col-lg-2"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
        <br /><br />
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div id="filters-div">
                    <table class="table table-striped table-bordered table-hover" id="filters-table">
                        <thead>
                        <tr>
                            <th class="text-center">用户名</th>
                            <th class="text-center">邮箱</th>
                            <th class="text-center">手机</th>
                            <th class="text-center">设置</th>
                        </tr>
                        </thead>
                        <tbody id="filters-tbody"></tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <script>
            createTable();
            function createTable() {
                $.ajax({
                    url: "<%=request.getContextPath()%>/SettingServlet?operate=user",
                    type: "GET",
                    dataType: "json",
                    success: function(data) {
                        var objson = eval(data);
                        for ( var i = 0; i < objson.length; i ++ ) {
                            var row = document.createElement("tr");
                            row.setAttribute("id", objson[i].uid);
                            row.setAttribute("class", "text-info");
                            var col1 = document.createElement("th");
                            col1.setAttribute("class", "text-center");
                            col1.appendChild(document.createTextNode(objson[i].realname));
                            row.appendChild(col1);
                            var col2 = document.createElement("th");
                            col2.setAttribute("class", "text-center");
                            col2.appendChild(document.createTextNode(objson[i].email));
                            row.appendChild(col2);
                            var col3 = document.createElement("th");
                            col3.setAttribute("class", "text-center");
                            col3.appendChild(document.createTextNode(objson[i].mobile));
                            row.appendChild(col3);

                            var col4 = document.createElement("th");
                            col4.setAttribute("class", "text-center");
                            var button1 = document.createElement("button");
                            button1.setAttribute("class", "btn btn-danger");
                            button1.setAttribute("value", objson[i].uid);
                            button1.setAttribute("name", "user");
                            button1.setAttribute("onClick", "del(this.value)");
                            button1.innerHTML = "删除";

                            var button2 = document.createElement("button");
                            button2.setAttribute("class", "btn btn-success");
                            button2.setAttribute("value", objson[i].uid);
                            button2.setAttribute("name", "admin");
                            button2.setAttribute("onClick", "manage(this.value)");
                            button2.innerHTML = "管理员";

                            var text = document.createTextNode(" ");
                            col4.appendChild(button1);
                            col4.appendChild(text);
                            col4.appendChild(button2);
                            row.appendChild(col4);
                            document.getElementById("filters-tbody").appendChild(row);
                        }
                    }
                });
            }
        </script>
        <script>
            function no() {
                alert("您没有权限进行此操作");
            }
            function del(uid) {
                $.ajax({
                    type: "get",
                    url: "<%=request.getContextPath()%>/SettingServlet?operate=deluser",
                    data: "uid="+uid,
                    success: function(msg) {    //msg是后台调用action时，你传过来的参数
                        if ( msg == "permission denied" ) {
                            alert("您没有权限进行此操作");
                        } else if ( msg == "not allow" ) {
                            alert("root用户无法删除")
                        } else if ( msg == "wrong" ) {
                            alert("错误的操作");
                        } else if ( msg == "success" ) {
                            alert("删除成功")
                            location.reload();
                        }
                    }
                });
            }
            function manage(uid) {
                $.ajax({
                    type: "get",
                    url: "<%=request.getContextPath()%>/SettingServlet?operate=manage",
                    data: "uid="+uid,
                    success: function(msg) {    //msg是后台调用action时，你传过来的参数
                        if ( msg == "permission denied" ) {
                            alert("您没有权限进行此操作");
                        } else if ( msg == "not allow" ) {
                            alert("无法对root用户进行此操作");
                        } else if ( msg == "is admin" ) {
                            alert("告用户已经是管理员 无法再次设置");
                        } else if ( msg == "success" ) {
                            alert("设置成功");
                        }
                    }
                });
            }
        </script>
        <script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
        <%@include file="../footer.jsp" %>
    </body>
</html>
