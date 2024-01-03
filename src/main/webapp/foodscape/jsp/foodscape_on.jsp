<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.lazytravel.foodscape.dao.*"%>
<%@page import="com.lazytravel.foodscape.entity.*"%>
<%@page import="com.lazytravel.foodscape.service.*"%>




<%
	FoodScape foodscape = (FoodScape) request.getAttribute("foodscape");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>後臺-美食/景點(新增)</title>

    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
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
//     	FoodScape foodScape = (FoodScape) request.getAttribute("foodScape");
    	
//     	FoodScapeService foodscapeService = new FoodScapeServiceImpl();
//     	List<FoodScape> foodscapeList = foodscapeService.getAllFoodScapes();
//     	pageContext.setAttribute("foodscape", foodscapeList);
    	
//     	pageContext.getAttribute("foodScapeId");
    %>
</head>
<body>
    <div id="header"></div>


    <main id="main">
        <p style="font-size: 20px;"><b>新增美食/景點</b></p>
        <p style="font-size: 20px;"><a href="foodscape.jsp">回首頁</a></p>
    
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
        
    	<form method="post" action="foodscape.do">

    
        <div class="div_foodScape_name">
            <label>美食/景點名稱 :</label>
            <td><input type="TEXT" name="foodScapeName" value="<%= (foodscape==null)? "美食/景點名稱..." : foodscape.getFoodScapeName()%>" size="45"/></td>
        </div>
        <br>
    
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
     		<input type="TEXT" name="lng" value="<%= (foodscape==null)? "121.5615184" : foodscape.getLng()%>" size="45"/>
     		
     		<label>緯度 :</label>
     		<input type="TEXT" name="lat" value="<%= (foodscape==null)? "25.0338315" : foodscape.getLat()%>" size="45"/>
     	</div>

        <br>

<!--         <label for="opening-time">星期一營業時間：</label> -->
<%-- 		<td><input type="TEXT" name="START_TIME" value="<%=(foodscape ==null)? "00:00:00" :  opentime.getStartTime()%>" size="45"/></td> --%>
<!-- 		<span>到</span> -->
<%-- 		<td><input type="TEXT" name="END_TIME" value="<%=(foodscape ==null)? "00:00:00" :  opentime.getEndTime()%>" size="45"/></td> --%>
<!--         <br> -->
        
<!--              <label for="opening-time">星期二營業時間：</label> -->
<%-- 		<td><input type="TEXT" name="START_TIME" value="<%=(foodscape ==null)? "00:00:00" :  opentime.getStartTime()%>" size="45"/></td> --%>
<!-- 		<span>到</span> -->
<%-- 		<td><input type="TEXT" name="END_TIME" value="<%=(foodscape ==null)? "00:00:00" :  opentime.getEndTime()%>" size="45"/></td> --%>
<!--         <br> -->
        
<!--              <label for="opening-time">星期三營業時間：</label> -->
<%-- 		<td><input type="TEXT" name="START_TIME" value="<%=(foodscape ==null)? "00:00:00" :  opentime.getStartTime()%>" size="45"/></td> --%>
<!-- 		<span>到</span> -->
<%-- 		<td><input type="TEXT" name="END_TIME" value="<%=(foodscape ==null)? "00:00:00" :  opentime.getEndTime()%>" size="45"/></td> --%>
<!--         <br> -->
        
<!--              <label for="opening-time">星期四營業時間：</label> -->
<%-- 		<td><input type="TEXT" name="START_TIME" value="<%=(foodscape ==null)? "00:00:00" :  opentime.getStartTime()%>" size="45"/></td> --%>
<!-- 		<span>到</span> -->
<%-- 		<td><input type="TEXT" name="END_TIME" value="<%=(foodscape ==null)? "00:00:00" :  opentime.getEndTime()%>" size="45"/></td> --%>
<!--         <br> -->
        
<!--              <label for="opening-time">星期五營業時間：</label> -->
<%-- 		<td><input type="TEXT" name="START_TIME" value="<%=(foodscape ==null)? "00:00:00" :  opentime.getStartTime()%>" size="45"/></td> --%>
<!-- 		<span>到</span> -->
<%-- 		<td><input type="TEXT" name="END_TIME" value="<%=(foodscape ==null)? "00:00:00" :  opentime.getEndTime()%>" size="45"/></td> --%>
<!--         <br> -->
        
<!--              <label for="opening-time">星期六營業時間：</label> -->
<%-- 		<td><input type="TEXT" name="START_TIME" value="<%=(foodscape ==null)? "00:00:00" :  opentime.getStartTime()%>" size="45"/></td> --%>
<!-- 		<span>到</span> -->
<%-- 		<td><input type="TEXT" name="END_TIME" value="<%=(foodscape ==null)? "00:00:00" :  opentime.getEndTime()%>" size="45"/></td> --%>
<!--         <br> -->
        
<!--              <label for="opening-time">星期日營業時間：</label> -->
<%-- 		<td><input type="TEXT" name="START_TIME" value="<%=(foodscape ==null)? "00:00:00" :  opentime.getStartTime()%>" size="45"/></td> --%>
<!-- 		<span>到</span> -->
<%-- 		<td><input type="TEXT" name="END_TIME" value="<%=(foodscape ==null)? "00:00:00" :  opentime.getEndTime()%>" size="45"/></td> --%>
<!--         <br> -->

          <label for="large-text">相關介紹：</label>
			<td><input type="TEXT" name="intro" value="<%= (foodscape==null)? "輸入想說的" : foodscape.getIntro()%>" size="45"/></td>

			<div class="div_updatetime">
			<td><input type="TEXT" name="upDateTime" value="<%= (foodscape==null)? new java.sql.Timestamp(System.currentTimeMillis()) : foodscape.getUpdateTime()%>" size="45"/></td>
			</div>
			

<!--             <label>標籤 :</label> -->
<!--             <select id="mark" name="mark"> -->
<!--                 <option value="japan">日式</option> -->
<!--                 <option value="taiwan">台式</option> -->
<!--                 <option value="america">美式</option> -->
<!--             </select>  -->
<!--         </div> -->

<!--         <form action="/upload" method="post" enctype="multipart/form-data"> -->
<!--             <label for="file-upload">選擇圖片：</label> -->
<!--             <input type="file" id="file-upload" name="image" accept="image/*"> -->
<!--             <input type="submit" value="上傳"> -->
<!--           </form> -->


            <div class="div_status">
                <label>狀態 :</label>
                <select id="div_status" name="foodscape_status">
					<option value="0" ${foodscape.foodScapeStatus == 0 ? "selected" : ""} >未上架</option>
					<option value="1" ${foodscape.foodScapeStatus == 1 ? "selected" : ""} >已上架</option>
                </select>
            </div>
            
                    <div class="div_category">
            <label>類別 :</label>
            <select id="div_category" name="category">
				  <option value="景點" ${(foodscape.category=='景點')? 'selected':'' }>景點
				  <option value="美食" ${(foodscape.category=='美食')? 'selected':'' }>美食
            </select>
            </div>

        <br>
        <div class="div_btn">
		    <button type="submit" class="btn_submit">送出</button>
		    <input type="hidden" name="action" value="foodscape_add">
            <button type="reset" class="btn_reset" onclick="redirectToFoodScape()">取消</button>
        </div>
</form>




    </main>

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