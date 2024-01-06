<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.lazytravel.journey.dao.*"%>
<%@page import="com.lazytravel.journey.entity.*"%>
<%@page import="com.lazytravel.foodscape.entity.*"%>

<%@page import="com.google.gson.Gson"%>
<%@page import="java.lang.reflect.Type"%>
<%@page import="com.google.gson.reflect.TypeToken"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>篩選行程-下單</title>

<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
<link rel="icon" href="<%=request.getContextPath()%>/static/images/logo.ico" type="image/x-icon">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<style>
	* {
		box-sizing: border-box;
	}
	
	body {
		margin: 0;
	}
	
	main#main {
		padding: 50px 80px;
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		width: 100%;
	}
	
	article {
		flex: 1;
	}
	
	section {
		flex: 100%;
	}
	
	@media screen and (max-width: 768px) {
		#main {
			flex-direction: column;
		}
		article {
			flex: none; /* 去除等分佈局 */
			width: 100%; /* 寬度 100% */
		}
	}
	
	div.journey_name {
		margin-bottom: 20px;
		font-size: 20px;
		font-weight: 600;
	}
	
	span.journey_name_label {
		margin-right: 25px;
		color: rgb(113, 120, 95);
	}
	
	span.journey_name_value {
		font-weight: 600;
		color: #CB997E;
	}
	
	p.preparedOrder {
		color: rgb(113, 120, 95);
		font-size: 22px;
		font-weight: 600;
	}
	
	table.journey_detail {
		border-collapse: separate;
		width: 100%;
		font-size: 15px;
	}
	
	table.journey_detail td,
	table.journey_detail th {
		padding-top: 5px;
		padding-bottom: 5px;
	}
	
	table.journey_detail th {
		color: darkgrey;
	}
	
	div.score {
		margin-left: 10px;
	}
	
	div.score div {
		border: 1px solid black;
		width: 320px;
		margin-top: 8px;
		margin-bottom: 8px;
	}
	
	div.journey_price_div {
		color: #787878;
		margin-left: 10px;
	}
	
	span.journey_price_span {
		margin-left: 40px;
	}
	
	section {
		margin-top: 50px;
	}
	
	button.btn_submit {
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
	
	button.btn_reset {
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
	
	button.btn_addCart {
		width: 100px;
		height: 30px;
		border-radius: 90px;
		border-color: transparent;
		color: white;
		background: #6B705C;
		margin-right: 15px;
	}
	
	button.btn_addCart:active {
		outline: 2px solid #6B705C;
		box-shadow: 0 0 8px #a1a397
	}
	
	div.nth_days {
		margin:10px 0px 0px 0px;
	}
	
/* 	div.nth_days i { */
/* 		margin-right: 5px; */
/* 	} */
	
/* 	div.nth_days i:hover { */
/* 		border-radius: 90px; */
/* 		background-color: #1a1919; */
/* 		color: white; */
/* 		box-shadow: 0 0 8px #a1a397 */
/* 	} */
	
	select.touristNum {
		width: 135px;
		height: 30px;
	}
	
	select.touristDate {
		width: 160px;
		height: 30px;
		margin-right: 70px;
	}
	
	td.sign_num,
	td.min_required,
	td.max_required,
	td.tourGroup_price {
		color: #787878;
/* 		text-align: right; */
	}
	
	div.div_wrapper table.selectDateAndNum {
	    display: inline-block;
	    vertical-align: top;
	    width: 60%;
	}
	
	div.div_wrapper table {
		display: inline-block;
		vertical-align: top;
		border-collapse: separate;
		border-spacing: 13px;
	}
	
	div.div_wrapper table.searchTourGroupData {
		margin:15px 0px 20px 70px;
		border: 1px solid darkgrey;
		border-radius:8px;
		padding: 10px 50px 10px 50px;
	}
	
 	div.div_btn{
		display: flex;
 		justify-content: flex-end;
 		margin-right: 36px;
 	}
 	
	#map {
		height: 490px;
		width: 100%;
		margin-left: 15px;
	}
		
	div.map_day {
		display: inline-block;
		margin-bottom: 20px;
		margin-left: 15px;
	}
	
	div.map_day button {
		padding-right: 10px;
		padding-left: 10px;
		font-size: 20px;
		font-weight: 600;
		color: rgb(129, 127, 127);
		border: 1px dashed;
   		border-radius: 5px;
	}
	
	div.map_day button:hover {
	    border: 1px dashed #CB997E;
	    background-color: #CB997E;
	    color: white;
	}
	
	div.map_day button.clicked {
	    border: 1px dashed #CB997E;
	    background-color: #CB997E;
	    color: white;
	}
	
</style>

<%	
	Integer journeyId = (Integer) request.getSession().getAttribute("journeyId");

	JourneyService journeySvc = new JourneyServiceImpl();
	Journey journey = journeySvc.getOneJourney(journeyId);
	pageContext.setAttribute("journey", journey);
	
	JourneyDetailService journeyDetailSvc = new JourneyDetailServiceImpl();
	List<JourneyDetail> journeyDetailList = journeyDetailSvc.getByJourneyId(journeyId);
	pageContext.setAttribute("journeyDetailList", journeyDetailList);

	List<FoodScape> foodScapeList = journeyDetailSvc.findFoodscapeNameAndAddress(journeyId);
	pageContext.setAttribute("foodScapeList", foodScapeList); 
	
	TourGroupService tourGroupSvc = new TourGroupServiceImpl();
	List<TourGroup> tourGroupList = tourGroupSvc.getMarketedByJourneyId(journeyId);
	pageContext.setAttribute("tourGroupList", tourGroupList);
	
	
	// 取得 map location
	String locationsJsonStr = (String) request.getAttribute("locationsJsonStr");
	System.out.println("-------------------------- locationsJsonStr: " + locationsJsonStr);
	request.setAttribute("classNotClick", 0);       // 用於JS判斷: 重新設置地圖上面的按鈕的 class
	
	Gson gson = new Gson();
	if(locationsJsonStr == null){
		List<FoodScape> foodScapeMapList = journeyDetailSvc.findFoodscapeLngAndLat(journeyId, 1);
		locationsJsonStr = gson.toJson(foodScapeMapList);
		request.setAttribute("locationsJsonStr", locationsJsonStr);
		request.setAttribute("classNotClick", 1);   // 用於JS判斷: 重新設置地圖上面的按鈕的 class
	}
	
// 	List<String> foodScapeNameList = new ArrayList<>();
// 	for(FoodScape foodScape : foodScapeList){
// 		String foodScapeName = foodScape.getFoodScapeName();
// 		foodScapeNameList.add(foodScapeName);
// 	}
// 	String foodScapeNameJsonStr = gson.toJson(foodScapeNameList);
// 	request.setAttribute("foodScapeNameJsonStr", foodScapeNameJsonStr);
	
%>

<body>

<!--     <header id="header"></header> -->
	<%@ include file="/components/html/header.jsp" %>

    <main id="main">
        <article>
            <div class="journey_name">
                <span class="journey_name_label">行程名稱&nbsp;: </span>
                <span class="journey_name_value">${journey.journeyName}</span>
            </div>
          	
            
			<c:set var="listSize" value="${fn:length(journeyDetailList)}" />
			<c:set var="lastIndex" value="${listSize - 1}" />
			<c:set var="lastNthDay" value="${journeyDetailList[lastIndex].nthDay}" />

            <c:forEach begin="1" end="${lastNthDay}" varStatus="daysLoop">	
            	<div class="nth_days" >
		        	<b style="font-weight: 600; color: rgb(129, 127, 127);">第 ${daysLoop.count} 天</b>
<!-- 		    		<i class="fa-solid fa-circle-chevron-left"></i> -->
<!-- 		    		<i class="fa-solid fa-circle-chevron-right"></i> -->
				</div>
            	
				<table class="journey_detail">
		        	<tr>
		                <th>時間</th>
<!-- 						<th></th> -->
						<th>美食景點</th>
						<th>地址</th>
					</tr>
		            
					<c:forEach var="journeyDetail" items="${journeyDetailList}" varStatus="loop">
		                <c:if test="${journeyDetail.nthDay == daysLoop.count}">		 
			                <fmt:formatDate value="${journeyDetail.startTime}" pattern="HH:mm" var="formattedStartTime" />
							<fmt:formatDate value="${journeyDetail.endTime}" pattern="HH:mm" var="formattedEndTime" />
			                <tr>
<%-- 			                	<td style="width: 50px;">第 ${journeyDetail.nthDay} 天</td> --%>
			                    <td style="width: 110px;">${formattedStartTime} ~ ${formattedEndTime}</td>
			                    <td style="width: 130px;">${foodScapeList[loop.index].foodScapeName}</td>
			                    <td>${foodScapeList[loop.index].address}</td>
			                </tr>
						</c:if>
					</c:forEach>
				</table>
				<hr>         		
            </c:forEach>
            
        </article>


        <article>
        	<c:set var="listSize_map" value="${fn:length(journeyDetailList)}" />
			<c:set var="lastIndex_map" value="${listSize_map - 1}" />
			<c:set var="lastNthDay_map" value="${journeyDetailList[lastIndex_map].nthDay}" />
	        
	        <form method="post" action="<%=request.getContextPath()%>/journey/user/mapLocation.do">
	            <c:forEach begin="1" end="${lastNthDay_map}" varStatus="mapLoop">
	            	<div class="map_day">
			        	<button type="submit" name="mapButton" value="${mapLoop.count}" onclick="buttonSelect(this)">第 ${mapLoop.count} 天</button>
					</div>

	           		<input type="hidden" name="action" value="map_location"/>
	           		<input type="hidden" name="nthDay_${mapLoop.count}" value="${mapLoop.count}" />
					<input type="hidden" name="journeyId" value="${journeyId}" />
				</c:forEach>
			</form>            
            
            <div id="map"></div>
        </article>
         
		
        <section>
            <hr style="border-width: 2px;" >
            <br>
            <p class="preparedOrder" id="originalPosition">下單</p>
            
            <div class="div_wrapper">
	            <table class="selectDateAndNum">
	                <tr>
	               		<td>出發日期:</td>
	                    <td>
		                    <form method="post" id="form_chooseStartTime" action="<%=request.getContextPath()%>/journey/user/journeySelect.do">
		                        <select class="touristDate" onchange="chooseStartTime(this)">
		                        	<option>請選擇日期</option>
		                        	<c:forEach var="tourGroup" items="${tourGroupList}">
			                            <option value="${tourGroup.startTime}" data-groupId="${tourGroup.groupId}">${tourGroup.startTime}</option>
			                    	</c:forEach> 
		                        </select>
		                        <input type="hidden" name="action" value="includeFragment"/>
								<input type="hidden" id="includeFragment" name="groupId" value="" />
	                        </form>
	                    </td>
	                    
	                    
						<td>旅遊人數:</td>
						<td>
	                        <select class="touristNum" onchange="chooseSignupNum(this)">
	                            <option value="0">請選擇人數</option>
	                            <c:forEach begin="1" end="5" varStatus="loop">
		                            <option value="${loop.count}">${loop.count} 人</option>
		                            <!-- <option value="1"> 1 人 </option> -->
									<!-- <option value="2"> 2 人 </option> -->
									<!-- <option value="3"> 3 人 </option> -->
									<!-- <option value="4"> 4 人 </option> -->
									<!-- <option value="5"> 5 人 </option> -->
		                    	</c:forEach> 
	                        </select>
	                    </td>
	                </tr>
	                
	                <tr>
	                	<td></td>
	                	<td><p class="startTime_errorMessage" style="color: red;"></p></td>
	                	<td></td>
	                	<td><p class="signupNum_errorMessage" style="color: red;"></p></td>
	                </tr>
				</table>
				
				
				
				<table class="searchTourGroupData">
					<c:set var="scrollToOriginalPosition" value="${not empty searchTourGroupData}" />
				    <c:choose>
					    <c:when test="${searchTourGroupData}">
							<%@ include file="/journey/user/searchTourGroupData.file" %>
					    </c:when>
					    
					    <c:otherwise>
							<tr>
			                    <td style="padding-right: 20px;">金額:</td>
			                    <td class="tourGroup_price">NT$ 0</td>
			            	</tr>
				            <tr>
				    	        <td style="padding-right: 20px;">已報名人數:</td>
				            	<td class="sign_num">0&nbsp;人</td>
				            </tr>
				            <tr>
				            	<td style="padding-right: 20px;">成團人數:</td>
				            	<td class="min_required">0&nbsp;人</td>
				            </tr>
				           	<tr>
				            	<td style="padding-right: 20px;">人數上限:</td>
				            	<td class="max_required">0&nbsp;人</td>
				            </tr>
					    </c:otherwise>
					</c:choose>
		            
				</table>
			</div>
		
		
			<br>
			<div class="div_btn">
<!-- 		    	<button type="reset" class="btn_reset" onclick="redirectToJourneySelect()">取消</button> -->
		     	<form method="post" action="<%=request.getContextPath()%>/journey/user/journeySelect.do">
		     		<button type="submit" class="btn_reset">返回</button>
		     		<input type="hidden" name="action" value="returnJourneySelect"/>
		     	</form>
		     	
		     	
		     	<form method="get" class="checkData" action="<%= request.getContextPath() %>/journey/user/shoppingCart.do">
		     		<button type="submit" class="btn_addCart">加入購物車</button>
		     		<input type="hidden" name="action" value="shoppingCart_add"/>
		     		<input type="hidden" id="selectedGroupId_addCart" name="groupId" value="" />
	            	<input type="hidden" id="selectedSignupNum_addCart" name="signupNum" value="" />
		     	</form>
		     		
		     	<form method="post" class="checkData" action="<%=request.getContextPath()%>/journey/user/journeySelect.do">
					<button type="submit" class="btn_submit" >結帳</button>
					<input type="hidden" name="action" value="journeySelect_order"/>
					<input type="hidden" id="selectedGroupId_order" name="groupId" value="" />
	            	<input type="hidden" id="selectedSignupNum_order" name="signupNum" value="" />
				</form>	
		     		
		    </div>
		    
        </section>
    </main>

	<%@ include file="/components/html/footer.jsp" %>
	
<!--     <footer id="footer"></footer> -->


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
 	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCxbbRWsKGQ0YvTzPas_aROXvtc8CUNSIE&callback=initMap&v=weekly" async defer></script>
	<script src="https://unpkg.com/@googlemaps/markerclusterer/dist/index.min.js"></script>

	<script>
	
        $(function () {
// 	        $("#header").load("../components/html/header.html");
// 	        $("#footer").load("../components/html/footer.html");


		    $("form.checkData").submit(function(event) {
		        var selectedGroupId = $("#selectedGroupId_order").val();
		        var selectedSignupNum = $("#selectedSignupNum_order").val();
		        
		        var errorMsgStartTime = $(".startTime_errorMessage");
		        var errorMsgSignupNum = $(".signupNum_errorMessage");
		        
		        
		        if (selectedGroupId === "") {
		            errorMsgStartTime.text("請選擇出發日期");
		        } else {
		            errorMsgStartTime.text("");
		        }

		        if (selectedSignupNum == 0) {
		            errorMsgSignupNum.text("請選擇旅遊人數");
		        } else {
		            errorMsgSignupNum.text("");
		        }
		        
		        // 只要其中一個有錯誤，表單就不送出
		        if (selectedGroupId === "" || selectedSignupNum == 0) {
		        	return false;
		        }
		    });
		    
		    
		 	// 重新設置地圖上面的按鈕的 class
			var locations = ${classNotClick};
    		console.log(locations);
    		
			if(locations === 1) {
				var storedSelectedDay = 1;
				$(".map_day button").removeClass("clicked");
				$(".map_day button").eq(storedSelectedDay - 1).addClass("clicked");
			} else {
				var storedSelectedDay = sessionStorage.getItem("selectedDay");
				if (storedSelectedDay !== null) {
					$(".map_day button").removeClass("clicked");
					$(".map_day button").eq(storedSelectedDay - 1).addClass("clicked");
				}
			}
			
        });
        
        
        function chooseStartTime(selectElement) {
        	var selectedOption = selectElement.options[selectElement.selectedIndex];
        	console.log(selectElement.selectedIndex);
        	
	        var groupId = selectedOption.getAttribute("data-groupId");
	        console.log(groupId);
	        
	        document.getElementById("selectedGroupId_addCart").value = groupId;
	        document.getElementById("selectedGroupId_order").value = groupId;
	        document.getElementById("includeFragment").value = groupId;
	        
	        
	  		// 將值存在sessionStorage中
	        sessionStorage.setItem("selectedValue_startTime", "請選擇日期");
	        sessionStorage.setItem("selectedValue_groupId", "");
	  		
	  		var startTime = selectedOption.value;
	        if (startTime === "請選擇日期") {
	            return;
	        }
	        sessionStorage.setItem("selectedValue_startTime", startTime);
	        sessionStorage.setItem("selectedValue_groupId", groupId);
	        
	  		// 送出form表單
	        document.getElementById("form_chooseStartTime").submit();
        }
        
        function chooseSignupNum(selectElement) {
        	var selectedOption = selectElement.options[selectElement.selectedIndex];
        	console.log(selectElement.selectedIndex);
        	
        	var signupNum = selectedOption.value;
        	
	        document.getElementById("selectedSignupNum_addCart").value = signupNum;
	        document.getElementById("selectedSignupNum_order").value = signupNum;
	        
	        
	     	// 將值存在sessionStorage中
	     	sessionStorage.setItem("selectedValue_signupNum", 0);
	        if (signupNum === "請選擇人數") {
	            return;
	        }
	        sessionStorage.setItem("selectedValue_signupNum", signupNum);
	    }
        
        
        
        // 初始化地圖
        function initMap() {
    	 	// 從後端接收位置訊息
    	    var locations = ${locationsJsonStr};	
//     	    var foodScapeNames = ${foodScapeNameJsonStr};	
    	    
            var map = new google.maps.Map(document.getElementById("map"), {
                zoom: 12,  // 地圖放大倍率
                center: locations.length > 0 ? locations[0] : { lat: 23.58259486, lng: 120.58552886 }   // 將第一個美食景點位置設為地圖中心，沒有符合則設為台灣中心
            });
            
            
			// 在地圖上marker
//          var markers = [];
            locations.forEach(function(location, index) {
                var marker = new google.maps.Marker({
                    position: location,
                    map: map,
                });

                
// 				// marker 的泡泡框
// 				var currentFoodScapeName = foodScapeNames[index];
// 				var infoContent = `<p>${currentFoodScapeName}</p>`;
// 				var infowindow = new google.maps.InfoWindow({
// 					content: infoContent
// 				});
				
//                 marker.infowindow = infowindow;
//                 markers.push(marker);
                
//                 // 點擊時，關閉所有泡泡框，開啟當前點擊的泡泡框
//                 marker.addListener("click", function() {
//                     markers.forEach(function(marker) {
//                         marker.infowindow.close();
//                     });

//                     infowindow.open(map, marker);
//                     console.log(currentFoodScapeName);
//                 });

            });
            
            
// 			// 新增標記叢集
// 			const markerCluster = new markerClusterer.MarkerClusterer({ map, markers });
			
        }
        

        // click時，重新設置地圖上面的按鈕的 class
	    function buttonSelect(button) {
	        $(".map_day button.clicked").removeClass("clicked");
	        $(button).addClass("clicked");

	        var selectedDay = $(button).val();
	        sessionStorage.setItem("selectedDay", selectedDay);
	    }

    </script>

</body>
</html>