<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.lazytravel.journey.dao.*"%>
<%@page import="com.lazytravel.journey.entity.*"%>
<%@page import="com.lazytravel.customer.entity.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>購物車</title>

<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
<link rel="icon" href="${pageContext.request.contextPath}/static/images/logo.ico" type="image/x-icon">


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
	table thead th.cart_header_signData,
	table thead th.cart_header_use {
		text-align: center;
	}
	
	table tbody td.price,
	table tbody td.amount,
	table tbody td.total,
	table tbody td.signData,
	table tbody td.div_btn{
		text-align: center;
	}
	
	thead th, tbody td {
        padding-bottom: 15px;
		padding-top: 15px;
	}
	
 	th.cart_header_nameAndDate, td.name_and_date{
 		padding-left: 20px;
 	}
 	
 	table thead th.cart_header_total,
 	table tbody td.total{
 		padding-right: 20px;
 	}
 	
 	table thead th.cart_header_price,
 	table tbody td.price{
 		padding-left: 20px;
 	}
 	
 	form {
        display: inline-block;
    }
</style>

</head>
<body>

<!--     <header id="header"></header> -->
	<%@ include file="/components/html/header.jsp" %>
	
    <main id="main">
        <div class="cart_title">購物車</div>
		
	    <table class="table-responsive">
			<thead style="border-bottom: 1px dotted #a1a397;">
				<tr>
					<th scope="col" class="cart_header_nameAndDate">行程名稱 / 日期</th>
					<th scope="col" class="cart_header_price">單價</th>
					<th scope="col" class="cart_header_amount">數量</th>
					<th scope="col" class="cart_header_total">總計</th>
					<th scope="col" class="cart_header_signData">是否成團&nbsp;/&nbsp;剩餘報名人數</th>
					<th scope="col" class="cart_header_use"></th>
				</tr>
			</thead>
	
			<tbody>
			
				<c:forEach var="tourGroup" items="${tourGroupList}" varStatus="loop">
					<tr>
						<td class="col-3 name_and_date">
							<span>${tourGroup.journey.journeyName}</span>
							<br>
							<span>${tourGroup.startTime} ~ ${tourGroup.endTime}</span>
						</td>
						
						<td class="col-1 price">
							<span class="price">$ ${tourGroup.price}</span>
						</td>
						
						<td class="col-2 amount">
							<form method="post" id="form_quantity_${loop.index}" action="<%=request.getContextPath()%>/journey/user/shoppingCart.do">
							    <button class="amount_reduce" onclick="reduce(this, ${loop.index}, event)"><b>-</b></button>
								<span class="count_${loop.index} mx-3">${shoppingCartList[loop.index].quantity}</span>	
							    <button class="amount_plus"  onclick="plus(this, ${loop.index}, event)"><b>+</b></button>
							    <input type="hidden" name="action" value="shoppingCart_update" />
							    <input type="hidden" name="loopIndex" value="${loop.index}" />
							    <input type="hidden" name="groupId_${loop.index}" value="${shoppingCartList[loop.index].groupId}" />
							    <input type="hidden" id="quantityChange_${loop.index}" name="quantityChange_${loop.index}" value="" />
							</form>
						</td>
						
						<td class="col-1 total">
							<span>$ ${tourGroup.price * shoppingCartList[loop.index].quantity}</span>
						</td>
						
						<td class="col-2 signData">
							<span>${(tourGroup.signupNum < tourGroup.minRequired)?  "未成團" : "已成團"}</span>
							<span>&nbsp;/&nbsp;</span>
							<span>剩餘名額 ${tourGroup.maxRequired - tourGroup.signupNum} 名</span>
						</td>
						
						<td class="col-3 div_btn">
							<form method="post" action="<%=request.getContextPath()%>/journey/user/shoppingCart.do">
							    <button type="submit" class="btn_submit">結帳</button>
							    <input type="hidden" name="action" value="shoppingCart_order" />
							    <input type="hidden" name="loopIndex" value="${loop.index}" />
							    <input type="hidden" name="groupId_${loop.index}" value="${shoppingCartList[loop.index].groupId}" />
							</form>
							
							<form method="post" id="form_delete_${loop.index}" action="<%=request.getContextPath()%>/journey/user/shoppingCart.do">
							    <button type="button" class="btn_delete" onclick="deleteJourney(this, ${loop.index})">刪除</button>
							    <input type="hidden" name="action" value="shoppingCart_delete" />
							    <input type="hidden" name="loopIndex" value="${loop.index}" />
							    <input type="hidden" name="groupId_${loop.index}" value="${shoppingCartList[loop.index].groupId}" />
							</form>
							
						</td>	
					</tr>
				</c:forEach>
				
				
				<!-- 若購物車中無資料時，顯示訊息，並維持表格寬高 -->
				<c:if test="${empty tourGroupList}">
				    <tr>
				        <td colspan="6" style="text-align: center; padding-top: 50px; font-size: 20px; font-weight: 600; color: #CB997E;">您沒有收藏行程唷，趕快去逛逛吧~~</td>
				    </tr>
				    <tr>
				        <td colspan="6" style="text-align: center; padding-top: 30px; font-size: 20px; font-weight: 600; color: #CB997E;">溫馨提示: 建議您不用收藏，直接下單唷^_^</td>
				    </tr>
				</c:if>
				
				<tr>
					<td class="col-3 name_and_date">
						<span style="visibility: hidden;">陽明山一日遊111</span>
						<br>
						<span style="visibility: hidden;">113/01/01~113/01/01</span>
					</td>
					<td class="col-1 price">
						<span class="price" style="visibility: hidden;">$&nbsp;3000</span>
					</td>
					<td class="col-2 amount">
						<button class="amount_reduce" style="visibility: hidden;" onclick="reduce(this)"><b>-</b></button>
						<span class="count mx-3" style="visibility: hidden;">1</span>
						<button class="amount_plus" style="visibility: hidden;" onclick="plus(this)"><b>+</b></button>
					</td>
					<td class="col-1 total">
						<span style="visibility: hidden;">$&nbsp;3000</span>
					</td>
					<td class="col-2 signData">
						<span style="visibility: hidden;">已成團</span>
						<span style="visibility: hidden;">/</span>
						<span style="visibility: hidden;">剩餘名額 xx 名</span>
					</td>
					<td class="col-3 div_btn">
						<button type="submit" class="btn_submit" style="visibility: hidden;">結帳</button>
						<button type="button" class="btn_delete" style="visibility: hidden;" onclick="deleteJourney(this)">刪除</button>
					</td>
				</tr>
				
	        </tbody>
		</table>
	
	    <br><br>
	    <div class="btn_button">
	    	<button type="button" onclick="redirectToHome()">繼續逛逛</button>
	    </div>

    </main>
    
	<%@ include file="/components/html/footer.jsp" %>
	
