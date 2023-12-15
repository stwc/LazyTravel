<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lazytravel.blog.entity.Blog"%>
<%@ page import="com.lazytravel.customer.entity.Customer"%>

<%
	Blog blog = (Blog) request.getAttribute("blog");
%>

<html>

<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>文章新增 - addblog.jsp</title>

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
		<h3>文章新增 - addBlog.jsp</h3></td><td>
		<h4><a href="select_page.jsp"><img src="../static/images/logo.png" width="100" height="100" border="0" alt="">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="blog.do" name="form1">
	<table>
		<tr>
			<td>文章標題:</td>
			<td><input type="TEXT" name="title" value="<%=  (blog == null)? "寫下你的這段旅程吧" : blog.getTitle() %>" size="45"/></td>
		</tr>
		<tr>
			<td>會員ID:</td>
			<td><input  readonly="readonly"  type="TEXT" name="customer_id" value="<%= (blog == null)? "11002" : blog.getCustomer().getCustomerId()  %>" size="45"/></td>
		</tr>
		<tr>
			<td>懶遊日期:</td>
			<td><input type="TEXT" name="blog_date" value="<%=(blog ==null)? "2023-12-8 00:00:00" :  blog.getBlogDate()%>" size="45"/></td>
		</tr>
		<tr>
			<td>內文:</td>
			<td><input type="TEXT" name="content" value="<%= (blog==null)? "123456" : blog.getContent()%>" size="45"/></td>
		</tr>
		<tr>
			<td>更新時間:</td>
			<td><input type="TEXT" name="upDateTime" value="<%= (blog==null)? new java.sql.Timestamp(System.currentTimeMillis()) : blog.getUpDateTime()%>" size="45"/></td>
		</tr>
		<tr>
			<td>發布時間:</td>
			<td><input type="TEXT" name="createTime" value="<%= (blog==null)? new java.sql.Timestamp(System.currentTimeMillis()) : blog.getCreateTime()%>" size="45"/></td>
		</tr>
		<tr>
			<td>文章狀態:</td>
			<td><input type="TEXT" name="blog_status" value="<%= (blog==null)?  "1"  :  blog.getBlogStatus() %>" size="45"/></td>
		</tr>
		
	</table>
	<br>
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增">
</FORM>

</body>

</html>