<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.bbs.df.po.Board"%>
<%@page import="com.bbs.df.service.BoardService"%>
<%@page import="com.bbs.df.service.impl.BoardManager"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>版块管理</title>
    <style type="text/css">
    td{font-size: 12;}
    a{text-decoration: none}
    table{margin: 0 0}
    </style>
	
  </head>
  
  <body topmargin="0" leftmargin="150px">
    <table align="center" width="80%" cellspacing="0">
       <tr><td colspan="3" align="right"><a href="<%=basePath %>manager/addboard.jsp">添加版块</a></td></tr>
       <tr style="background-color:#408080"><td>父版块</td><td>子版块</td><td>操作</td></tr>
       <% 
          ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
          BoardService bs = (BoardService)ac.getBean("boardmgr");
          List<Board> fboards = bs.findMainBoards(); 
          for(Board b:fboards){
       %>
       <tr onmouseover="this.style.backgroundColor='#3D81A3'" onmouseout="this.style.backgroundColor='#FFFFFF'">
         <td width="60%"><%=b.getName() %>(<%=b.getIntroduction() %>)</td>
         <td>
           <% 
              int pid = b.getId();
              List<Board> sboards = bs.findBoardsByPid(pid);
              int i=0;
              for(Board bd:sboards){
              ++i;
           %>
                <%=i+"."+bd.getName() %><a href="<%=basePath %>manager/board/delBoard.action?bid=<%=bd.getId() %>" target="_self"><font color="white">-->删除</font></a><br>
           <% 
              }
           %> 
         </td>
         <td><a href="<%=basePath %>manager/board/delBoard.action?bid=<%=b.getId() %>" target="_self">删除</a></td>
       </tr>
       <%} %>
    </table>
  </body>
</html>
