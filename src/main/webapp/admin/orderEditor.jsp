<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>後台-訂單修改</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
    <script src="https://kit.fontawesome.com/cb6bf56872.js" crossorigin="anonymous"></script>
    <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">

    <style>
        button.btn_submit {
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

        button.btn_reset {
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
    </style>
</head>


<body>
    <header id="header"></header>
    <main class="main">

        <div class="card mx-5 my-5">
            <div class="card-header">
                歷史訂單修改
            </div>
            <form method="post" action="order.do" id="orderForm" onsubmit="return checkOrderId()">
            <div class="card-body">
                <div class="orderId my-3">
                    <label>訂單ID :</label>
                    <span id="orderId"></span>
                </div>
                
                <div class="orderNo my-3">
                    <label>訂單NO :</label>
                    <span id="orderNo"></span>
                </div>
                

                

                <div class="orderStatus my-3">
                    <label>訂單狀態 :</label>
                    <select name="orderStatus" id="orderStatus">
                        <option value="0">未付款</option>
                        <option value="1">已付款</option>
                        <option value="2">取消</option>
                    </select>
                </div>


                <br>
                <div class="div_btn d-flex justify-content-end">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="orderId" id="orderIdInput" value="">    
                    <button type="submit" class="btn_submit mx-2">送出</button>
                    <button type="reset" class="btn_reset mx-2"  onclick="goBack()">取消</button>
                </div>
            </div>
            </form>
        </div>




    </main>

    <footer id="footer"></footer>
    <script src="https://code.jquery.com/jquery-3.7.1.js"
        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="//cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>




    <script>
        $(function () {
            $("#header").load("../admin/header.html");
            const urlParams = new URLSearchParams(window.location.search);
            const orderId = urlParams.get('order_id');
            const orderNo = urlParams.get('order_no');
            const orderStatus = urlParams.get('order_status');
            
            $(".orderId span").text(orderId);
            $(".orderNo span").text(orderNo);
            $("#orderIdInput").val(orderId);
            
            if (orderStatus == 0) {
            	 $("#orderStatus").val('0') 
			} else if (orderStatus == 1) {
				$("#orderStatus").val('1') 
			} else if (orderStatus == 2) {
				$("#orderStatus").val('2') 
			}     
           
             
        });
        	
        function goBack() {
            // 使用 history 物件返回上一頁
            window.history.go(-1);
        }
       
        
        
    </script>




</body>

</html>