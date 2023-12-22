<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lazytravel.customerservice.entity.CSImg"%>
<%@ include file="/admin/header.html"%>


<%
CSImg csImg= (CSImg) request.getAttribute("csImg");
%>

<html>



<body bgcolor='white'>



<h3>圖片新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="CSImg.do" name="form1">
	<table>
		
		<tr>
			<td>訊息ID:</td>
			<td><input  type="TEXT" name="MessageId" value="<%= (csImg== null)? "1" : csImg.getCSMessage().getMessageId()  %>" size="45"/></td>
		</tr>
		<tr>
			<td>內文圖片:</td>
			<td><input type="TEXT" name="img" value="<%= (csImg==null)? "321" : csImg.getImg()%>" size="45"/></td>
		</tr>
		<tr>
			<td>上傳時間:</td>
			<td><input type="TEXT" name="createTime" value="<%= (csImg==null)? new java.sql.Timestamp(System.currentTimeMillis()) : csImg.getCreateTime()%>" size="45"/></td>
		</tr>
		
	</table>
	<br>
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增" >
	<input type="submit" value="回首頁">
	<table id="table-1">
	</table>
</FORM>

</body>

</html>