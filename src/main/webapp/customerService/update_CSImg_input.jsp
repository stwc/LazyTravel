<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page import="com.lazytravel.customerservice.controller.CSImgServlet "%> --%>
<%@ page import="com.lazytravel.customerservice.entity.CSImg"%>
<%@ include file="/admin/header.html"%>

<%
CSImg csImg= (CSImg) request.getAttribute("csImg");
%>

<html>

<head>

	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>訊息修改 - update_CSImg_input.jsp</title>

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



<h3>圖片修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="CSImg.do"" name="form1">
	<table>
	<tr>
		<tr>
			<td>圖片ID:</td>
			<td><input  type="TEXT" name="Imgid" value="<%= (csImg == null)? "1" : csImg.getImgId()%>" size="45"/></td>
		</tr>
		<tr>
			<td>訊息ID:</td>
			<td><input  type="TEXT"  name="MessageId" value="<%= (csImg == null)? "1" : csImg.getCSMessage().getMessageId()%>" size="45"/></td>
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
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="ImgId" value="<%=csImg.getImgId()%>">
	<input type="submit" value="送出修改">
</FORM>

</body>

</html>
