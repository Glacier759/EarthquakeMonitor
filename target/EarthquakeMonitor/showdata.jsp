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
        <%@include file="header.jsp"%>
        <%@include file="system.jsp"%>
        <%@include file="navbar.jsp"%>
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
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <div id="filters-div">
                    <div class="row">
                        <button type="button" class="btn btn-info" onclick="showAll()">全部</button>
                        <button type="button" class="btn btn-warning" onclick="showDis()">灾情获取</button>
                        <button type="button" class="btn btn-success" onclick="showPub()">舆情监控</button>
                        <a type="button" class="btn btn-default" href="<%=request.getContextPath()%>/settings/manage-whitelist.jsp#tips">白名单</a>
                    </div>
                    <br />
                    <div class="row">
                        <table class="table table-striped table-bordered table-hover" id="filters-table" style="table-layout: fixed;">
                            <thead>
                            <tr>
                                <th class="text-center" width="50px">序号</th>
                                <th class="text-center" width="60px">来源</th>
                                <th class="text-center" width="110px">获取时间</th>
                                <th class="text-center" width="110px">发布时间</th>
                                <th class="text-center" width="170px">标题</th>
                                <th class="text-center" width="320px">摘要</th>
                                <th class="text-center" width="80px">状态</th>
                                <th class="text-center" width="100px">审核人</th>
                                <th class="text-center" width="110px">审核时间</th>
                                <th class="text-center" width="80px">设置</th>
                            </tr>
                            </thead>
                            <tbody id="filters-tbody"></tbody>
                        </table>
                    </div>
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

                            var num = document.createElement("td");
                            num.setAttribute("class", "text-center");
                            var span_num = document.createElement("span");
//                            span_num.setAttribute("class", "label label-success");
                            span_num.innerHTML = objson[i].number;
                            num.appendChild(span_num);
                            row.appendChild(num);

                            var col0 = document.createElement("td");
                            col0.setAttribute("class", "text-center");
                            var span0 = document.createElement("span");
                            span0.setAttribute("class", "label label-default");
                            span0.innerHTML = objson[i].origin;
                            col0.appendChild(span0);
                            row.appendChild(col0);

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

                            var examiner = document.createElement("th");
                            examiner.setAttribute("class", "text-center");
                            examiner.appendChild(document.createTextNode(objson[i].examiner));
                            row.appendChild(examiner);

                            var examine_date = document.createElement("th");
                            examine_date.setAttribute("class", "text-center");
                            examine_date.appendChild(document.createTextNode(objson[i].examinedate));
                            row.appendChild(examine_date);

                            var check = document.createElement("th");
                            check.setAttribute("class", "text-center");
//                            var button1 = document.createElement("button");
//                            button1.setAttribute("class", "btn btn-success");
//                            button1.setAttribute("type", "button");
//                            button1.setAttribute("value", objson[i].id);
//                            button1.setAttribute("onclick", "show(this.value)");
//                            button1.innerHTML = "查看";
//                            col4.appendChild(button1);
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
                $('[data-toggle="tooltip"]').tooltip();
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
