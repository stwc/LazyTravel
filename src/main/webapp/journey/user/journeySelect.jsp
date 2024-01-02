<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.lazytravel.journey.entity.*" %>
<%@page import="com.lazytravel.journey.dao.*" %>
<%@page import="java.util.Map" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>篩選行程</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
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
	}
	
	input, textarea {
		border: 1px solid #CCD5AE;
	}
	
	input:focus, textarea:focus {
		outline: 2px solid #CCD5AE;
		box-shadow: 0 0 8px #a1a397
	}
	
	select {
		border: 1px solid #CCD5AE;
		height: 27px;
	}
	
	select:focus {
		border: 2px solid #CCD5AE;
		box-shadow: 0 0 8px #a1a397;
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
	
	button.btn_more {
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
	
	div.div_btn {
		display: flex;
		justify-content: flex-end;
	}
	
	hr:not([zzz]) {
		height: 1.5px;
		background: rgba(0, 0, 0, 0.863);
		box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.368);
	}
	
	div.list_group li {
		list-style: none;
		padding-left: 80px;
	}
	
	button.btn_x {
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
	
	button.btn_x:active {
		outline: 2px solid #CCCCCC;
		box-shadow: 0 0 8px #a1a397
	}
	
	div.select_journey {
		margin: 20px 0px;
	}
	
	li.journey {
		box-sizing: border-box;
		background: rgba(217, 217, 217, 0.42);
		border-radius: 20px;
		list-style: none;
		border: 1px dotted rgba(109, 109, 109, 0.737);
		margin: 20px 0 0 -40px;
		padding: 20px 40px 20px 40px;
	}
	
	div.journey_name,
	div.journey_days,
	div.journey_buyCount {
		margin-bottom: 10px;
	}
	
	div.journey_name span,
	div.journey_days span,
	div.journey_buyCount span {
		margin-right: 5px;
	}
	
	div.journey_name span {
		font-size: 20px;
	} 
	
	div.journey_name span:nth-child(2) {
		font-weight: 600;
		color: #CB997E;
	}    
	
	div.journey_idCount span:nth-child(1) {
		margin-right: 22px;
	}    
	
	span.journey_title {
		margin-right: 10px;
	}
	
	span.journey_title, div.journey_store, div.journey_price {
		/* border: 1px solid red; */
		display: inline-block;
		height: 130px;
	}
	
	div.journey_store button {
		margin-top: 7px;
	}
	
	div.journey_price {
		display: flex;
		justify-content: flex-end;
	}
	
	div.journey_price_div {
		font-weight: 600;
		color: rgb(152, 152, 152);
		display: flex;
		justify-content: space-between;
	}
	
	span.journey_price_span {
		margin-left: 5px;
		margin-right: 5px;
		margin-bottom: 15px;
	}
	
	div.label span {
		border-radius: 98px;
		border-color: transparent;
		color: white;
		background: #6B705C;
		padding: 6px 20px 6px 20px;
		margin: 10px 0px 10px 8px;
		white-space: nowrap;
		font-weight: 550;
	}
	
	div.label {
		display: flex;
		flex-wrap: wrap;
		gap: 5px;
		margin-left: 220px;
	}
	
	div.label span i {
		margin-right: 12px;
	}
	
	button.btn_label_x {
		line-height: 10px;
		border-radius: 90px;
		border-color: transparent;
		color: white;
		background: #6B705C;
		padding-left: 15px;
	}
	
	a.a_page {
		text-decoration: none;
		color: black;
	}

	a.a_page:hover {
   		color: #3b6481;
    }
    
    span.selectTitle,
    label.selectedLabel {
		font-size: 24px;
		font-weight: 600;
		color: rgb(113, 120, 95);
	}
	
	label.selectedLabel {
		padding-left: 10px;
	}
	
	span.star-ratings {
	    color: #FFD700;
		font-size: 20px;
	}
</style>

<%
	JourneyDetailService journeyDetailSvc = new JourneyDetailServiceImpl();
%>

</head>
<body>
<!--     <header id="header"></header> -->
	<%@ include file="/components/html/header.jsp" %>

    <main id="main">

<!--         <div class="container"> -->
<!--             <div class="row"> -->
<!--                 <div class="col-4  d-flex justify-content-start"> -->
<!--                     <div class="row"> -->
<!--                         <label class="col-3 d-flex justify-content-end ">地點</label> -->
<!--                         <input type="text" class="col-9"> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="col-4 d-flex justify-content-start"> -->
<!--                     <div class="row"> -->
<!--                         <label class="col-4">美食/景點</label> -->
<!--                         <input type="text" class="col-8"> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="col-4 d-flex justify-content-end"> -->
<!--                     <div class="row"> -->
<!--                         <label class="col-3 d-flex justify-content-end">預算</label> -->
<!--                         <input type="text" class="col-9"> -->
<!--                     </div> -->
<!--                 </div> -->

<!--             </div> -->
<!--         </div> -->

<!--         <br> -->
<!--         <div class="label"> -->
<!--             <span>標籤AAAAA<button type="button" class="btn_label_x">x</button></span> -->
<!--             <span>標籤BBBBB<button type="button" class="btn_label_x">x</button></span> -->
<!--             <span>標籤CCCCC<button type="button" class="btn_label_x">x</button></span> -->
<!--             <span>標籤DDDDD<button type="button" class="btn_label_x">x</button></span> -->
<!--             <span>標籤EEEEE<button type="button" class="btn_label_x">x</button></span> -->
<!--         </div> -->
            
<!--         <br> -->
<!--         <div class="div_btn"> -->
<!--             <button type="reset" class="btn_reset">重新篩選</button> -->
<!--             <button type="submit" class="btn_submit">送出</button> -->
<!--         </div> -->
<!--         <hr> -->
        
               
		<label class="selectedLabel">已勾選的美食/景點</label>
        <div class="label">
            <c:forEach var="foodScapeName" items="${foodScapeNameList}">
            	<!-- <li>店家1<button type="button" class="btn_x">x</button></li> -->
				<span><i class="fa-solid fa-location-dot"></i>${foodScapeName}</span>
            </c:forEach>
        </div>
        <hr>
		<br>
		
		
        <div>
            <div class="container">
                <div class="row">
                    <span class="col-8 selectTitle">篩選行程</span>
<!--                     <div class="col-4 d-flex justify-content-end align-items-end"> -->
<!--                         <span>常見問題&nbsp;&nbsp;</span> -->
<!--                         <span>|&nbsp;&nbsp;</span> -->
<!--                         <span>聯繫客服</span> -->
<!--                     </div> -->
                </div>
            </div>
            
    
            <c:if test="${not empty list}">
	            <ul>
	            
	            	<c:forEach var="mapEntry" items="${list}">
		                <li class="journey">
				            <div class="container">
					            <div class="row">
					                <div class="col-4  d-flex justify-content-start">
					                    <div>
						                    <div class="journey_name">
						                        <span>行程名稱 :</span>
						                        <span>${mapEntry.key.journeyName}</span>
						                    </div>
						                    <div class="journey_days">
						                    	<span>行程天數 :</span>
							                	<span>${mapEntry.key.days} 天</span>	
						                    </div>
						                    <div class="journey_buyCount">
						                    	<span>購買次數 :</span>
							                	<span>${mapEntry.key.buyCount} 次</span>	
						                    </div>
						                    <div class="journey_idCount">
						                    	<span>相關度 :</span>
												<%-- <span>${mapEntry.value / selectedCount * 100} %</span>	 --%>
												<span class="star-ratings" data-rating="${mapEntry.value / selectedCount * 100}"></span>
						                    </div>
										</div>
					                </div>
					                <div class="col-8 d-flex justify-content-end">
					                    <div class="journey_price d-flex flex-column ms-auto align-items-end">
					                    	<div class="journey_price_div">
					                        	<span>金額&nbsp;&nbsp;</span>
					                        	<span class="journey_price_span">NT$ ${mapEntry.key.price}</span>
					                    	</div>
					                            
					                    	<form method="post" action="<%=request.getContextPath()%>/journey/user/journeySelect.do">
					                        	<button type="submit" class="btn_submit">查看更多</button>
					                        	<input type="hidden" name="action" value="journeySelect_more">
					                        	<input type="hidden" name="loopIndex" value="${loop.index}" />
					                        	<input type="hidden" name="journeyId_${loop.index}" value="${mapEntry.key.journeyId}">
					                     	</form>
					                    </div>
					                </div>
					            </div>
					        </div>
		                </li>
	                </c:forEach>
	                
	            </ul>
            </c:if>
            
            <c:if test="${empty list}">
            	<p style="text-align: center; padding-top: 50px; font-size: 24px; font-weight: 600; color: #CB997E;"> 沒有符合的行程&nbsp;QQ </p>
			</c:if>
        </div>
        
        

        <br><br>
<!--         <div class="container-fluid"> -->
<!--             <div class="row"> -->
<!--                 <div class="col-md-12"> -->
<!--                     <nav class="pagination-sm"> -->
<!--                         <ul class="pagination justify-content-end"> -->
<!--                             <li class="page-item"><a class="page-link a_page" href="#" style="padding-right: 10px; padding-left: 10px;">上一頁</a></li> -->
<!--                             <li class="page-item"><a class="page-link a_page" href="#" style="padding-right: 10px; padding-left: 10px;">1</a></li> -->
<!--                             <li class="page-item"><a class="page-link a_page" href="#" style="padding-right: 10px; padding-left: 10px;">2</a></li> -->
<!--                             <li class="page-item"><a class="page-link a_page" href="#" style="padding-right: 10px; padding-left: 10px;">3</a></li> -->
<!--                             <li class="page-item"><a class="page-link a_page" href="#" style="padding-right: 10px; padding-left: 10px;">下一頁</a></li> -->
<!--                         </ul> -->
<!-- 					</nav> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->


    </main>
    
    <%@ include file="/components/html/footer.jsp" %>
    
<!--     <footer id="footer"></footer> -->

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script>
// 	    const matchRate_el = document.querySelectorAll('.journey_idCount span:nth-child(2)');
	    const starRatings_el = document.querySelectorAll('.star-ratings');
	    
	    $(function () {
// 	        $("#header").load("../components/html/header.html");
// 	        $("#footer").load("../components/html/footer.html");


//     	    matchRate_el.forEach((element) => {
//     	    	const matchRateValue = parseFloat(element.textContent);

//     			if (matchRateValue >= 75) {
//     				element.style.color = 'rgb(225, 72, 72)';
//     				element.style.fontWeight = 600;
//     			}
//     		});
    	    
    	    
    	    starRatings_el.forEach((element) => {
    	        const rating = parseFloat(element.getAttribute('data-rating'));

    	        if (rating >= 80) {
    	            element.textContent = "★★★★★";
    	            
    	        } else if (rating >= 60 && rating < 80) {
    	            element.textContent = "★★★★☆";
    	        } else if (rating >= 40 && rating < 60) {
    	            element.textContent = "★★★☆☆";
    	        } else if (rating >= 20 && rating < 40) {
    	            element.textContent = "★★☆☆☆";
    	        } else if (rating < 20) {
    	            element.textContent = "☆☆☆☆☆";
    	        }
    	    });
    	    
    	    
        });
        
        
    </script>
</body>
</html>