<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>後臺 - 優惠券管理</title>
	<link
		href="https://cdn.datatables.net/v/dt/dt-1.13.8/af-2.6.0/b-2.4.2/b-colvis-2.4.2/b-html5-2.4.2/cr-1.7.0/date-1.5.1/fc-4.3.0/kt-2.11.0/r-2.5.0/rr-1.4.1/sc-2.3.0/sb-1.6.0/sp-2.2.0/sl-1.7.0/datatables.min.css"
		rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">


	<link rel="icon" href="../static/images/logo.ico" type="image/x-icon">

	<style>
		button.btn-modify,
		#filterBtn, a.btn-modify{
			background-color: #9C6644;
			color: white;
		}
		
		
		button.btn-modify,
		#filterBtn, a.btn-modify, a.couponAdd {
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
  
  button.btn-modify:hover,
		#filterBtn:hover, a.btn-modify:hover, a.couponAdd:hover {
     background-color: #804C33;
        background-color: #B07C56;
        /* Slightly lighter shade for hover */
        color: white;
  }
  
  button.btn-modify:active,
		#filterBtn:active, a.btn-modify:active, a.couponAdd:active{
           background-color: #804C33;
        /* Slightly darker shade for click */
        color: white;
        box-shadow: 0 5px #666;
        transform: translateY(4px);
  }
		
		.coupon{
			width: 1347px;
		}
	</style>
</head>

<body>
	<div id="header"></div>

	<div id="main" class="p-3">
		<h3 class="mx-3 mb-4">優惠券總覽</h3>
		<div class="card mx-5 my-5">
			<div class="card-header">
				總覽查詢
			</div>
			<div class="card-body mx-2">
				<table class="my-5">
					<a class="btn couponAdd" href="couponAdd.jsp" role="button"
						style="background-color: #9C6644;color: white"> 新增</a>

				</table>


				<table id="coupon" class="table table-striped">
					<thead>
						<tr>
							<th>優惠券Id</th>
							<th>優惠碼</th>
							<th>優惠名稱</th>
							<th>折扣金額</th>
							<th>折扣門檻</th>
							<th>創建時間</th>
							<th>結束時間</th>
							<th>剩餘數量</th>
							<th>總數量</th>
							<th>狀態</th>
							<th>修改</th>

						</tr>
					</thead>
				</table>

			</div>
		</div>

	</div>


	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>
	<script
		src="https://cdn.datatables.net/v/dt/dt-1.13.8/af-2.6.0/b-2.4.2/b-colvis-2.4.2/b-html5-2.4.2/cr-1.7.0/date-1.5.1/fc-4.3.0/kt-2.11.0/r-2.5.0/rr-1.4.1/sc-2.3.0/sb-1.6.0/sp-2.2.0/sl-1.7.0/datatables.min.js"></script>




	<script>

		$(function () {
			$("#header").load("../admin/header.jsp");
			init();
		});


		function init() {

			$.ajax({
				url: "http://localhost:8081/LazyTravel/order/coupon.do",
				type: "GET",
				data: { "action": "getall" },
				dataType: "json",
				beforeSend: function () {
					$("ul.task_list").html(
						'<li style="text-align: center;"><i class="fas fa-spinner fa-spin fa-3x"></i></li>'
					);
				},

				
				success: function (data) {
					let dataSet = [];
					data.forEach((item) => {
						let tmpArr = [];
						let modifyBtn = 
							
						    '<td>' +
						    '<a href="couponEditor.jsp?coupon_id=' + item.couponID + '&coupon_status=' + item.couponStatus + '" class="btn-modify btn">修改</a>' +
						    '<input type="hidden" name="action" value="update">' +
						    '</td>';
						

						let statusCell = '';
						if (item.couponStatus == 0) {
							statusCell = '<span class="status">下架</span>';
						} else if (item.couponStatus == 1) {
							statusCell = '<span class="status">上架</span>';
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

						tmpArr.push(item.couponID);
						tmpArr.push(item.serialNo);
						tmpArr.push(item.couponName);
						tmpArr.push(item.discount);
						tmpArr.push(item.threshold);
						tmpArr.push(formatDateTime(item.startTime));
						tmpArr.push(formatDateTime(item.endTime));
						tmpArr.push(item.stock);
						tmpArr.push(item.total);
						tmpArr.push(statusCell);
						tmpArr.push(modifyBtn);
						dataSet.push(tmpArr)
					});

					new DataTable('#coupon', {
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
							{ data: 7 },
							{ data: 8 },
							{ data: 9 },
							{ data: 10, orderable: false, searchable: false }
						],

						headerCallback: function (thead, data, start, end, display) {
							$(thead).find('th').each(function (index) {
								const text = $(this).text();
								$(this).html('<div class="dt-head-inner">' + text + '</div>');
							});
						}
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