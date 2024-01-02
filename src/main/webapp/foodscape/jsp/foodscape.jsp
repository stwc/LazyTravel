<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.lazytravel.foodscape.dao.*"%>
<%@page import="com.lazytravel.foodscape.entity.*"%>
<%@page import="com.lazytravel.foodscape.service.*"%>


<%
FoodScapeService foodscapeService = new FoodScapeServiceImpl();
List<FoodScape> list = foodscapeService.getAllFoodScapes();
pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>後臺 - 美食/景點</title>

  <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
  <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">

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
  </style>
  

</head>

<body>
  <div id="header"></div>
	
	<%-- 錯誤列表--%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 	    <c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<!-- <ul> -->
<!--   <li><a href='foodscape.jsp'>List</a> all FoodScapes.  <br><br></li> -->
  
  <div id="main" class="p-3">
    <div class="mx-3 mb-4 d-flex align-items-start">
        <h3 class="d-inline-block me-3">美食景點</h3>
        <a href="${pageContext.request.contextPath}/foodscape/jsp/foodscape_on.jsp" id="add" class="btn">新增</a>
    </div>

    <div class="table-responsive mx-3">
      <table id="example" class="table table-striped" style="width:100%">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">名稱</th>
            <th scope="col">電話</th>
            <th scope="col">地址</th>
            <th scope="col">縣市</th>
            <th scope="col">經度</th>
            <th scope="col">緯度</th>
            <th scope="col">介紹</th>
            <th scope="col">更新時間</th>
            <th scope="col">狀態</th>
            <th scope="col">類別</th>
            <th scope="col">修改</th>
          </tr>

        </thead>
        <tbody>
        <c:forEach var="foodscape" items="${list}">
          <tr>
          	<td>${foodscape.foodScapeId}</td>
          	<td>${foodscape.foodScapeName}</td>
          	<td>${foodscape.phone}</td>
          	<td>${foodscape.address}</td>
          	<td>${foodscape.city}</td>
          	<td>${foodscape.lng}</td>
          	<td>${foodscape.lat}</td>
          	<td>${foodscape.intro}</td>
          	<td>${foodscape.updateTime}</td>
          	<td>${foodscape.foodScapeStatus}</td>
          	<td>${foodscape.category}</td>
            <td>
              <form method="post" action="<%=request.getContextPath()%>/foodscape/jsp/foodscapeModify.jsp">
                <button type="submit" class="btn-modify btn">修改</button>
                <input type="hidden" name="FOODSCAPE_ID" value="${foodScape.foodScapeId}">
                <input type="hidden" name="action" value="modify">
              </form>
            </td>
            </tr>
            </c:forEach>
            </tbody>
<!--             <td>22001</td> -->
<!--             <td>台北101</td> -->
<!--             <td>27787443</td> -->
<!--             <td>信義區信義路五段311號</td> -->
<!--             <td>台北</td> -->
<!--             <td>121.784723</td> -->
<!--             <td>25.221873</td> -->
<!--             <td>夜景真的很美</td> -->
<!--             <td>2023/12/02/15:22</td> -->
<!--             <td>已上架</td> -->
<!--             <td>景點</td> -->
          </tr>
          <tr>
            <td>
<%--               <form method="post" action="<%=request.getContextPath()%>/foodscape/jsp/foodscapeModify.jsp"> --%>
<!--                 <button type="submit" class="btn-modify btn">修改</button> -->
<!--                 <input type="hidden" name="customer_id" value=""> -->
<!--                 <input type="hidden" name="action" value="modify"> -->
<!--               </form> -->
<!--             </td> -->
<!--             <td>22002</td> -->
<!--             <td>鼎泰豐</td> -->
<!--             <td>27787443</td> -->
<!--             <td>信義區信義路五段333號</td> -->
<!--             <td>台北</td> -->
<!--             <td>121.784721</td> -->
<!--             <td>25.231231</td> -->
<!--             <td>這間真的好吃</td> -->
<!--             <td>2023/12/04/18:23</td> -->
<!--             <td>已上架</td> -->
<!--             <td>美食</td> -->

<!--           </tr> -->
        </tbody>
      </table>
    </div>

  </div>


  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
  <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
  <script>
    $(function () {
        $("#header").load("<%=request.getContextPath()%>/admin/header.jsp");
      new DataTable('#example');
    });
  </script>
</body>

</html>