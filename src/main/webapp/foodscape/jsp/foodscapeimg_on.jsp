<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.lazytravel.foodscape.dao.*"%>
<%@page import="com.lazytravel.foodscape.entity.*"%>
<%@page import="com.lazytravel.foodscape.service.*"%>


<%@ include file="/admin/header.html" %>

<%
	FoodScapeImg foodscapeimg = (FoodScapeImg) request.getAttribute("foodscapeimg");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>後臺-營業時間(新增)</title>

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
        
    	<form method="post" action="<%=request.getContextPath()%>/foodscape/jsp/FoodScapeImg.do">
        <div class="div_foodscapeId">
            <label>美食景點圖片ID :</label>
            

        </div>
    
        <br>
    
        <div class="div_foodScape_name">
            <label>美食/景點ID :</label>
            <td><input type="TEXT" name="foodScapeId" value="${foodscape.foodScapeId}"/></td>
        </div>
        <br>

        <label for="opening-time">創建時間：</label>
		<td><input type="TEXT" name="START_TIME" value="<%=(foodscapeimg ==null)? new java.sql.Timestamp(System.currentTimeMillis()) :  foodscapeimg.getCreateTime()%>" size="45"/></td>
		
        
        <br>
        

		<label for="large-text">美食/景點圖片：</label>
		<div class="col-7 d-flex align-items-center">
    		<div class="input-group mb-3 h-auto mt-3 m-1" style="width: 545px">
        		<input type="file" name="blogImg" class="form-control" id="inputGroupFile01" />
    		</div>
		</div>
		<div class="col-2 d-flex align-items-center">
    		<button type="button" class="btn btn-success" id="cancelImage">取消圖片</button>
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
    <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
    <script>
        $(function () {
            $("#header").load("header.html");
            new DataTable('#example');
        });
    </script>
    
    <script>
    $(document).ready(function () {
        // 監聽取消圖片按鈕的點擊事件
        $('#cancelImage').click(function () {
            // 清空檔案輸入元素的值
            $('#inputGroupFile01').val('');
        });
    });
</script>
</body>
  
</html>