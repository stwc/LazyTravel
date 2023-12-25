<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.customerservice.entity.*"%>
<%@ page import="com.lazytravel.customerservice.dao.*"%>
<%@ page import="com.lazytravel.customerservice.service.*"%>
<%@ include file="/admin/header.html"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
CSImgService csImgSvc = new CSImgServiceImpl();
List<CSImg> list = csImgSvc.getAllCSImgs();
pageContext.setAttribute("list",list);
%>


<html>

<head>
	<title>訊息圖片 - listAllCSImg.jsp</title>

	

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

	
			
	
<h3>圖片</h3>
	<table>
		<tr>
			<th>圖片ID</th>
			<th>訊息ID</th>
			<th>圖片</th>
			<th>上傳時間</th>
		</tr>
<%--		<%@ include file="page1.file" %>--%>
<%--		<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">--%>
		<c:forEach var="csImg" items="${list}">
			<tr>
				<td>${csImg.getImgId()}</td>
				<td>${csImg.getCSMessage().getMessageId()}</td>
				<td>${csImg.img}</td>
				<td>${csImg.createTime}</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customerService/CSImg.do" style="margin-bottom: 0px;">
					 <input type="submit" value="修改" >
					 <input type="hidden" name="ImgId"  value="${csImg.getImgId()}">
					 <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
<%--	<%@ include file="page2.file" %>--%>

</body>

</html>