<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>會員中心-歷史訂單</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/checkComplete.css">
<link rel="stylesheet"
	href="//cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
<script src="https://kit.fontawesome.com/cb6bf56872.js"
	crossorigin="anonymous"></script>
<link rel="icon" href="../static/images/logo.ico" type="image/x-icon">
<style>
button.btn-modify, #filterBtn, a.btn-modify {
	background-color: #9C6644;
	color: white;
}
</style>
</head>


<body>
	<header id="header"></header>
	<main class="main">

		<div class="card mx-5 my-5">
			<div class="card-header">歷史訂單查詢</div>
			<div class="card-body mx-5">



				<table id="order" class="display mb-5">
					<div class="my-5 mx-5">
						<thead>
							<tr>
								<th>訂單日期</th>
								<th>訂單編號</th>
								<th>旅客人數</th>
								<th>訂單狀態</th>
								<th>查詢訂單明細</th>
							</tr>
						</thead>
						<tbody>

						</tbody>

					</div>
				</table>

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
	
	const orderId = 11001;
		$(function (){
			$("#header").load("../components/html/header.jsp");
			$("#footer").load("../components/html/footer.jsp");
			
			init();
			
			
		});
		
		
		
		function init(){
			$.ajax({
				url: "http://localhost:8081/LazyTravel/order/order.do",
				type: "GET",
				data: {"action": "getOrderByCustomerId" , "customerId" : orderId},
				dataType: "json",
				success: function(data) {
					let dataSet = [];
					data.forEach((item) => {
						let tmpArr = [];

						let ordersDetail = 
							'<td>' +
				            '<a class="customerDetail btn-modify btn" href="orderDetails.jsp?order_id=' + item.orderId + '&order_no=' + item.orderNo + '&order_status=' + item.orderStatus + '&tourist=' + item.tourist + '" class="btn-modify btn" style="white-space: nowrap;">訂單明細</a>'  +
							'</td>';
						
						
						let statusCell = '';
						if (item.orderStatus == 0) {
							statusCell = '<span class="status-unpaid">未付款</span>';
						} else if (item.orderStatus == 1) {
							statusCell = '<span class="status-paid">已付款</span>';
						} else if (item.orderStatus == 2) {
							statusCell = '<span class="status-canceled">已取消</span>';
						}
						
						
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
						
						tmpArr.push(formatDateTime(item.createTime));
						tmpArr.push(item.orderNo);
						tmpArr.push(item.tourist);
						tmpArr.push(statusCell);
						tmpArr.push(ordersDetail);
						dataSet.push(tmpArr)
					});
					
					
					new DataTable("#order" , {
						autoWidth: false,
						data: dataSet,
						columns: [
							{ data: 0 },
							{ data: 1 },
							{ data: 2 },
							{ data: 3 },
							{ data: 4 , orderable: false, searchable: false},
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