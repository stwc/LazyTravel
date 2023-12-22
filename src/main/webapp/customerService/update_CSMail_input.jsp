<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--@ page import="com.lazytravel.customerservice.controller.CSMailServlet "--%>--%>
<%@ page import="com.lazytravel.customerservice.entity.CSMail"%>
<%@ include file="/admin/header.html"%>
<%
CSMail csMail = (CSMail) request.getAttribute("csMail");
%>

<html>

<head>

	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>訊息修改 - update_CSMail_input.jsp</title>

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
		<c:forEach var="csMail" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="CSMail.do"" name="form1">
	<table>
	<tr>
		<tr>
			<td>信件ID:</td>
		    <td><input    type="TEXT" name="mailId" 
		    value="<%=  (csMail == null)? "1" : csMail.getMailId() %>" 
		    size="45"/></td>
		</tr>
		<tr>
			<td>會員ID:</td>
			<td><input type="TEXT" name="customer_id"
					value="<%=(csMail == null) ? "1001" : csMail.getCustomer().getCustomerId()%>"
					size="45" /></td>
		</tr>
		<tr>
			<td>信件標題:</td>
			<td><input type="TEXT" name="title" value="<%=  (csMail == null)? "請寫下您的問題" : csMail.getTitle() %>" size="45"/></td>
		</tr>
		<tr>
			<td>建立時間:</td>
			<td><input type="TEXT" name="createTime" 
			value="<%= (csMail==null)? new java.sql.Timestamp(System.currentTimeMillis()) : csMail.getCreateTime()%>" 
			size="45"/></td>
		</tr>
		<tr>
			<td>最後訊息時間:</td>
			<td><input type="TEXT" name="lastMsgTime"
			value="<%= (csMail==null)? new java.sql.Timestamp(System.currentTimeMillis()) : csMail.getLastMsgTime()%>" 
			size="45"/></td>
		</tr>
		<tr>
			<td>信件狀態:</td>
			<td><input type="TEXT" name="csMail_status" value="<%=csMail.getCsMailStatus() %>" 
			size="45"/></td>
		</tr>
	</table>
	<br>
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="mailId" value="<%=csMail.getMailId()%>">
	<input type="submit" value="送出修改">
</FORM>

</body>

</html>
