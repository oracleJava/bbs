<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    
    <title>发帖</title>
    <style type="text/css">
      td{font-size: 12px}
    </style>
  </head>
  
  <body>
  <form action="<%=basePath %>post/newPost.action" method="post">
    <table width="80%" align="center">
        <input type="hidden" name="bid" value=<%=Integer.parseInt(request.getParameter("bid")) %>>
        <tr><td>标题：<input type="text" name="title"/></td></tr>
        <tr><td colspan="2">
        <label for="content">内容：</label>
        <textarea name="content" rows="10" cols="100%"></textarea></td></tr>
        <tr><td colspan="2" align="right"><input type="submit" value="提交"></td></tr>
    </table>
    <ckeditor:replace replace="content" basePath="ckeditor/" />
  </form>
  </body>
</html>
