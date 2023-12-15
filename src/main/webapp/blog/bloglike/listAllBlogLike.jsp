<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.blog.entity.*"%>
<%@ page import="com.lazytravel.blog.dao.*"%>
<%@ page import="com.lazytravel.blog.service.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
BlogLikeService blogLikeSvc = new BlogLikeServiceImpl();
List<BlogLike> list = blogLikeSvc.getAllBlogLikes();
pageContext.setAttribute("list",list);
%>


<html>

<head>
	<title>所有文章資料 - listAllBlogLike.jsp</title>

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
			<h3>所有文章點讚資料 - listAllBlog.jsp</h3>
			<h4><a href="blogLike_select_page.jsp"><img src="../static/images/logo.png" width="100" height="100" border="0" alt="">回首頁</a></h4>
		</td></tr>
	</table>

	<table>
		<tr>
			<th>按讚ID</th>
			<th>會員ID</th>
			<th>按讚時間</th>
			<th>文章ID</th>
			<th>狀態</th>
		</tr>
<%--		<%@ include file="page1.file" %>--%>
<%--		<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">--%>
		<c:forEach var="blog_like" items="${list}">
			<tr>
				<td>${blog_like.getBlogLikeId()}</td>
				<td>${blog_like.customer.customerId}</td>
				<td>${blog_like.createTime}</td>
				<td>${blog_like.blog.blogId}</td>
				<td>${blog_like.blogLikeStatus}</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/bloglike/blogLike.do" style="margin-bottom: 0px;">
					 <input type="submit" value="修改">
					 <input type="hidden" name="blogLikeId"  value="${blog_like.getBlogLikeId()}">
					 <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
<%--	<%@ include file="page2.file" %>--%>

</body>

</html>