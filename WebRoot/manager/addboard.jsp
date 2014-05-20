<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="com.bbs.df.service.BoardService"%>
<%@page import="com.bbs.df.po.Board"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加版块</title>
    <style type="text/css">
      td{font-size: 12px}
    </style>
    <script>
       function check(form){
           with(form){
               if(form.name.value==null||form.name.value.match(/^\s*$/)){
                  alert("版块名不能为空");
                  return false;
               }
               if(form.id.value==-1){
                   if(form.introduction.value==null||form.introduction.value.match(/^\s*$/)){
                      alert("介绍不能为空");
                      return false;
                   }
               }
               return true;
           }     
       }
       function submitAdd(form){
           if(check(form)){
              form.submit();
           }
       }
       function hideOrShow(intro){
          if(intro!=-1){
             document.all.intro.style.display="none";
             
          }else{
             document.all.intro.style.display="";
          }
       }
    </script>
  </head>
  
  <body topmargin="0"  bgcolor="#408080">
  <%
    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    BoardService  bs = (BoardService)ac.getBean("boardmgr");
    List<Board> boards = bs.findMainBoards();     
  %>
  <form action="<%=basePath %>manager/board/addBoard.action" method="post"> 
  <table align="center">
  
     <tr><td align="right">版块名：</td><td><input type="text" name="name"></td></tr>
     <tr>
        <td>所属版块</td>
        <td>
        <select name="id" onchange="hideOrShow(this.value)">
        <option value="-1">无</option>
        <% for(Board b:boards){ %>
        <option value="<%=b.getId() %>"><%=b.getName() %></option>
        <%} %>
        </select></td>
      </tr>
      <tr id="intro"><td>介绍：</td><td><textarea name="introduction"></textarea></td></tr>
      <tr><td colspan="2" align="right"><input type="button" value="添加" onclick="submitAdd(this.form)"/></td></tr>
  
  </table>
  </form>
  </body>
</html>
