<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>後台-優惠券修改</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/checkComplete.css">
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
    <main class="main" class="p-3">
        <h3 class="mx-3 my-4">優惠券</h3>

        <div class="card mx-5 my-5">
            <div class="card-header">
                優惠券修改
            </div>
            <div class="card-body">
                 <form method="post" action="coupon.do" id="couponForm">
                    <div class="couponId my-3">
                        <label>優惠券Id：</label>
                        <span id="couponId"></span>
                    </div>
                    
                    <div class="couponStatus my-3">
                    <label>優惠券狀態 :</label>
                    <select name="couponStatus" id="couponStatus">
                        <option value="0">下架</option>
                        <option value="1">上架</option>
                    </select>
               		</div>


                    <div class="div_btn d-flex justify-content-end">
	                    <input type="hidden" name="action" value="update">
	                    <input type="hidden" name="couponId" id="couponIdInput" value="">  
                        <button type="submit" class="btn_submit mx-2">送出</button>
                        <button type="reset" class="btn_reset mx-2" onclick="goBack()">取消</button>
                    </div>

                </form>


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
            $("#header").load("../admin/header.jsp");
            const urlParms = new URLSearchParams(window.location.search);
            const couponId = urlParms.get('coupon_id');
            const couponStatus = urlParms.get('coupon_status');
            
            $(".couponId span").text(couponId);
            $("#couponIdInput").val(couponId);
            
            if (couponStatus == 0) {
           	 $("#couponStatus").val('0') 
			} else if (couponStatus == 1) {
				$("#couponStatus").val('1') 
			}
            
        });
        
        function goBack() {
            // 使用 history 物件返回上一頁
            window.history.go(-1);
        }
	

    </script>




</body>

</html>