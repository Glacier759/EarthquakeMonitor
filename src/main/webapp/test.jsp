<%@ page import="com.zzx.graduate.service.QAService" %>
<%@ page import="com.zzx.graduate.entity.ChoiceQuestionBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Random" %>
<%@ page import="com.zzx.graduate.entity.ShortAnswerBean" %>
<%@ page import="com.zzx.graduate.entity.ShortQuestionBean" %>
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery-2.1.1.min.js"></script>
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
    Random random = new Random();
    QAService service = new QAService();
    String questionType = "a";
    if ( random.nextBoolean() ) {   questionType = "b"; }
    List<ChoiceQuestionBean> choiceBeans = service.getChoiceQuestion(questionType);
    List<ShortQuestionBean> shortBeans = service.getShortQuestion(questionType);
    session.setAttribute("choiceBeans", choiceBeans);
    session.setAttribute("shortBeans", shortBeans);
%>
<div id="content">
        <div class="test">
        <table class="datalist">
            <tr>
                <th scope="col">试题类型</th>
            </tr>
            <tr >
                <td><%=questionType%>卷</td>
            </tr>
        </table>
          <form class="question" action="<%=request.getContextPath()%>/TestServlet" method="post">
              <p>不定项选择题</p>
              <%
                for ( int index = 0; index < choiceBeans.size(); index ++ ) {
                    ChoiceQuestionBean choiceBean = choiceBeans.get(index);
              %>
              <ul>
                  <li>第<%=index+1%>题</li>
                  <li><%=choiceBean.getQuestionName()%></li>
                  <li>
                      <input name="a<%=index+1%>" class="check" type="checkbox" value="A"  /><%=choiceBean.getOptionA()%>
                      <input name="a<%=index+1%>" class="check" type="checkbox" value="B" /><%=choiceBean.getOptionB()%>
                      <input name="a<%=index+1%>" class="check" type="checkbox" value="C" /><%=choiceBean.getOptionC()%>
                      <input name="a<%=index+1%>" class="check" type="checkbox" value="D" /><%=choiceBean.getOptionD()%>
                  </li>
              </ul>
              <%
                }
              %>
              <p>简答题</p>
              <div class="jianda">
                  <%
                    for ( int index = 0; index < shortBeans.size(); index ++ ) {
                        ShortQuestionBean shortBean = shortBeans.get(index);
                  %>
                  <ul>
                      <li>第<%=index+1%>题</li>
                      <li><%=shortBean.getQuestionName()%></li>
                      <li><input name="a<%=index+6%>" class="check" type="text" value="" /></li>
                  </ul>
                  <%
                    }
                  %>
              </div>
              <input type="submit" class="input_login center" name="submit" value="提交" />
          </form>
        </div>
</div>
<footer class="footer"><h4>Copyright © 软件工程实践综合教学平台</h4></footer>
</body>
</html>