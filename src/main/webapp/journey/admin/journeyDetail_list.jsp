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
<title>後臺-行程明細(查詢)</title>

<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css" />
<link rel="icon" href="${pageContext.request.contextPath}/static/images/logo.ico" type="image/x-icon" />

<style>
	div.card-header span, div.card-header form {
		display: inline-block;
	}
	
	div.card-header span {
		margin-right: 20px;
	}
	
	a#add {
		width: 70px;
		height: 30px;
		border-radius: 90px;
		border-color: transparent;
		color: white;
		background: #9C6644;
		padding-top: 3px;
	}
	
	a#add:active {
		outline: #9C6644;
		box-shadow: 0 0 8px #a1a397
	}
	
	div.content {
		margin: 0px 30px 40px 30px;
	}
	
	div.content div {
		margin-bottom: 8px;
	}
</style>

<%	
	Journey journey = (Journey) request.getAttribute("journey");
	JourneyDetail journeyDetailList = (JourneyDetail) request.getAttribute("journeyDetail");
%>

</head>
<body>

<!--     <div id="header"></div> -->
    <%@ include file="/admin/header.jsp" %>

    <div id="main" class="p-3">
      <h3 class="mx-3 mb-5">行程總覽</h3>
      <div class="card mx-4 my-4">
        <div class="card-header mb-5">
            <span>行程明細</span>   
			<!-- 用Agax送 -->
			<a href="${pageContext.request.contextPath}/journey/admin/journey_list.jsp" id="add" class="btn">返回</a>  
        </div>

        <div class="card-body mx-3">

          <table id="123" class="display">
            <thead>
              <tr>
                <th>行程ID</th>
                <th>美食/景點名稱</th>
                <th>開始時間</th>
                <th>結束時間</th>
                <th>第幾天</th>
              </tr>
            </thead>

			<c:forEach var="journeyDetailList" items="${journeyDetailList}">
				<tbody>
					<tr>
						<td>${journeyDetailList.journeyId}</td>
						<td>${journeyDetailList.foodScapeId}</td>
						<td>${journeyDetailList.startTime}</td>
						<td>${journeyDetailList.endTime}</td>
						<td>${journeyDetailList.nthDay}</td>
					</tr>
				</tbody>
			</c:forEach>

          </table>
        </div>
                
        <div class="content">
          <div><b>介紹文</b></div>
          <textarea name="content" style="height: 120px; width: 100%;" readonly>${journey.content}</textarea>
        </div>

      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
    <script>
      let table = new DataTable("#123");

      $(function () {
//         $("#header").load("${pageContext.request.contextPath}/admin/header.html");
        new DataTable('#example');
      });
    </script>



</body>
</html>