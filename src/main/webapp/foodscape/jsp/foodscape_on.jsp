<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.lazytravel.foodscape.dao.*"%>
<%@page import="com.lazytravel.foodscape.entity.*"%>

<%@ include file="/admin/header.html" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>後臺-美食/景點(新增修改)</title>

    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
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
            margin-left: 50px;
        }

        input{
            border: 1px solid #CCD5AE;   
        }

        input:focus{
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
            
            margin-right: 15px;
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
        }

        button.btn_reset:active {
            outline: 2px solid #9C6644;
            box-shadow: 0 0 8px #a1a397
        }

        div.div_btn{
            display: flex;
            justify-content: flex-end;
        }



    </style>
</head>
<body>
    <div id="header"></div>


    <main id="main">
        <p style="font-size: 20px;"><b>新增或修改美食/景點</b></p>
    
        <br>
    
        <div class="div_foodscapeId">
            <label>美食/景點ID :</label>
            <input type="number">
        </div>
    
        <br>
    
        <div class="div_foodScape_name">
            <label>美食/景點名稱 :</label>
            <input type="text" id="div_foodScape_name" name="div_foodScape_name">
        </div>
        <br>
    
        <div class="div_foodscape_phone">
            <label>電話 :</label>
            <input type="number">
        </div>
    
        <br>
        <div class="div_foodscape_place">
            <label>地點 :</label>
            
            <label>縣市 :</label>
            <select id="div_foodscape_place" name="div_foodscape_place">
                <option value="taipei">台北</option>
                <option value="new taipei">新北</option>
                <option value="taichun">台中</option>
                <option value="tainana">台南</option>
                <option value="kaohsiung">高雄</option>
            </select>
            
            <label>地址 :</label>
            <input type="text" name="div_foodscape_place">
        </div>
        
     

        <br>

        <label for="opening-time">星期一營業時間：</label>

<!-- 開始時間選擇框 -->
<select id="opening-time" name="opening-time-start">
    <option value="00:00">00:00</option>
    <option value="01:00">01:00</option>
    <option value="02:00">02:00</option>
    <option value="03:00">03:00</option>
    <option value="04:00">04:00</option>
    <option value="05:00">05:00</option>
    <option value="06:00">06:00</option>
    <option value="07:00">07:00</option>
    <option value="08:00">08:00</option>  
    <option value="09:00">09:00</option>
    <option value="10:00">10:00</option>
    <option value="11:00">11:00</option>
    <option value="12:00">12:00</option>
    <option value="13:00">13:00</option>
    <option value="14:00">14:00</option>
    <option value="15:00">15:00</option>
    <option value="16:00">16:00</option>
    <option value="17:00">17:00</option>
    <option value="18:00">18:00</option>
    <option value="19:00">19:00</option>
    <option value="20:00">20:00</option>
    <option value="21:00">21:00</option>
    <option value="22:00">22:00</option>
    <option value="23:00">23:00</option>
    <option value="公休">公休</option>
    
      <!-- 其他時間選項 -->
</select>

<span> 到 </span>


<!-- 結束時間選擇框 -->
<select id="closing-time" name="closing-time-end">
    <option value="00:00">00:00</option>
    <option value="01:00">01:00</option>
    <option value="02:00">02:00</option>
    <option value="03:00">03:00</option>
    <option value="04:00">04:00</option>
    <option value="05:00">05:00</option>
    <option value="06:00">06:00</option>
    <option value="07:00">07:00</option>
    <option value="08:00">08:00</option>  
    <option value="09:00">09:00</option>
    <option value="10:00">10:00</option>
    <option value="11:00">11:00</option>
    <option value="12:00">12:00</option>
    <option value="13:00">13:00</option>
    <option value="14:00">14:00</option>
    <option value="15:00">15:00</option>
    <option value="16:00">16:00</option>
    <option value="17:00">17:00</option>
    <option value="18:00">18:00</option>
    <option value="19:00">19:00</option>
    <option value="20:00">20:00</option>
    <option value="21:00">21:00</option>
    <option value="22:00">22:00</option>
    <option value="23:00">23:00</option>
    <!-- 其他時間選項 -->
  </select>
<br>

  <label for="opening-time">星期二營業時間：</label>

