<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Student Manager</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/head.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/content.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/foot.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery-2.1.1.min.js"></script>
</head>
<body>
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
    <div style="width: 800px;margin: 0 auto;background-color: #fff;height: 392px">
        <div style="margin: 20px auto;width: 400px;padding-top:30px;text-align: center">
            <IMG height=211 src="<%=request.getContextPath()%>/resource/img/error-error.gif" width=330px>
        </div>
        <ul style="height:75px;width: 300px;">
            <li style="font-size: 30px;display: inline-block;color: red">×</li>
            <li style="display: inline-block font-weight: bold;font-size: 20px;color: #2bebff"> 您的学号或密码不正确</li>
        </ul>

       <div style="text-align: center">
           <a href="<%=request.getContextPath()%>/index.jsp" style="width:200px;height:80px;background: #2EAFBB;border-radius: 5px;padding: 10px;font-size: 16px;font-weight: bold;color: #fff3f3">
               返回登录
           </a>
       </div>
    </div>
</div>
<footer class="footer"><h4>Copyright © 软件工程实践综合教学平台</h4></footer>
</body>
</html>