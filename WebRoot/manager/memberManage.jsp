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
    <base href="<%=basePath%>">
    
    <title>会员管理</title>
    <style type="text/css">
     td{font-size: 12;text-align: center;height:24}
     a{text-decoration: none}
    </style>
	

  </head>
  
  <body topmargin="0" leftmargin="100px">
    <table align="center" width="90%" cellpadding="0" cellspacing="0" border=0 style="margin: 0pt; padding: 0pt;">
       <tr> 
        <td colspan="6" style="text-align: center">
          <form action="<%=basePath%>manager/backUser/memberList.action" method="post">
                              会员名：<input type="text" name="name" style="width:150px"><input type="submit" value="查询">
          </form>
        </td>
      </tr>
       <tr> 
         <td colspan="6" style="text-align: right;"><a href="<%=basePath%>manager/backUser/memberList.action?pageNo=1">首页 </a>  第 <fmt:formatNumber>${page.pageNo}</fmt:formatNumber>页  
       <c:if test="${page.pageNo>1}">
       <a href="<%=basePath%>manager/backUser/memberList.action?pageNo=${page.pageNo-1}">上一页</a>
       </c:if>
       <c:if test="${page.pageNo<page.totalPage}">
       <a href="<%=basePath%>manager/backUser/memberList.action?pageNo=${page.pageNo+1}">下一页</a> 
       </c:if>
       <a href="<%=basePath%>manager/backUser/memberList.action?pageNo=${page.totalPage}">末页</a>
                  总共<fmt:formatNumber>${page.totalPage}</fmt:formatNumber>页  
       </td>
       </tr>
       <tr style="background-color:#408080">
         <td>会员名</td><td>密码</td><td>等级</td><td>积分</td><td>注册时间</td><td>删除</td>
       </tr>
       <c:forEach items="${members}" var="m">
       <tr onmouseover="this.style.backgroundColor='#3D81A3'" onmouseout="this.style.backgroundColor='#FFFFFF'">
         <td>${m.name}</td><td>${m.password}</td><td>${m.level}</td><td>${m.score}</td><td>${m.regDate}</td><td><a href="manager/backUser/delMember?id=${m.id }" target="_self"><img src="<%=basePath %>images/del.png" border="0px"/></a></td>
       </tr>
       </c:forEach>
       
       <tr> 
         <td colspan="6" style="text-align: right;"><a href="<%=basePath%>manager/backUser/memberList.action?pageNo=1">首页 </a>  第 <fmt:formatNumber>${page.pageNo}</fmt:formatNumber>页  
       <c:if test="${page.pageNo>1}">
       <a href="<%=basePath%>manager/backUser/memberList.action?pageNo=${page.pageNo-1}">上一页</a>
       </c:if>
       <c:if test="${page.pageNo<page.totalPage}">
       <a href="<%=basePath%>manager/backUser/memberList.action?pageNo=${page.pageNo+1}">下一页</a> 
       </c:if>
       <a href="<%=basePath%>manager/backUser/memberList.action?pageNo=${page.totalPage}">末页</a>
                  总共<fmt:formatNumber>${page.totalPage}</fmt:formatNumber>页  
       </td>
       </tr>
    </table>
  </body>
</html>
