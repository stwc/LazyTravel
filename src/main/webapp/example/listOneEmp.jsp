<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.lazytravel.example.entity.Customer"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	Customer customer = (Customer) request.getAttribute("customer"); // 在CustomerService中setAttribute("customer")
%>

<html>

<head>
	<title>員工資料 - listOneEmp.jsp</title>

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

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr><td>
			<h3>會員資料 - listOneEmp.jsp</h3>
			<h4><a href="select_page.jsp"><img src="../static/images/logo.png" width="100" height="100" border="0" alt="">回首頁</a></h4>
		</td></tr>
	</table>

	<table>
		<tr>
			<th>會員ID</th>
			<th>姓名</th>
			<th>暱稱</th>
			<th>性別</th>
			<th>電話</th>
			<th>生日</th>
			<th>地址</th>
			<th>Email</th>
			<th>狀態</th>
			<th>身份證</th>
			<th>會員金</th>
			<th>建立時間</th>
			<th>更新時間</th>
		</tr>
		<tr>
			<td><%=customer.getCustomerId()%></td>
			<td><%=customer.getCustomerName()%></td>
			<td><%=customer.getNickname()%></td>
			<td><%=(customer.getSex().equals("0")) ? "男" : "女"%></td>
			<td><%=customer.getPhone()%></td>
			<td><%=customer.getBirth()%></td>
			<td><%=customer.getAddress()%></td>
			<td><%=customer.getEmail()%></td>
			<td><%=(customer.getCustomerStatus().equals("0")) ? "停權" : "啟用"%></td>
			<td><%=customer.getIdno()%></td>
			<td><%=customer.getCustomerPoint()%></td>
			<td><%=customer.getCreateTime()%></td>
			<td><%=customer.getUpdateTime()%></td>
		</tr>
	</table>

</body>

</html>