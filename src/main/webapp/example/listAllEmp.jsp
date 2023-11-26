<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.example.entity.*"%>
<%@ page import="com.lazytravel.example.dao.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    CustomerService cusSvc = new CustomerService();
    List<Customer> list = cusSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>

<head>
	<title>所有會員資料 - listAllEmp.jsp</title>

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
			<th>修改</th>
		</tr>
<%--		<%@ include file="page1.file" %>--%>
<%--		<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">--%>
		<c:forEach var="customer" items="${list}">

			<tr>
				<td>${customer.customerId}</td>
				<td>${customer.customerName}</td>
				<td>${customer.nickname}</td>
<%--				<td>${(customer.sex.equals("0")) ? "男" : "女"}</td>--%>
				<td>${customer.sex}</td>
				<td>${customer.phone}</td>
				<td>${customer.birth}</td>
				<td>${customer.address}</td>
				<td>${customer.email}</td>
<%--				<td>${(customer.customerStatus.equals("0")) ? "停權" : "啟用"}</td>--%>
				<td>${customer.customerStatus}</td>
				<td>${customer.idno}</td>
				<td>${customer.customerPoint}</td>
				<td>${customer.createTime}</td>
				<td>${customer.updateTime}</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/example/customer.do" style="margin-bottom: 0px;">
					 <input type="submit" value="修改">
					 <input type="hidden" name="customer_id"  value="${customer.customerId}">
					 <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
<%--	<%@ include file="page2.file" %>--%>

</body>

</html>