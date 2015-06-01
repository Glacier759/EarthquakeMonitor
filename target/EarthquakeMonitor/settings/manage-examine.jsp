<%--
  Created by IntelliJ IDEA.
  User: glacier
  Date: 15-5-25
  Time: 下午8:32
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
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><h4>审核管理<span class="caret"></span></h4></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="<%=request.getContextPath()%>/settings/manage-disaster.jsp">灾情获取匹配式管理</a></li>
                                    <li class="divider"></li>
                                    <li><a href="<%=request.getContextPath()%>/settings/manage-public.jsp">舆情监测匹配式管理</a></li>
                                    <li class="divider"></li>
                                    <li><a href="<%=request.getContextPath()%>/settings/manage-whitelist.jsp">白名单管理</a></li>
                                    <li class="divider"></li>
                                    <li><a href="<%=request.getContextPath()%>/settings/manage-warning.jsp">报警设置</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#">审核管理</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->
                </div>
                <!-- /.container-fluid -->
            </nav>
        </div>
        <div class="row" align="center">
            <button id="examine-start" type="button" onclick="fun(1)" class="btn btn-info">开启审核</button>
            <button id="examine-stop" type="button" onclick="fun(0)" class="btn btn-warning">关闭审核</button>
        </div>
        <br />
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4>信息审核<a class="anchorjs-link" href="#"><span class="anchorjs-icon"></span></a></h4>
                    <br />
                    <div class="row">
                        <div class="col-lg-2"></div>
                        <div class="col-lg-8" align="center">
                            <p>您可以对以下信息进行审核, 普通用户可以查看审核通过的信息</p>
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
                    <form id="examine-ok" method="post">
                        <div class="row">
                            <table class="table table-striped table-bordered table-hover" id="filters-table" style="table-layout: fixed;">
                                <thead>
                                <tr>
                                    <th class="text-center" width="30px"></th>
                                    <th class="text-center" width="250px">Title</th>
                                    <th class="text-center">URL</th>
                                    <th class="text-center" width="180px">Crawl Date</th>
                                    <th class="text-center" width="80px">Setting</th>
                                </tr>
                                </thead>
                                <tbody id="filters-tbody"></tbody>
                            </table>
                        </div>
                        <div class="row">
                            <div class="col-lg-10"></div>
                            <div class="col-lg-2">
                                <button class="btn btn-info" type="submit">审核通过</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <div class="modal fade" id="show-div" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                                class="sr-only">Close</span></button>
                        <h4>详细信息</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="alert alert-info alert-dismissible" role="alert">
                                    <h3 align="center">源文件信息<a class="anchorjs-link" href="#"><span
                                            class="anchorjs-icon"></span></a></h3>
                                    <br/>

                                    <div class="row">
                                        <%--<div class="col-lg-2"></div>--%>
                                        <div class="col-lg-12" align="center">
                                            <%--<p id="filter-source"></p>--%>
                                            <iframe width="100%" height="100%" src="" id="filter-iframe"></iframe>
                                        </div>
                                        <%--<div class="col-lg-2"></div>--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="row">
                            <div class="col-lg-3">
                                <button type="button" class="btn btn-info" id="filter-type"></button>
                            </div>
                            <div id="filter-rule-div" class="col-lg-9" align="right">
                                <button id="filter-rule" type="button" class="btn btn-warning" data-toggle="tooltip"
                                        data-placement="bottom" title="">规则
                                </button>
                                <button id="filter-patten" type="button" class="btn btn-warning" data-toggle="tooltip"
                                        data-placement="bottom" title="">正则
                                </button>
                                <button id="filter-unexist" type="button" class="btn btn-warning" data-toggle="tooltip"
                                        data-placement="bottom" title="">不含关键字
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            createTable();
            function createTable() {
                $.ajax({
                    url: "<%=request.getContextPath()%>/SettingServlet?operate=examine",
                    type: "GET",
                    dataType: "json",
                    success: function(data) {
                        var objson = eval(data);
                        for ( var i = 0; i < objson.length; i ++ ) {
                            var row = document.createElement("tr");
                            row.setAttribute("id", objson[i].id);
                            row.setAttribute("class", "text-info");
                            var col0 = document.createElement("th");
                            col0.setAttribute("class", "text-center");
                            var select = document.createElement("input");
                            select.setAttribute("type", "checkbox");
                            select.setAttribute("name", "check");
                            select.setAttribute("value", objson[i].id);
                            col0.appendChild(select);
                            row.appendChild(col0);

                            var col1 = document.createElement("th");
                            col1.setAttribute("class", "text-center");
                            col1.appendChild(document.createTextNode(objson[i].title));
                            row.appendChild(col1);
                            var col2 = document.createElement("th");
                            col2.setAttribute("class", "text-center");
                            col2.setAttribute("style", "overflow-x:hidden;");
                            col2.appendChild(document.createTextNode(objson[i].url));
                            row.appendChild(col2);
                            var col3 = document.createElement("th");
                            col3.setAttribute("class", "text-center");
                            col3.appendChild(document.createTextNode(objson[i].crawldate));
                            row.appendChild(col3);

                            var col4 = document.createElement("th");
                            col4.setAttribute("class", "text-center");
                            var button1 = document.createElement("button");
                            button1.setAttribute("class", "btn btn-success");
                            button1.setAttribute("type", "button");
                            button1.setAttribute("value", objson[i].id);
                            button1.setAttribute("onclick", "show(this.value)");
                            button1.innerHTML = "查看";
                            col4.appendChild(button1);
                            row.appendChild(col4);
                            document.getElementById("filters-tbody").appendChild(row);
                        }
                    }
                });
            }
        </script>
        <script>
            function show(value) {
                $.ajax({
                    type: "get",
                    url: "<%=request.getContextPath()%>/SettingServlet?operate=spiderinfo",
                    data: "id=" + value,
                    dataType: "json",
                    success: function (msg) {    //msg是后台调用action时，你传过来的参数
                        var objson = eval(msg);
                        //$("#filter-source").html(objson.source);
                        $("#filter-iframe").attr("src", objson.url);
                        if (objson.type == "disaster") {
                            $("#filter-type").html("信息类型: 灾情获取");
                            $("#filter-patten").remove();
                            $("#filter-unexist").remove();
                            $("#filter-rule").attr("title", objson.rule);
                        } else if (objson.type == "public") {
                            $("#filter-type").html("信息类型: 舆情监测");
                            $("#filter-rule").attr("title", objson.name);
                            $("#filter-patten").attr("title", objson.matcher);
                            $("#filter-unexist").attr("title", objson.unexist);
                        }
                        $(function () {
                            $('[data-toggle="tooltip"]').tooltip();
                        });
                        $("#show-div").modal("toggle");
                    }
                });
            }
            function fun(value) {
                $.ajax({
                    type: "get",
                    url: "<%=request.getContextPath()%>/SettingServlet?operate=switch",
                    data: "value=" + value,
                    success: function (msg) {    //msg是后台调用action时，你传过来的参数
                        if (msg == "examine start") {
                            alert("审核模块已被开启 普通用户只能浏览通过审核的内容");
                        } else if (msg == "examine stop") {
                            alert("审核模块已经关闭 普通用户可以浏览所有获取到的数据");
                        } else if (msg == "permission denied") {
                            alert("您没有权限进行此操作");
                        } else if (msg == "wrong stop") {
                            alert("审核模块已经处于关闭状态");
                        } else if (msg == "wrong start") {
                            alert("审核模块已经处于开启状态");
                        }
                    }
                });
            }

            $("#examine-ok").submit(function() {
                    var ajax_url = "<%=request.getContextPath()%>/SettingServlet?operate=examine-ok";
                    var ajax_type = $(this).attr('method');
                    var ajax_data = $(this).serialize();
                    $.ajax({
                        type: ajax_type,
                        url: ajax_url,
                        data: ajax_data,
                        success: function(msg) {    //msg是后台调用action时，你传过来的参数
                            if ( msg == "wrong" ) {
                                alert("审核出现异常");
                                location.reload();
                            } else if ( msg == "success" ) {
                                alert("操作成功");
                                location.reload();
                            } else if ( msg == "permission denied" ) {
                                alert("您没有权限进行此操作");
                            }
                        }
                    });
                    return false;   //阻止表单的默认提交事件
            });
        </script>
        <script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
        <%@include file="../footer.jsp"%>
    </body>
</html>
