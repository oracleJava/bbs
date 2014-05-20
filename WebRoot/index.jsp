<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.bbs.df.po.Board"%>
<%@page import="com.bbs.df.dao.impl.BoardHibernateDao"%>
<%@page import="com.bbs.df.service.BoardService"%>
<%@page import="com.bbs.df.service.PostService"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>论坛首页</title>
    <style type="text/css">
      td{font-size: 12px}
      a{text-decoration:none; color: #4491CA} 
      a:hover {text-decoration:underline;}
      #loginform {
        position: absolute;
        z-index: 4;
        width: 700px;
        height: 300px;
        background-color: #F5FFE0;
        border: 1px solid #A5CF3D;
      }
      #regform {
        position: absolute;
        z-index: 4;
        width: 700px;
        height: 300px;
        background-color: #F5FFE0;
        border: 1px solid #A5CF3D;
      }
    </style>
    <script type="text/javascript" src="js/data.js"></script>
    <script type="text/javascript">
       function reloadValidate(img){
         img.src = "user/generateValidateCode.action?num="+Math.random();
       }
       function popLogin(){
          if (true) {
<!--		  document.getElementById("mask").style.height=document.body.clientHeight + 'px';-->
<!--	      document.getElementById("mask").style.width=document.body.clientWidth +'px';-->
<!--		  document.getElementById("mask").style.display='block';-->
 		  document.getElementById("loginform").style.top=(window.screen.availHeight -480)/2 + 'px';
          //alert(document.body.scrollHeight);
          //alert(document.getElementById("loginform").style.top);
	      document.getElementById("loginform").style.left=(document.body.clientWidth -600)/2 + 'px';
          document.getElementById("loginform").style.display='block';
         }
       }
       function popReg(){
          if (true) {
<!--		  document.getElementById("mask").style.height=document.body.clientHeight + 'px';-->
<!--	      document.getElementById("mask").style.width=document.body.clientWidth +'px';-->
<!--		  document.getElementById("mask").style.display='block';-->
 		  document.getElementById("regform").style.top=(window.screen.availHeight -480)/2 + 'px';
          //alert(document.body.scrollHeight);
          //alert(document.getElementById("loginform").style.top);
	      document.getElementById("regform").style.left=(document.body.clientWidth -600)/2 + 'px';
          document.getElementById("regform").style.display='block';
         } 
       }
       function closeLogin(){
          document.getElementById("loginform").style.display='none';
       }
       function closeReg(){
          document.getElementById("regform").style.display='none';
       }
       function checkLogin(form){
         with(form){
             if(form.username.value==null||form.username.value.match(/^\s*$/)){
                alert("请输入用户名！");
                return false;
             }
             if(form.password.value==null||form.password.value.match(/^\s*$/)){
                alert("请输入密码！");
                return false;
             }
             if(form.validateCode.value==null||form.validateCode.value.match(/^\s*$/)){
                alert("请输入验证码！");
                return false;
             }
             
         }   
         return true;
       }
       function submitLogin(form){
         if(checkLogin(form)){
            form.submit();
         }
       }
       function checkReg(form){
          with(form){
             if(form.name.value==null||form.name.value.match(/^\s*$/)){
                alert("用户名不能为空");
                return false;
             }
             if(form.password.value==null||form.password.value.match(/^\s*$/)){
                alert("密码不能为空");
                return false;
             }
             if(form.repassword.value==null||form.repassword.value.match(/^\s*$/)){
                alert("请确认密码");
                return false;
             }
             if(form.photo.value==null||form.photo.value.match(/^\s*$/)){
                alert("头像不能为空");
                return false;
             }
             if(form.password.value!=form.repassword.value){
                alert("确认密码错误");
                return false;
             }
             return true;
          }
       }
       function submitReg(form){
           if(checkReg(form)){
               form.submit();
           }
           
       }
    </script>
  </head>
<%
    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    BoardService  bs = (BoardService)ac.getBean("boardmgr"); 
    PostService ps = (PostService)ac.getBean("postmgr");
    List<Board> boards = bs.findMainBoards();  
    
    
