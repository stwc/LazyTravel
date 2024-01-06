<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.lazytravel.customer.entity.Customer" %>





 <% Customer customerObj = (Customer) session.getAttribute("customer");
  Integer customerId = customerObj.getCustomerId();
  Integer passengerCount = (Integer)session.getAttribute("signupNum");
  Integer groupId = Integer.parseInt((String)(session.getAttribute("groupId")));
%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>填寫旅客資訊</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/order/css/checkOut.css">

<script src="https://kit.fontawesome.com/cb6bf56872.js"
	crossorigin="anonymous"></script>
<link rel="icon" href="<%=request.getContextPath()%>/static/images/logo.ico" type="image/x-icon">

</head>


<body>
	<header id="header"></header>
	<%@ include file="/components/html/header.jsp" %>
	<main class="main" style="padding-left: 70px; padding-right: 70px;">
		<div class="container mt-5 px-3 d-flex justify-content-center">
			<div class="progresss-container ">
				<div class="progresss" id="progress"></div>
				<div class="circle active" style="font-weight: bold;">選擇行程</div>
				<div class="circle" style="font-weight: bold;">填寫資料</div>
				<div class="circle" style="font-weight: bold;">完成付款</div>
			</div>
		</div>
		<hr class="mx-5">
		<form class="mx-5 " id="customerDetailsForm">
			<div class="passenger-detail">
				<div class="row">
					<div class="col mt-5">
						<p class="mx-3"
							style="font-size: 24px; font-weight: 600; color: darkgrey;">填寫旅客資料</p>
						<div id="passenger-form-placeholder"></div>

					</div>

					<div class="col mt-5">
						<div class="card mx-3">
							<div class="card-body">
								<h5 class="card-title mb-4 "
									style="font-size: 24px; font-weight: 600; color: darkgrey;">商品明細</h5>
								<p class="card-subtitle mb-4 text-body-secondary ">
									商品名稱：<span class="journey-name ms-2" id="journey-name"></span>
								</p>
								<p class="card-subtitle mb-4 text-body-secondary">
									出發日期：<span class="group-strat-time ms-2" id="group-strat-time"></span>
								</p>
								<p class="card-subtitle mb-4 text-body-secondary">
									回程日期：<span class="group-end-time ms-2" id="group-end-time"></span>
								</p>
								<p class="card-subtitle mb-4 text-body-secondary">
									旅客人數：<span class="tourist ms-2" id="tourist"></span>
								</p>
								<hr class="mx-1 border-2">
								<p class="card-subtitle mb-4 fw-bold " style="color: #6B705C;">
									訂單小計：<span class="total_amt ms-2" id="total_amt"></span>
								</p>

								<a id="customButton" class="btnn mt-3" tabindex="-1"
									role="button" aria-disabled="true" onclick="goBack()">回上一頁</a>

							</div>
						</div>
					</div>
				</div>
			</div>
			</div>

			<div class="mx-4">
				<div class="card">
					<div class="card-header"
						style="font-size: 24px; font-weight: 600; color: gray;">折扣使用
					</div>
					<div class="card-body">

						<h5 class="card-title ms-5 my-3"
							style="font-size: 24px; font-weight: 600; color: gray;">新增優惠券</h5>
						<div class="ms-5">
							<input type="text ms-5" name="couponNo"
								style="font-size: 24px; font-weight: 600; color: gray;">
							<button type="button" class="btn btn-secondary btn-sm"
								onclick="addCouponClick()">新增</button>
							<div class="my-3 fw-bolder" id="couponResponse"
								style="color: red"></div>
						</div>

						<h5 class="card-title ms-5 my-3"
							style="font-size: 24px; font-weight: 600; color: gray;">選擇優惠券</h5>

						<select class="form-select mx-5" id="couponSelect"
							style="width: 500px">

						</select>



					</div>
				</div>
			</div>

			<div class="card mx-4 mt-5">
				<div class="card-header"
					style="font-size: 18px; font-weight: 600; color: gray;">訂單總金額
				</div>
				<div class="card-body">
					<div class="d-inline"
						style="font-weight: 600; color: gray; font-size: 24px">
						<span>總計：</span>
					</div>

					<div class="d-inline justify-content-end"
						style="font-size: 24px; font-weight: 600; color: red;">
						<span id="DSTotal"></span>
					</div>

					<div class="d-flex justify-content-end mt-3">
						<button type="submit" class="btnn" id="customerDetailForm">確認訂單</button>
					</div>
				</div>
			</div>
		</form>



	</main>

	<footer id="footer"></footer>
	<%@ include file="/components/html/footer.jsp" %>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>




	<script>
	let selectedCouponId = 0;
	let globalTotalAmt = 0;
	let journey_name = 0;
	let group_strat_time = 0;
	let group_end_time = 0;
	let originalTotal = 0;
	let couponId = 0;

