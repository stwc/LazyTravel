<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.lazytravel.order.dao.*"%>
<%@page import="com.lazytravel.order.entity.*"%>

<!DOCTYPE html>
<html lang="en">


<%


%>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>會員中心-歷史訂單</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="//cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
  <script src="https://kit.fontawesome.com/cb6bf56872.js" crossorigin="anonymous"></script>
  <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">

  <style>

    
    button.btn-modify {
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
  
  button.btn-modify:hover {
     background-color: #804C33;
        background-color: #B07C56;
        /* Slightly lighter shade for hover */
        color: white;
  }
  
  button.btn-modify:active{
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

    <div class="card mx-5 my-5">
      <div class="card-header">
        訂單明細：
      </div>
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
      <form method="post" action="<%=request.getContextPath()%>/order/passenger.do">
        <button type="submit" class="btn-modify btn mx-3">修改旅客明細</button>
        <input class="passengermodify" type="hidden" name="orderId" value="" />
        <input type="hidden" name="action" value="passsengerDetail_modify" />  
      </form>
        <button class="btn-modify btn mx-3" onclick="goBack()">回上頁</button>
      </div>

    </div>





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
     const urlParams = new URLSearchParams(window.location.search);
     const orderId = urlParams.get('order_id');
     const orderNo = urlParams.get('order_no');
     const orderStatus = urlParams.get('order_status');
     const orderName = urlParams.get('order_name');
     const tourist = urlParams.get('tourist');
  
    $(function () {
    	$("#header").load("../admin/header.jsp");

      $("#orderNo").text(orderNo);
      $("#orderName").text(orderName);
      $("#tourist").text(tourist);

      if (orderStatus == 0) {
        $("#orderStatus").text('未付款')
      } else if (orderStatus == 1) {
        $("#orderStatus").text('已付款')
      } else if (orderStatus == 2) {
        $("#orderStatus").text('取消')
      }

      getJourByOrderId();
      getPasDetails();
      $(".passengermodify").val(orderId);

    });

    function cancelClick() {
      var button = document.getElementById('cancelButton');
      button.classList.add('clicked');
      window.location.href = '#';
    }

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
               	'</tr>';


          });
          $("table.table.mt-5 tbody").html(tbody);
        },
        error: function (xhr, status, error) {
          console.error("Error fetching passenger details:", status, error);
        }
      });
    }
    
    


    function goBack() {
      window.location.href = "orderList.jsp";
    }
    
    


  </script>




</body>

</html>