<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lazytravel.blog.entity.BlogCl"%>
<%@ page import="com.lazytravel.customer.entity.Customer"%>

<%
	BlogCl blogCl = (BlogCl) request.getAttribute("blogCl");
%>

<html>

<head>

	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>�峹�ק� - update_blogCl_input.jsp</title>

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

<table id="table-1">
	<tr><td>
		<h3>�峹�ק� - update_blogCl_input.jsp</h3></td><td>
		<h4><a href="blogCl_select_page.jsp"><img src="../static/images/logo.png" width="100" height="100" border="0" alt="">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="blogCl.do" name="form1">
	<table>
	<tr>
			<td>����ID:</td>
			<td><input readonly="readonly" type="TEXT" name="blogClId" value="<%=  blogCl.getBlogClId()%>" size="45"/></td>
		</tr>
		<tr>
			<td>�|��ID:</td>
			<td><input readonly="readonly" type="TEXT" name="customer_id" value="<%= blogCl.getCustomer().getCustomerId()%>" size="45"/></td>
		</tr>
		<tr>
			<td>���îɶ�:</td>
			<td><input type="TEXT" name="likeTime" value="<%=new java.sql.Timestamp(System.currentTimeMillis())%>" size="45"/></td>
		</tr>
		<tr>
			<td>�峹ID:</td>
			<td><input type="TEXT" name="blogId" value="<%=blogCl.getBlog().getBlogId()%>" size="45"/></td>
		</tr>
		<tr>
			<td>���A:</td>
			<td><input type="TEXT" name="blogClstatus" value="<%= blogCl.getBlogClStatus()%>" size="45"/></td>
		</tr>
	</table>
	<br>
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="blogClId" value="<%=blogCl.getBlogClId()%>">
	<input type="submit" value="�e�X�ק�">
</FORM>

</body>

</html>