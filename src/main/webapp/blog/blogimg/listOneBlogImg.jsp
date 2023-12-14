<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.lazytravel.blog.entity.BlogImg"%>
<%@ page import="com.lazytravel.blog.entity.Blog"%>
<%@ page import="com.lazytravel.blog.entity.BlogImg"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	BlogImg blogImg = (BlogImg) request.getAttribute("blogImg"); 
%>

<html>

<head>
	<title>文章資料 - listOneBlogImg.jsp</title>

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
		/*width: 600px;*/
		background-color: white;
		margin-top: 5px;
		margin-bottom: 5px;
	  }
	  table, th, td {
		border: 1px solid #CCCCFF;
	  }
	  th, td {
		padding: 5px;
		text-align: center;
	  }
	</style>

</head>

<body bgcolor='white'>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr><td>
			<h3>文章圖片資料 - listOneBlogImg.jsp</h3>
			<h4><a href="blogImg_select_page.jsp"><img src="../static/images/logo.png" width="100" height="100" border="0" alt="">回首頁</a></h4>
		</td></tr>
	</table>

	<table>
		<tr>
			<th>內文圖片ID</th>
			<th>文章ID</th>
			<th>上傳時間</th>
			<th>內文圖片</th>
		</tr>
		<tr>
			    <td>${blogImg.getBlogImgId()}</td>
				<td>${blogImg.blog.blogId}</td>
				<td>${blogImg.createTime}</td>
				<td>${blogImg.img}</td>
				<td>
		</tr>
	</table>

</body>

</html>