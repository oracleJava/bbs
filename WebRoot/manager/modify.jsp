<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 
    <title>修改密码</title>
    <style type="text/css">
      
    </style>
    <script type="text/javascript">
       function check(form){
           with(form){
               if(form.password.value==null||form.password.value.match(/^\s*$/)){
                  alert("请输入原密码");
                  return false;
               }
               if(form.newpass.value==null||form.newpass.value.match(/^\s*$/)){
                  alert("请输入新密码");
                  return false;
               }
               if(form.newpass1.value==null||form.newpass1.value.match(/^\s*$/)){
                  alert("请确认新密码");
                  return false;
               }
               if(form.newpass.value!=form.newpass1.value){
                  alert("密码不匹配，请重新确认密码");
                  return false;
               }
               return true;
           }
       }
       function submitModify(form){
           if(check(form)){
              form.submit();
           }
       }
    </script>
  </head>
  
  <body bgcolor="#408080">
  <form action="<%=basePath %>manager/backUser/modifyPass.action" method="post">
    <table align="center">
      <tr>
        <td align="right"><font size="2">输入原密码</font>:</td><td><input type="password" name="password" style="width:150px"/></td>
      </tr>
      <tr>
        <td align="right"><font size="2">输入新密码</font>:</td><td align="left"><input type="password" name="newpass" style="width:150px"/></td>
      </tr>
      <tr>
        <td align="right"><font size="2">再次输入新密码</font>:</td><td align="left"><input type="password" name="newpass1" style="width:150px"/></td>
      </tr>
      <tr>
      <td colspan="2" align="right">
       <font color="red" size="2">${info }</font><input type="button" value="确认提交" onclick="submitModify(this.form)"/>
      </td>
      </tr>
    </table>
   </form>
  </body>
</html>