<select id="opening-time" name="opening-time-start">
    <option value="00:00">00:00</option>
    <option value="01:00">01:00</option>
    <option value="02:00">02:00</option>
    <option value="03:00">03:00</option>
    <option value="04:00">04:00</option>
    <option value="05:00">05:00</option>
    <option value="06:00">06:00</option>
    <option value="07:00">07:00</option>
    <option value="08:00">08:00</option>  
    <option value="09:00">09:00</option>
    <option value="10:00">10:00</option>
    <option value="11:00">11:00</option>
    <option value="12:00">12:00</option>
    <option value="13:00">13:00</option>
    <option value="14:00">14:00</option>
    <option value="15:00">15:00</option>
    <option value="16:00">16:00</option>
    <option value="17:00">17:00</option>
    <option value="18:00">18:00</option>
    <option value="19:00">19:00</option>
    <option value="20:00">20:00</option>
    <option value="21:00">21:00</option>
    <option value="22:00">22:00</option>
    <option value="23:00">23:00</option>
    <option value="公休">公休</option>   
</select>

<span> 到 </span>

<select id="closing-time" name="closing-time-end">
    <option value="00:00">00:00</option>
    <option value="01:00">01:00</option>
    <option value="02:00">02:00</option>
    <option value="03:00">03:00</option>
    <option value="04:00">04:00</option>
    <option value="05:00">05:00</option>
    <option value="06:00">06:00</option>
    <option value="07:00">07:00</option>
    <option value="08:00">08:00</option>  
    <option value="09:00">09:00</option>
    <option value="10:00">10:00</option>
    <option value="11:00">11:00</option>
    <option value="12:00">12:00</option>
    <option value="13:00">13:00</option>
    <option value="14:00">14:00</option>
    <option value="15:00">15:00</option>
    <option value="16:00">16:00</option>
    <option value="17:00">17:00</option>
    <option value="18:00">18:00</option>
    <option value="19:00">19:00</option>
    <option value="20:00">20:00</option>
    <option value="21:00">21:00</option>
    <option value="22:00">22:00</option>
    <option value="23:00">23:00</option>

  </select>
  <br>

  <label for="opening-time">星期三營業時間：</label>

<select id="opening-time" name="opening-time-start">
    <option value="00:00">00:00</option>
    <option value="01:00">01:00</option>
    <option value="02:00">02:00</option>
    <option value="03:00">03:00</option>
    <option value="04:00">04:00</option>
    <option value="05:00">05:00</option>
    <option value="06:00">06:00</option>
    <option value="07:00">07:00</option>
    <option value="08:00">08:00</option>  
    <option value="09:00">09:00</option>
    <option value="10:00">10:00</option>
    <option value="11:00">11:00</option>
    <option value="12:00">12:00</option>
    <option value="13:00">13:00</option>
    <option value="14:00">14:00</option>
    <option value="15:00">15:00</option>
    <option value="16:00">16:00</option>
    <option value="17:00">17:00</option>
    <option value="18:00">18:00</option>
    <option value="19:00">19:00</option>
    <option value="20:00">20:00</option>
    <option value="21:00">21:00</option>
    <option value="22:00">22:00</option>
    <option value="23:00">23:00</option>
    <option value="公休">公休</option>   
</select>

<span> 到 </span>

