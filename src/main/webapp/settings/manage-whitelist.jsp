<%@ page import="com.glacier.earthquake.monitor.server.pojo.User" %>
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
        <link href="<%=request.getContextPath()%>/resource/fonts/google_api.css?family=Montserrat|Varela+Round"
              rel="stylesheet">
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
                        <ul class="nav navbar-nav navbar-left">
                            <li><a href="<%=request.getContextPath()%>/index.jsp"><h4>主页</h4></a></li>
                            <li><a href="#" onclick="system()"><h4>系统</h4></a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><h4>设置<span class="caret"></span></h4></a>
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
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><h4>用户<span class="caret"></span></h4></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="<%=request.getContextPath()%>/manager.jsp">修改资料</a></li>
                                    <li class="divider"></li>
                                    <li><a href="<%=request.getContextPath()%>/settings/manage-user.jsp">用户管理</a></li>
                                </ul>
                            </li>
                            <li><a href="<%=request.getContextPath()%>/about.jsp"><h4>关于</h4></a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="<%=request.getContextPath()%>/showdata.jsp"><h4>查看数据记录</h4></a></li>
                            <li><a href="#"><h4>白名单管理</h4></a></li>
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
                        <div class="panel-heading">增加白名单过滤条目</div>
                        <div class="panel-body">
                            <div id="filterinfo">
                                <div class="row" name="disaster" count="1">
                                    <div class="col-lg-12">
                                        <div class="input-group">
                                            <span class="input-group-addon">白名单条目</span>
                                            <input type="text" class="form-control" placeholder="http://www.baidu.com" name="whitelist" value="" />
                                        </div>
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
                            <th class="text-center" width="200px">创建时间</th>
                            <th class="text-center">地址</th>
                            <th class="text-center" width="80px">设置</th>
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
                    url: "<%=request.getContextPath()%>/SettingServlet?operate=delete&type=whitelist",
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
                    url: "<%=request.getContextPath()%>/SettingServlet?operate=table&type=whitelist",
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
                            col1.appendChild(document.createTextNode(objson[i].create_time));
                            row.appendChild(col1);
                            var col2 = document.createElement("th");
                            col2.setAttribute("class", "text-center");
                            col2.setAttribute("style", "overflow-x:hidden;");
                            col2.appendChild(document.createTextNode(objson[i].url));
                            row.appendChild(col2);
                            var col3 = document.createElement("th");
                            col3.setAttribute("class", "text-center");
                            var button = document.createElement("button");
                            button.setAttribute("class", "btn btn-danger");
                            button.setAttribute("value", objson[i].id);
                            button.setAttribute("name", "filter");
                            button.setAttribute("onClick", "del(this.value)");
                            button.innerHTML = "删除";
                            col3.appendChild(button);
                            row.appendChild(col3);
                            document.getElementById("filters-tbody").appendChild(row);
                        }
                    }
                });
            }
        </script>
        <script>
            $("#form-addfilter").submit(function() {
                var ajax_url = "<%=request.getContextPath()%>/SettingServlet?operate=addfilter&type=whitelist";
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
                var FileController = "<%=request.getContextPath()%>/UploadServlet?type=whitelist&op=" + op;                // 接收上传文件的后台地址

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
