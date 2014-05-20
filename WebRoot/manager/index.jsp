<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>后台首页</title>
   
  </head>
  

    <frameset rows="20%,80%">
       <frame src="top.jsp" />
       <frameset cols="21%,79%">
         <frame src="left.jsp" name="left"/>
         <frame name="right" src="login.jsp"/>
       </frameset>
    </frameset>

</html>
