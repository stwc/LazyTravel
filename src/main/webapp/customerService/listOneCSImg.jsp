<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lazytravel.customerservice.entity.CSImg"%>
<%@ include file="/admin/header.html"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
CSImg csImg= (CSImg) request.getAttribute("csImg");
%>

<html>

<head>
	<title>圖片資料 - listOneCSImg.jsp</title>

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
			<th>圖片ID</th>
			<th>訊息ID</th>
			<th>圖片</th>
			<th>上傳時間</th>
		</tr>
		<tr>
			    <td>${csImg.getImgId()}</td>
				<td>${csImg.getCSMessage().getMessageId()}</td>
				<td>${csImg.img}</td>
				<td>${csImg.createTime}</td>
				
		</tr>
	</table>

</body>

</html>