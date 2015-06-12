<%--
  Created by IntelliJ IDEA.
  User: glacier
  Date: 15-5-28
  Time: 下午2:14
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
                        <ul class="nav navbar-nav navbar-left">
                            <li><a href="<%=request.getContextPath()%>/index.jsp"><h4>主页</h4></a></li>
                            <li><a href="#" onclick="system()"><h4>系统</h4></a></li>
                            <li><a href="<%=request.getContextPath()%>/setting.jsp"><h4>设置</h4></a></li>
                            <li><a href="<%=request.getContextPath()%>/manager.jsp"><h4>用户管理</h4></a></li>
                            <li><a href="<%=request.getContextPath()%>/about.jsp"><h4>关于</h4></a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="<%=request.getContextPath()%>/setting.jsp"><h4>查看数据概览</h4></a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><h4>管理设置<span class="caret"></span></h4></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="<%=request.getContextPath()%>/settings/manage-disaster.jsp">灾情获取匹配式管理</a></li>
                                    <li class="divider"></li>
                                    <li><a href="<%=request.getContextPath()%>/settings/manage-public.jsp">舆情监测匹配式管理</a></li>
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
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4>查看数据记录<a class="anchorjs-link" href="#"><span class="anchorjs-icon"></span></a></h4>
                    <br/>

                    <div class="row">
                        <div class="col-lg-2"></div>
                        <div class="col-lg-8" align="center">
                            <p>以下是系统收集到的符合条件的数据记录信息</p>
                        </div>
                        <div class="col-lg-2"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
        <br/><br/>


        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div id="filters-div">
                    <div class="row">
                        <button type="button" class="btn btn-info" onclick="showAll()">全部</button>
                        <button type="button" class="btn btn-warning" onclick="showDis()">灾情获取</button>
                        <button type="button" class="btn btn-success" onclick="showPub()">舆情监控</button>
                    </div>
                    <br />
                    <div class="row">
                        <table class="table table-striped table-bordered table-hover" id="filters-table" style="table-layout: fixed;">
                            <thead>
                            <tr>
                                <th class="text-center" width="60px">来源</th>
                                <th class="text-center" width="300px">标题</th>
                                <th class="text-center">来源地址</th>
                                <th class="text-center" width="200px">获取时间</th>
                                <th class="text-center" width="80px">设置</th>
                            </tr>
                            </thead>
                            <tbody id="filters-tbody"></tbody>
                        </table>
                    </div>
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
                                            <p id="filter-source" hidden></p>
                                            <iframe width="100%" height="100%" src="" id="filter-iframe" hidden></iframe>
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
                    url: "<%=request.getContextPath()%>/SettingServlet?operate=showdata",
                    type: "GET",
                    dataType: "json",
                    success: function (data) {
                        var objson = eval(data);
                        for (var i = 0; i < objson.length; i++) {
                            var row = document.createElement("tr");
                            row.setAttribute("id", objson[i].id);
                            row.setAttribute("class", "text-info");
                            row.setAttribute("status", objson[i].type);

                            var col0 = document.createElement("td");
                            col0.setAttribute("valign", "middle");
                            col0.setAttribute("class", "text-center");
                            var span0 = document.createElement("span");
                            span0.setAttribute("class", "label label-default");
                            span0.innerHTML = objson[i].origin;
                            col0.appendChild(span0);
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

                        if ( objson.source != null ) {
                            $("#filter-iframe").attr("hidden","");
                            $("#filter-source").removeAttr("hidden");
                            $("#filter-source").html(objson.source);
                        }
                        else {
                            $("#filter-source").attr("hidden", "");
                            $("#filter-iframe").removeAttr("hidden");
                            $("#filter-iframe").attr("src", objson.url);
                        }

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
            function showAll() {
                $("tr[status]").attr("style", "display:");
            }
            function showDis() {
                $("tr[status='0']").attr("style", "display:");
                $("tr[status='1']").attr("style", "display:none");
            }
            function showPub() {
                $("tr[status='1']").attr("style", "display:");
                $("tr[status='0']").attr("style", "display:none");
            }
        </script>
        <script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
        <%@include file="footer.jsp" %>
    </body>
</html>
