<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.lazytravel.journey.entity.Journey"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	Journey journey = (Journey) request.getAttribute("journey");	// 要在JourneyServlet.java 存入req的Journey物件，並對journey的setAttribute  
%>                                                                  

<html>

<head>
	<title>行程資料-查詢</title>

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
			<h3>行程資料-查詢</h3>
			<h4><a href="select_Journey_page.jsp"><img src="../static/images/logo.png" width="100" height="100" border="0" alt="">回行程頁面</a></h4>
		</td></tr>
	</table>

	<table>
		<tr>
			<th>行程ID</th>
			<th>行程名稱</th>
			<th>價格</th>
			<th>介紹文</th>
			<th>建立時間</th>
			<th>行程天數</th>
			<th>購買次數</th>
			<th>平均評分</th>
			<th>評分次數</th>
			<th>狀態</th>
		</tr>
		<tr>
			<td><%= journey.getJourneyId() %></td>
			<td><%= journey.getJourneyName() %></td>
			<td><%= journey.getPrice() %></td>
			<td><%= journey.getContent() %></td>
			<td><%= journey.getCreateTime() %></td>
			<td><%= journey.getDays() %></td>
			<td><%= journey.getBuyCount() %></td>
			<td><%= journey.getAvgScore() %></td>
			<td><%= journey.getScoreCount() %></td>
			<td><%= (journey.getJourneyStatus().equals("0")) ? "未上架" : "已上架" %></td>
		</tr>
	</table>

</body>

</html>