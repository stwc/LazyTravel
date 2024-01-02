<%@page import="com.lazytravel.customerservice.entity.CSMail"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.customerservice.entity.*"%>
<%@ page import="com.lazytravel.customerservice.dao.*"%>
<%@ page import="com.lazytravel.customerservice.service.*"%>
<%@ include file="/admin/header.html"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
CSMailService csMailSvc = new CSMailServiceImpl();
List<CSMail> list = csMailSvc.getAllCSMails();
pageContext.setAttribute("list",list);
%>


<html>

<head>
	<title>所有訊息資料 - listAllCSMail.jsp</title>

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
			<h3>所有會員資料</h3>

	<table>
		<tr>
			<th scope="col">信件ID</th>
	        <th scope="col">會員ID</th>
	        <th scope="col">客戶發信時間</th>
	         <th scope="col">客戶問題</th>
	         <th scope="col">客服發信時間</th>
	          <th scope="col">客服回答</th>
	          <th scope="col">信件狀態</th>
		</tr>
		<c:forEach var="csMail" items="${list}">
			<tr>
				 <tr>
                            <td>${csMail.getMailId()}</td>
                            <td>${csMail.customer.customerId}</td>
                            <td>${csMail.createTime}</td>
                            <td>${csMail.questions}</td>
                            <td>${csMail.RECEIVED_TIME}</td>
                            <td>${csMail.answer}</td>
                            <td>${csMail.csMailStatus}</td>
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customerService/CSMail.do" style="margin-bottom: 0px;">
					 <input type="submit" value="修改">
					 <input type="hidden" name="mailId"  value="${csMail.getMailId()}">
					 <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
<%--	<%@ include file="page2.file" %>--%>

</body>

</html>