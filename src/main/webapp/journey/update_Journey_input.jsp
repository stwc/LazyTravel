<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lazytravel.journey.entity.Journey"%>

<%
	Journey journey = (Journey) request.getAttribute("journey");
%>

<html>

<head>

	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>行程資料-修改</title>

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
		<h3>行程資料-修改</h3></td><td>
		<h4><a href="select_Journey_page.jsp"><img src="../static/images/logo.png" width="100" height="100" border="0" alt="">返回行程頁面</a></h4>
	</td></tr>
</table>

<h3>行程資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="journey.do" name="form1">
	<table>
		<tr>
			<td>行程ID:<font color=red><b>*</b></font></td>
			<td><%=journey.getJourneyId()%></td>
		</tr>
		<tr>
			<td>行程名稱:</td>
			<td><input type="TEXT" name="journey_name" value="<%= (journey==null)? "行程名稱..." : journey.getJourneyName()%>" size="45"/></td>
		</tr>
		<tr>
			<td>行程介紹:</td>
			<td><input type="TEXT" name="content" value="<%= (journey==null)? "行程介紹..." : journey.getContent()%>" size="45"/></td>
		</tr>	
		<tr>
			<td>價格:</td>
			<td><input type="NUMBER" name="price" value="<%= (journey==null)? "0" : journey.getPrice()%>" size="45"/></td>
		</tr>
		<tr>
			<td>行程天數:</td>
			<td><input type="NUMBER" name="days" value="<%= (journey==null)? "0" : journey.getDays()%>" size="45"/></td>
		</tr>	
		<tr>
			<td>行程狀態:</td>
			<td>
				<select name="journey_status">
					<option value="0" <%= (journey.getJourneyStatus().equals("0")) ? "selected" : "" %> >未上架</option>
					<option value="1" <%= (journey.getJourneyStatus().equals("1")) ? "selected" : "" %> >已上架</option>
				</select>
			</td>	
		</tr>
<!-- 		<tr> -->
<!-- 			<td>行程新增時間:</td> -->
<%-- 			<td><input type="DATE" name="create_time" value="<%= (journey==null)? "0" : journey.getCreateTime()%>" size="45"/></td> --%>
<!-- 			<td><input type="TEXT" id="f_date1" name="create_time" size="45"/></td> -->
<!-- 		</tr>	 -->
	</table>
	
	<br>
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="journey_id" value="<%=journey.getJourneyId()%>">
	<input type="submit" value="送出修改">
</FORM>


</body>

</html>


