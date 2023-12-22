<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lazytravel.blog.entity.BlogImg"%>
<%@ page import="com.lazytravel.customer.entity.Customer"%>

<%
	BlogImg blogImg = (BlogImg) request.getAttribute("blogImg");
%>

<html>

<head>

	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>文章修改 - update_blog_input.jsp</title>

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
		width: 550px;
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
		<h3>文章修改 - update_blog_input.jsp</h3></td><td>
		<h4><a href="blogImg_select_page.jsp"><img src="../static/images/logo.png" width="100" height="100" border="0" alt="">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="blogImg.do" name="form1" enctype="multipart/form-data">
	<table>
	<tr>
			<td>圖片ID:</td>
			<td><input type="TEXT" name="blogImgId" value="<%= blogImg.getBlogImgId()%>" size="45"/></td>
		</tr>
	<tr>
			<td>文章ID:</td>
			<td><input type="TEXT" name="blog_id" value="<%=blogImg.getBlog().getBlogId()%>" size="45"/></td>
		</tr>
		<tr>
			<td>上傳時間:</td>
			<td><input type="TEXT" name="createTime" value="<%=new java.sql.Timestamp(System.currentTimeMillis())%>" size="45"/></td>
		</tr>
		<tr>
		<tr>
			<td>內文圖片:</td>
			<td><input type="file" name="upFiles" value="<%="${blogImg.img}" %>" size="45"/></td>
		</tr>
	</table>
	<br>
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="blogImgId" value="<%=blogImg.getBlogImgId()%>">
	<input type="submit" value="送出修改">
</FORM>

</body>

</html>
