<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/payCheck.css">
    <script src="https://kit.fontawesome.com/cb6bf56872.js" crossorigin="anonymous"></script>
    <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">
</head>


<body>
    <header id="header"></header>
    <main class="main">
        <div class="container mt-5 px-3 d-flex justify-content-center">
            <div class="progresss-container ">
                <div class="progresss" id="progress"> </div>
                <div class="circle active" style="font-weight: bold;">選擇行程</div>
                <div class="circle" style="font-weight: bold;">填寫資料</div>
                <div class="circle" style="font-weight: bold;">完成付款</div>
            </div>
        </div>
        <hr class="mx-5">


        <div class="card mx-5 mt-5">
            <div class="card-header" style="font-size: 18px; font-weight: 600; color: gray;">
                訂單詳情
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col">
                        <h5 class="card-title mb-4 " style="font-size: 24px; font-weight: 600; color: darkgrey;">旅客明細
                        </h5>

                        <div class="passenger-detail-container">
                           
                        </div>

                    </div>
                    <div class="col">
                        <h5 class="card-title mb-4 " style="font-size: 24px; font-weight: 600; color: darkgrey;">訂單明細
                        </h5>
                        <p class="card-subtitle mb-4 text-body-secondary ">商品名稱：<span class="journey-name ms-2"></span>
                        </p>
                        <p class="card-subtitle mb-4 text-body-secondary">出發日期：<span
                                class="group-strat-time ms-2"></span></p>
                        <p class="card-subtitle mb-4 text-body-secondary">回程日期：<span class="group-end-time ms-2"></span>
                        </p>
                        <p class="card-subtitle mb-4 text-body-secondary">旅客人數：<span class="tourist ms-2"></span></p>
                    </div>
                </div>


                <div class="d-flex justify-content-end mt-3">
                    <button class="btnn" id ="backward" onclick="backwardClick()">修改旅客資訊</button>
                </div>
            </div>
        </div>


        <div class="card mx-5 mt-5">
            <div class="card-header" style="font-size: 18px; font-weight: 600; color: gray;">
                選擇付款方式
            </div>
            <div class="card-body">
                <div>
                    <p>訂單總金額： <span class="globalTotalAmt" style="color: red; font-weight: 600"></span></p>
                    

                </div>
                    <div class="col">
                        <ul class="list-group">
                            <li class="list-group-item">
                              <input class="form-check-input me-1" type="radio" name="listGroupRadio" value="" id="firstRadio" checked>
                              <label class="form-check-label" for="firstRadio">信用卡付款</label>
                            </li>
                           
                          </ul>
                    </div>
                    
                </div>

			<form id="ecpayform" method="POST" action="http://localhost:8081/LazyTravel/order/ecpay.do">
                <div class="d-flex justify-content-end mx-3 my-3">
                    <button type="submit" class="btnn" id="payButton" onclick="nextClick()">去買單</button>
                    <input id="ecpay" type="hidden" name="orderId" value=" " >
                </div>
			</form>
				
            </div>
        </div>


    </main>

    <footer id="footer"></footer>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    <!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> -->

    <script>
        $("#header").load("../components/html/header.jsp");
        $("#footer").load("../components/html/footer.jsp");
    </script>

    <script>
    let formData;
    let passengerCount;
    let groupId;
    let customerId;
    let selectedCouponId; 
    let globalTotalAmt;
    let orderId;
    let passengersDataList = [];
        // 進度條特效
        const progress = document.getElementById("progress");
        const stepCircles = document.querySelectorAll(".circle");
        let currentActive = 1;

        //NOTE CHANGE HERE TO 1-4
        //1=25%
        //2=50%
        //3=75%
        //4=100%
        update(3);

        
        
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
        
        $(function(){
        	formData = JSON.parse(sessionStorage.getItem('formData'));
            passengerCount = sessionStorage.getItem('passengerCount');
            groupId = sessionStorage.getItem('groupId');
            customerId = sessionStorage.getItem('customerId');
            selectedCouponId = sessionStorage.getItem('selectedCouponId');
            globalTotalAmt = sessionStorage.getItem('globalTotalAmt');
            
            $(".journey-name").text(sessionStorage.getItem('journey-name'));
            $(".group-strat-time").text(sessionStorage.getItem('group-strat-time'));
            $(".group-end-time").text(sessionStorage.getItem('group-end-time'));
            $(".journey-name").text(sessionStorage.getItem('journey-name'));
            $(".tourist").text(sessionStorage.getItem('passengerCount'));
            $(".globalTotalAmt").text(sessionStorage.getItem('globalTotalAmt') + " 元");

            
            	
            // 檢查是否成功獲取數據
            if (!formData || !passengerCount) {
                console.error('無法檢索到表單數據或旅客人數');
                return;
            }

            // 獲取顯示旅客詳細信息的容器
            var passengerDetailContainer = document.querySelector('.passenger-detail-container');

            // 遍歷每個旅客並生成對應的 HTML 元素
            for (var i = 1; i <= passengerCount; i++) {
                var passengerData = formData['passenger' + i];

                var passengerInfoHTML = 
                	
                    '<div class="passenger-form">' +
                        '<p class="ms-4" style="font-size: 18px; font-weight: 600; color: #CB997E;">旅客' + i + '</p>' +
                        '<p class="form-label ms-4 my-3">姓名：' + passengerData.name + '</p>' +
                        '<p class="form-label ms-4 my-3">性別：' + passengerData.gender + '</p>' +
                        '<p class="form-label ms-4 my-3">證件號碼：' + passengerData.idno + '</p>' +
                        '<p class="form-label ms-4 my-3">生日：' + passengerData.birth + '</p>' +
                        '<p class="form-label ms-4 my-3">電話：' + passengerData.phone + '</p>' +
                        '<p class="form-label ms-4 my-3">Email：' + passengerData.mail + '</p>' +
                    '</div>';
                

                // 將生成的 HTML 添加到容器中
                passengerDetailContainer.innerHTML += passengerInfoHTML;
            }
            
        });

        


        function backwardClick() {
       var button = document.getElementById('backward');
        button.classList.add('clicked');
        sessionStorage.setItem('scrollToTop', 'true');
//         window.history.back();

	      window.location.href = 'checkOut.jsp';
        }
        
        function nextClick() {
        	
        	event.preventDefault();
            
            // 假设您希望延迟提交2秒钟
            setTimeout(function() {
              // 获取表单元素
              var form = document.getElementById('ecpayform');
              
              // 执行表单提交
              form.submit();
            }, 2000); // 2000毫秒 = 2秒钟
       
        	

			
        	//第一次請求新增訂單
        	$.ajax({
		    	url: "http://localhost:8081/LazyTravel/order/order.do",
				type: "POST",
				data: {
					"action" : "insert" , 
					"customer_id" : customerId ,
					"group_id" : groupId ,
					"tourist" : passengerCount ,
					"coupon_id" : selectedCouponId ,
					"total_amt" : globalTotalAmt ,
					"order_status" : 0
					 }, 
				dataType: "json",
				success: function(response) {
					orderId = response;
					console.log(orderId)
					$("#ecpay").val(orderId);
					
					// 构建包含乘客数据的数组或对象
		            for (var i = 1; i <= passengerCount; i++) {
		                var passengerData = formData['passenger' + i];
		                passengersDataList.push(passengerData);
		            }
					
					


		            // 构建要发送的数据对象
		            var postData = {
			                "action": "insertPassengers",
			                "orderId": orderId,
			                "passengers": JSON.stringify(passengersDataList)      
		            };
		            
					//第二次請求新增旅客明細
					$.ajax({
				    	url: "http://localhost:8081/LazyTravel/order/passenger.do",
						type: "POST",
						data: postData,
						dataType:"json",
						success: function(response) {
							// 在这里处理从后端返回的响应
					        console.log('Response received:', response);
					        window.location.href = 'checkOut.jsp';
				      	},
				      	error:function(xhr, status, error) {
				              console.error('Error:', error);
				          }

       			 	});
					
		        },
		        error: function(xhr, status, error) {
		            console.error('Error:', error);
		        }
			});		
        }




    </script>


</body>

</html>