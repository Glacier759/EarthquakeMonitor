<%@ page import="com.zzx.graduate.entity.StudentInfo" %>
<%@ page import="com.zzx.graduate.entity.ExperimentBean" %>
<%@ page import="com.zzx.graduate.service.ExperimentService" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    if ( session.getAttribute("login") == null || session.getAttribute("login").equals("false") ) {
        response.sendRedirect(request.getContextPath() + "/nogrant.jsp");
    }
    else if ( request.getParameter("expID") == null ) {
        response.sendRedirect(request.getContextPath() + "/student.jsp");
    }
%>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Student Manager</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/head.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/content.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/foot.css">
</head>
<body>
<%
    if ( session.getAttribute("login") != null && session.getAttribute("login").equals("true") ) {
%>
    <span class="quick">
        <a href="LogoutServlet">退出登录</a>
    </span>
<%
    }
%>
<header id="header">
    <h1>软件工程实践综合教学平台</h1>
    <nav >
        <ul>
            <li><a href="<%=request.getContextPath()%>/student.jsp">实验安排</a></li>
            <li><a href="<%=request.getContextPath()%>/select.jsp">成绩查询</a></li>
            <li><a href="<%=request.getContextPath()%>/test.jsp">在线测试</a></li>
        </ul>
    </nav>
</header>
<%
    StudentInfo student = (StudentInfo)session.getAttribute("stu_info");
    ExperimentService service = new ExperimentService(student.getStuNumber());
    String expID = request.getParameter("expID");
    if ( expID == null ) {
        response.sendRedirect(request.getContextPath() + "/student.jsp");
    }
    ExperimentBean experimentBean = service.getExperimentByExpID(new Integer(expID));
%>
<div id="content">
    <div id="groupLogin">
        <form id="zhuce" action="<%=request.getContextPath()%>/RegisterServlet" method="post">
            <h4 class="zhuce">实验小组注册</h4>
            <input type="hidden" id="expID" name="expID" value="<%=request.getParameter("expID")%>">
            <ul class="zhuceList">
                <li class="zhuceList-li">小组名称：</li>
                <li>
                    <input id="groupName" name="groupName" class="zhuceList-li-input" type="text" value="">
                </li>
            </ul>
            <ul class="zhuceList">
                <li class="zhuceList-li">组&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长：</li>
                <li>
                    <input id="leader" name="leader"  class="zhuceList-li-input" type="text" value="<%=student.getStuName()%>">
                </li>
            </ul>
            <ul class="zhuceList">
                <li class="zhuceList-li">组长学号：</li>
                <li>
                    <input id="leaderId" name="leaderId"  class="zhuceList-li-input" type="text" value="<%=student.getStuNumber()%>">
                </li>
            </ul>
            <ul class="zhuceList">
                <li class="zhuceList-li">实验题目：</li>
                <li >
                    <input id="testName" name="testName" class="zhuceList-li-input" type="text" value="<%=service.getExpTaskByExpID(experimentBean.getExpID()).getTaskName()%>">
                </li>
            </ul>
            <ul class="zhuceList">
                <li class="zhuceList-li">开始时间：</li>
                <li >
                    <input id="timeS" name="timeS" class="zhuceList-li-input" type="text" value="<%=experimentBean.getCreateTime()%>">
                </li>
            </ul>
            <ul class="zhuceList">
                <li class="zhuceList-li">项目描述：</li>
                <li >
                    <input id="introduce" name="introduce" class="zhuceList-li-input" type="text" value="<%=service.getExpTaskByExpID(experimentBean.getExpID()).getDescription()%>">
                </li>
            </ul>
            <ul class="zhuceList">
                <table  class="zhuceList-memberName">
                    <caption>小组成员</caption>
                    <tr>
                        <th scope="col">姓名</th>
                        <th scope="col">学号</th>
                    </tr>
                    <tr>
                        <td scope="row">
                           <input id="memberName0"   name="memberName" value="">
                        </td>
                        <td>
                            <input id="member0" name="memberNum" value="">
                        </td>

                    </tr>
                    <tr class="altrow">
                        <td scope="row">
                            <input id="memberName1" name="memberName"  value="">
                        </td>
                        <td>
                            <input id="member1" name="memberNum" value="">
                        </td>

                    </tr>
                    <tr>
                        <td scope="row">
                            <input id="memberName2" name="memberName"  value="">
                        </td>
                        <td>
                            <input id="member2" name="memberNum" value="">
                        </td>

                    </tr>
                    <tr class="altrow">
                        <td scope="row">
                            <input id="memberName3" name="memberName"  value="">
                        </td>
                        <td>
                            <input id="member3" name="memberNum" value="">
                        </td>
                    </tr>
                    <tr class="altrow">
                        <td scope="row">
                            <input id="memberName4" name="memberName"  value="">
                        </td>
                        <td>
                            <input id="member4" name="memberNum" value="">
                        </td>
                    </tr>
                    </table>
            </ul>

            <input name="zhuce" class="input_zhuce" type="submit" value="注册" >
        </form>
    </div>
</div>
<footer class="footer"><h4>Copyright © 软件工程实践综合教学平台</h4></footer>
</body>
</html>