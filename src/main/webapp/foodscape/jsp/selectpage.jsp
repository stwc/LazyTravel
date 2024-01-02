<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.stream.Collectors" %>
<%@ page import="com.lazytravel.foodscape.entity.*"%>
<%@ page import="com.lazytravel.foodscape.entity.FoodScape" %>
<%@ page import="com.lazytravel.foodscape.dao.*"%>
<%@ page import="com.lazytravel.foodscape.service.*"%>
<%@ page import="com.lazytravel.journey.entity.*"%>
<%@ page import="com.lazytravel.journey.dao.*"%>


<%
	FoodScape foodscape = (FoodScape) request.getAttribute("foodscape");
	Journey journey = (Journey) request.getAttribute("journey");
%>

<%
FoodScapeService foodscapeService = new FoodScapeServiceImpl();
List<FoodScape> list = foodscapeService.getAllFoodScapes();
pageContext.setAttribute("list", list);
%>

<%
List<Integer> foodScapeIdList = new ArrayList<>();
if (foodscape != null) {
    foodScapeIdList.add(foodscape.getFoodScapeId());
}

//foodScapeIdList轉換為以,分隔的字串

String foodScapeIdList_Str = foodScapeIdList.stream().map(Object::toString).collect(Collectors.joining(","));
if (!foodScapeIdList_Str.isEmpty()) {
    try {
        int foodScapeId = Integer.parseInt(foodScapeIdList_Str);
        // 其他處理
    } catch (NumberFormatException e) {
    	System.out.println("輸入的 foodScapeId 不是有效的數字：" + foodScapeIdList_Str);
    }
} else {
    // 處理空字串的情況，可能是給一個預設值或者其他邏輯
}
//送資料
request.getSession().setAttribute("foodScapeIdList_Str", foodScapeIdList_Str);

%>



<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>美食/景點搜尋</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">
  
<!--   <link rel="stylesheet" href="./css/selectpage.css"> -->
<!--   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-xr3m6W2+K7UreKck7jC+y2BxegjgUoeqgRN8dguLmOfiRSRtK5UeR8xRqjkFR0UW3yHwDvi6PKxdFiKA+Ai7Ow==" crossorigin="anonymous" /> -->

  <style>
    *{
         box-sizing: border-box;
     }

     body{
         margin: 0;
     }

     main#main{
         padding: 50px 80px;
     }

     input, textarea{
         border: 1px solid #CCD5AE;   
     }

     input:focus, textarea:focus{
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

         margin-right: 15px;
     }

     button.btn_reset:active {
         outline: 2px solid #9C6644;
         box-shadow: 0 0 8px #a1a397
     }

     button.btn_more{
         width: 100px;
         height: 30px;
         border-radius: 90px;
         border-color: transparent;
         color: white;
         background: #6B705C;
     }

     button.btn_more:active {
         outline: 2px solid #6B705C;
         box-shadow: 0 0 8px #a1a397
     }

     div.div_btn{
         display: flex;
         justify-content: flex-end;
     }

     hr:not([zzz]) {
         height: 1.5px;
         background: rgba(0, 0, 0, 0.863);
         box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.368);
     }

     div.list_group li{
         list-style: none;
         padding-left: 80px;
     }

     button.btn_x{
         width: 19px;
         height: 19px;
         line-height: 10px;
         border-radius: 90px;
         border-color: transparent;
         background-color: #CCCCCC;
         color: white;

         margin-left: 20px;
         margin-bottom: 5px;
         padding-left: 3.5px;
     }
     
     button.btn_x:active{
         outline: 2px solid #CCCCCC;
         box-shadow: 0 0 8px #a1a397
     }

     
     li.foodscape{
         box-sizing: border-box;
         background: rgba(217, 217, 217, 0.42);
         border-radius: 20px;
         list-style: none;
         border: 1px dotted rgba(109, 109, 109, 0.737);
         margin: 20px 0 0 -40px;
         padding: 20px 40px 20px 40px;
     }

     
     div.label span{
         border-radius: 90px;
         border-color: transparent;
         color: white;
         background: #6B705C;
         padding: 4px 7px 4px 18px;
         margin-right: 8px;

         white-space: nowrap;   
     }

     div.label{
         display: flex;
         flex-wrap: wrap;
         gap: 5px;     
     }
     
     button.btn_label_x{
         line-height: 10px;
         border-radius: 90px;
         border-color: transparent;
         color: white;
         background: #6B705C;

         padding-left: 15px;
     }
     
     .fas.fa-check-circle.checked {
      color: green;  /* 设置勾起的颜色 */
  }
  
      .foodscape {
        margin-bottom: 20px; /* 设置列表项之间的底部间距 */
        border: 1px solid #ccc; /* 为列表项添加边框 */
        padding: 10px; /* 为列表项添加内边距 */
    }
    
        .selectfsId {
        display: flex;
        justify-content: flex-end;
        align-items: center;
    }

    .btn_reset, .btn_submit {
        margin-left: 10px; /* 可以根据需要调整按钮之间的间距 */
    }
 </style>

</head>

<body>
    
    <header id="header"></header>
	<%@ include file="/components/html/header.jsp" %>
    <main id="main">

<!-- 	<form action="FoodScape.do" method="post"> -->
<!--     <div class="container"> -->
<!--         <div class="row"> -->
<!--             <div class="col-4 d-flex justify-content-start"> -->
<!--                 <div class="row"> -->
<!--                     <label class="col-3 d-flex justify-content-end">地點</label> -->
<%--                     <input type="text" name="city" value="<%= (foodscape == null) ? "" : foodscape.getCity() %>" class="col-9"> --%>
<!--                 </div> -->
<!--             </div> -->

