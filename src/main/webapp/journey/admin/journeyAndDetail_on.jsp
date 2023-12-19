<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.lazytravel.journey.dao.*"%>
<%@page import="com.lazytravel.journey.entity.*"%>

<%@ page import="java.text.SimpleDateFormat" %>

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
    
    input.nth_day_size{
        pointer-events: none;
        width:70px;
        border: none;
    }

    input.foodscape_id_size{
        pointer-events: none;
        width:110px;
        border: none;
    }

    input.start_time_size,
    input.end_time_size{
        pointer-events: none;
        width:110px;
        border: none;
    }
</style>

<% 
	// 在JourneyServlet.java 存入req的Journey物件，並對journey的setAttribute 
	Journey journey = (Journey) request.getAttribute("journey");

	List<JourneyDetail> journeyDetailList = (List<JourneyDetail>) request.getAttribute("journeyDetailList");

	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
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
					<input type="number" id="nth_day">
				</div>
				
				<div class="div_detal_joyrneyId">
					<label>美食景點ID / 名稱 :</label>
					<input type="number" id="foodScope_Id">
<!-- 					<select id="placeSelect"> -->
<!-- 						<option value="22001" selected>陽明山一日遊</option> -->
<!-- 						<option value="22002">台北三天兩夜美食旅</option> -->
<!-- 					</select> -->
				</div>
							  
				<div class="div_detal_time">
					<label>開始時間 :</label>
					<input type="time" id="start_time">
					<label>結束時間 :</label>
					<input type="time" id="end_time">
				</div>
				
				<button class="journey_detail_add" onclick="addRow(event)"><b>+</b></button>
			</div>

			<br>
			<table id="journeyDetailsTable">
		        <thead>
		            <tr>
						<th>第幾天</th>
						<th>美食景點ID</th>
						<th>開始時間</th>
						<th>結束時間</th>
						<th></th>
						<th></th>
		            </tr>
		        </thead>
		        
		        <tbody id="journeyDetailsBody">     
		        	<!-- addRow()動態產生行程細項 -->
		        	
					<!-- 若有錯誤處理時，接收後端回傳數值並產生行程細項 -->					
					<c:if test="${not empty errorMsgs}">
						<c:forEach var="journeyDetail" items="${journeyDetailList}">
							<tr>
								<td><input type="number" name="nth_day${journeyDetail.getIndex()}" value="${journeyDetail.getNthDay()}" class="nth_day_size"></td>
								<td><input type="number" name="foodscape_id${journeyDetail.getIndex()}" value="${journeyDetail.getFoodScapeId()}" class="foodscape_id_size"></td>
								
<!-- 								<td> -->
<%-- 								    <c:set var="startTimeStr" value="${journeyDetailList.getStartTime()}" /> --%>
<%-- 									<c:set var="startTime" value="${dateFormat.parse(startTimeStr)}" /> --%>
<%-- 									<c:set var="formattedStartTime" value="${outputFormat.format(startTime)}" /> --%>
<%-- 								    <input type="time" name="start_time${journeyDetailList.getIndex()}" value="${formattedStartTime}" class="start_time_size">   --%>
<!-- 								</td> -->
<!-- 								<td> -->
<%-- 								    <c:set var="endTimeStr" value="${journeyDetailList.getEndTime()}" /> --%>
<%-- 									<c:set var="endTime" value="${dateFormat.parse(endTimeStr)}" /> --%>
<%-- 									<c:set var="formattedEndTime" value="${outputFormat.format(endTime)}" /> --%>
<%-- 								    <input type="time" name="end_time${journeyDetailList.getIndex()}" value="${formattedEndTime}" class="start_time_size">   --%>
<!-- 								</td>								 -->
								
								
								
								
								
								
						    	<td><input type="time" name="start_time${journeyDetail.getIndex()}" value="${journeyDetail.getStartTime()}" class="start_time_size"></td>
						    	<td><input type="time" name="end_time${journeyDetail.getIndex()}" value="${journeyDetail.getEndTime()}" class="end_time_size"></td>
						    	<td><button class="journey_detail_delete" onclick="deleteRow(this)"><b>-</b></button></td>
						    	<td><input type="hidden" name="index${journeyDetail.getIndex()}" value="${journeyDetail.getIndex()}"></td>	
							</tr>
						</c:forEach>
					</c:if>
		
		        </tbody>
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
				<input type="hidden" id="totalIndex" name="totalIndex" value="0">
				
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

		
		var index = 0;
		var totalIndex = parseInt(document.getElementById("totalIndex").value);
		
		<c:if test="${not empty errorMsgs}">
	        var index = <%= request.getAttribute("index") %>;
	        var totalIndex = <%= request.getAttribute("totalIndex") %>;
		</c:if>
		
		function addRow(event) {
		    event.preventDefault();
		    
			index++;
			totalIndex++;
			document.getElementById("totalIndex").value = totalIndex;
			
		    var nthDayJS = document.getElementById("nth_day").value;
		    var foodScapeIdJS = document.getElementById("foodScope_Id").value;
		    var startTimeJS = document.getElementById("start_time").value;
		    var endTimeJS = document.getElementById("end_time").value;
			
		    var nthDayName = "nth_day" + index;
		    var foodScapeIdName = "foodscape_id" + index;
		    var startTimeName = "start_time" + index;
		    var endTimeName = "end_time" + index;
		    var indexName = "index" + index;
		    
		    var newRow = document.createElement("tr");
		    newRow.innerHTML = 
		    	'<td><input type="number" name="' + nthDayName + '" value="' +　nthDayJS　+ '" class="nth_day_size"></td>' + 
		    	'<td><input type="number" name="' + foodScapeIdName + '" value="' +　foodScapeIdJS　+ '" class="foodscape_id_size"></td>' + 
		    	'<td><input type="time" name="' + startTimeName + '" value="' +　startTimeJS　+ '" class="start_time_size"></td>' + 
		    	'<td><input type="time" name="' + endTimeName + '" value="' +　endTimeJS　+ '" class="end_time_size"></td>' + 
		    	'<td><button class="journey_detail_delete" onclick="deleteRow(this)"><b>-</b></button></td>' + 
		    	'<td><input type="hidden" name="' + indexName + '" value="' + index + '"></td>';
		    
			var journeyDetailsBody = document.getElementById("journeyDetailsBody");
			journeyDetailsBody.appendChild(newRow);
			
			console.log(nthDayName);
			console.log(foodScapeIdName);
			console.log(startTimeName);
			console.log(endTimeName);
			console.log(index);
			console.log(totalIndex);
		}

		
		function deleteRow(button) {
		    var row = button.parentNode.parentNode;   // button的父節點tr
		    row.remove();
		}
		
	</script>

</body>
</html>