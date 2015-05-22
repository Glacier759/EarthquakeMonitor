<%@ page contentType="text/html; charset=UTF-8" %>
<%
    if ( session.getAttribute("login") == null || session.getAttribute("login").equals("false") ) {
        response.sendRedirect(request.getContextPath() + "/nogrant.jsp");
    }
    if ( request.getParameter("groupID") == null ) {
        response.sendRedirect(request.getContextPath() + "/select.jsp");
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery-2.1.1.min.js"></script>
    <script>

    </script>
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
<div id="content">
    <div id="submit">
        <form id="submitF" action="<%=request.getContextPath()%>/UploadServlet" method="post" enctype="multipart/form-data">
            <%
                String groupID = request.getParameter("groupID");
                if ( groupID == null ) {
                    response.sendRedirect(request.getContextPath() + "/select.jsp");
                }
                session.setAttribute("groupID", new Integer(groupID));
            %>
            <label for="file">文件名:</label>
            <input class="submit" type="file" name="file" id="file" />
            <br />
            <input type="submit" class="input_zhuce" name="submit" value="提交" />
        </form>
    </div>
</div>
<footer class="footer"><h4>Copyright © 软件工程实践综合教学平台</h4></footer>
</body>
</html>