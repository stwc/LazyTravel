<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.order.entity.*"%>
<%@ page import="com.lazytravel.order.dao.*"%>
<%@ page import="com.lazytravel.order.service.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	OrdersService ordSvc = new OrdersService();
	List<Orders> list = ordSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>

<head>
	<title>所有訂單資料 - listAllEmp.jsp</title>

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
			<h3>所有會員資料 - listAllEmp.jsp</h3>
			<h4><a href="select_page.jsp"><img src="../static/images/logo.png" width="100" height="100" border="0" alt="">回首頁</a></h4>
		</td></tr>
	</table>

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
<%--		<%@ include file="page1.file" %>--%>
<%--		<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">--%>
		<c:forEach var="order" items="${list}">

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
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order/order.do" style="margin-bottom: 0px;">
					 <input type="submit" value="修改">
					 <input type="hidden" name="order_id"  value="${order.orderId}">
					 <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
<%--	<%@ include file="page2.file" %>--%>

</body>

</html>