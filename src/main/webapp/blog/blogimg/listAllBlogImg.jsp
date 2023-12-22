	<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.List"%>
	<%@ page import="java.util.Base64" %>
	<%@ page import="com.lazytravel.blog.entity.*"%>
	<%@ page import="com.lazytravel.blog.dao.*"%>
	<%@ page import="com.lazytravel.blog.service.*"%>
	<%-- 此頁練習採用 EL 的寫法取值 --%>
	
	<%
	BlogImgService blogImgSvc = new BlogImgServiceImpl();
	List<BlogImg> list = blogImgSvc.getAllBlogImgs();
	pageContext.setAttribute("list",list);
	%>
	
	
	<html>
	
	<head>
		<title>所有圖片資料 - listAllBlogImg.jsp</title>
	
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
				<h3>所有文章圖片資料 - listAllBlogImg.jsp</h3>
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
	<%--		<%@ include file="page1.file" %>--%>
	<%--		<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">--%>
			<c:forEach var="blog_img" items="${list}">
				<tr>
					<td>${blog_img.getBlogImgId()}</td>
					<td>${blog_img.blog.blogId}</td>
					<td>${blog_img.createTime}</td>
					
					<td><img src="<%=request.getContextPath()%>/blog/blog/DBGifReader?blogImgId=${param.blogImgId}" alt="Blog Image" width="100" height="100"></td>

					<td>
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/blogimg/blogImg.do" style="margin-bottom: 0px;">
						 <input type="submit" value="修改">
						 <input type="hidden" name="blogImgId"  value="${blog_img.getBlogImgId()}">
						 <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
					</td>
					<td>
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/blog/blogimg/blogImg.do" style="margin-bottom: 0px;">
						 <input type="submit" value="刪除">
						 <input type="hidden" name="blogImgId"  value="${blog_img.getBlogImgId()}">
						 <input type="hidden" name="action"	value="delete"></FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
	<%--	<%@ include file="page2.file" %>--%>
	
	</body>
	
	</html>