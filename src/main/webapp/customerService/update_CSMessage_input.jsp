<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page import="com.lazytravel.customerservice.controller.CSMessageServlet "%> --%>
<%@ page import="com.lazytravel.customerservice.entity.CSMessage"%>
<%@ include file="/admin/header.html"%>
<%
CSMessage csMessage = (CSMessage) request.getAttribute("csMessage");
%>

<html>

<head>

	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>訊息修改 - update_CSMessage_input.jsp</title>

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


		

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="csMessage" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="CSMessage.do"" name="form1">
	<table>
	<tr>
			<tr>
			<td>訊息ID:</td>
			<td><input type="TEXT" name="messageId" value="<%=  (csMessage == null)? "請寫下您的問題" : csMessage.getMessageId() %>" size="45"/></td>
		</tr>
		<tr>
			<td>信件ID:</td>
			<td><input   type="TEXT" name="mailId" value="<%= (csMessage == null)? "1" : csMessage.getCsMail().getMailId()  %>" size="45"/></td>
		</tr>
		<tr>
			<td>內文:</td>
			<td><input  type="TEXT" name="content "value=<%= (csMessage == null)? "1" :csMessage.getContent() %>" size="45"/></td>
		</tr>
		<tr>
			<td>發送時間:</td>
			<td><input type="TEXT" name="createTime" value="<%= (csMessage==null)? new java.sql.Timestamp(System.currentTimeMillis()) : csMessage.getCreateTime()%>" size="45"/></td>
		</tr>
		<tr>
			<td>來源:</td>
			<td><input type="TEXT" name="messageFrom" value="<%= (csMessage==null)? new java.sql.Timestamp(System.currentTimeMillis()) : csMessage.getMessageFrom()%>" size="45"/></td>
		</tr>
	</table>
	<br>
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="messageId" value="<%=csMessage.getMessageId()%>">
	<input type="submit" value="送出修改">
</FORM>

</body>

</html>
