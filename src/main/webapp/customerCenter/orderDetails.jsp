<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>會員中心-歷史訂單</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/orderDetails.css">
	<link rel="stylesheet" href="//cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
	<script src="https://kit.fontawesome.com/cb6bf56872.js" crossorigin="anonymous"></script>
	<link rel="icon" href="../static/images/logo.ico" type="image/x-icon">
	<style>
		
		.btnn {
            width: 100px;
            height: 30px;
            border-radius: 90px;
            border-color: transparent;

            color: white;
            background: #9C6644;
            margin-right: 15px;
        }

        .btnn:active {
            outline: 2px solid #CCD5AE;
            box-shadow: 0 0 8px #a1a397
        }

        #goBackButton {
            width: 100px;
            height: 30px;
            border-radius: 90px;
            border-color: transparent;
            color: white;
            background: #CCD5AE;
        }
	</style>
</head>


<body>
	<header id="header"></header>
	<main class="main">

		<div class="card mx-5 my-5">
			<div class="card-header">訂單明細：</div>
			<div class="card-body">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">訂單編號#</th>
							<th scope="col">商品名稱</th>
							<th scope="col">旅客人數</th>
							<th scope="col">訂單狀態</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row" id="orderNo"></th>
							<td id="orderName"></td>
							<td id="tourist"></td>
							<td id="orderStatus"></td>
						</tr>

					</tbody>
				</table>

				<table class="table mt-5">
					<thead>
						<tr>
							<th scope="col">旅客編號#</th>
							<th scope="col">旅客姓名</th>
							<th scope="col">身份證字號</th>
							<th scope="col">出生日期</th>
							<th scope="col">連絡電話</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
			<div class="d-flex justify-content-end mx-3 my-3">
				<button class="btnn mx-5" id="goBackButton" onclick="goBack()">回上一頁</button>
				<!-- Button trigger modal -->
				<button type="button" class="btnn" data-bs-toggle="modal" data-bs-target="#exampleModal"
					id="cancelOrderButton">取消訂單</button>

				<!-- Modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title fs-5" id="exampleModalLabel">真的要取消訂單嗎?</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">按下去就掰掰囉，想清楚了嗎?</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">再想想</button>
								<button type="submit" class="btn btn-primary" onclick="cancelButton()">取消訂單</button>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>





		</div>
		</div>




	</main>

	<footer id="footer"></footer>
	<script src="https://code.jquery.com/jquery-3.7.1.js"
		integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="//cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>




	<script>
		$(function () {
			$("#header").load("<%=request.getContextPath()%>/components/html/header.jsp");
			$("#footer").load("<%=request.getContextPath()%>/components/html/footer.jsp");
			const urlParams = new URLSearchParams(window.location.search);
			const orderId = urlParams.get('order_id');
			const orderNo = urlParams.get('order_no');
			const orderStatus = urlParams.get('order_status');
			const tourist = urlParams.get('tourist');

			$("#orderNo").text(orderNo);
			$("#tourist").text(tourist);

			if (orderStatus == 0) {
				$("#orderStatus").text('未付款')
			} else if (orderStatus == 1) {
				$("#orderStatus").text('已付款')
			} else if (orderStatus == 2) {
				$("#orderStatus").text('已取消')
				// 隐藏取消订单按钮
				$("#cancelOrderButton").hide();
			}



			getJourByOrderId();
			getPasDetails();
		});

		function getJourByOrderId() {
			const urlParams = new URLSearchParams(window.location.search);
			const orderId = urlParams.get('order_id');

			$.ajax({
				url: "<%=request.getContextPath()%>/order/order.do",
				type: 'GET',
				data: { "action": "getJourneyNameByOrderId", "orderId": orderId },
				dataType: 'json',
				success: function (data) {
					let journeyName = data;

					if (journeyName) {
						$('#orderName').text(journeyName);
					}
				},
				error: function (xhr, status, error) {
					console.error("Error fetching journeyName:", status, error);
				}
			});
		}

		function getPasDetails() {
			const urlParams = new URLSearchParams(window.location.search);
			const orderId = urlParams.get('order_id');

			$.ajax({
				url: "<%=request.getContextPath()%>/order/passenger.do",
				type: 'GET',
				data: { "action": "getPasDetails", "orderId": orderId },
				dataType: 'json',
				success: function (passengers) {
					let tbody = "";
					passengers.forEach((item, index) => {
						tbody += 	
							'<tr>' +
	                        '<th scope="row">' + (index + 1) + '</th>' + 
	                        '<td>' + item.passengerName + '</td>' +
	                        '<td>' + item.idno + '</td>' +
	                        '<td>' + item.birth + '</td>' + 
	                        '<td>' + item.phone + '</td>' +
	                      	'</tr>'
					});
					$("table.table.mt-5 tbody").html(tbody);
				},
				error: function (xhr, status, error) {
					console.error("Error fetching passenger details:", status, error);
				}
			});
		}

		function cancelButton() {
			const urlParams = new URLSearchParams(window.location.search);
			const orderId = urlParams.get('order_id');

			$.ajax({
				url: "<%=request.getContextPath()%>/order/order.do",
				type: 'POST',
				data: { "action": "cancelorder", "orderId": orderId },
				dataType: 'json',
				success: function (response) {
					if (response.success) {

						// 顯示成功消息
						alert("訂單已成功取消！");

						// 重新載入訂單明細頁面
						location.href = "<%=request.getContextPath()%>/customerCenter/orderList.jsp";
					} else {
						alert("取消訂單失敗，請稍後再試。");
					}
				},
				error: function (xhr, status, error) {
					console.error("Error fetching passenger details:", status, error);
				}
			});
		}

		function goBack() {
			// 使用 history 物件返回上一頁
			window.history.go(-1);
		}

	</script>




</body>

</html>