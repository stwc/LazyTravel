<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="com.lazytravel.foodscape.controller.*"%>

<%@ page import="java.util.Random" %>
<%@ include file="/components/html/header.jsp" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>首頁_進階搜尋</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">
<style>
  *{
    box-sizing: border-box;
}

body{
    margin: 0;
}

main#main{
    padding: 50px 80px;
}

input, textarea{
    border: 1px solid #CCD5AE;   
}

input:focus, textarea:focus{
    outline: 2px solid #CCD5AE;
    box-shadow: 0 0 8px #a1a397
}

select {
    border: 1px solid #CCD5AE;
    height: 27px;
}

select:focus {
    border:2px solid #CCD5AE;
    box-shadow: 0 0 8px #a1a397;
}


button.btn_submit{
    width: 100px;
    height: 30px;
    border-radius: 90px;
    border-color: transparent;
    color: white;
    background: #CCD5AE;
}

button.btn_submit:active {
    outline: 2px solid #CCD5AE;
    box-shadow: 0 0 8px #a1a397
}

button.btn_reset{
    width: 100px;
    height: 30px;
    border-radius: 90px;
    border-color: transparent;
    color: white;
    background: #9C6644;

    margin-right: 15px;
}

button.btn_reset1{
    width: 100px;
    height: 30px;
    border-radius: 90px;
    border-color: transparent;
    color: white;
    background: #6B705C;

    margin-right: 15px;
}

button.btn_reset:active {
    outline: 2px solid #9C6644;
    box-shadow: 0 0 8px #a1a397
}

button.btn_more{
    width: 100px;
    height: 30px;
    border-radius: 90px;
    border-color: transparent;
    color: white;
    background: #6B705C;
}

button.btn_more:active {
    outline: 2px solid #6B705C;
    box-shadow: 0 0 8px #a1a397
}

div.div_btn{
    display: flex;
    justify-content: flex-end;
}

hr:not([zzz]) {
    height: 1.5px;
    background: rgba(0, 0, 0, 0.863);
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.368);
}

div.list_group li{
    list-style: none;
    padding-left: 80px;
}

button.btn_x{
    width: 19px;
    height: 19px;
    line-height: 10px;
    border-radius: 90px;
    border-color: transparent;
    background-color: #CCCCCC;
    color: white;

    margin-left: 20px;
    margin-bottom: 5px;
    padding-left: 3.5px;
}

button.btn_x:active{
    outline: 2px solid #CCCCCC;
    box-shadow: 0 0 8px #a1a397
}

div.select_journey{
    margin: 20px 0px;
}

li.foodscape{
    box-sizing: border-box;
    background: rgba(217, 217, 217, 0.42);
    border-radius: 20px;
    list-style: none;
    border: 1px dotted rgba(109, 109, 109, 0.737);
    margin: 20px 0 0 -40px;
    padding: 20px 40px 20px 40px;
}

div.journey_name{
    margin-bottom: 10px;
}

div.journey_name span{
    margin-right: 5px;
}

span.journey_title {
    margin-right: 10px;
}

span.journey_title,
div.journey_store,
div.journey_price{
    /* border: 1px solid red; */
    display: inline-block;
    height: 132.5px;
}

/* div.journey_store div{
    border: 1px solid red;
} */

/* div.journey_store button{
    margin-top: 7px;
}

div.journey_price{
    display: flex;
    justify-content: flex-end;
}

div.journey_price_div{
    color: #787878;
    display: flex;
    justify-content: space-between;
}

span.journey_price_span{
    margin-left: auto;
    margin-right: 5px;
    margin-bottom: 10px;
} */



div.label span{
    border-radius: 90px;
    border-color: transparent;
    color: white;
    background: #6B705C;
    padding: 4px 7px 4px 18px;
    margin-right: 8px;

    white-space: nowrap;   
}

div.label{
    display: flex;
    flex-wrap: wrap;
    gap: 5px;     
}

