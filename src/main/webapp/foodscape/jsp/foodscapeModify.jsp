<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.lazytravel.foodscape.dao.*"%>
<%@page import="com.lazytravel.foodscape.entity.*"%>
<%@page import="com.lazytravel.foodscape.service.*"%>



<%
FoodScape foodscape = (FoodScape) request.getAttribute("foodscape");
FoodScapeService foodscapeService = new FoodScapeServiceImpl();
List<FoodScape> list = foodscapeService.getAllFoodScapes();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>後臺 - 修改美食景點</title>
  

  <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">

  <style>

  </style>
</head>

<body>
  <div id="header"></div>
<main>
	<div class="container" method="post" ACTION="foodscape.do" name="form1"
			enctype="multipart/form-data">
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

  <div class="container">
    <form method="post" action="user.do" class="row m-3 p-3">
      <h2 class="mb-4">修改美食景點</h2>
      <p style="font-size: 20px;"><a href="foodscape.jsp">回首頁</a></p>
      
      <div class="col-12 mb-3">
        <label for="staticId" class="col-md-6 col-form-label">美食/景點ID</label>
<%--         <td><%=foodscape.getFoodScapeId()%></td> --%>
      </div>
      <div class="div_foodScape_name">
        <label for="InputName" class="form-label">美食/景點名稱</label>
        <td><input type="TEXT" name="foodScapeName" value="${foodscape.foodScapeName}" size="45"/></td>
      </div>
      <div class="div_foodscape_phone">
            <label>電話 :</label>
            <td><input type="TEXT" name="phone" value="${foodscape.phone}" size="45"/></td>
        </div>
      <br>
        <div class="div_foodscape_place">
            <label>地點 :</label>
            
            
            <label>縣市 :</label>
            <input type="TEXT" name="city" value="${foodscape.city}" size="45"/>
            
            <label>地址 :</label>
            <input type="TEXT" name="address" value="${foodscape.address}" size="45"/>
        </div>
        
      <div class="div_foodscape_location">
     		<label>經度 :</label>
     		<input type="TEXT" name="lng" value="${foodscape.lng}" size="45"/>
     		
     		<label>緯度 :</label>
     		<input type="TEXT" name="lat" value="${foodscape.lat}" size="45"/>
     	</div>
     	
     	<label for="large-text">相關介紹：</label>
			<td><input type="TEXT" name="INTRO" value="${foodscape.intro}" size="45"/></td>

			<div class="div_updatetime">
			<td><input type="TEXT" name="upDateTime" value="<%= (foodscape==null)? new java.sql.Timestamp(System.currentTimeMillis()) : foodscape.getUpdateTime()%>" size="45"/></td>
			</div>
			
 <div class="div_status">
                <label>狀態 :</label>
                <select id="div_status" name="div_status">
					<option value="0" ${journey.journeyStatus == 0 ? "selected" : ""} >未上架</option>
					<option value="1" ${journey.journeyStatus == 1 ? "selected" : ""} >已上架</option>
                </select>
            </div>
            
                    <div class="div_category">
            <label>類別 :</label>
            <select id="div_category" name="div_category">
				  <option value="景點" ${(foodscape.category=='景點')? 'selected':'' }>景點
				  <option value="美食" ${(foodscape.category=='美食')? 'selected':'' }>美食
            </select>
            </div>

        <br>
        <div class="div_btn">
		    <button type="submit" class="btn_submit">送出</button>
		    <input type="hidden" name="action" value="foodScape_add">
		    
            <button type="reset" class="btn_reset" onclick="redirectToFoodScape()">取消</button>
        </div>
	</form>

    </main>



  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
  <script>
    $(function () {
        $("#header").load("<%=request.getContextPath()%>/admin/header.jsp");
    });
  </script>
</body>

</html>