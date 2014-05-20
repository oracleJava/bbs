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
    
    <title>后台登录</title>
    <script type="text/javascript">
       function check(form){
          with(form){
             if(form.name.value==null||form.name.value.match(/^\s*$/)){
                 alert("请输入用户名");
                 return false;
             }
             if(form.password.value==null||form.password.value.match(/^\s*$/)){
                 alert("请输入密码");
                 return false;
             }
             return true;
          }
       }
       function submitLog(form){
           if(check(form)){
              form.submit();
           }
       }
    </script>
	
  </head>
  
  <body bgcolor="#408080" style="text-align: center;">
    <div style="width: 100%;height:100%;">
    <c:if test="${admin == null}">
    <form action="manager/backUser/loginAdmin" method="post" target="_parent">
      <p style="font-size: 13px;">用户名：<input type="text" name="name" style="width:150px"/>密码：<input type="password" name="password" style="width:150px"/><font color="red">${info }</font><input type="button" value="登录" onclick="submitLog(this.form)"/></p>
    </form>
    </c:if>
    <c:if test="${admin != null}">
                 欢迎${admin.name }使用BBS后台管理系统！
    </c:if>
    </div>
  </body>
</html>
