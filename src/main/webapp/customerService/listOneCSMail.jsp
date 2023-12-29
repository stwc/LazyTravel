<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lazytravel.customerservice.entity.CSMail"%>
<%@ include file="/admin/header.html"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
CSMail csMail = (CSMail) request.getAttribute("csMail");
%>

<html>

<head>
	<title>訊息資料 - listOneCSMail.jsp</title>

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

	
			<h3>訊息資料</h3>
			

	<table>
		<tr>
			<th>信件ID</th>
			<th>會員ID</th>
			<th>信件標題</th>
			<th>建立時間</th>
			<th>最後訊息時間</th>
			<th>信件狀態</th>
			<th>客戶問題</th>
			<th>客服回答</th>
		</tr>
		<tr>
			    <td>${csMail.getMailId()}</td>
				<td>${csMail.customer.customerId}</td>
				<td>${csMail.getTitle()}</td>
				<td>${csMail.createTime}</td>
				<td>${csMail.lastMsgTime}</td>
				<td>${csMail.csMailStatus}</td>
				<td>${csMail.questions}</td>
				<td>${csMail.answer}</td>
		</tr>
	</table>

</body>

</html>