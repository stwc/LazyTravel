<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.lazytravel.order.dao.*"%>
<%@page import="com.lazytravel.order.entity.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>後台-優惠券新增</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/checkComplete.css">
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

<%

	Orders order = (Orders) request.getAttribute("order");
	List<Passenger> passengers = (List<Passenger>) request.getAttribute("passengers");

%>


<body>
    <header id="header"></header>
    <main class="main" class="p-3">
        <h3 class="mx-3 my-4">旅客明細</h3>

        <div class="card mx-5 my-5">
            <div class="card-header">
               修改旅客明細
            </div>
            <div class="card-body">
                <form method="post" action="passenger.do" id="passengerEditorForm">
                	<c:forEach var="passenger" items="${passengers}" varStatus="status">
                	<input type="hidden" name="passengerId" value="${passenger.passengerId}">
                	<div class="No my-3">
                        <label>旅客編號：</label>
                        <span class="ms-3" style="font-weight: 600">${status.index + 1}</span>
                    </div>

                    <div class="passengerName my-3">
                        <label>旅客姓名： </label>
                        <input class="ms-3" type="text" name="passengerName" value="${passenger.passengerName}" required>
                    </div>

                    <div class="idno my-3">
                        <label>身份證字號： </label>
                        <input type="text" name="idno" value="${passenger.idno}" required>
                    </div>

                    <div class="birth my-3">
                        <label>出生日期： </label>
                        <input class="ms-3" type="date" name="birth" value="${passenger.birth}" required>
                    </div>

                    <div class="phone my-3">
                        <label>連絡電話： </label>
                        <input class="ms-3" type="tel" name="phone" value="${passenger.phone}" required>
                    </div>
                	</c:forEach>

                    <div class="div_btn d-flex justify-content-end">
                    	<input type="hidden" name="action" value="update">
                    	<input type="hidden" name="orderId" value="${order.getOrderId()}">
                        <button type="submit" class="btn_submit mx-2">送出</button>
                        <button type="reset" class="btn_reset mx-2" onclick="goBack()" >取消</button>
                    </div>

                </form>


            </div>
        </div>

    </main>

    <footer id="footer"></footer>
    	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/select/1.7.0/js/dataTables.select.min.js"></script>




    <script>
        $(function () {
        	$("#header").load("../admin/header.jsp");
        });
		
        function goBack() {
            // 使用 history 物件返回上一頁
            window.history.go(-1);
        }
        


    </script>




</body>

</html>