<!--     <footer id="footer"></footer> -->

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
 	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script>
 		$(function () {
// 	        $("#header").load("../components/html/header.jsp");
// 	        $("#footer").load("../components/html/footer.jsp");
        });

 		function reduce(button, index, event) {
        	var countClass = "count_" + index;
        	var label = button.parentNode.querySelector("." + countClass);
            var value = parseInt(label.innerText);
            if (value > 1) {
                value--;
                label.innerText = value;
                document.getElementById("quantityChange_" + index).value = value;
                
                document.getElementById("form_quantity_" + index).submit();
            } else {
            	swal("報名人數", "最少1人", "info");
            	
                // 阻止表单提交
                event.preventDefault();
                
            	return false;
            }
        }
        
        function plus(button, index, event) {
        	var countClass = "count_" + index;
        	var label = button.parentNode.querySelector("." + countClass);
            var value = parseInt(label.innerText);
            if (value < 5) {
                value++;
                label.innerText = value;
                document.getElementById("quantityChange_" + index).value = value;
                
                document.getElementById("form_quantity_" + index).submit();
            } else {
            	swal("報名人數", "最多5人", "info");
            	
                // 阻止表单提交
                event.preventDefault();
                
            	return false;
            }
        }
        
        function deleteJourney(button, index) {
            document.getElementById("form_delete_" + index).submit();
            var row = button.closest("tr");
            if (row) {
                row.remove();
            }
        }
        
        var contextPath = "${pageContext.request.contextPath}";
        function redirectToHome() {
        	window.location.href = contextPath + "/foodscape/jsp/selectpage.jsp";
        }
    </script>
</body>

</html>