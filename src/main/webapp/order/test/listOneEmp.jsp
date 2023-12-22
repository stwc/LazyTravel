<%@page import="com.lazytravel.order.entity.Orders"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Orders order = (Orders) request.getAttribute("order"); 
%>

<html>

<head>
	<title>訂單資料 - listOneEmp.jsp</title>

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

<!-- 	<h4>此頁暫練習採用 EL 的寫法取值:</h4> -->
	<table id="table-1">
		<tr><td>
			<h3>訂單資料 - listOneEmp.jsp</h3>
			<h4><a href="select_page.jsp"><img src="../static/images/logo.png" width="100" height="100" border="0" alt="">回首頁</a></h4>
		</td></tr>
	</table>order

	<table>
		<tr>
			<th>訂單Id</th>
			<th>訂單編號</th>
			<th>會員Id</th>
			<th>旅行團Id</th>
			<th>旅客人數</th>
			<th>使用會員金</th>
			<th>優惠券Id</th>
			<th>消費總金額</th>
			<th>付款時間</th>
			<th>評價內容</th>
			<th>評價分數</th>
			<th>評價時間</th>
			<th>建立時間</th>
			<th>更新時間</th>
			<th>訂單狀態</th>
		</tr>
		
		<tr>
			<td>${order.getOrderId()}</td>
			<td>${order.getOrderNo()}</td>
			<td>${order.getCustomerId()}</td>
			<td>${order.getGroupId()}</td>
			<td>${order.getTourist()}</td>
			<td>${order.getCustomerPoint()}</td>
			<td>${order.getCouponId()}</td>
			<td>${order.getTotalAmt()}</td>
			<td>${order.getPaidTime()}</td>
			<td>${order.getContent()}</td>
			<td>${order.getScore()}</td>
			<td>${order.getContentTime()}</td>
			<td>${order.getCreateTime()}</td>
			<td>${order.getUpdateTime()}</td>
			<td>${order.getOrderStatus()}</td>
			
		</tr>
	</table>

</body>

</html>