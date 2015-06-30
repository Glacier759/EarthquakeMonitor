<%--
  Created by IntelliJ IDEA.
  User: glacier
  Date: 15-6-30
  Time: 下午3:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            <h4>知识库管理<span class="caret"></span></h4></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="<%=request.getContextPath()%>/settings/manage-disaster.jsp">灾情获取匹配式管理</a></li>
                            <li class="divider"></li>
                            <li><a href="<%=request.getContextPath()%>/settings/manage-public.jsp">舆情监测匹配式管理</a></li>
                            <li class="divider"></li>
                            <li><a href="<%=request.getContextPath()%>/settings/manage-whitelist.jsp">白名单管理</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            <h4>设置<span class="caret"></span></h4></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="<%=request.getContextPath()%>/settings/manage-warning.jsp">报警设置</a></li>
                            <li class="divider"></li>
                            <li><a href="<%=request.getContextPath()%>/settings/manage-examine.jsp">审核管理</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            <h4>用户管理<span class="caret"></span></h4></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="<%=request.getContextPath()%>/manager.jsp">修改资料</a></li>
                            <li class="divider"></li>
                            <li><a href="<%=request.getContextPath()%>/settings/manage-user.jsp">用户管理</a></li>
                        </ul>
                    </li>
                    <li><a href="<%=request.getContextPath()%>/about.jsp"><h4>关于</h4></a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <%if ( request.getServletPath().contains("/index") && session.getAttribute("login") != null && session.getAttribute("login").equals("true") ) {%>
                    <li><a href="#" id="logout"><h4>退出</h4></a></li>
                    <%}if ( request.getServletPath().equals("/showdata.jsp") ) {%>
                    <li><a href="<%=request.getContextPath()%>/setting.jsp"><h4>查看数据概览</h4></a></li>
                    <%}if ( request.getServletPath().equals("/setting.jsp") ) {%>
                    <li><a href="<%=request.getContextPath()%>/showdata.jsp"><h4>查看数据记录</h4></a></li>
                    <%}if ( request.getServletPath().contains("/settings/") ) {%>
                    <li><a href="<%=request.getContextPath()%>/showdata.jsp"><h4>查看数据记录</h4></a></li>
                    <%}if ( request.getServletPath().equals("/settings/manage-disaster.jsp") ) {%>
                    <li><a href="#"><h4>灾情获取匹配式管理</h4></a></li>
                    <%}if ( request.getServletPath().equals("/settings/manage-public.jsp") ) {%>
                    <li><a href="#"><h4>舆情监测匹配式管理</h4></a></li>
                    <%}if ( request.getServletPath().equals("/settings/manage-whitelist.jsp") ) {%>
                    <li><a href="#"><h4>白名单管理</h4></a></li>
                    <%}if ( request.getServletPath().equals("/settings/manage-warning.jsp") ) {%>
                    <li><a href="#"><h4>报警设置</h4></a></li>
                    <%}if ( request.getServletPath().equals("/settings/manage-examine.jsp") ) {%>
                    <li><a href="#"><h4>审核管理</h4></a></li>
                    <%}if ( request.getServletPath().equals("/manager.jsp") ) {%>
                    <li><a href="<%=request.getContextPath()%>/showdata.jsp"><h4>查看数据记录</h4></a></li>
                    <li><a href="#"><h4>修改资料</h4></a></li>
                    <%}if ( request.getServletPath().equals("/settings/manage-user.jsp") ) {%>
                    <li><a href="#"><h4>用户管理</h4></a></li>
                    <%}if ( request.getServletPath().contains("/about.jsp") ) {%>
                    <li><a href="<%=request.getContextPath()%>/showdata.jsp"><h4>查看数据记录</h4></a></li>
                    <%}%>
                </ul>
            </div>
        </div>
    </nav>
</div>
