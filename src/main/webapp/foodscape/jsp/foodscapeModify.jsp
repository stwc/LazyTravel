<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.lazytravel.foodscape.dao.*"%>
<%@page import="com.lazytravel.foodscape.entity.*"%>
<%@page import="com.lazytravel.foodscape.service.*"%>



<%-- <% --%>
<!-- // FoodScape foodscape = (FoodScape) request.getAttribute("foodscape"); -->
<!-- // FoodScapeService foodscapeService = new FoodScapeServiceImpl(); -->
<!-- // List<FoodScape> list = foodscapeService.getAllFoodScapes(); -->
<!-- // pageContext.setAttribute("list", list); -->
<%-- %> --%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>後臺 - 修改美食景點</title>
  

  <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">

    <style>
       *{
            box-sizing: border-box;
        }

        body{
            margin: 0;
        }

        main#main{
            padding: 50px 80px;
            margin-left: 50px;
        }

        input{
            border: 1px solid #CCD5AE;   
        }

        input:focus{
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



    </style>
    
    <%
	FoodScape foodscape = (FoodScape) request.getAttribute("foodscape");
	%>
    
</head>

<body>
  <div id="header"></div>
<main>
<!-- 	<div class="container" method="post" ACTION="foodscape.do" name="form1" -->
<!-- 			enctype="multipart/form-data"> -->
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			



      <h2 class="mb-4">修改美食景點</h2>
      <p style="font-size: 20px;"><a href="foodscape.jsp">回首頁</a></p>
      
        <div class="container">
    <form method="post" action="<%=request.getContextPath()%>/foodscape/jsp/foodscape.do">
      
<!--       <div class="col-12 mb-3"> -->
<!--         <label for="staticId" class="col-md-6 col-form-label">美食/景點ID</label> -->
<%--         <div><%=foodscape.getFoodScapeId()%></div> --%>
<!--       </div> -->
      <div class="div_foodScape_name">
        <label for="InputName" class="form-label">美食/景點名稱</label>
         <td><input type="TEXT" name="foodScapeName" value="<%= (foodscape==null)? "美食/景點名稱..." : foodscape.getFoodScapeName()%>" size="45"/></td>
      </div>
      <div class="div_foodscape_phone">
            <label>電話 :</label>
            <td><input type="TEXT" name="phone" value="<%= (foodscape==null)? "8 or 10碼" : foodscape.getPhone()%>" size="45"/></td>
        </div>
      <br>
        <div class="div_foodscape_place">
            <label>地點 :</label>
            
            
            <label>縣市 :</label>
            <input type="TEXT" name="city" value="<%= (foodscape==null)? "縣市名稱" : foodscape.getCity()%>" size="45"/>
            
            <label>地址 :</label>
            <input type="TEXT" name="address" value="<%= (foodscape==null)? "" : foodscape.getAddress()%>" size="45"/>
        </div>
        
      <div class="div_foodscape_location">
     		<label>經度 :</label>
     		<input type="TEXT" name="lng" value="<%= (foodscape==null)? "" : foodscape.getLng()%>" size="45"/>
     		
     		<label>緯度 :</label>
     		<input type="TEXT" name="lat" value="<%= (foodscape==null)? "" : foodscape.getLat()%>" size="45"/>
     	</div>
     	
     	<label for="large-text">相關介紹：</label>
			<td><input type="TEXT" name="intro" value="<%= (foodscape==null)? "輸入想說的" : foodscape.getIntro()%>" size="45"/></td>

			<div class="div_updatetime">
			<td><input type="TEXT" name="upDateTime" value="<%= (foodscape==null)? new java.sql.Timestamp(System.currentTimeMillis()) : foodscape.getUpdateTime()%>" size="45"/></td>
			</div>
			
 <div class="div_status">
                <label>狀態 :</label>
                <select id="div_status" name="div_status">
					<option value="0" ${foodscape.foodScapeStatus == 0 ? "selected" : ""} >未上架</option>
					<option value="1" ${foodscape.foodScapeStatus == 1 ? "selected" : ""} >已上架</option>
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
		    <input type="hidden" name="action" value="foodscape_update">
		    
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