%> 
  <body>
  <table width=1200 border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="100px" bgcolor="#00B0E6" colspan="2" align="center"><font color="white" size="13">论坛首页</font></td>
  </tr>
  <tr>
   <td>当前时间：
   <span id="time">
   <script>      
       document.getElementById('time').innerHTML=getDate(); 
       setInterval("document.getElementById('time').innerHTML=getDate();",1000);      
   </script> 
   </span>
   </td>
   <td align="right">
   <c:if test="${user==null}">
   <a href="#" onclick="popLogin()">会员</a>|<a href="#" onclick="popReg()">注册</a>
   </c:if>
   <c:if test="${user!=null}">欢迎${user.name} <a href="<%=basePath%>user/logout.action">注销</a></c:if>
   </td>
  </tr>
  
  </table>
  <!-- 登录界面 开始   -->
  <div id="loginform" style="display:none">
    <table width="700" height="300" border="0" align="center" bgcolor="#E9EFF3">
  <tr>
    <td align="left" height="50px"><font color="#00B0E6" size="3">登录界面</font></td>
    <td align="right"><a href="#" onclick="closeLogin()">关闭</a></td>
  </tr>
  <tr>
  <td align="center" valign="top" colspan="2">
  <table>
  <form action="<%=basePath%>user/login" method="post">
    <tr>
       <td align="right">用户名：</td>
       <td><input name="username" type="text" style="width:150px"></td>
    </tr>    
    <tr>
       <td align="right">密码：</td> 
       <td><input name="password" type="password" style="width:150px"></td>
    </tr>
    <tr>    
       <td align="right">验证码：</td>  
       <td><input type="text" name = "validateCode" style="width:60px"><img onclick="reloadValidate(this)" style="cursor:pointer" src="<%=basePath%>user/generateValidateCode.action?num=<%= Math.random() %>"/></td>
    </tr>
    <tr>
       <td colspan="2" align="right"><input type="button" value="登录" onclick="submitLogin(this.form)"/></td>
    </tr>
  </form>
  </table>
  </td>
  </tr>
  </table>
  </div>
  <!-- 登录界面 介绍   -->
  <!-- 注册界面开始 -->
  <div id="regform" style="display:none">
  <table width="700" height="300" border="0" align="center" bgcolor="#E9EFF3">
  <tr>
    <td align="left" height="50px"><font color="#00B0E6" size="3">注册界面</font></td>
    <td align="right"><a href="#" onclick="closeReg()">关闭</a></td>
  </tr>
  <tr>
  <td align="center" valign="top" colspan="2">
  <s:form action="register" method="post" namespace="/user" enctype="multipart/form-data">
    <table>
       <tr><td align="right">用户名：</td><td><input type="text" name="name" style="width: 150px"/><font color="red">*</font></td></tr>
       <tr><td align="right">密码：</td><td><input type="password" name="password" style="width: 150px"/><font color="red">*</font></td></tr>
       <tr><td align="right">确认密码：</td><td><input type="password" name="repassword" style="width: 150px"/><font color="red">*</font></td></tr>
       <tr><td align="right">头像：</td><td><input type="file" name="photo"/><font color="red">*</font></td></tr>
       <tr><td align="right">个性签名：</td><td><textarea cols="20" name="sign"></textarea></td></tr>
       <tr><td colspan="2" align="right"><input type="button" value="注册" onclick="submitReg(this.form)"/></td></tr>
    </table>
<!--    <s:textfield name="name" label="用户名" size="13.9" /><s:label>*</s:label>-->
<!--    <s:password name="password" label="密码" size="15"/>-->
<!--    <s:password name="repassword" label="确认密码" size="15"/>-->
<!--    <s:file name="photo" label="头像" size="15"/>-->
<!--    <s:textarea name="sign" label="个性签名"/>-->
<!--    <s:submit value="注册" onclick="submitReg(this.form)"/>-->
<!--  </s:form>-->
  </td>
  </tr>
  </table>
  </div>
  <!-- 注册界面结束  -->
  <% for(Board b:boards){ %>
  <table width=1200 border="0" cellpadding="2" cellspacing="0"  align="center">
        <tr style="background: #E9EFF3;height: 32"><td colspan="4"><b><font size="2" color="#121442"><%=b.getName() %></font></b>（<%=b.getIntroduction() %>）</td></tr>
        
        <tr height="32">
              <td width="65px"></td>
              <td>版块</td>
              <td>主题</td>
              <td align="center">帖数</td>
        </tr>
        <% List<Board> children = b.getChildren();
           for(Board child:children){
           if(child!=null){
           int count = ps.queryCountByBid(child.getId());
           int mainCount = ps.queryMainCountByBid(child.getId());
         %>
        <tr onmouseover="this.style.backgroundColor='#E9EFF3'" onmouseout="this.style.backgroundColor='#FFFFFF'">
                <td width="70px" align="center" >
                 <img src="<%=basePath%>images/red_forum.gif" style="padding: 0 0">
                </td>
                <td width="700">
                   <a href="<%=basePath%>post/listPost.action?bid=<%=child.getId()%>"><%=child.getName() %></a><br/>
                </td>
                <td><%=mainCount %></td>
                <td align="center"><%=count %></td>
       </tr>
       <%}} %>
       
     </table>
     <br/>
   <% }%>
   <table width=1200 align="center">
     <tr>
       <td>
         <b>在线用户</b>-${onlineNum}人在线 -${guestNum }位游客  ,${memberNum}位<a href ="<%=basePath%>userlist.jsp">注册会员</a>
       </td>
     </tr>
   </table>
  </body>
</html>
