<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.lazytravel.foodscape.entity.FoodScape"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	FoodScape foodscape = (FoodScape) request.getAttribute("foodscape"); // 在FoodscapeService中setAttribute("foodscape")
%>

<html>

<head>
	<title>美食景點資料 - listOneFoodScape.jsp</title>

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

<!-- 	<h4>此頁暫練習採用 Script 的寫法取值:</h4> -->
	<table id="table-1">
		<tr><td>
			<h3>美食景點資料 - listOneFoodScape.jsp</h3>
			<h4><a href="select_FoodScape_page.jsp"><img src="../static/images/logo.png" width="100" height="100" border="0" alt="">回首頁</a></h4>
		</td></tr>
	</table>

	<table>
		<tr>
			<th>美食/景點ID</th>
			<th>美食/景點名稱</th>
			<th>電話</th>
			<th>地址</th>
			<th>縣市</th>
			<th>經度</th>
			<th>緯度</th>
			<th>介紹</th>
			<th>更新時間</th>
			<th>狀態</th>
			<th>類別</th>
		</tr>
		<tr>
			<td><%= foodscape.getFoodScapeId()%></td>
			<td><%= foodscape.getFoodScapeName()%></td>
			<td><%= foodscape.getPhone()%></td>
			<td><%= foodscape.getAddress()%></td>
			<td><%= foodscape.getCity()%></td>
			<td><%= foodscape.getLng()%></td>
			<td><%= foodscape.getLat()%></td>
			<td><%= foodscape.getIntro()%></td>
			<td><%= foodscape.getUpdateTime()%></td>
			<td><%= (foodscape.getFoodScapeStatus().equals("0")) ? "已上架" : "已下架"%></td>
			<td><%= foodscape.getCategory()%></td>


		</tr>
	</table>

</body>

</html>