<%@ page import="com.zzx.graduate.entity.StudentInfo" %>
<%@ page import="com.zzx.graduate.service.SelectService" %>
<%@ page import="com.zzx.graduate.entity.ExpGroupBean" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zzx.graduate.entity.ExpGroupMemBean" %>
<%@ page import="com.zzx.graduate.entity.TaskResultBean" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    if ( session.getAttribute("login") == null || session.getAttribute("login").equals("false") ) {
        response.sendRedirect(request.getContextPath() + "/nogrant.jsp");
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
    SelectService service = new SelectService();
    List<ExpGroupBean> beans = service.getExperimentByStuSN(student.getStuNumber());
%>
<div id="content">
    <div >
        <table  class="datalist">
            <caption><%=student.getStuName()%>，您所参与的所有实验</caption>
            <tr>
                <th scope="col">实验ID</th>
                <th scope="col">实验内容</th>
                <th scope="col">小组成员</th>
                <th scope="col">小组组长</th>
                <th scope="col">状态</th>
                <th scope="col">提交作业</th>
                <th scope="col">成绩查看</th>
            </tr>
            <%
                for ( int index = 1; index <= beans.size(); index ++ ) {
                    ExpGroupBean bean = beans.get(index-1);
                    if ( index % 2 == 1 ) {
            %>
            <tr>
            <%
                    } else {
            %>
            <tr class="altrow">
            <%
                    }
            %>
                <th scope="row"><%=bean.getExpID()%></th>
                <td><%=bean.getPrjDescription()%></td>
                <%
                    String members = "";
                    String leader = "";
                    List<ExpGroupMemBean> memBeans = service.getExpGroupMemByGroupID(bean.getGroupID());
                    TaskResultBean result;
                    for ( ExpGroupMemBean memBean : memBeans ) {
                        if ( memBean.getLeaderTag() == 1 ) {
                            result = service.getTaskResultByExpID_SubbmiterID(memBean.getStuID(), bean.getExpID());
                            leader = service.getStuNameByStuID(memBean.getStuID()).getName();
                        }
                        else {
                            members = "," + service.getStuNameByStuID(memBean.getStuID()).getName();
                        }
                    }
                    if ( members.length() > 0 )
                        members = members.substring(1);
                %>
                <td><%=members%></td>
                <td><%=leader%></td>
                <td><%=bean.getStatus()%></td>
            <%
                if ( bean.getAttachFile() != null ) {
            %>
                <td><a href="<%=request.getContextPath()%>"><%=bean.getAttach2File().toFile()%></a </td>
            <%
                } else {
            %>
                <td><a href="<%=request.getContextPath()%>/submit.jsp?groupID=<%=bean.getGroupID()%>">UP</a></td>
            <%
                }
            %>
                <td>一个按钮</td>
            <%
                }
            %>
            </tr>
        </table>
    </div>
</div>
<footer class="footer"><h4>Copyright © 软件工程实践综合教学平台</h4></footer>
</body>
</html>