<select id="closing-time" name="closing-time-end">
    <option value="00:00">00:00</option>
    <option value="01:00">01:00</option>
    <option value="02:00">02:00</option>
    <option value="03:00">03:00</option>
    <option value="04:00">04:00</option>
    <option value="05:00">05:00</option>
    <option value="06:00">06:00</option>
    <option value="07:00">07:00</option>
    <option value="08:00">08:00</option>  
    <option value="09:00">09:00</option>
    <option value="10:00">10:00</option>
    <option value="11:00">11:00</option>
    <option value="12:00">12:00</option>
    <option value="13:00">13:00</option>
    <option value="14:00">14:00</option>
    <option value="15:00">15:00</option>
    <option value="16:00">16:00</option>
    <option value="17:00">17:00</option>
    <option value="18:00">18:00</option>
    <option value="19:00">19:00</option>
    <option value="20:00">20:00</option>
    <option value="21:00">21:00</option>
    <option value="22:00">22:00</option>
    <option value="23:00">23:00</option>
  </select>
  <br>

  <label for="opening-time">星期四營業時間：</label>

  <select id="opening-time" name="opening-time-start">
      <option value="00:00">00:00</option>
      <option value="01:00">01:00</option>
      <option value="02:00">02:00</option>
      <option value="03:00">03:00</option>
      <option value="04:00">04:00</option>
      <option value="05:00">05:00</option>
      <option value="06:00">06:00</option>
      <option value="07:00">07:00</option>
      <option value="08:00">08:00</option>  
      <option value="09:00">09:00</option>
      <option value="10:00">10:00</option>
      <option value="11:00">11:00</option>
      <option value="12:00">12:00</option>
      <option value="13:00">13:00</option>
      <option value="14:00">14:00</option>
      <option value="15:00">15:00</option>
      <option value="16:00">16:00</option>
      <option value="17:00">17:00</option>
      <option value="18:00">18:00</option>
      <option value="19:00">19:00</option>
      <option value="20:00">20:00</option>
      <option value="21:00">21:00</option>
      <option value="22:00">22:00</option>
      <option value="23:00">23:00</option>
      <option value="公休">公休</option>   
  </select>
  
  <span> 到 </span>
  
  <select id="closing-time" name="closing-time-end">
      <option value="00:00">00:00</option>
      <option value="01:00">01:00</option>
      <option value="02:00">02:00</option>
      <option value="03:00">03:00</option>
      <option value="04:00">04:00</option>
      <option value="05:00">05:00</option>
      <option value="06:00">06:00</option>
      <option value="07:00">07:00</option>
      <option value="08:00">08:00</option>  
      <option value="09:00">09:00</option>
      <option value="10:00">10:00</option>
      <option value="11:00">11:00</option>
      <option value="12:00">12:00</option>
      <option value="13:00">13:00</option>
      <option value="14:00">14:00</option>
      <option value="15:00">15:00</option>
      <option value="16:00">16:00</option>
      <option value="17:00">17:00</option>
      <option value="18:00">18:00</option>
      <option value="19:00">19:00</option>
      <option value="20:00">20:00</option>
      <option value="21:00">21:00</option>
      <option value="22:00">22:00</option>
      <option value="23:00">23:00</option>
    </select>
    <br>

    <label for="opening-time">星期五營業時間：</label>

  <select id="opening-time" name="opening-time-start">
      <option value="00:00">00:00</option>
      <option value="01:00">01:00</option>
      <option value="02:00">02:00</option>
      <option value="03:00">03:00</option>
      <option value="04:00">04:00</option>
      <option value="05:00">05:00</option>
      <option value="06:00">06:00</option>
      <option value="07:00">07:00</option>
      <option value="08:00">08:00</option>  
      <option value="09:00">09:00</option>
      <option value="10:00">10:00</option>
      <option value="11:00">11:00</option>
      <option value="12:00">12:00</option>
      <option value="13:00">13:00</option>
      <option value="14:00">14:00</option>
      <option value="15:00">15:00</option>
      <option value="16:00">16:00</option>
      <option value="17:00">17:00</option>
      <option value="18:00">18:00</option>
      <option value="19:00">19:00</option>
      <option value="20:00">20:00</option>
      <option value="21:00">21:00</option>
      <option value="22:00">22:00</option>
      <option value="23:00">23:00</option>
      <option value="公休">公休</option>   
  </select>
  
  <span> 到 </span>
  
  <select id="closing-time" name="closing-time-end">
      <option value="00:00">00:00</option>
      <option value="01:00">01:00</option>
      <option value="02:00">02:00</option>
      <option value="03:00">03:00</option>
      <option value="04:00">04:00</option>
      <option value="05:00">05:00</option>
      <option value="06:00">06:00</option>
      <option value="07:00">07:00</option>
      <option value="08:00">08:00</option>  
      <option value="09:00">09:00</option>
      <option value="10:00">10:00</option>
      <option value="11:00">11:00</option>
      <option value="12:00">12:00</option>
      <option value="13:00">13:00</option>
      <option value="14:00">14:00</option>
      <option value="15:00">15:00</option>
      <option value="16:00">16:00</option>
      <option value="17:00">17:00</option>
      <option value="18:00">18:00</option>
      <option value="19:00">19:00</option>
      <option value="20:00">20:00</option>
      <option value="21:00">21:00</option>
      <option value="22:00">22:00</option>
      <option value="23:00">23:00</option>
    </select>
    <br>
    
    <label for="opening-time">星期六營業時間：</label>

    <select id="opening-time" name="opening-time-start">
        <option value="00:00">00:00</option>
        <option value="01:00">01:00</option>
        <option value="02:00">02:00</option>
        <option value="03:00">03:00</option>
        <option value="04:00">04:00</option>
        <option value="05:00">05:00</option>
        <option value="06:00">06:00</option>
        <option value="07:00">07:00</option>
        <option value="08:00">08:00</option>  
        <option value="09:00">09:00</option>
        <option value="10:00">10:00</option>
        <option value="11:00">11:00</option>
        <option value="12:00">12:00</option>
        <option value="13:00">13:00</option>
        <option value="14:00">14:00</option>
        <option value="15:00">15:00</option>
        <option value="16:00">16:00</option>
        <option value="17:00">17:00</option>
        <option value="18:00">18:00</option>
        <option value="19:00">19:00</option>
        <option value="20:00">20:00</option>
        <option value="21:00">21:00</option>
        <option value="22:00">22:00</option>
        <option value="23:00">23:00</option>
        <option value="公休">公休</option>   
    </select>
    
    <span> 到 </span>
    
    <select id="closing-time" name="closing-time-end">
        <option value="00:00">00:00</option>
        <option value="01:00">01:00</option>
        <option value="02:00">02:00</option>
        <option value="03:00">03:00</option>
        <option value="04:00">04:00</option>
        <option value="05:00">05:00</option>
        <option value="06:00">06:00</option>
        <option value="07:00">07:00</option>
        <option value="08:00">08:00</option>  
        <option value="09:00">09:00</option>
        <option value="10:00">10:00</option>
        <option value="11:00">11:00</option>
        <option value="12:00">12:00</option>
        <option value="13:00">13:00</option>
        <option value="14:00">14:00</option>
        <option value="15:00">15:00</option>
        <option value="16:00">16:00</option>
        <option value="17:00">17:00</option>
        <option value="18:00">18:00</option>
        <option value="19:00">19:00</option>
        <option value="20:00">20:00</option>
        <option value="21:00">21:00</option>
        <option value="22:00">22:00</option>
        <option value="23:00">23:00</option>
      </select>
      <br>

      <label for="opening-time">星期日營業時間：</label>

      <select id="opening-time" name="opening-time-start">
          <option value="00:00">00:00</option>
          <option value="01:00">01:00</option>
          <option value="02:00">02:00</option>
          <option value="03:00">03:00</option>
          <option value="04:00">04:00</option>
          <option value="05:00">05:00</option>
          <option value="06:00">06:00</option>
          <option value="07:00">07:00</option>
          <option value="08:00">08:00</option>  
          <option value="09:00">09:00</option>
          <option value="10:00">10:00</option>
          <option value="11:00">11:00</option>
          <option value="12:00">12:00</option>
          <option value="13:00">13:00</option>
          <option value="14:00">14:00</option>
          <option value="15:00">15:00</option>
          <option value="16:00">16:00</option>
          <option value="17:00">17:00</option>
          <option value="18:00">18:00</option>
          <option value="19:00">19:00</option>
          <option value="20:00">20:00</option>
          <option value="21:00">21:00</option>
          <option value="22:00">22:00</option>
          <option value="23:00">23:00</option>
          <option value="公休">公休</option>   
      </select>
      
      <span> 到 </span>
      
      <select id="closing-time" name="closing-time-end">
          <option value="00:00">00:00</option>
          <option value="01:00">01:00</option>
          <option value="02:00">02:00</option>
          <option value="03:00">03:00</option>
          <option value="04:00">04:00</option>
          <option value="05:00">05:00</option>
          <option value="06:00">06:00</option>
          <option value="07:00">07:00</option>
          <option value="08:00">08:00</option>  
          <option value="09:00">09:00</option>
          <option value="10:00">10:00</option>
          <option value="11:00">11:00</option>
          <option value="12:00">12:00</option>
          <option value="13:00">13:00</option>
          <option value="14:00">14:00</option>
          <option value="15:00">15:00</option>
          <option value="16:00">16:00</option>
          <option value="17:00">17:00</option>
          <option value="18:00">18:00</option>
          <option value="19:00">19:00</option>
          <option value="20:00">20:00</option>
          <option value="21:00">21:00</option>
          <option value="22:00">22:00</option>
          <option value="23:00">23:00</option>
        </select>
        <br>
        <div class="div_category">
            <label>類別 :</label>
            <select id="div_category" name="div_category">
                <option value="food">美食</option>
                <option value="scape">景點</option>
            </select>

            <label>標籤 :</label>
            <select id="mark" name="mark">
                <option value="japan">日式</option>
                <option value="taiwan">台式</option>
                <option value="america">美式</option>
            </select> 
        </div>

        <form action="/upload" method="post" enctype="multipart/form-data">
            <label for="file-upload">選擇圖片：</label>
            <input type="file" id="file-upload" name="image" accept="image/*">
            <input type="submit" value="上傳">
          </form>

          <label for="large-text">相關介紹：</label>
            <textarea id="large-text" name="large-text" rows="4" cols="88">
            </textarea>

            <div class="div_status">
                <label>狀態 :</label>
                <select id="div_status" name="div_status">
                    <option value="已上架" selected>已上架</option>
                    <option value="未上架">未上架</option>
                </select>
            </div>

        <br>
        <div class="div_btn">
            <button type="submit" class="btn_submit">送出</button>
            <button type="reset" class="btn_reset">取消</button>
        </div>


    </main>

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
    <script>
        $(function () {
            $("#header").load("header.html");
            new DataTable('#example');
        });
    </script>
</body>
  
</html>