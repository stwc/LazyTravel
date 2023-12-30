<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.lazytravel.journey.dao.*"%>
<%@page import="com.lazytravel.journey.entity.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>後臺-旅行團(查詢)</title>

<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css" />
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
		background: #ccd5ae;
		padding-top: 3px;
	}
	
	a#add:hover {
		background-color: #6b705c;
		color: white;
	}

	a#add:active {
		outline: 2px solid #6b705c;
		box-shadow: 0 0 8px #a1a397;
	}
	
	button.btn_modify {
		width: 70px;
		height: 30px;
		border-radius: 90px;
		border-color: transparent;
		color: white;
		background: #9c6644;
	}
	
	button.btn_modify:active {
		outline: 2px solid #9c6644;
		box-shadow: 0 0 8px #a1a397;
	}
	
	button.btn_search {
		width: 70px;
		height: 30px;
		border-radius: 90px;
		border-color: transparent;
		color: white;
		background: #ccd5ae;
	}
	
	button.btn_search:hover {
		background-color: #6b705c;
		color: white;
	}
	
	button.btn_search:active {
		outline: 2px solid #6b705c;
		box-shadow: 0 0 8px #a1a397;
	}
	
	table.findByTime input, table.findByTime button {
		margin-left: 15px;
	}
	
	table{
        font-size: 14px;
    }
    
    table#list td:nth-child(9) {
    	padding: 8px 5px;
    }
    
    
</style>
	
<%
	// getTourGroupListByTime()的條件判斷
	Boolean searchPerformed = (Boolean)request.getAttribute("searchPerformed");
	if (searchPerformed == null || !searchPerformed) {
	    TourGroupService tourGroupSvc = new TourGroupServiceImpl();
	    List<TourGroup> tourGroupList = tourGroupSvc.getAll();
	    pageContext.setAttribute("tourGroupList", tourGroupList);
	}
%>

</head>
  <body>
<!--     <div id="header"></div> -->
	<%@ include file="/admin/header.jsp" %>

    <div id="main" class="p-3">
      <h3 class="mx-3 mb-5">
        <span>旅行團總覽</span>
        <a href="${pageContext.request.contextPath}/journey/admin/tourGroup_on.jsp" id="add" class="btn">新增</a>
      </h3>
      <div class="card mx-4 my-4">
        <div class="card-header">旅行團查詢</div>
        <div class="card-body mx-3">
          
   		<form method="post" action="<%=request.getContextPath()%>/journey/admin/tourGroup.do">
   		  	<table class="findByTime my-5">
	        	<tr>
	                <td>行程時間：</td>
	                <td><input type="date" name="startTimeSearch" value="<%= request.getParameter("startTimeSearch") %>" /></td>
	                <td>&nbsp;&nbsp;&nbsp;~</td>
	                <td><input type="date" name="endTimeSearch" value="<%= request.getParameter("endTimeSearch") %>"/></td>
	                <td>
	                    <button type="submit" class="btn_search">查詢</button>
	                    <input type="hidden" name="action" value="tourGroup_search" />                 
                	</td>
             	</tr>
          	</table>
        </form> 

          <table id="list" class="display mb-5">
            <thead>
              <tr>
                <th>旅行團ID</th>
                <th>行程ID<br>/ 名稱</th>
                <th>行程起訖日</th>
                <th>價格</th>
                <th>報名人數</th>
                <th>成團人數</th>
                <th>人數上限</th>
                <th>報名起訖日</th>
                <th>狀態</th>
                <th>建立時間</th>
                <th>更新時間</th>
                <th style="width: 50px; padding:10px 0px;"></th>
              </tr>
            </thead>


			<tbody>
				<c:forEach var="tourGroup" items="${tourGroupList}">
	              <tr>
					<td>${tourGroup.groupId}</td>
					<td>${tourGroup.journey.journeyId} <br> ${tourGroup.journey.journeyName}</td>
					<td>${tourGroup.startTime} ~ ${tourGroup.endTime}</td>
					<td>${tourGroup.price}</td>
					<td>${tourGroup.signupNum}</td>
					<td>${tourGroup.minRequired}</td>
					<td>${tourGroup.maxRequired}</td>
					<td>${tourGroup.signupDate} ~ ${tourGroup.dueDate}</td>
					<td>${(tourGroup.groupStatus eq 0) ? "未上架" : "已上架"}</td>
					<td>${tourGroup.createTime}</td>
					<td>${tourGroup.updateTime}</td>     
	                <td>
	                  <form method="post" action="<%=request.getContextPath()%>/journey/admin/tourGroup.do">
	                    <button type="submit" class="btn_modify">修改</button> 
	                    <input type="hidden" name="group_id" value="${tourGroup.groupId}" />
	                    <input type="hidden" name="action" value="tourGroup_modify" />             
	                  </form>
	                </td>
	              </tr>
	        	</c:forEach>
	      	<tbody>

          </table>
        </div>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
    <script>
      let table = new DataTable("#list");

      $(function () {
//         $("#header").load("../admin/header.html");
        new DataTable('#example');
      });
    </script>
  </body>
</html>
