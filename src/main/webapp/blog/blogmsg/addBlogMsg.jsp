<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lazytravel.blog.entity.BlogMsg"%>
<%@ page import="com.lazytravel.blog.entity.Blog"%>
<%@ page import="com.lazytravel.customer.entity.Customer"%>

<%
	BlogMsg blogMsg = (BlogMsg) request.getAttribute("blogMsg");
%>

<html>

<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>文章新增 - addBlogMsg.jsp</title>

	<style>
	  table#table-1 {
		background-color: #CCCCFF;
		border: 2px solid black;
		text-align: center;
	  }
	  table#table-1 h4 {
		color: red;
		display: block;
		margin-bottom: 1px;
	  }
	  h4 {
		color: blue;
		display: inline;
	  }
	</style>

	<style>
	  table {
		width: 450px;
		background-color: white;
		margin-top: 1px;
		margin-bottom: 1px;
	  }
	  table, th, td {
		border: 0px solid #CCCCFF;
	  }
	  th, td {
		padding: 1px;
	  }
	</style>

</head>

<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		<h3>文章新增 - addBlogMsg.jsp</h3></td><td>
		<h4><a href="blog/blogmsg/blogMsg_select_page.jsp"><img src="../static/images/logo.png" width="100" height="100" border="0" alt="">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="blogMsg.do" name="form1">
	<table>
		<tr>
			<td>留言ID:</td>
			<td><input  readonly="readonly"  type="TEXT" name="blog_msg_id" value="<%=  (blogMsg == null)? "1" : blogMsg.getBlogMsgId() %>" size="45"/></td>
		</tr>
		<tr>
			<td>留言內文:</td>
			<td><input   type="TEXT" name="content" value="<%= (blogMsg == null)? "XXXXXx" : blogMsg.getContent() %>" size="45"/></td>
		</tr>
		<tr>
			<td>文章ID:</td>
			<td><input type="TEXT" name="blog_id" value="<%=(blogMsg ==null)? "41001" :  blogMsg.getBlog().getBlogId()%>" size="45"/></td>
		</tr>
		<tr>
			<td>會員ID:</td>
			<td><input readonly="readonly" type="TEXT" name="customer_id" value="<%= (blogMsg==null)? "11002" : blogMsg.getCustomer().getCustomerId()%>" size="45"/></td>
		</tr>
		<tr>
			<td>發布時間:</td>
			<td><input type="TEXT" name="createTime" value="<%= (blogMsg==null)? new java.sql.Timestamp(System.currentTimeMillis()) : blogMsg.getCreateTime()%>" size="45"/></td>
		</tr>
		
	</table>
	<br>
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增">
</FORM>

</body>

</html>