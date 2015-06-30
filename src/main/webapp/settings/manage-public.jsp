<%@ page import="com.glacier.earthquake.monitor.server.pojo.User" %>
<%@ page import="com.glacier.earthquake.monitor.server.pojo.FilterPublicSentiment" %>
<%@ page import="com.glacier.earthquake.monitor.server.configure.user.FilterRuleMonitor" %>
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
        <%@include file="../navbar.jsp"%>
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
                            <button class="btn btn-info" onclick="uploadiv()">上传</button>
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
                                        <div class="col-md-1"></div>
                                        <div class="col-md-5">
                                            <div class="input-group">
                                                <span class="input-group-addon">匹配规则</span>
                                                <input type="text" class="form-control" placeholder="福州*发生地震" name="filter-name" value="" />
                                            </div>
                                        </div>
                                        <div class="col-md-5">
                                            <div class="input-group">
                                                <span class="input-group-addon">不含关键字</span>
                                                <input type="text" class="form-control" placeholder="汶川 新西兰" name="filter-unexist" value="" />
                                            </div>
                                        </div>
                                        <div class="col-md-1"></div>
                                    </div>
                                    <br />
                                    <div class="row">
                                        <div class="col-md-1"></div>
                                        <div class="col-md-10">
                                            <div class="input-group">
                                                <span class="input-group-addon">正则表达式</span>
                                                <input type="text" class="form-control" placeholder="S*福建[^，。；？！]发生[^，。；？！]" name="filter-matcher" value="" />
                                            </div>
                                        </div>
                                        <div class="col-md-1"></div>
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
                    <table class="table table-striped table-bordered table-hover" id="filters-table" style="table-layout: fixed;">
                        <thead>
                        <tr>
                            <th class="text-center" width="70px">创建人</th>
                            <th class="text-center">创建时间</th>
                            <th class="text-center">规则</th>
                            <th class="text-center">正则</th>
                            <th class="text-center">不包含</th>
                            <th class="text-center" width="140px">设置</th>
                        </tr>
                        </thead>
                        <tbody id="filters-tbody"></tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <div class="modal fade" id="uploadiv" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h3>上传文件</h3>
                    </div>
                    <div class="modal-body">
                        追加规则将会在原有规则的基础上增加新导入的规则；覆盖规则则会清除原有的规则使用新导入的规则
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" onclick="uploadFile(0)">追加规则</button>
                        <button type="button" class="btn btn-danger" onclick="uploadFile(1)">覆盖规则</button>
                        <button type="button" class="btn btn-warning" data-dismiss="modal">退出</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modifyRule" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <form method="post" id="modifyForm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h3>修改规则</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="input-group">
                                        <span class="input-group-addon">匹配规则</span>
                                        <input type="text" id="modifyName" class="form-control" name="filter-name" value="" />
                                        <input hidden="hidden" name="filter-id" value="" id="modifyID" />
                                    </div>
                                </div>
                            </div>
                            <br /><br />
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="input-group">
                                        <span class="input-group-addon">不含关键字</span>
                                        <input type="text" id="modifyUnexist" class="form-control" name="filter-unexist" value="" />
                                    </div>
                                </div>
                            </div>
                            <br /><br />
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="input-group">
                                        <span class="input-group-addon">正则表达式</span>
                                        <input type="text" id="modifyPattern" class="form-control" name="filter-matcher" value="" />
                                    </div>
                                </div>
                            </div>
                            <br /><br />
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success">更新规则</button>
                        </div>
                    </div>
                </form>
            </div>
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
            function modify(val)
            {
                var row = document.getElementById(val);
                $("#modifyID").attr("value", val);
                if ( row.querySelector('[name=name]').innerHTML != null ) {
                    $("#modifyName").attr("value", row.querySelector('[name=name]').innerHTML);
                }
                else {
                    $("#modifyName").attr("value", "");
                }
                if ( row.querySelector('[name=matcher]').innerHTML != null ) {
                    $("#modifyPattern").attr("value", row.querySelector('[name=matcher]').innerHTML);
                }
                else {
                    $("#modifyPattern").attr("value", "");
                }
                if ( row.querySelector('[name=unexist]').innerHTML != null ) {
                    $("#modifyUnexist").attr("value", row.querySelector('[name=unexist]').innerHTML);
                }
                else {
                    $("#modifyUnexist").attr("value", "");
                }
                $("#modifyRule").modal("toggle");
            }
            $("#modifyForm").submit(function() {
                var ajax_url = "<%=request.getContextPath()%>/SettingServlet?operate=modify&type=public";
                var ajax_type = $(this).attr('method');
                var ajax_data = $(this).serialize();
                $.ajax({
                    type: ajax_type,
                    url: ajax_url,
                    data: ajax_data,
                    success: function(msg) {    //msg是后台调用action时，你传过来的参数
                        if ( msg == "wrong" ) {
                            alert("修改失败");
                        } else if ( msg == "ok" ) {
                            alert("修改成功");
                        } else if ( msg == "permission denied" ) {
                            alert("您没有权限进行此操作");
                        }
                        location.reload();
                    }
                });
            });
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
                            var col0 = document.createElement("td");
                            col0.setAttribute("valign", "middle");
                            col0.setAttribute("class", "text-center");
                            var span0 = document.createElement("span");
                            span0.setAttribute("class", "label label-default");
                            span0.innerHTML = objson[i].submiter;
                            col0.appendChild(span0);
                            row.appendChild(col0);
                            var col1 = document.createElement("th");
                            col1.setAttribute("class", "text-center");
                            col1.setAttribute("style", "overflow-x:hidden;");
                            col1.appendChild(document.createTextNode(objson[i].create_time));
                            row.appendChild(col1);
                            var col2 = document.createElement("th");
                            col2.setAttribute("class", "text-center");
                            col2.setAttribute("name", "name");
                            col2.appendChild(document.createTextNode(objson[i].name));
                            row.appendChild(col2);
                            var col3 = document.createElement("th");
                            col3.setAttribute("class", "text-center");
                            col3.setAttribute("name", "matcher");
                            col3.appendChild(document.createTextNode(objson[i].matcher));
                            row.appendChild(col3);
                            var col4 = document.createElement("th");
                            col4.setAttribute("class", "text-center");
                            col4.setAttribute("name", "unexist");
                            col4.appendChild(document.createTextNode(objson[i].unexist));
                            row.appendChild(col4);

                            var col5 = document.createElement("th");
                            col5.setAttribute("class", "text-center");
                            var button = document.createElement("button");
                            button.setAttribute("class", "btn btn-warning");
                            button.setAttribute("value", objson[i].id);
                            button.setAttribute("name", "filter");
                            button.setAttribute("onClick", "modify(this.value)");
                            button.innerHTML = "修改";

                            var button2 = document.createElement("button");
                            button2.setAttribute("class", "btn btn-danger");
                            button2.setAttribute("value", objson[i].id);
                            button2.setAttribute("name", "filter");
                            button2.setAttribute("onClick", "del(this.value)");
                            button2.innerHTML = "删除";

                            var text = document.createTextNode(" ");
                            col5.appendChild(button);
                            col5.appendChild(text);
                            col5.appendChild(button2);
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
            function uploadiv() {
                $("#uploadiv").modal("toggle");
            }
            function uploadFile(op) {
                <%
                    User user = (User)session.getAttribute("login_user");
                    if ( user.getPrivilege() >= 1 ) {
                %>
                var fileObj = document.getElementById("file").files[0]; // 获取文件对象
                var FileController = "<%=request.getContextPath()%>/UploadServlet?type=public&op=" + op;                // 接收上传文件的后台地址

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
            $(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
        <script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
        <%@include file="../footer.jsp"%>
    </body>
</html>
