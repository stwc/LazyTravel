<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page import="com.lazytravel.customerservice.controller.CSMailServlet "%> --%>
<%@ page import="com.lazytravel.customerservice.entity.CSMail"%>
<%@ include file="/admin/header.html"%>
<%
CSMail csMail = (CSMail) request.getAttribute("csMail");
%>

<html>



<body bgcolor='white'>



<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="CSMail.do" name="form1">
	<table>
		<tr>
			<td>會員ID:</td>
			<td><input   type="TEXT" name="customer_id"
			 value="<%= (csMail == null)? "" : csMail.getCustomer().getCustomerId() %>" 
			 size="45"/></td>
		</tr>
		<tr>
			<td>建立時間:</td>
			<td><input type="TEXT" name="createTime" 
			value="<%= (csMail==null)? new java.sql.Timestamp(System.currentTimeMillis()) : csMail.getCreateTime()%>" 
			size="45"/></td>
		</tr>
		<tr>
			<td>信件狀態:</td>
			<td><input type="TEXT" name="csmail_status" value="<%= (csMail==null)?  "0"  :  csMail.getCsMailStatus() %>" 
			size="45"/></td>
		</tr>
		<tr>
			<td>客戶問題:</td>
			<td><input type="TEXT" name="questions" value="<%=  (csMail == null)? "" : csMail.getQuestions()%>" size="45"/></td>
		</tr>
		<tr>
			<td>客服回答:</td>
			<td><input type="TEXT" name="answer" value="<%=  (csMail == null)? "" : csMail.getAnswer()%>" size="45"/></td>
		</tr>
		
	</table>
	<br>
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增">
	<table id="table-1">
</table>
</FORM>

</body>

</html>