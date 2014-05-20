<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>menu</title>
    <style type="text/css">
ul {
	list-style-type: none;
	padding: 0 0;
}

li {
	padding: 0 0;
	background-color: #3D81A3
	
}

a {
	display: block;
	text-decoration: none;
	padding: 0 0;
	margin:0 0;
	border: 1px white;
	color: white;
	font-size: 14
}

a:HOVER {
	color: black;
	border: 1px solid white;
	background-color: #3D81A3;
	font-size: 15
}
</style>
	
  </head>
  
  <body style="padding: 0 0;margin: 0 0;background-color:#408080;">
     <div style="padding: 0 0;margin: 0 0;">
     <ul style="text-align: center;padding: 0 0">
       <li><c:if test="${admin.name!=null}"><a href="manager/modify.jsp" target="right">密码修改</a></c:if><c:if test="${admin.name==null}"><font style="font-size: 14px;cursor:pointer"><span onclick="alert('请登录')">密码修改</span></font></c:if></li>
       <li><c:if test="${admin.name!=null}"><a href="<%=basePath%>manager/backUser/memberList.action" target="right">会员管理</a></c:if><c:if test="${admin.name==null}"><font style="font-size: 14px;cursor:pointer"><span onclick="alert('请登录')">会员管理</span></font></c:if></li>
       <li><c:if test="${admin.name!=null}"><a href="manager/boardManage.jsp" target="right">版块管理</a></c:if><c:if test="${admin.name==null}"><font style="font-size: 14px;cursor:pointer"><span onclick="alert('请登录')">版块管理</span></font></c:if></li>
       <li><c:if test="${admin.name!=null}"><a href="manager/post/postManage.action" target="right">帖子管理</a></c:if><c:if test="${admin.name==null}"><font style="font-size: 14px;cursor:pointer"><span onclick="alert('请登录')">帖子管理</span></font></c:if></li>
       <li><c:if test="${admin.name!=null}"><a href="manager/logout.jsp" target="_parent">退出后台</a></c:if><c:if test="${admin.name==null}"><font style="font-size: 14px;cursor:pointer"><span onclick="alert('请登录')">退出后台</span></font></c:if></li>
     </ul>
     </div>
  </body>
</html>
