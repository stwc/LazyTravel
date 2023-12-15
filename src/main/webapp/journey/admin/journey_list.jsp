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
<title>後臺-行程(查詢)</title>

<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css" />
<link rel="stylesheet" href="//cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css" />
<link rel="icon" href="${pageContext.request.contextPath}/static/images/logo.ico" type="image/x-icon" />

<style>
	h3 span {
		margin-right: 10px;
	}

	a#add {
		width: 70px;
		height: 30px;
		border-radius: 90px;
		border-color: transparent;
		color: white;
		background: #CCD5AE;
		padding-top: 3px;
	}

	a#add:hover {
		background-color: #6B705C;
		color: white;
	}
	
	a#add:active {
		outline: #6B705C;
		box-shadow: 0 0 8px #a1a397
	}

    button.btn_journey_detail_search{
        width: 90px;
        height: 30px;
        border-radius: 90px;
        border-color: transparent;
        color: white;
        background: #9C6644;
    }

    button.btn_journey_detail_search:active {
        outline: 2px solid #9C6644;
   		box-shadow: 0 0 8px #a1a397
    }
	
	button.btn_modify {
		width: 70px;
		height: 30px;
		border-radius: 90px;
		border-color: transparent;
		color: white;
		background: #9C6644;
	}

	button.btn_modify:active {
		outline: 2px solid #9C6644;
		box-shadow: 0 0 8px #a1a397
	}
</style>

<%
	JourneyService journeySvc = new JourneyServiceImpl();
	List<Journey> journeyList = journeySvc.getAll();
	pageContext.setAttribute("journeyList", journeyList);
%>

</head>
<body>
	
    <div id="header"></div>

    <div id="main" class="p-3">
      <h3 class="mx-3 mb-5">
        <span>行程總覽</span>
        <a href="${pageContext.request.contextPath}/journey/admin/journeyAndDetail_on.jsp" id="add" class="btn">新增</a>
      </h3>
      <div class="card mx-4 my-4">
        <div class="card-header mb-5">行程查詢</div>
        <div class="card-body mx-3">

          <table id="123" class="display mb-5">
            <thead>
              <tr>
                <th>行程ID</th>
                <th>行程名稱</th>
                <th>價格</th>
                <th>建立時間</th>
                <th>行程<br>天數</th>
                <th>購買<br>次數</th>
                <th>平均<br>評分</th>
                <th>評分<br>次數</th>
                <th>狀態</th>
                <th></th>
              </tr>
            </thead>
			
			<tbody>
				<c:forEach var="journey" items="${journeyList}">
	              <tr>
					<td>${journey.journeyId}</td>
					<td>${journey.journeyName}</td>
					<td>${journey.price}</td>
					<td>${journey.createTime}</td>
					<td>${journey.days}</td>
					<td>${journey.buyCount}</td>
					<td>${journey.avgScore}</td>
					<td>${journey.scoreCount}</td>
					<td>${(journey.journeyStatus == 0) ? "未上架" : "已上架" }         
	                <td>
	                  <form method="post" action="<%=request.getContextPath()%>/journey/admin/journey.do" style="display: inline-block;">
	                    <button type="submit" class="btn_journey_detail_search">行程明細</button> 
	                    <input type="hidden" name="journey_id" value="${journey.journeyId}" />
	                    <input type="hidden" name="action" value="journey_detail_search" />             
	                  </form>
	                  <form method="post" action="<%=request.getContextPath()%>/journey/admin/journey.do" style="display: inline-block; margin-left: 4px;">
	                    <button type="submit" class="btn_modify">修改</button>
	                    <input type="hidden" name="journey_id" value="${journey.journeyId}" />
	                    <input type="hidden" name="action" value="journey_modify" />
	                  </form>  
	                </td>
	              </tr>
	        	</c:forEach>
	        <tbody>
	        
        
	        
        </div>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
    <script>
    	let table = new DataTable("#123");
    	
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