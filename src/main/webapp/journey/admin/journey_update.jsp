<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.lazytravel.journey.service.*"%>
<%@page import="com.lazytravel.journey.dao.*"%>
<%@page import="com.lazytravel.journey.entity.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後臺-行程(修改)</title>

<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
<link rel="icon" href="${pageContext.request.contextPath}/static/images/logo.ico" type="image/x-icon">

<style>
	button.btn-modify {
		background-color: #9C6644;
		color: white;
	}
	
	a#add {
		background-color: #B7B7A4;
		color: white;
	}
	
	a#add:hover {
		background-color: #6B705C;
		color: white;
	}
	
	* {
		box-sizing: border-box;
	}
	
	body {
		margin: 0;
	}
	
	main#main {
		padding: 50px 80px;
	}
	
	input, textarea {
		border: 1px solid #CCD5AE;
	}
	
	input:focus, textarea:focus {
		outline: 2px solid #CCD5AE;
		box-shadow: 0 0 8px #a1a397
	}
	
	select {
		border: 1px solid #CCD5AE;
		height: 27px;
	}
	
	select:focus {
		border: 2px solid #CCD5AE;
		box-shadow: 0 0 8px #a1a397;
	}
	
	button.btn_submit {
		width: 100px;
		height: 30px;
		border-radius: 90px;
		border-color: transparent;
		color: white;
		background: #CCD5AE;
		margin-right: 15px;
	}
	
	button.btn_submit:active {
		outline: 2px solid #CCD5AE;
		box-shadow: 0 0 8px #a1a397
	}
	
	button.btn_reset {
		width: 100px;
		height: 30px;
		border-radius: 90px;
		border-color: transparent;
		color: white;
		background: #9C6644;
	}
	
	button.btn_reset:active {
		outline: 2px solid #9C6644;
		box-shadow: 0 0 8px #a1a397
	}
	
	div.div_btn {
		display: flex;
		justify-content: flex-end;
	}
	
	div.div_journeyId label, div.div_journeyId div {
		display: inline-block;
	}
	
	div.div_journeyId label {
		margin-right: 30px;
	}
	
    div.div_journeyName label, 
    div.div_days label, 
    div.div_journeyStatus label{
        margin-right: 15px;
    }
    
    div.div_price label{
        margin-right: 47px;
    }

    table {
        border-collapse: separate;
        border-spacing: 10px;
    }

    div.div_journeyName,
    div.div_price,
    div.div_days,
    div.div_journeyStatus{
        margin-bottom: 2px;
    }
</style>

<% 
	// 在JourneyServlet.java 存入req的Journey物件，並對journey的setAttribute 
	Journey journey = (Journey) request.getAttribute("journey");
%>

</head>
<body>

<!-- 	<div id="header"></div> -->
	<%@ include file="/admin/header.jsp" %>

	<main id="main">

		<p style="font-size: 20px;"><b>修改行程</b></p>
		<br>
		
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<form method="post" action="<%=request.getContextPath()%>/journey/admin/journey.do">
			<div class="div_journeyId">
				<label>行程ID :</label>
				<div><%=journey.getJourneyId()%></div>
			</div>
			<br>

			<div class="div_journeyName">
				<label>行程名稱 :</label>
				<input type="text" name="journey_name" value="<%= (journey == null) ? "行程名稱..." : journey.getJourneyName() %>">
			</div>

			<div class="div_price">
				<label>價格 :</label>
				<input type="number" name="price" value="<%= (journey == null) ? "0" : journey.getPrice() %>">
			</div>

			<div class="div_days">
				<label>行程天數 :</label>
				<input type="number" name="days" value="<%= (journey == null) ? "0" : journey.getDays() %>">
			</div>

			<div class="div_journeyStatus">
				<label>行程狀態 :</label>
				<select name="journey_status">
					<option value="0" <%= (journey.getJourneyStatus().equals("0")) ? "selected" : "" %>>未上架</option>
					<option value="1" <%= (journey.getJourneyStatus().equals("1")) ? "selected" : "" %>>已上架</option>
				</select>
			</div>

			<br> <br>
			<div>
				<label>行程介紹 :</label>
				<textarea name="content" style="height: 150px; width: 100%;"><%= (journey == null) ? "行程介紹..." : journey.getContent() %></textarea>
			</div>
			
			<!-- 下面四個欄位不能透過後台修改資料 -->
			<input type="hidden" name="create_time" value="<%=journey.getCreateTime()%>">
			<input type="hidden" name="avg_score" value="<%=journey.getAvgScore()%>">
			<input type="hidden" name="score_count" value="<%=journey.getScoreCount()%>">
			<input type="hidden" name="buy_count" value="<%=journey.getBuyCount()%>">
				
			<br>
			<div class="div_btn">
				<button type="submit" name="journey_update" class="btn_submit">送出</button>
				<input type="hidden" name="journey_id" value="<%=journey.getJourneyId()%>">
				<input type="hidden" name="action" value="journey_update">
				
				<button type="reset" class="btn_reset" onclick="redirectToJourneyList()">取消</button>
			</div>
		</form>

	</main>


	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script	src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
	<script>
		$(function() {
// 			$("#header").load("header.html");
			new DataTable('#example');
		});
		
		var contextPath = "${pageContext.request.contextPath}";
		function redirectToJourneyList() {
			window.location.href = contextPath + "/journey/admin/journey_list.jsp";
		}
	</script>

</body>
</html>