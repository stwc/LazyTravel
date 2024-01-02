<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.lazytravel.customer.entity.Customer" %>





 <% Customer customerObj = (Customer) session.getAttribute("customer");
  Integer customerId = customerObj.getCustomerId();
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>會員中心-歷史訂單</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="../css/coupon.css"> -->
<link rel="stylesheet"
	href="//cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
<script src="https://kit.fontawesome.com/cb6bf56872.js"
	crossorigin="anonymous"></script>
<link rel="icon" href="../static/images/logo.ico" type="image/x-icon">

<style>
  .btnn {
        background-color: #9C6644;
        color: white;
        border: none;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        transition-duration: 0.4s;
        cursor: pointer;
        border-radius: 8px;
  }
  
  .btnn:hover {
     background-color: #804C33;
        background-color: #B07C56;
        /* Slightly lighter shade for hover */
        color: white;
  }
  
  .btnn:active{
           background-color: #804C33;
        /* Slightly darker shade for click */
        color: white;
        box-shadow: 0 5px #666;
        transform: translateY(4px);
  }

</style>

</head>

<body>
	<header id="header"></header>
	<main class="main">
		<div class="container mt-5">
			<!-- 優惠券新增表單 -->
			<div class="card mb-5" style="width: 600px;">
				<div class="card-header">新增優惠券</div>
				<div class="card-body">
					<form id="couponForm">
						<div class="mb-3">
							<label for="couponCode" class="form-label">優惠碼：</label> 
							<input type="text" class="form-control" name="couponNo" required>
						</div>
						<button type="button" class="btnn" id="addCouponButton"
							onclick="addCouponClick()">新增優惠券</button>
					</form>
					
					<div class="my-3 fw-bolder" id="couponResponse" style="color: red" ></div>
				</div>
			</div>

			<!-- 擁有的優惠券列表 -->
			<div class="card">
				<div class="card-header">我的優惠券</div>
				<div class="card-body">
					<table class="table mt-5" id="customercoupon">
						<thead>
							<tr>
								<th scope="col">優惠碼#</th>
								<th scope="col">優惠券名稱</th>
								<th scope="col">起始日期</th>
								<th scope="col">失效日期</th>
								<th scope="col">折扣門檻</th>
								<th scope="col">折扣金額</th>
								<th scope="col">使用狀態</th>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</div>
			</div>
		</div>




	</main>

	<footer id="footer"></footer>
	<script src="https://code.jquery.com/jquery-3.7.1.js"
		integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="//cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>



	<script>
		$(function() {
			$("#header").load("<%=request.getContextPath()%>/components/html/header.jsp");
			$("#footer").load("<%=request.getContextPath()%>/components/html/footer.jsp");
			const customerId = 11001;
			getcustomercoupon ();
			 $("#couponResponse").text(sessionStorage.successmessage);
	         $("#couponResponse").text(sessionStorage.errormessage);
		
			
		});
		
		
		function addCouponClick() {
			let customerId = <%= customerId %>;
			const couponNo = $("input[name='couponNo']").val();
			
			
			$.ajax({
				url: "http://localhost:8081/LazyTravel/customorCenter/customercoupon.do",
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
		               sessionStorage.successmessage = data.message;
// 		                $("#couponResponse").text(data.success);
		            } else if (data.message) {
		            	 console.log(data.message)
		            	 sessionStorage.errormessage = data.message;
// 		                $("#couponResponse").text(data.error);
		            	 const errormessage = data.message;
		            }
		            
		            location.href = "/LazyTravel/customerCenter/coupon.jsp";
		           
		           
		        },
		        error: function(xhr, status, error) {
		            // Generic error handling
		            $("#couponResponse").text("An error occurred while processing your request.");
		        }  
				});
			
		}
		
		function getcustomercoupon (){
			const customerId = 11001;
			
			$.ajax({
				url: "http://localhost:8081/LazyTravel/customorCenter/customercoupon.do",
				type: "GET",
				data: {
					"action": "getcustomercouponByCutomerId" ,
					"customerId" : customerId },
				dataType: "json",
				success: function(data){

					
					let dataSet = [];
					data.forEach((item) => {
						let tmpArr = [];
	
						function formatDateTime(timestamp) {
							const options = {
								year: 'numeric',
								month: '2-digit',
								day: '2-digit',
								hour: '2-digit',
								minute: '2-digit',
								second: '2-digit',
							};
							return new Date(timestamp).toLocaleString('zh-TW', options).replace(',', '');
						}
						
						let statusCell = '';
						if (item.couponStatus == 0) {
							statusCell = '<span class="status-unpaid">未使用</span>';
						} else if (item.couponStatus == 1) {
							statusCell = '<span class="status-paid">已使用</span>';
						}
						
						tmpArr.push(item.serialNo);
						tmpArr.push(item.couponName);
						tmpArr.push(formatDateTime(item.startTime));
						tmpArr.push(formatDateTime(item.endTime));
						tmpArr.push(item.discount);
						tmpArr.push(item.threshold);
						tmpArr.push(statusCell);
						dataSet.push(tmpArr)

					});
					
					new DataTable("#customercoupon" , {
						autoWidth: false,
						data: dataSet,
						columns: [
							{ data: 0 },
							{ data: 1 },
							{ data: 2 },
							{ data: 3 },
							{ data: 4 },
							{ data: 5 },
							{ data: 6 },
						],
						
					});

				},
				error: function () {
					console.log("init error");
				}
			});
		}
		
		
		
		
	</script>




</body>

</html>