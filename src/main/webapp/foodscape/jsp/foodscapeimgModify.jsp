<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.lazytravel.foodscape.dao.*"%>
<%@page import="com.lazytravel.foodscape.entity.*"%>
<%@page import="com.lazytravel.foodscape.service.*"%>

<%@ include file="/admin/header.html" %>

<%

FoodScapeImg foodscapeimg = (FoodScapeImg) request.getAttribute("foodscapeimg");
FoodScapeImgService foodscapeimgService = new FoodScapeImgServiceImpl();
List<FoodScapeImg> list = foodscapeimgService.getAllFoodScapeImgs();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>後臺 - 修改美食/景點圖片</title>
  

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
    <form method="post" action="foodscape.do" class="row m-3 p-3">
      <h2 class="mb-4">修改美食/景點圖片</h2>
      <p style="font-size: 20px;"><a href="foodscapeimg.jsp">回首頁</a></p>
      
      <div class="col-12 mb-3">
        <label for="imgId" class="col-md-6 col-form-label">美食/景點圖片ID</label>
<%--         <td><%=foodscapeimg.getImgId()%></td> --%>
      
      <div class="col-12 mb-3">
        <label for="FoodScapeId" class="col-md-6 col-form-label">美食/景點ID</label>
<%--         <td><%=foodscape.getFoodScapeId()%></td> --%>
      </div>
      <br>
        <label for="opening-time">創建時間：</label>
		<td><input type="TEXT" name="Create_TIME" value="<%=(foodscapeimg ==null)? new java.sql.Timestamp(System.currentTimeMillis()) :  foodscapeimg.getCreateTime()%>" size="45"/></td>     
        <br>
          <label for="large-text">美食/景點圖片：</label>
							<div class="col-7 d-flex align-items-center">
					<div class="input-group mb-3 h-auto mt-3 m-1" style="width: 545px">
						<input type="file" name="blogImg"
							class="form-control" id="inputGroupFile01" />
					</div>
				</div>
				<div class="col-2 d-flex align-items-center">
					<button type="button" class="btn btn-success">取消圖片</button>
				</div>
			
        <br>
     
        <div class="div_btn">
		    <button type="submit" class="btn_submit">送出</button>
		    <input type="hidden" name="action" value="foodScapeimg_add">
		    
            <button type="reset" class="btn_reset" onclick="redirectToFoodScape()">取消</button>
        </div>
	</form>

    </main>



  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
  <script>
    $(function () {
      $("#header").load("header.html");

    });
  </script>
</body>

</html>