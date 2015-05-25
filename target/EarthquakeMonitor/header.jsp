<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="button-container" id="menu">
  <span class="top" id="span-top" key="menu"></span>
  <span class="middle"></span>
  <span class="bottom"></span>
</div>
<%
  if ( session.getAttribute("login") == null || session.getAttribute("login").equals("false") ) {
%>
<div class="overlay" id="overlay">
  <nav class="overlay-menu">
    <ul>
      <li><a href="<%=request.getContextPath()%>/index.jsp">主页</a></li>
      <li><a href="#" class="unlogin">系统</a></li>
      <li><a href="#" class="unlogin">设置</a></li>
      <li><a href="#" class="unlogin">用户管理</a></li>
      <li><a href="#" class="unlogin">关于</a></li>
    </ul>
  </nav>
</div>
<%} else {%>
<div class="overlay" id="overlay">
  <nav class="overlay-menu">
    <ul>
      <li><a href="<%=request.getContextPath()%>/index.jsp">主页</a></li>
      <li><a href="#" id="system">系统</a></li>
      <li><a href="<%=request.getContextPath()%>/setting.jsp">设置</a></li>
      <li><a href="<%=request.getContextPath()%>/manager.jsp">用户管理</a></li>
      <li><a href="<%=request.getContextPath()%>/about.jsp">关于</a></li>
    </ul>
  </nav>
</div>
<%}%>
