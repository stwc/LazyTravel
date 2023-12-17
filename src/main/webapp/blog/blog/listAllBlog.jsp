<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.blog.entity.*"%>
<%@ page import="com.lazytravel.blog.dao.*"%>
<%@ page import="com.lazytravel.blog.service.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
BlogService blogSvc = new BlogServiceImpl();
List<Blog> list = blogSvc.getAllBlogs();
pageContext.setAttribute("list",list);
%>


<html>

<head>
	<title>所有文章資料 - listAllBlog.jsp</title>

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
		/*width: 800px;*/
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

<body>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr><td>
			<h3>所有會員資料 - listAllBlog.jsp</h3>
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
<%--		<%@ include file="page1.file" %>--%>
<%--		<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">--%>
		<c:forEach var="blog" items="${list}">
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
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/blog/blog.do" style="margin-bottom: 0px;">
					 <input type="submit" value="修改">
					 <input type="hidden" name="blogId"  value="${blog.blogId}">
					 <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
<%--	<%@ include file="page2.file" %>--%>

</body>

</html>