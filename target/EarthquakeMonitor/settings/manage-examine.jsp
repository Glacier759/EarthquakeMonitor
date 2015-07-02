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
        <%@include file="../navbar.jsp"%>
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
                    <h3>信息审核<a class="anchorjs-link" href="#"><span class="anchorjs-icon"></span></a></h3>
                    <br />
                    <div class="row">
                        <div class="col-lg-2"></div>
                        <div class="col-lg-8" align="center">
                            <h3>您可以对以下信息进行审核, 普通用户可以查看审核通过的信息</h3>
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
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <div id="filters-div">
                    <form id="examine-ok" method="post">
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
                                    <th class="text-center" width="30px"></th>
                                    <th class="text-center" width="50px">序号</th>
                                    <th class="text-center" width="60px">来源</th>
                                    <th class="text-center" width="110px">获取时间</th>
                                    <th class="text-center" width="110px">发布时间</th>
                                    <th class="text-center" width="170px">标题</th>
                                    <th class="text-center" width="320px">摘要</th>
                                    <th class="text-center" width="80px">状态</th>
                                    <th class="text-center" width="80px">设置</th>
                                </tr>
                                </thead>
                                <tbody id="filters-tbody"></tbody>
                            </table>
                        </div>
                        <div class="row">
                            <div class="col-lg-12" align="right">
                                <input class="btn btn-danger btn-lg" onclick="submit_form(0)" type="button" value="加入白名单" data-toggle="tooltip2" data-placement="top" title="审核淘汰" />
                                <input class="btn btn-info btn-lg" onclick="submit_form(1)" type="button" value="记录提交" data-toggle="tooltip2"  data-placement="top" title="审核通过" />
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-1"></div>
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
                    url: "<%=request.getContextPath()%>/SettingServlet?operate=examine",
                    type: "GET",
                    dataType: "json",
                    success: function(data) {
                        var objson = eval(data);
                        for ( var i = 0; i < objson.length; i ++ ) {
                            var row = document.createElement("tr");
                            row.setAttribute("id", objson[i].id);
                            row.setAttribute("class", "text-info");
                            row.setAttribute("status", objson[i].type);
                            var col0 = document.createElement("th");
                            col0.setAttribute("class", "text-center");
                            var select = document.createElement("input");
                            select.setAttribute("type", "checkbox");
                            select.setAttribute("name", "check");
                            select.setAttribute("value", objson[i].id);
                            col0.appendChild(select);
                            row.appendChild(col0);


                            var num = document.createElement("td");
                            num.setAttribute("class", "text-center");
                            var span_num = document.createElement("span");
                            span_num.innerHTML = objson[i].number;
                            num.appendChild(span_num);
                            row.appendChild(num);

                            var origin = document.createElement("td");
                            origin.setAttribute("class", "text-center");
                            var span0 = document.createElement("span");
                            span0.setAttribute("class", "label label-default");
                            span0.innerHTML = objson[i].origin;
                            origin.appendChild(span0);
                            row.appendChild(origin);

                            var crawldate = document.createElement("th");
                            crawldate.setAttribute("class", "text-center");
                            crawldate.appendChild(document.createTextNode(objson[i].crawldate));
                            row.appendChild(crawldate);
                            var pagedate = document.createElement("th");
                            pagedate.setAttribute("class", "text-center");
                            pagedate.appendChild(document.createTextNode(objson[i].pagedate));
                            row.appendChild(pagedate);

                            var title = document.createElement("th");
                            title.setAttribute("class", "text-center");
                            title.setAttribute("style", "overflow-x:hidden;");
                            title.appendChild(document.createTextNode(objson[i].title));
                            row.appendChild(title);
                            var source = document.createElement("th");
                            source.setAttribute("class", "text-center");
                            var ele = document.createElement("p");
                            ele.innerHTML = objson[i].source;
                            source.appendChild(ele);
                            row.appendChild(source);

                            var status = document.createElement("th");
                            status.setAttribute("class", "text-center");
                            var span_status = document.createElement("span");
                            span_status.setAttribute("class", "label label-info");
                            span_status.innerHTML = objson[i].status;
                            status.appendChild(span_status);
                            row.appendChild(status);

                            var check = document.createElement("th");
                            check.setAttribute("class", "text-center");
                            var button1 = document.createElement("a");
                            button1.setAttribute("class", "btn btn-success");
                            button1.setAttribute("target", "_blank");
                            button1.setAttribute("href", objson[i].url);
                            button1.innerHTML = "查看";
                            check.appendChild(button1);
                            row.appendChild(check);
                            document.getElementById("filters-tbody").appendChild(row);
                        }
                    }
                });
            }
        </script>
        <script>
            $(function () {
                $('[data-toggle="tooltip2"]').tooltip();
            });
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

            function submit_form(op) {
                var ajax_url = "<%=request.getContextPath()%>/SettingServlet?operate=examine-ok&type=" + op;
                var ajax_type = $("#examine-ok").attr('method');
                var ajax_data = $("#examine-ok").serialize();
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
                        location.reload();
                    }
                });
            }
        </script>
        <script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
        <%@include file="../footer.jsp"%>
    </body>
</html>
