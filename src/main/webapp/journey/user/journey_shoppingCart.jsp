<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/components/html/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>購物車</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    
<style>
	* {
		box-sizing: border-box;
	}
	
	body {
		margin: 0;
	}
	
	main#main {
		padding: 40px 80px;
		/* border: 1px solid red; */
	}
	
	img.cart_main {
		border-radius: 10px;
	}
	
	div.cart_title {
		font-size: 26px;
		margin-bottom: 20px;
	}
	
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
	
	button.btn_delete {
		width: 100px;
		height: 30px;
		border-radius: 90px;
		border-color: transparent;
		color: white;
		background: #9C6644;
	}
	
	button.btn_delete:active {
		outline: 2px solid #9C6644;
		box-shadow: 0 0 8px #a1a397
	}
	
	button.amount_plus, button.amount_reduce {
		width: 26px;
		height: 26px;
		line-height: 10px;
		border-radius: 90px;
		border-color: transparent;
		background-color: #CCCCCC;
		color: white;
	}
	
	button.amount_plus:active, button.amount_reduce:active {
		outline: 2px solid #CCCCCC;
		box-shadow: 0 0 8px #a1a397
	}
	
	div.btn_button {
		display: flex;
		justify-content: flex-end;
	}
	
	div.btn_button button {
		width: 100px;
		height: 30px;
		border-radius: 90px;
		border-color: transparent;
		color: white;
		background: #6B705C;
	}
	
	div.btn_button button:active {
		outline: 2px solid #6B705C;
		box-shadow: 0 0 8px #a1a397
	}
	
	table thead th.cart_header_price,
	table thead th.cart_header_amount,
	table thead th.cart_header_total,
	table thead th.cart_header_use {
		text-align: center;
	}
	
	table tbody td.price,
	table tbody td.amount,
	table tbody td.total,
	table tbody td.div_btn{
		text-align: center;
	}
	
	thead th, tbody td {
		padding-bottom: 25px;
	}
	
 	th.cart_header_nameAndDate, td.name_and_date{
 		padding-left: 20px;
 	}
</style>


</head>
<body>

<!--     <header id="header"></header> -->

    <main id="main">
        <div class="cart_title">購物車</div>
		
		<form method="post" action="<%=request.getContextPath()%>/journey/user/shoppingCart.do">
	        <table class="table-responsive">
				<thead>
					<tr>
						<th scope="col"></th>
						<th scope="col" class="cart_header_nameAndDate">行程名稱 / 日期</th>
						<th scope="col" class="cart_header_price">單價</th>
						<th scope="col" class="cart_header_amount">數量</th>
						<th scope="col" class="cart_header_total">總計</th>
						<th scope="col" class="cart_header_use"></th>
					</tr>
				</thead>

			
				<tbody>
	                <tr>
						<td>
							<img src="${pageContext.request.contextPath}/journey/images/journey1.jpg" alt="" class="img-fluid cart_main">
						</td>
						<td class="col-3 name_and_date">
							<div class="name">陽明山一日遊111</div>
							<div>113/01/01~113/01/01</div>
						</td>
						<td class="col-1 price">
							<span>$</span>
							<span>300</span></td>
						<td class="col-2 amount">
							<button class="amount_reduce" onclick="reduce(this)"><b>-</b></button>
							<label class="count mx-3">1</label>
							<button class="amount_plus"  onclick="plus(this)"><b>+</b></button>
						</td>
						<td class="col-1 total">
							<span>$</span>
							<span>300</span>
						</td>
						<td class="col-3 div_btn">
							<button type="submit" class="btn_submit">結帳</button>
							<button type="button" class="btn_delete" onclick="deleteJourney(this)">刪除</button>
						</td>
					</tr>
			
	            </tbody>
        	</table>
	
	        <br>
	        <br>
	        <div class="btn_button">
	            <button type="button" onclick="redirectToHome()">繼續逛逛</button>
	        </div>
		</form>
    </main>
    
	<%@ include file="/components/html/footer.jsp" %>
	
<!--     <footer id="footer"></footer> -->

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
 	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script>
 		$(function () {
// 	        $("#header").load("../components/html/header.html");
// 	        $("#footer").load("../components/html/footer.html");
        });

        
        function reduce(button) {
            var label = button.parentNode.querySelector(".count");
            var value = parseInt(label.innerText);
            if (value > 1) {
                value--;
                label.innerText = value;
            }
        }

        function plus(button) {
            var label = button.parentNode.querySelector(".count");
            var value = parseInt(label.innerText);
            value++;
            label.innerText = value;
        }
        
        function deleteJourney(button) {
            var row = button.closest("tr");
            if (row) {
                row.remove();
            }
        }
        
        var contextPath = "${pageContext.request.contextPath}";
        function redirectToHome() {
//             window.location.href = contextPath + "/journey/admin/tourGroup_list.jsp";
        }
    </script>
</body>

</html>