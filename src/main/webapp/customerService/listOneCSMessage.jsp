<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lazytravel.customerservice.entity.CSMessage"%>
<%@ include file="/admin/header.html"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
CSMessage csMessage = (CSMessage) request.getAttribute("csMessage");
%>

<html>

<head>
	<title>訊息內文 - listOneCSMessage.jsp</title>

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

	
			<h3>訊息內文</h3>
			

	<table>
		<tr>
			<th>訊息ID</th>
			<th>信件ID</th>
			<th>內文</th>
			<th>發送時間</th>
			<th>來源</th>
		</tr>
		<tr>
			    <td>${csMessage.getMessageId()}</td>
				<td>${csMessage.getCsMail().getMailId()}</td>
				<td>${csMessage.content}</td>
				<td>${csMessage.createTime}</td>
				<td>${csMessage.messageFrom}</td>
				
		</tr>
	</table>

</body>

</html>