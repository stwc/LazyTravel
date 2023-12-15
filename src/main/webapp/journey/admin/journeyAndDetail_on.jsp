<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.lazytravel.journey.dao.*"%>
<%@page import="com.lazytravel.journey.entity.*"%>

<%@ include file="/admin/header.html" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後臺-行程(新增)</title>

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

   *{
        box-sizing: border-box;
    }

    body{
        margin: 0;
    }

    main#main{
        padding: 50px 80px;
    }

    input, textarea{
        border: 1px solid #CCD5AE;
    }

    input:focus, textarea:focus{
        outline: 2px solid #CCD5AE;
        box-shadow: 0 0 8px #a1a397
    }

    select {
        border: 1px solid #CCD5AE;
        height: 27px;
    }

    select:focus {
        border:2px solid #CCD5AE;
        box-shadow: 0 0 8px #a1a397;
    }

    button.journey_detail_add, 
    button.journey_detail_delete{
        width: 23px;
        height: 23px;
        line-height: 10px;
        border-radius: 90px;
        border-color: transparent;
        background-color: #CCCCCC;
        color: white;
    }

    button.journey_detail_add{
        padding-left: 3.5px;
        display: inline-block;
    }

    div.div_detal_time{
        display: inline-block;
    }

    button.journey_detail_add:active, 
    button.journey_detail_delete:active{
        outline: 2px solid #CCCCCC;
        box-shadow: 0 0 8px #a1a397
    }

    button.btn_submit{
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
    
    button.btn_reset{
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

    div.div_btn{
        display: flex;
        justify-content: flex-end;
    }

    div.div_journeyName label, 
    div.div_days label, 
    div.div_journeyStatus label,
    div.div_detal_joyrneyId label,
    div.div_detal_time label{
        margin-right: 15px;
    }
    
    div.div_price label{
        margin-right: 47px;
    }

    div.div_nthDay label{
        margin-right: 31px;
    }

    div.div_detal_joyrneyId input[type="number"]{
        width: 90px;
        margin-right: 5px;
    }

    div.div_detal_joyrneyId select{
        width: 181px;
        margin-right: 5px;
    }

    div.div_detal_time input{
        margin-right: 25px;
    }

    table {
        border-collapse: separate;
        border-spacing: 10px;
    }

    div.div_journeyName,
    div.div_price,
    div.div_days,
    div.div_journeyStatus,
    div.div_nthDay,
    div.div_detal_joyrneyId,
    div.div_detal_time{
		margin-bottom: 2px;
    }
</style>

<% 
	// 在JourneyServlet.java 存入req的Journey物件，並對journey的setAttribute 
	Journey journey = (Journey) request.getAttribute("journey");
	JourneyDetail journeyDetailList = (JourneyDetail) request.getAttribute("journeyDetailList");
%>

</head>
<body>

	<div id="header"></div>

	<main id="main">

		<p style="font-size: 20px;"><b>新增行程</b></p>
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
					<option value="0" ${journey.journeyStatus == "0" ? "selected" : ""} >未上架</option>
					<option value="1" ${journey.journeyStatus == "1" ? "selected" : ""} >已上架</option>
				</select>
			</div>

			<br> <br>
			<p>行程細項</p>
			<div>
				<div class="div_nthDay">
					<label>第幾天 :</label>
					<input type="number" id="day" name="nth_day" value="<%=(journeyDetailList == null) ? "0" : journeyDetailList.getNthDay()%>">
				</div>
				
				<div class="div_detal_joyrneyId">
					<label>美食景點ID / 名稱 :</label>
					<input type="number" id="placeId" name="foodscape_id" value="<%=(journeyDetailList == null) ? "0" : journeyDetailList.getFoodScapeId()%>">
<!-- 					<select id="placeSelect"> -->
<!-- 						<option value="22001" selected>陽明山一日遊</option> -->
<!-- 						<option value="22002">台北三天兩夜美食旅</option> -->
<!-- 					</select> -->
				</div>
							  
				<div class="div_detal_time">
					<label>開始時間 :</label>
					<input type="time" id="start_time" name="foodscape_id" value="<%=(journeyDetailList == null) ? "00:00" : journeyDetailList.getFoodScapeId()%>">
					<label>結束時間 :</label>
					<input type="time" id="end_time" name="end_time" value="<%=(journeyDetailList == null) ? "00:00" : journeyDetailList.getEndTime()%>">
				</div>
				
				<button class="journey_detail_add" onclick="addRow(event)"><b>+</b></button>
			</div>

			<br>
			<table id="journeyDetailsTable">
				<tr>
					<th>第幾天</th>
					<th>美食景點ID</th>
<!-- 					<th>美食景點名稱</th> -->
					<th>時間起訖</th>
<!-- 					<th>地址</th> -->
					<th></th>
				</tr>
			</table>


			<br> <br>
			<div>
				<label>行程介紹 :</label>
				<textarea name="content" style="height: 150px; width: 100%;"><%=(journey == null) ? "行程介紹..." : journey.getContent()%></textarea>
			</div>

			<br>
			<div class="div_btn">
				<button type="submit" name="journeyAndDetail_add" class="btn_submit">送出</button>
				<input type="hidden" name="action" value="journeyAndDetail_add">
				
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
		
		
		function addRow(event) {
			event.preventDefault();

		    var nthDay = document.getElementById("day").value;
		    var foodScapeId = document.getElementById("placeId").value;
		    var startTime = document.getElementById("start_time").value;
		    var endTime = document.getElementById("end_time").value;
		    
		    // 新增一個row，並依序加入對應的值
		    var table = document.getElementById("journeyDetailsTable");
		    var newRow = table.insertRow(table.rows.length);
		    var cell0 = newRow.insertCell(0);
		    var cell1 = newRow.insertCell(1);
		    var cell2 = newRow.insertCell(2);
		    var cell3 = newRow.insertCell(3);

		    cell0.innerHTML = nthDay;
		    cell1.innerHTML = foodScapeId;
		    cell2.innerHTML = startTime + " ~ " + endTime;
		    cell3.innerHTML = '<button class="journey_detail_delete" onclick="deleteRow(this)"><b>-</b></button>';
		    
<%-- 			<% request.getParameter("nthDay"); %> --%>
<%-- 			<% request.getParameter("foodScapeId"); %> --%>
<%-- 			<% request.getParameter("startTime"); %> --%>
<%-- 			<% request.getParameter("endTime"); %> --%>
			
<%-- 			<% request.setParameter("nthDay", nthDay); %> --%>
<%-- 			<% request.setParameter("foodScapeId", foodScapeId); %> --%>
<%-- 			<% request.setParameter("startTime", startTime); %> --%>
<%-- 			<% request.setParameter("endTime", endTime); %> --%>
			
// 			var newRowData = {
// 			nthDay: "${journeyDetailList.nthDay}",
// 			foodScapeId: "${journeyDetailList.foodScapeId}",
// 			startTime: "${journeyDetailList.startTime}",
// 			endTime: "${journeyDetailList.endTime}"
// 			};

// 			journeyDetailList.add(newRowData);
		}
		
		function deleteRow(button) {
		    var row = button.parentNode.parentNode;   // button的父節點tr
		    row.remove();

// 		    var rowIndex = row.rowIndex - 1;
// 		    if (rowIndex >= 0) {
// 		        journeyDetailList.splice(rowIndex, 1);
// 		    }
		}
		
	</script>

</body>
</html>