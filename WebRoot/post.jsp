<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>帖子列表</title>
    <style type="text/css">
      td{font-size: 12px;margin: 0 0;padding: 0 0;}
      a{text-decoration: none;color: black}
    </style>
  </head>
  
  <body>
   <table width="1200" cellpadding="3" cellspacing="0" align="center">
       <tbody><tr><td colspan="5"><a href="<%=basePath%>index.jsp" style="text-decoration: none"><font color="#006699">论坛首页</font></a>——><a href="#" style="text-decoration: null"><font color="#006699">${board.name }</font></a><br/></td></tr></tbody>

       <tr align="right">
       <td align="left"><c:if test="${user!=null}"><a href="../newPost.jsp?bid=${bid }"><img src="<%=basePath %>images/button_topic_new.gif" border="0 px"></a></c:if><c:if test="${user==null}"><a href="#" onclick="alert('请登入后发帖')"><img src="<%=basePath %>images/button_topic_new.gif" border="0px"></a></c:if><br/></td>
       <td colspan="4"><a href="<%=basePath%>post/listPost.action?pageNo=1">首页 </a>  第 <fmt:formatNumber>${page.pageNo}</fmt:formatNumber>页  
       <c:if test="${page.pageNo>1}">
       <a href="<%=basePath%>post/listPost.action?pageNo=${page.pageNo-1}">上一页</a>
       </c:if>
       <c:if test="${page.pageNo<page.totalPage}">
       <a href="<%=basePath%>post/listPost.action?pageNo=${page.pageNo+1}">下一页</a> 
       </c:if>
       <a href="<%=basePath%>post/listPost.action?pageNo=${page.totalPage}">末页</a>
                  总共<fmt:formatNumber>${page.totalPage}</fmt:formatNumber>页  
       </td></tr>
       
       <tr style="background: #00B0E6;height: 32"><td width="80px"></td><td align="center">标题</td><td align="center">作者</td><td align="center">浏览/回复</td><td align="center">更新</td></tr>
       <!-- 循环开始 -->
         <c:forEach items="${posts}" var="post">
           <tr onmouseover="this.style.backgroundColor='#F5FBFF'" onmouseout="this.style.backgroundColor='#FFFFFF'"><td width="80px" align="center"><img src="<%=basePath%>images/forum_read.gif"/></td><td align="center"><a href="<%=basePath%>post/readPost.action?id=${post.id }">${post.title}</a></td><td align="center">${post.user.name }</td><td align="center"><fmt:formatNumber>${post.readNum}</fmt:formatNumber>/<fmt:formatNumber>${post.replyNum}</fmt:formatNumber></td><td align="center"><fmt:formatDate value="${post.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td></tr>
         </c:forEach>
         
       <!-- 循环结束 -->
       <tr align="right">
          <td colspan="5"><a href="<%=basePath%>post/listPost.action?pageNo=1">首页 </a>  第 <fmt:formatNumber>${page.pageNo}</fmt:formatNumber>页  
       <c:if test="${page.pageNo>1}">
       <a href="<%=basePath%>post/listPost.action?pageNo=${page.pageNo-1}">上一页</a>
       </c:if>
       <c:if test="${page.pageNo<page.totalPage}">
       <a href="<%=basePath%>post/listPost.action?pageNo=${page.pageNo+1}">下一页</a> 
       </c:if>
       <a href="<%=basePath%>post/listPost.action?pageNo=${page.totalPage}">末页</a>
                  总共<fmt:formatNumber>${page.totalPage}</fmt:formatNumber>页  
       </td>
       </tr>
    </table>
  </body>
</html>
