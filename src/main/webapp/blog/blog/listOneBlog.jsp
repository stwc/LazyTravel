<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.lazytravel.blog.entity.Blog"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	Blog blog = (Blog) request.getAttribute("blog"); 
%>

<html>

<head>
	<title>文章資料 - listOneBlog.jsp</title>

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
			<h3>文章資料 - listOneBlog.jsp</h3>
			<h4><a href="select_page.jsp"><img src="../static/images/logo.png" width="100" height="100" border="0" alt="">回首頁</a></h4>
		</td></tr>
	</table>

	<table>
		<tr>
			<th>文章ID</th>
			<th>文章標題</th>
			<th>會員ID</th>
			<th>懶遊日期</th>
			<th>文章內文</th>
			<th>更新時間</th>
			<th>發布時間</th>
			<th>按讚數</th>
			<th>瀏覽數</th>
			<th>收藏數</th>
			<th>縮圖</th>
			<th>文章狀態</th>
		</tr>
		<tr>
			    <td>${blog.getBlogId()}</td>
				<td>${blog.getTitle()}</td>
				<td>${blog.customer.customerId}</td>
				<td>${blog.blogDate}</td>
				<td>${blog.content}</td>
				<td>${blog.upDateTime}</td>
				<td>${blog.createTime}</td>
<%-- 				<td>${(customer.customerStatus.equals("0")) ? "停權" : "啟用"}</td> --%>
				<td>${blog.likeSum}</td>
				<td>${blog.viewSum}</td>
				<td>${blog.clSum}</td>
				<td>${blog.img}</td>
				<td>${(blog.blogStatus ==  0) ?  "停權" : "啟用"}</td>
				
		</tr>
	</table>

</body>

</html>