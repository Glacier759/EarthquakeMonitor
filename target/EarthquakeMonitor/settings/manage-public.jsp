<%--
  Created by IntelliJ IDEA.
  User: glacier
  Date: 15-5-25
  Time: 下午8:31
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
        <%@include file="../header.jsp"%>
        <%@include file="../system.jsp"%>
        <div id="header-div">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="<%=request.getContextPath()%>/showdata.jsp"><h4>查看数据记录</h4></a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><h4>舆情监测匹配式管理<span class="caret"></span></h4></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="<%=request.getContextPath()%>/settings/manage-disaster.jsp">灾情获取匹配式管理</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#">舆情监测匹配式管理</a></li>
                                    <li class="divider"></li>
                                    <li><a href="<%=request.getContextPath()%>/settings/manage-whitelist.jsp">白名单管理</a></li>
                                    <li class="divider"></li>
                                    <li><a href="<%=request.getContextPath()%>/settings/manage-warning.jsp">报警设置</a></li>
                                    <li class="divider"></li>
                                    <li><a href="<%=request.getContextPath()%>/settings/manage-examine.jsp">审核管理</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->
                </div>
                <!-- /.container-fluid -->
            </nav>
        </div>
        <br /><br />
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="alert alert-info alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4>从文件导入<a class="anchorjs-link" href="#从文件导入"><span class="anchorjs-icon"></span></a></h4>
                    <div class="row">
                        <div>
                            <label class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传文件:</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12" align="center">
                            <input type="file" name="file" id="file" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12" align="right">
                            <button class="btn btn-info" onclick="uploadFile()">上传</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <br /><br />
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <form id="form-addfilter" method="get">
                    <div class="panel panel-info">
                        <div class="panel-heading">增加灾情获取匹配式</div>
                        <div class="panel-body">
                            <div id="filterinfo">
                                <div class="row" name="disaster" count="1">
                                    <div class="row">
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-5">
                                            <div class="input-group">
                                                <span class="input-group-addon">匹配规则</span>
                                                <input type="text" class="form-control" placeholder="福州*发生地震" name="filter-name" value="" />
                                            </div>
                                        </div>
                                        <div class="col-lg-5">
                                            <div class="input-group">
                                                <span class="input-group-addon">不含关键字</span>
                                                <input type="text" class="form-control" placeholder="汶川 新西兰" name="filter-unexist" value="" />
                                            </div>
                                        </div>
                                        <div class="col-lg-1"></div>
                                    </div>
                                    <br />
                                    <div class="row">
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-10">
                                            <div class="input-group">
                                                <span class="input-group-addon">正则表达式</span>
                                                <input type="text" class="form-control" placeholder="S*福建[^，。；？！]发生[^，。；？！]" name="filter-matcher" value="" />
                                            </div>
                                        </div>
                                        <div class="col-lg-1"></div>
                                    </div>
                                    <br /><br />
                                </div>
                            </div>
                            <!--filterinfo-->
                            <div class="row">
                                <div class="col-lg-4">
                                    <div class="input-group">
                                        <button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="bottom" title="增加一条规则" name="addfilter">
                                            <span class="glyphicon glyphicon-plus" />
                                        </button>
                                        <button type="button" class="btn btn-default " data-toggle="tooltip" data-placement="bottom" title="减少一条规则" name="minufilter">
                                            <span class="glyphicon glyphicon-minus" />
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12" align="right">
                                    <button type="submit" class="btn btn-info">提交</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-2"></div>
        </div>
        <br /><br />
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div id="filters-div">
                    <table class="table table-striped table-bordered table-hover" id="filters-table">
                        <thead>
                        <tr>
                            <th class="text-center">Create Time</th>
                            <th class="text-center">Name</th>
                            <th class="text-center">Matcher</th>
                            <th class="text-center">Unexist</th>
                            <th class="text-center">Setting</th>
                        </tr>
                        </thead>
                        <tbody id="filters-tbody"></tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <script>
            $(function() {
                $("[name='addfilter']").click(function() {
                    var filterinfo = document.getElementById('filterinfo');
                    var filter = filterinfo.lastElementChild;
                    var f = filter.cloneNode(true);
                    f.setAttribute('count',parseInt(f.getAttribute('count'))+1);
                    filterinfo.appendChild(f);
                });
            });
            $(function() {
                $("[name='minufilter']").click(function() {
                    var filterinfo = document.getElementById('filterinfo');
                    if ( filterinfo.childElementCount == 1 ) {
                    }
                    else {
                        var filter = filterinfo.lastElementChild;
                        filterinfo.removeChild(filter);
                    }
                });
            });
            function del(val)
            {
                $.ajax({
                    type: "get",
                    url: "<%=request.getContextPath()%>/SettingServlet?operate=delete&type=public",
                    data: "filter_id="+val,
                    success: function(msg) {    //msg是后台调用action时，你传过来的参数
                        if ( msg == "permission denied" ) {
                            alert("您没有权限进行此操作");
                        } else {
                            var filterChild=document.getElementById(val);
                            document.getElementById("filters-tbody").removeChild(filterChild);
                        }
                    }
                });
            }
        </script>
        <script>
            createTable();
            function createTable() {
                $.ajax({
                    url: "<%=request.getContextPath()%>/SettingServlet?operate=table&type=public",
                    type: "GET",
                    dataType: "json",
                    success: function(data) {
                        var objson = eval(data);
                        for ( var i = 0; i < objson.length; i ++ ) {
                            var row = document.createElement("tr");
                            row.setAttribute("id", objson[i].id);
                            row.setAttribute("class", "text-info");
                            var col1 = document.createElement("th");
                            col1.setAttribute("class", "text-center");
                            col1.appendChild(document.createTextNode(objson[i].create_time));
                            row.appendChild(col1);
                            var col2 = document.createElement("th");
                            col2.setAttribute("class", "text-center");
                            col2.appendChild(document.createTextNode(objson[i].name));
                            row.appendChild(col2);
                            var col3 = document.createElement("th");
                            col3.setAttribute("class", "text-center");
                            col3.appendChild(document.createTextNode(objson[i].matcher));
                            row.appendChild(col3);
                            var col4 = document.createElement("th");
                            col4.setAttribute("class", "text-center");
                            col4.appendChild(document.createTextNode(objson[i].unexist));
                            row.appendChild(col4);
                            var col5 = document.createElement("th");
                            col5.setAttribute("class", "text-center");
                            var button = document.createElement("button");
                            button.setAttribute("class", "btn btn-danger");
                            button.setAttribute("value", objson[i].id);
                            button.setAttribute("name", "filter");
                            button.setAttribute("onClick", "del(this.value)");
                            button.innerHTML = "删除";
                            col5.appendChild(button);
                            row.appendChild(col5);
                            document.getElementById("filters-tbody").appendChild(row);
                        }
                    }
                });
            }
        </script>
        <script>
            $("#form-addfilter").submit(function() {
                var ajax_url = "<%=request.getContextPath()%>/SettingServlet?operate=addfilter&type=public";
                var ajax_type = $(this).attr('method');
                var ajax_data = $(this).serialize();
                $.ajax({
                    type: ajax_type,
                    url: ajax_url,
                    data: ajax_data,
                    success: function(msg) {    //msg是后台调用action时，你传过来的参数
                        if ( msg == "permission denied" ) {
                            alert("您没有权限进行此操作");
                        } else {
                            location.reload();
                        }
                    }
                });
                return false;   //阻止表单的默认提交事件
            });
            function uploadFile() {
                <%
                    User user = (User)session.getAttribute("login_user");
                    if ( user.getPrivilege() >= 1 ) {
                %>
                var fileObj = document.getElementById("file").files[0]; // 获取文件对象
                var FileController = "<%=request.getContextPath()%>/UploadServlet?type=public";                // 接收上传文件的后台地址

                var form = new FormData();
                form.append("file", fileObj);                           // 文件对象

                var xhr = new XMLHttpRequest();
                xhr.open("post", FileController, true);
                xhr.onload = function () {
                    alert("上传完成!");
                    location.reload();
                };
                xhr.send(form);
                <%} else {%>
                alert("您没有权限进行此操作");
                <%}%>
            }
        </script>
        <script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
        <%@include file="../footer.jsp"%>
    </body>
</html>
