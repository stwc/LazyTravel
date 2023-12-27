<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.lazytravel.foodscape.dao.*"%>
<%@page import="com.lazytravel.foodscape.entity.*"%>
<%@page import="com.lazytravel.foodscape.service.*"%>


<%@ include file="/admin/header.html" %>

<%
OpenTimeService opentimeService = new OpenTimeServiceImpl();
List<OpenTime> list = opentimeService.getAllOpenTimes();
pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>後臺 - 營業時間</title>

  <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
  <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">

  <style>
  < button.btn-modify {
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
  </style>
  

</head>

<body>
  <div id="header"></div>
	
  
  <div id="main" class="p-3">
    <div class="mx-3 mb-4 d-flex align-items-start">
        <h3 class="d-inline-block me-3">營業時間</h3>
        <a href="${pageContext.request.contextPath}/foodscape/jsp/opentime_on.jsp" id="add" class="btn">新增</a>
    </div>

    <div class="table-responsive mx-3">
      <table id="example" class="table table-striped" style="width:100%">
        <thead>
          <tr>
            <th scope="col">營業時間ID</th>
            <th scope="col">美食景點ID</th>
            <th scope="col">開店時間</th>
            <th scope="col">打烊時間</th>
            <th scope="col">星期</th>
          </tr>

        </thead>
        <tbody>
        <c:forEach var="opentime" items="${list}">
          <tr>
          	<td>${opentime.openTimeId}</td>
<%--           	<td>${opentime.foodscape.foodScapeId}</td> --%>
          	<td>${opentime.startTime}</td>
          	<td>${opentime.endTime}</td>
          	<td>${opentime.week}</td>
            <td>
              <form method="post" action="<%=request.getContextPath()%>/foodscape/jsp/opentimeModify.jsp">
                <button type="submit" class="btn-modify btn">修改</button>
                <input type="hidden" name="OPENTIME_ID" value="${opentime.openTimeId}">
                <input type="hidden" name="action" value="modify">
              </form>
            </td>
            </tr>
            </c:forEach>
            </tbody>

        </tbody>
      </table>
    </div>

  </div>


  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
  <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
  <script>
    $(function () {
      $("#header").load("header.html");
      new DataTable('#example');
    });
  </script>
</body>

</html>