//=========>  最終用session取得
// 	let passengerCount = 1;
// 	let customerId = 11001;
// 	let groupId = 24002;

	let customerId = <%= customerId %>;
	let passengerCount = <%= passengerCount %>;
	let groupId = <%= groupId %>;
	console.log(customerId);
	console.log(passengerCount);
	console.log(groupId);
//=========>

	$(window).on('load', function() {
    if (sessionStorage.getItem('scrollToTop') === 'true') {   
        setTimeout(function() {
            $('html, body').animate({ scrollTop: 0 }, 'slow');
            sessionStorage.removeItem('scrollToTop');
        }, 1000); // 延遲 1000 毫秒
    }
});
	
	
	
	
	$(function(){
// 		 $("#header").load("../components/html/header.html");
// 		 $("#footer").load("../components/html/footer.html");
		 init();
		 getCustomerCoupon();
		 recovery_data();
	
	

	});
	
	$('#couponSelect').on('change', function() {
		var selectedDiscount = $('#couponSelect').val();
		var selectedOption = $('#couponSelect option:selected');
		couponIdval = selectedOption.data('couponId'); // 获取选中的option的couponId
		if(couponIdval != null){
			couponId = couponIdval;
		}else{
			couponId = null;
		}
		console.log(couponId);
		if(selectedDiscount !== undefined){
			if(selectedDiscount === "0"){
				$("#DSTotal").text(originalTotal + " 元");
			}else{
				calculateTotal(selectedDiscount);
			}
		}
	    });
	
	function calculateTotal(selectedDiscount){
		if(selectedDiscount !=0 ){
			var discountedTotal = originalTotal - selectedDiscount; // 計算折扣後的總金額        	
		}else{
			 discountedTotal = originalTotal;
		}

        // 確保總金額不會小於0
        discountedTotal = (discountedTotal < 0) ? 0 : discountedTotal;

        // 更新頁面上的總金額
        $("#DSTotal").text(discountedTotal + " 元");
        globalTotalAmt = discountedTotal;
	}

	
	function init(){

		// 進度條特效
	    const progress = document.getElementById("progress");
	    const stepCircles = document.querySelectorAll(".circle");
	    let currentActive = 1;

	    //NOTE CHANGE HERE TO 1-4
	    //1=25%
	    //2=50%
	    //3=75%
	    //4=100%
	    update(2);

	    function update(currentActive) {
	      stepCircles.forEach((circle, i) => {
	        if (i < currentActive) {
	          circle.classList.add("active");
	        } else {
	          circle.classList.remove("active");
	        }
	      });

	      const activeCircles = document.querySelectorAll(".active");
	      progress.style.width =
	        ((activeCircles.length - 1) / (stepCircles.length - 1)) * 100 + "%";
	    }

		 $("#tourist").text(passengerCount+ " 人 ") ;
		    
		    $.ajax({
		    	
		    	url: "<%=request.getContextPath()%>/order/order.do",
				type: "GET",
				data: {
					"action" : "getOrderDetails" ,
					"groupId" : groupId }, 
				dataType: "json",
				success: function(data) {
					console.log(data);
					data.forEach((item) => {
						
						let totalAmt = '';
						totalAmt = (item.price) * passengerCount;
						
						
						$("#journey-name").text(item.journeyName);
						journey_name = item.journeyName;
						$("#group-strat-time").text(item.startTime);
						group_strat_time = item.startTime;
						$("#group-end-time").text(item.endTime);
						group_end_time = item.endTime;
						$("#total_amt").text(totalAmt + " 元 ");
						globalTotalAmt = totalAmt;
						originalTotal = totalAmt;
						$("#DSTotal").text(totalAmt + " 元 ")
					});
		        },
		        error: function(xhr, status, error) {
		            // Generic error handling
		            $("#couponResponse").text("An error occurred while processing your request.");
		        }
			
		    });

           for (var i = 1; i <= passengerCount; i++) {
        	   var myDate = new Date;
	    	   var year = myDate.getFullYear(); //获取当前年
	    	   var mon = myDate.getMonth()+1 < 10 ? "0"+(myDate.getMonth()+1) : (myDate.getMonth()+1);//
	    	   var dat = myDate.getDate() < 10 ? "0"+myDate.getDate() : myDate.getDate();
        	   
        	   
           	let customerDetails = 
          		'<div class="passenger-form">' +
          		'<p class="ms-4" style="font-size: 18px; font-weight: 600; color: #CB997E;">旅客' + i + '</p>' +
          		'<label for="inputName' + i + '" class="form-label ms-4">旅客姓名：</label>' +
                '<input name="inputName" type="text" id="inputName' + i + '" class="form-control ms-4 w-50 mb-4" required>' +
                '<label for="gender' + i + '" class="form-label ms-4">旅客姓別：</label>' +
                '<div class="form-check form-check-inline">' +
                '<input class="form-check-input" type="radio" name="gender' + i + '" id="male' + i + '" value="男性" required>' +
                '<label class="form-check-label" for="male' + i + '">男性</label>' +
                '</div>'+
                '<div class="form-check form-check-inline">' +
                '<input class="form-check-input" type="radio" name="gender' + i + '" id="female' + i + '" value="女性" required>' +
                '<label class="form-check-label" for="female' + i + '">女性</label>' +
                '</div>' +
                '<div class="form-check form-check-inline">' +
                '<input class="form-check-input" type="radio" name="gender' + i + '" id="other' + i + '" value="其他" required>' +
                '<label class="form-check-label" for="other' + i + '">其他</label>' +
                '</div>' +
                '<p for="inputId" class="form-label  ms-4 my-3">身分證字號(護照號碼)：</p>' +
                '<input name="inputId" type="text" id="inputId" class="form-control ms-4 w-50 mb-4" required>' +
                '<p for="inputbirth" class="form-label  ms-4 my-3">出生日期：</p>' +
                '<input name="inputbirth" type="date" id="inpudate" class="form-control ms-4 w-50 mb-4" name= "birth' + i + '" id="birth' + i + '" max="' + year + '-' + mon + '-' + dat + '" required>' +
                '<p for="inputel" class="form-label  ms-4 my-3">連絡電話：</p>' +
                '<input name="inputel" type="tel" id="inputel" class="form-control ms-4 w-50 mb-4" name= "phone' + i + '" id="phone' + i + '"  required="\d+">' +
                '<p for="inputMail" class="form-label  ms-4 my-3">Email信箱：</p>' +
                '<input name="inputMail"  type="email" id="inputMail" class="form-control ms-4 w-50 mb-4" name= "mail' + i + '" id="mail' + i + '"  required>' +
                '</div>' +
                '<hr class="mx-4 border-2">';
                
           	
           	 $("#passenger-form-placeholder").append(customerDetails);
           		

           }
           
           
	    }
	

	
	function getCustomerCoupon(){
		
		  // GET Coupon請求
        $.ajax({
		    	url: "<%=request.getContextPath()%>/order/customercoupon.do",
				type: "GET",
				data: {
					"action" : "getcustomercouponByCutomerId" , "customerId" : customerId
					 }, 
				dataType: "json",
				success: function(data) {
			        console.log(data);
			      var $select = $('#couponSelect');

			      $select.empty(); // 清空select選項
        		  var $option0 = $('<option></option>').text("無").val(0);
        		  $select.append($option0);
	
        		  var now = new Date();
        		  
        		  
			        data.forEach(function(item) {
			        	var startTimeStr = item.startTime.replace(/(\d{1,2}),/, '$1');
			        	var endTimestr = item.endTime.replace(/(\d{1,2}),/, '$1');
			        	var startTime = new Date(startTimeStr);
			        	var endTime = new Date(endTimestr);
			        	
			        	if(now > startTime && now < endTime && globalTotalAmt >= item.threshold  && item.couponStatus != 1){
			        		var $option = $('<option></option>').val(item.discount)
				                								.text(item.couponName + "：消費滿 " +　item.threshold + " 折抵 " + item.discount + " 元")
			        											.data('couponId', item.couponId);
									
				            $select.append($option);

			        	}
			        	
			        });
			    },
			    error: function(xhr, status, error) {
			        $("#couponResponse").text("An error occurred while processing your request.");
			    }
					
			
		    });
	};
		
		
	
	$("#customerDetailsForm").on('submit' , function(e){
			  e.preventDefault(); 
			 
//===========> 填入旅客資料裝箱
		      for (var i = 1; i <= passengerCount; i++) {
	          var inputName = document.getElementById('inputName' + i).value.trim();
	          var genderSelected = document.querySelector('input[name="gender' + i + '"]:checked');
	          var inputId = document.getElementById('inputId').value.trim();
	          var inputel = document.getElementById('inputel').value.trim();
	          var inputMail = document.getElementById('inputMail').value.trim();
		        }
					
		        // 創建一個對象來保存表單數據
		      var formData = {};

		      // 遍歷每個旅客
		      for (var i = 1; i <= passengerCount; i++) {
		        formData['passenger' + i] = {
		          'name': document.getElementById('inputName' + i).value,
		          'gender': document.querySelector('input[name="gender' + i + '"]:checked').value,
		          'idno': document.getElementById('inputId').value,
		          'birth': document.getElementById('inpudate').value,
		          'phone': document.getElementById('inputel').value,
		          'mail': document.getElementById('inputMail').value
		        };
		       
		      }
//===========> 填入旅客資料裝箱


		      // 將表單數據保存到 localStorage 中
		      sessionStorage.setItem('formData', JSON.stringify(formData));
		      sessionStorage.setItem('passengerCount', passengerCount);
		      sessionStorage.setItem('groupId', groupId);
		      sessionStorage.setItem('customerId', customerId);
		      sessionStorage.setItem('selectedCouponId', couponId);
		      sessionStorage.setItem('globalTotalAmt', globalTotalAmt);
		      sessionStorage.setItem('journey-name', journey_name);
		      sessionStorage.setItem('group-strat-time', group_strat_time);
		      sessionStorage.setItem('group-end-time', group_end_time);
		      
		    
		      
		      
		      
		      
		     

		      
			

		      window.location.href = "<%=request.getContextPath()%>/order/payCheck.jsp';

			
			
			
		});
		
		function addCouponClick() {
			const couponNo = $("input[name='couponNo']").val();
			
			
			$.ajax({
				url: "<%=request.getContextPath()%>/customorCenter/customercoupon.do",
				type: "POST",
				data: {
					"action": "addcustomercoupon" ,
					"customerId" : customerId ,  
					"couponNo" : couponNo},
				dataType: "json",
				success: function(data) {
		            // Check if response contains 'success' or 'error'
		            if (data.message) {	
		                // Handle success
// 		               sessionStorage.successmessage = data.message;
		                $("#couponResponse").text(data.message);
		            } else if (data.message) {
		            	 console.log(data.message)
// 		            	 sessionStorage.errormessage = data.message;
		                $("#couponResponse").text(data.message);
		            	 
		            }
		            
		            getCustomerCoupon();

		           
		        },
		        error: function(xhr, status, error) {
		            // Generic error handling
		            $("#couponResponse").text("An error occurred while processing your request.");
		        }  
				});
	
		}
		
		function recovery_data(){
			let formData = sessionStorage.getItem('formData');
			console.log(formData);
		    if (formData) {
		        formData = JSON.parse(formData);
		        for (let i = 1; i <= passengerCount; i++) {
		            const passengerData = formData['passenger' + i];
		            if (passengerData) {
		            	document.getElementById('inputName' + i).value = passengerData.name;
		                document.querySelector('input[name="gender'+ i +'"][value="' + passengerData.gender + '"]').checked = true;
		                document.getElementById('inputId').value = passengerData.idno;
		                document.getElementById('inpudate').value = passengerData.birth;
		                document.getElementById('inputel').value = passengerData.phone;
		                document.getElementById('inputMail').value = passengerData.mail;
		                        
		            }
		        }
		        sessionStorage.removeItem('formData');
		    }

	        };
	        
	        function goBack() {
	            // 使用 history 物件返回上一頁
	            window.history.go(-1);
	        }
	        
	        

	        
	        

	        
  </script>


</body>

</html>