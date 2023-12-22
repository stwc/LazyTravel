<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lazytravel.customerservice.entity.CSMessage"%>
<%@ include file="/admin/header.html"%>

<%
CSMessage csMessage = (CSMessage) request.getAttribute("csMessage");
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

<FORM METHOD="post" ACTION="CSMessage.do" name="form1">
	<table>
		<tr>
			<td>信件ID:</td>
			<td><input  readonly="readonly"  type="TEXT" name="mail_Id" value="<%= (csMessage== null)? "1" : csMessage.getCsMail().getMailId()  %>" size="45"/></td>
		</tr>
		<tr>
			<td>內文:</td>
			<td><input   type="TEXT" name="content" value="<%= (csMessage == null)? "XXXXXx" :csMessage.getContent() %>" size="45"/></td>
		</tr>
		<tr>
		<tr>
			<td>發送時間:</td>
			<td><input type="TEXT" name="createTime" value="<%= (csMessage==null)? new java.sql.Timestamp(System.currentTimeMillis()) : csMessage.getCreateTime()%>" size="45"/></td>
		</tr>
		<tr>
			<td>來源:</td>
			<td><input type="TEXT" name="message_from" value="<%= (csMessage==null)?  "1"  : csMessage.getMessageFrom() %>" size="45"/></td>
		</tr>
		
	</table>
	<br>
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增">
	<input type="submit" value="回首頁">
	<table id="table-1">
</table>
</FORM>

</body>

</html>