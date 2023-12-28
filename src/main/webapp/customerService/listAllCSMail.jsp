<%@page import="com.lazytravel.customerservice.entity.CSMail"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.customerservice.entity.*"%>
<%@ page import="com.lazytravel.customerservice.dao.*"%>
<%@ page import="com.lazytravel.customerservice.service.*"%>
<%@ include file="/admin/header.html"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
CSMailService csMailSvc = new CSMailServiceImpl();
List<CSMail> list = csMailSvc.getAllCSMails();
pageContext.setAttribute("list",list);
%>


<html>

<head>
	<title>所有訊息資料 - listAllCSMail.jsp</title>

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
			<h3>所有會員資料</h3>

	<table>
		<tr>
			<th>信件ID</th>
			<th>會員ID</th>
			<th>信件標題</th>
			<th>建立時間</th>
			<th>最後訊息時間</th>
			<th>信件狀態</th>
		</tr>
<%--		<%@ include file="page1.file" %>--%>
<%--		<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">--%>
		<c:forEach var="csMail" items="${list}">
			<tr>
				<td>${csMail.getMailId()}</td>
				<td>${csMail.customer.customerId}</td>
				<td>${csMail.getTitle()}</td>
				<td>${csMail.createTime}</td>
				<td>${csMail.lastMsgTime}</td>
				<td>${csMail.csMailStatus}</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customerService/CSMail.do" style="margin-bottom: 0px;">
					 <input type="submit" value="修改">
					 <input type="hidden" name="mailId"  value="${csMail.getMailId()}">
					 <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
<%--	<%@ include file="page2.file" %>--%>

</body>

</html>