<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lazytravel.foodscape.entity.FoodScape"%>

<%
	FoodScape foodscape = (FoodScape) request.getAttribute("foodscape");
%>

<html>

<head>

	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>美食景點資料修改</title>

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
		<h3>美食景點資料修改</h3></td><td>
		<h4><a href="select_FoodScape_page.jsp"><img src="../static/images/logo.png" width="100" height="100" border="0" alt="">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="foodscape.do" name="form1">
	<table>
		<tr>
			<td>會員ID:<font color=red><b>*</b></font></td>
			<td><%=foodscape.getFoodScapeId()%></td>
		</tr>
		<tr>
			<td>美食景點:</td>
			<td><input type="TEXT" name="foodscape_name" value="<%= (foodscape==null)? "台北101" : foodscape.getFoodScapeName()%>" size="45"/></td>
		</tr>
		<tr>
			<td>電話:</td>
			<td><input type="TEXT" name="phone" value="<%= (foodscape==null)? "81018800" : foodscape.getPhone()%>" size="45"/></td>
		</tr>
		<tr>
			<td>地址:</td>
			<td><input type="TEXT" name="address" value="<%= (foodscape==null)? "信義區市府路45號" : foodscape.getAddress()%>" size="45"/></td>
		</tr>
		<tr>
			<td>城市:</td>
			<td><input type="TEXT" name="city" value="<%= (foodscape==null)? "台北市" : foodscape.getCity()%>" size="45"/></td>
		</tr>
		<tr>
			<td>經度:</td>
			<td><input type="TEXT" name="lng" value="<%= (foodscape==null)? "121.5615184" : foodscape.getLng()%>" size="45"/></td>
		</tr>
		<tr>
			<td>緯度:</td>
			<td><input type="TEXT" name="lat" value="<%= (foodscape==null)? "25.0338315" : foodscape.getLat()%>" size="45"/></td>
		</tr>
		<tr>
			<td>介紹:</td>
			<td><input type="TEXT" name="intro" value="<%= (foodscape==null)? "觀景台可以看整個北市的夜景" : foodscape.getIntro()%>" size="45"/></td>
		</tr>

		<tr>
			<td>狀態:</td>
			<td>
				<select name="foodscape_status">
					<option value="1" selected>已上架</option>
					<option value="0">未上架</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>類別:</td>
			<td><input type="TEXT" name="category" value="<%= (foodscape==null)? "景點" : foodscape.getCategory()%>" size="45"/></td>
		</tr>
	</table>
	<br>
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="foodscape_id" value="<%=foodscape.getFoodScapeId()%>">
	<input type="submit" value="送出修改">
</FORM>

</body>

</html>