<!--             <div class="col-4 d-flex justify-content-start"> -->
<!--                 <div class="row"> -->
<!--                     <label class="col-4">美食/景點</label> -->
<%--                     <input type="text" name="foodScapeName" value="<%= (foodscape == null) ? "" : foodscape.getFoodScapeName() %>" class="col-8"> --%>
<!--                 </div> -->
<!--             </div> -->

<!--             <div class="col-4 d-flex justify-content-end"> -->
<!--                 <div class="row"> -->
<!--                     <label class="col-3 d-flex justify-content-end">預算</label> -->
<%--                     <input type="text" name="price" value="<%= (journey == null) ? "" : journey.getPrice() %>" class="col-9"> --%>
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
<!-- </form> -->

<!--         <br> -->
<!--         <div class="div_btn"> -->
<!--             <button type="reset" class="btn_reset">重新篩選</button> -->
<!--             <button type="submit" class="btn_submit">送出</button> -->
<!--         </div> -->
<!--         <hr> -->
        <!-- <div style="border-top: 1px solid red; margin: 16px auto;"></div> -->


        <div>
            <div class="container">
                <div class="row">
                    <span class="col-8">篩選美食/景點</span>
<!--                     <div class="col-4 d-flex justify-content-end"> -->
<!--                         <span class="mr-3">常見問題&nbsp;&nbsp;</span> -->
<!--                         <span class="mr-3">|&nbsp;&nbsp;</span> -->
<!--                         <span>聯繫客服</span> -->
<!--                     </div> -->
                </div>
            </div>
            

<ul>
    <c:forEach var="foodscape" items="${list}">
        <li class="foodscape d-flex position-relative">
            <div class="container-fluid">
                <form class="row" method="post" action="<%=request.getContextPath()%>/foodscape/jsp/selectmore.jsp">
                    <div class="row">
                        <!-- 左側圖片區塊 -->
                        <div class="col-md-6 order-md-1">
                            <div class="img_block">
                                <img src="/LazyTravel/foodscape/image/logo.ico" class="img-fluid" alt="">
                            </div>
                        </div>

                        <!-- 右側其他內容區塊 -->
                        <div class="col-md-6 order-md-2 d-flex flex-column position-relative">
                            <!-- 在右上方添加打勾符號 -->
                            <i id="checkIcon" class="fas fa-check-circle position-absolute top-0 end-0"></i>
                            <div class="items-right">
                                <span class="title1" style="font-size: 20px; margin-bottom: 10px;">${foodscape.foodScapeName}</span>
                                <ul class="desc_list">
                                    <li>電話：<span class="phone">${foodscape.phone}</span></li>
                                    <li>地址：<span class="address">${foodscape.address}</span></li>
                                </ul>
                            </div>

                            <!-- 加入 hidden input，將 foodScapeId 傳遞到下一個頁面 -->
                            <c:if test="${not empty foodscape.foodScapeId}">
                            <input type="hidden" name="foodScapeId" value="${foodscape.foodScapeId}">
                            </c:if>
                            <input type="submit" name="action" value="查看更多" class="btn_submit mt-auto">
                        </div>
                    </div>
                </form>
            </div>
        </li>
    </c:forEach>
</ul>

                            <br>
<!--                             <div class="div_btn">                            -->
<!--                                 <button type="reset" class="btn_reset">全部取消</button> -->

					<form class="selectfsId" method="post" action="<%=request.getContextPath()%>/journey/user/journeySelect.do">
					 <button type="reset" class="btn_reset">全部取消</button>                                
    				<button type="submit" class="btn_submit">送出</button>
    				<input type="hidden" name="action" value="receiveFoodScapeId" id="selectedFoodScapeIdsInput">
					</form>
                            </div>



        
    </main>


    <footer id="footer"></footer>
    <%@ include file="/components/html/footer.jsp" %>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
      crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script>
      $(function () {
//         $("#header").load("../components/html/header.jsp");
//         $("#footer").load("../components/html/footer.jsp");
      });
    </script>
    
  	
  	<script>
  	$(document).ready(function () {
  	    // 给 "全部取消" 按钮添加点击事件处理程序
  	    $('button.btn_reset').on('click', function () {
  	        // 取消所有景点的勾选
  	        $('li.selected').removeClass('selected');
  	        $('i.fas.fa-check-circle').removeClass('checked');
  	    });
  		
  	    // 给勾号元素添加点击事件处理程序
  	    $('.fas.fa-check-circle').on('click', function () {
  	        // 切换勾号的状态，如果已经被选中则取消选中，反之亦然
  	        $(this).toggleClass('checked');

  	        // 获取当前点击的 li 元素
  	        var listItem = $(this).closest('li');

  	        // 切换 li 元素的选中状态
  	        listItem.toggleClass('selected');
  	    });

  	    // 处理提交按钮点击事件
  	    $('button.btn_submit').on('click', function () {
  	        // 获取所有选中的项
  	        var selectedItems = $('li.selected');
  	        
  	     	var selectedFoodScapeIds = [];

  	        // 遍历选中项，将 foodScapeId 添加到隐含的 input 元素中
  	        selectedItems.each(function (index) {
  	            var foodScapeId = $(this).find('input[name="foodScapeId"]').val();
  	          console.log('Selected FoodScapeId: ' + foodScapeId);
  	       		 selectedFoodScapeIds.push(foodScapeId);
  	          
  	          
  	           
  	        });
  	        
  	      var selectedFoodScapeIdsString = selectedFoodScapeIds.join(',');
  	      
	  	    $('<input>').attr({
	  	        type: 'hidden',
	  	        name: 'selectedFoodScapeIds',
	  	        value: selectedFoodScapeIdsString
	  	    }).appendTo('form');
	  	    console.log(selectedFoodScapeIdsString);
  	    });
  	});
  	

</script>
  

  
  </body>
  
  </html>