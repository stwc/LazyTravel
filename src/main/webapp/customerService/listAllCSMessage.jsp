<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.customerservice.entity.*"%>
<%@ page import="com.lazytravel.customerservice.dao.*"%>
<%@ page import="com.lazytravel.customerservice.service.*"%>
<%@ include file="/admin/header.html"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
CSMessageService csMessageSvc = new CSMessageServiceImpl();
List<CSMessage> list = csMessageSvc.getAllCSMessages();
pageContext.setAttribute("list",list);
%>


<html>

<head>
	<title>訊息內文資料 - listAllCSMessage.jsp</title>

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

	
			<h3>訊息內文</h3>
			
	<table>
		<tr>
			<th>訊息ID</th>
			<th>信件ID</th>
			<th>內文</th>
			<th>發送時間</th>
			<th>來源</th>
		</tr>
<%--		<%@ include file="page1.file" %>--%>
<%--		<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">--%>
		<c:forEach var="csMessage" items="${list}">
			<tr>
				<td>${csMessage.getMessageId()}</td>
				<td>${csMessage.getCsMail().getMailId()}</td>
				<td>${csMessage.content}</td>
				<td>${csMessage.createTime}</td>
				<td>${csMessage.messageFrom}</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customerService/CSMessage.do" style="margin-bottom: 0px;">
					 <input type="submit" value="修改">
					 <input type="hidden" name="messageId"  value="${csMessage.getMessageId()}">
					 <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
<%--	<%@ include file="page2.file" %>--%>

</body>

</html>