button.btn_label_x{
    line-height: 10px;
    border-radius: 90px;
    border-color: transparent;
    color: white;
    background: #6B705C;

    padding-left: 15px;
}

button {
            border-radius: 20px; /* 设置按钮为半圆形 */
            margin: 5px; /* 设置按钮之间的间距 */
        }


        .active {
            background-color: #6B705C;
        }
  </style>
</head>

<body>
  <header id="header"></header>
  <main id="main">

    <button class="first_btn" onclick="changeColor('first_btn')" style="border-radius: 20px; border-bottom-right-radius: 0; border-bottom-left-radius: 0; transition: background-color 0.3s;">簡易搜尋</button>
    <button class="second_btn" onclick="changeColor('second_btn')" style="border-radius: 20px; border-bottom-right-radius: 0; border-bottom-left-radius: 0; transition: background-color 0.3s;">進階搜尋</button>
    <hr style="margin-top: -2px">

    <br>
    <div class="container">
      <div class="row">
          <div class="col-4  d-flex justify-content-start">
              <div class="row">
                  <label class="col-3 d-flex justify-content-end ">地點</label>
                  <input type="text" class="col-9">
              </div>
          </div>
          <div class="col-4 d-flex justify-content-start">
              <div class="row">
                  <label class="col-4">美食/景點</label>
                  <input type="text" class="col-8">
              </div>
          </div>
          <div class="col-4 d-flex justify-content-end">
              <div class="row">
                  <label class="col-3 d-flex justify-content-end">預算</label>
                  <input type="text" class="col-9">
              </div>
          </div>

      </div>
  </div>

  <br>
  <div class="label">
      <span>標籤AAAAA<button type="button" class="btn_label_x">x</button></span>
      <span>標籤BBBBB<button type="button" class="btn_label_x">x</button></span>
      <span>標籤CCCCC<button type="button" class="btn_label_x">x</button></span>
      <span>標籤DDDDD<button type="button" class="btn_label_x">x</button></span>
      <span>標籤EEEEE<button type="button" class="btn_label_x">x</button></span>
  </div>
      
  <br>
  <div class="div_btn">
      <button type="reset" class="btn_reset">重新篩選</button>
      <button type="submit" class="btn_submit">送出</button>
  </div>
  <hr>
  
  <div class="container">
    <div class="row align-items-center justify-content-between">
      <div class="col-3">
        <span style="font-size: 20px;">熱門行程</span>
        <span style="font-size: 20px; margin-left: 20px;">排行榜</span>
      </div>
      <button type="button" class="btn_reset1">查詢更多</button>
    </div>
  </div>
  
<br>
<br>
<br>

    <div class="p-3">
      <div class="container">
        <div class="row">
          <div class="col-lg">
            <div class="card shadow-lg">
              <img src="image/綠島行程.jpg" alt="Description of the image" class="card=img-top">
              <div class="card-body"></div>
              <span class="card-title" style="position: absolute; bottom: 10px; left: 30px;">綠島行程</span>
              <button class="btn_reset" style="position: absolute; bottom: 0; right: 0; ">查看詳情</button>
            </div>  
          </div>

          <div class="col-lg">
            <div class="card shadow-lg">
              <img src="image/綠島行程.jpg" alt="Description of the image" class="card=img-top">
              <div class="card-body"></div>
              <span class="card-title" style="position: absolute; bottom: 10px; left: 30px;">綠島行程</span>
              <button class="btn_reset" style="position: absolute; bottom: 0; right: 0;">查看詳情</button>
            </div>  
          </div>

          <div class="col-lg">
            <div class="card shadow-lg">
              <img src="image/綠島行程.jpg" alt="Description of the image" class="card=img-top">
              <div class="card-body"></div>
              <span class="card-title" style="position: absolute; bottom: 10px; left: 30px;">綠島行程</span>
              <button class="btn_reset" style="position: absolute; bottom: 0; right: 0;">查看詳情</button>
            </div>  
          </div>

          <div class="col-lg">
            <div class="card shadow-lg" style="position: relative;">
              <img src="image/綠島行程.jpg" alt="Description of the image" class="card-img-top">
              <div class="card-body"></div>
              <span class="card-title" style="position: absolute; bottom: 10px; left: 30px;">綠島行程</span>
              <button class="btn_reset" style="position: absolute; bottom: 0; right: 0px;">查看詳情</button>
            </div>  
          </div>
          

        </div>
      </div>
    </div>

    <hr>

    <div class="container">
      <div class="row align-items-center justify-content-between">
          <div class="col-3"style="font-size: 20px;">美食廣告大圖</div>
            <button type="button" class="btn_reset1">查詢更多</button>
          </div>
        </div>


    <br>
    <br>
    <br>
	<div id="imageContainer"></div>
    
