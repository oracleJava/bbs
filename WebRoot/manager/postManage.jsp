<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>帖子管理</title>
    <style>
     td{font-size: 12;text-align: center;height:24}
     a{text-decoration: none}
    </style>
	
  </head>
  
  <body topmargin="0" leftmargin="100px">
    <table align="center" width="90%" cellpadding="0" cellspacing="0" border=0 style="margin: 0pt; padding: 0pt;" >
      
      <tr> 
        <td colspan="6">
          <form action="<%=basePath%>manager/post/postManage.action" method="post">
                               版块：<input type="text" name="boardName" style="width: 80px">标题：<input type="text" name="title" style="width: 80px">作者：<input type="text" name="authorName" style="width: 80px"><input type="submit" value="查询">
          </form>
        </td>
      </tr>

      <tr>
         <td colspan="6" style="text-align: right;"><a href="<%=basePath%>manager/post/postManage.action?pageNo=1">首页 </a>  第 <fmt:formatNumber>${page.pageNo}</fmt:formatNumber>页  
       <c:if test="${page.pageNo>1}">
       <a href="<%=basePath%>manager/post/postManage.action?pageNo=${page.pageNo-1}">上一页</a>
       </c:if>
       <c:if test="${page.pageNo<page.totalPage}">
       <a href="<%=basePath%>manager/post/postManage.action?pageNo=${page.pageNo+1}">下一页</a> 
       </c:if>
       <a href="<%=basePath%>manager/post/postManage.action?pageNo=${page.totalPage}">末页</a>
                  总共<fmt:formatNumber>${page.totalPage}</fmt:formatNumber>页  
       </td>
      </tr>
      <tr style="background-color:#408080"><td>标题</td><td>作者</td><td>所属版块</td><td>浏览/回复</td><td>发贴时间</td><td>删除</td></tr>
      <c:forEach items="${posts}" var="post">
      <tr><td>${post.title}</td><td>${post.user.name}</td><td>${post.board.name}</td><td><fmt:formatNumber>${post.readNum}</fmt:formatNumber>/<fmt:formatNumber>${post.replyNum }</fmt:formatNumber></td><td>${post.postDate }</td><td><a href="manager/post/delPost?id=${post.id }" target="_self"><img src="<%=basePath %>images/del.png" border="0px"/></a></td></tr>
      </c:forEach>
      <tr>
         <td colspan="6" style="text-align: right;"><a href="<%=basePath%>manager/post/postManage.action?pageNo=1">首页 </a>  第 <fmt:formatNumber>${page.pageNo}</fmt:formatNumber>页  
       <c:if test="${page.pageNo>1}">
       <a href="<%=basePath%>manager/post/postManage.action?pageNo=${page.pageNo-1}">上一页</a>
       </c:if>
       <c:if test="${page.pageNo<page.totalPage}">
       <a href="<%=basePath%>manager/post/postManage.action?pageNo=${page.pageNo+1}">下一页</a> 
       </c:if>
       <a href="<%=basePath%>manager/post/postManage.action?pageNo=${page.totalPage}">末页</a>
                  总共<fmt:formatNumber>${page.totalPage}</fmt:formatNumber>页  
       </td>
      </tr>
    </table>
  </body>
</html>
