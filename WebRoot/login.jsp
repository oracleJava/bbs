<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录页面</title>
    <style type="text/css">
      td{font-size: 12px}
    </style>
    <script type="text/javascript">
       function reloadValidate(img){
         img.src = "user/generateValidateCode.action?num="+Math.random();
       }
    </script>
  </head>
  
  <body>
  <table width="700" height="300" border="1" align="center" bgcolor="#c0c0c0">
  <tr>
    <td align="left">登录界面</td>
  </tr>
  <tr>
  <td align="center" valign="top">
  <table>
  <form action="<%=basePath%>user/login" method="post">
    <tr>
       <td>用户名：</td>
       <td><input name="username" type="text" size="10"></td>
    </tr>    
    <tr>
       <td>密码：</td> 
       <td><input name="password" type="password" size="12"></td>
    </tr>
    <tr>    
       <td>验证码  ：</td>  
       <td><input type="text" name = "validateCode" size="10"><img onclick="reloadValidate(this)" style="cursor:pointer" src="<%=basePath%>user/generateValidateCode.action?num=<%= Math.random() %>"/></td>
    </tr>
    <tr>
       <td colspan="2" align="right"><input type="submit" value="登录"/></td>
    </tr>
  </form>
  </table>
  </td>
  </tr>
  </table>
  </body>
</html>
