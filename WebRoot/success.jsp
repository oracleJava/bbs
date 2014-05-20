<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发帖成功</title>

  </head>
  
  <body>
    发帖成功！！！！<a href="<%=basePath%>post/listPost.action">点击</a>返回帖子列表。
  </body>
</html>