<!--     <div class="container"> -->
<!--       <div class="row"> -->
<!--         <div class="col-4"> -->
<!--           <img src="image/螃蟹.jpg" alt="圖片描述" class="img-fluid" style="width: 100%; object-fit: cover;"> -->
<!--         </div> -->
<!--         <div class="col-4"> -->
<!--           <img src="image/鮭魚卵.jpg" alt="Description of 圖片" class="img-fluid" style="width: 100%; object-fit: cover;"> -->
<!--         </div> -->
<!--         <div class="col-4"> -->
<!--           <img src="image/舒芙蕾.jpg" alt="圖片描述" class="img-fluid" style="width: 100%; object-fit: cover;"> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
    
    
    
    <hr>

    <div class="container">
      <div class="row align-items-center justify-content-between">
          <div class="col-3"style="font-size: 20px;">景點廣告大圖</div>
            <button type="button" class="btn_reset1">查詢更多</button>
        </div>
      </div>


    <br>
    <br>
    <br>
	<div id="imageContainer"></div>
    
<!--     <div class="container"> -->
<!--       <div class="row"> -->
<!--         <div class="col-4"> -->
<!--           <img src="image/101.jpg" alt="圖片描述" class="img-fluid" style="width: 100%; object-fit: cover;"> -->
<!--         </div> -->
<!--         <div class="col-4"> -->
<!--           <img src="image/女王頭.jpg" alt="Description of 圖片" class="img-fluid" style="width: 100%; object-fit: cover;"> -->
<!--         </div> -->
<!--         <div class="col-4"> -->
<!--           <img src="image/教堂.jpg" alt="圖片描述" class="img-fluid" style="width: 100%; object-fit: cover;"> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
   
    
  </main>

  <footer id="footer"></footer>

  <script>
    function changeColor(buttonId) {
        var firstButton = document.querySelector('.first_btn');
        var secondButton = document.querySelector('.second_btn');

        // 设置按钮的样式
        firstButton.classList.remove("active");
        secondButton.classList.remove("active");

        var clickedButton = document.querySelector('.' + buttonId);
        clickedButton.classList.add("active");
    }
</script>

<script>
    function loadRandomImages() {
        $.ajax({
            url: '/LazyTravel/imageLoader', // 替换为你的Servlet的URL
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                // 清空之前的图片
                $('#imageContainer').empty();

                // 将新的图片添加到页面
                data.forEach(function (imagePath) {
                    var imageUrl = '/LazyTravel/foodscape/image/' + imagePath; // 替换为实际路径
                    $('#imageContainer').append('<img src="' + imageUrl + '" alt="Random Image">');
                });
            }
        });
    }

    // 每隔10秒调用一次loadRandomImages方法
    setInterval(loadRandomImages, 10000);

    // 页面加载时初始化一次
    loadRandomImages();
</script>


  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
  <script>
    $(function () {
      $("#header").load("../components/html/header.html");
      $("#footer").load("../components/html/footer.html");
    });
  </script>
</body>

</html>