<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>回复成功</title>

  </head>
  
  <body>
    回复成功！！！！<a href="<%=basePath%>post/readPost.action">点击</a>返回帖子。
  </body>
</html>
