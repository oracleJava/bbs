<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>帖子</title>
    <style type="text/css">
      td{font-size: 12px}
    </style>
    <script type="text/javascript">
       function check(form){
          var e = document.getElementById("content").value;
          if(e==null||e==''||e.match(/^\s*$/)){
             alert("评论内容不能为空");
             return false;
          }
          return true;
       }
       function reply(form){
          if(check(form)){
             form.submit();
          }
       }
    </script>
  </head>
  
  <body>

    <TABLE style="BORDER-COLLAPSE: collapse" borderColor=#a4b6d7 cellSpacing=0 
cellPadding=0 align=center border=1 width="80%">
  
  <TBODY>
  <TR>
    <TD style="TEXT-ALIGN: center">
      <CENTER>
      </CENTER></TD></TR>
  <TR class=ContentBg>
    <TD style="TEXT-ALIGN: center">
       <TABLE class=TopicBg1 style="BORDER-COLLAPSE: collapse" borderColor=#a4b6d7 
cellSpacing=0 cellPadding=5 align=center border=1>
  <TBODY>
  <tr><td colspan="2"><a href="#reply"><img src="<%=basePath %>images/button_topic_reply.gif" border="0"/></a></td></tr>
  <TR>
    <TD vAlign=top width=180>
      <DIV style="OVERFLOW: hidden; WIDTH: 175px"><A 
      href="http://sky/bbs/forumUserInfo.jsp?id=skyhappy&amp;ntime=398884912" 
      target=_blank><FONT color=#dd3333><B>${post.user.name}</B></FONT></A><BR><BR><IMG 
      src="${post.user.photo}"
      width=120><BR>等级：${post.user.level }
      <BR>积分：<FONT 
      color=#dd3333><B><fmt:formatNumber>${post.user.score }</fmt:formatNumber></B></FONT>
      <br/>签名：${post.user.sign }
      <br>注册：<fmt:formatDate value="${post.user.regDate }" pattern="yyyy-MM-dd"/><BR>状态：<c:set value="true" var="flag"></c:set><c:forEach items="${userMap}" var="ety"><c:if test="${ety.value.id == post.user.id}"><c:set value="false" var="flag"></c:set>在线</c:if></c:forEach><c:if test="${flag==true}">离线</c:if></DIV></TD>
    <TD vAlign=top align=middle>
      <TABLE style="TABLE-LAYOUT: fixed; WIDTH: 95%; HEIGHT: 200px" 
      cellSpacing=0>
        <TBODY>
        <TR>
          <td style="BORDER-BOTTOM: #a4b6d7 1px solid">${post.title }</td>
          <TD  align=right style="BORDER-BOTTOM: #a4b6d7 1px solid"
            height=30><B>楼主</B></TD></TR>
        <TR>
          <TD id=topic_msg1 style="LINE-HEIGHT: 170%; HEIGHT: auto" 
            colSpan=2><BR>
              ${post.content }
          </TD></TR></TBODY></TABLE><BR>
      <TABLE style="TABLE-LAYOUT: fixed; WIDTH: 95%; HEIGHT: 80px" cellSpacing=0 
      align=center>
        <TBODY>
        <TR>
          <TD 
            style="WIDTH: 68%; LINE-HEIGHT: 170%; HEIGHT: auto">-------------------------------------------<BR>
          </TD>
          <TD vAlign=bottom align=right><fmt:formatDate value="${post.postDate}" pattern="yyyy-MM-dd HH:mm:ss"/> 
    </TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
    
      </TD></TR></TBODY></TABLE>
      <!-- 跟帖开始 -->
      <c:forEach items="${posts}" var="p" varStatus="floor">
         <TABLE style="BORDER-COLLAPSE: collapse" borderColor=#a4b6d7 cellSpacing=0 
cellPadding=0 align=center border=1 width="80%">
  <TBODY>
  <TR>
    <TD style="TEXT-ALIGN: center">
      <CENTER>
      </CENTER></TD></TR>
  <TR class=ContentBg>
    <TD style="TEXT-ALIGN: center">
       <TABLE class=TopicBg1 style="BORDER-COLLAPSE: collapse" borderColor=#a4b6d7 
cellSpacing=0 cellPadding=5 align=center border=1>
  <TBODY>
  <TR>
    <TD vAlign=top width=180>
      <DIV style="OVERFLOW: hidden; WIDTH: 175px"><A 
      href="http://sky/bbs/forumUserInfo.jsp?id=skyhappy&amp;ntime=398884912" 
      target=_blank><FONT color=#dd3333><B>${p.user.name}</B></FONT></A><BR><BR><IMG 
      src="${p.user.photo }" 
      width=120><BR>等级：${p.user.level }<BR>积分：<FONT 
      color=#dd3333><B><fmt:formatNumber>${p.user.score }</fmt:formatNumber></B></FONT>
      <br>签名：${p.user.sign }<br>
           注册：<fmt:formatDate value="${p.user.regDate }" pattern="yyyy-MM-dd"/><BR>状态：<c:set value="true" var="flag"></c:set><c:forEach items="${userMap}" var="ety"><c:if test="${ety.value.id == p.user.id}"><c:set value="false" var="flag"></c:set>在线</c:if></c:forEach><c:if test="${flag==true}">离线</c:if></DIV></TD>
    <TD vAlign=top align=middle>
      <TABLE style="TABLE-LAYOUT: fixed; WIDTH: 95%; HEIGHT: 200px" 
      cellSpacing=0>
        <TBODY>
        <TR>
          <TD  align=right style="BORDER-BOTTOM: #a4b6d7 1px solid" colspan="2"
            height=30><B>#${floor.count}楼</B></TD></TR>
        <TR>
          <TD id=topic_msg1 style="LINE-HEIGHT: 170%; HEIGHT: auto" 
            colSpan=2><BR>
              ${p.content }
          </TD></TR></TBODY></TABLE><BR>
      <TABLE style="TABLE-LAYOUT: fixed; WIDTH: 95%; HEIGHT: 80px" cellSpacing=0 
      align=center>
        <TBODY>
        <TR>
          <TD 
            style="WIDTH: 68%; LINE-HEIGHT: 170%; HEIGHT: auto">-------------------------------------------<BR>
          </TD>
          <TD vAlign=bottom align=right>&nbsp;<fmt:formatDate value="${p.postDate}" pattern="yyyy-MM-dd HH:mm:ss"/> 
    </TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
    
      </TD></TR></TBODY></TABLE>
      </c:forEach>
      <!-- 跟帖结束 -->
      <br/><br/>
      <div id="reply"></div>
      <form action="<%=basePath%>post/replyPost" method="post">
      <TABLE style="BORDER-COLLAPSE: collapse" borderColor=#a4b6d7 cellSpacing=0 
       cellPadding=0 align=center border=1 width="80%">
       <tr>
       <td valign="top">评论:</td>
       <td>
         <input type="hidden" name="id" value=${post.id}>           
         <textarea id="content" name="content" cols="80%" rows="10"></textarea>
         <c:if test="${user!=null}">
            <input type="button" onclick="reply(this.form)" value="提交"/>
         </c:if>
         <c:if test="${user==null}">
            <input type="button" value="提交" onclick="alert('请先登入')"/>
         </c:if>
        </td>
        </tr>
      </table>
      <!--<ckeditor:replace replace="content" basePath="../ckeditor/"/>-->
      </form>
  </body>
</html>
