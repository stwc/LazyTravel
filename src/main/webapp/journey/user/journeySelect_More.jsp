<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/components/html/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>篩選行程-下單</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

<style>
	* {
		box-sizing: border-box;
	}
	
	body {
		margin: 0;
	}
	
	main#main {
		padding: 50px 80px;
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		width: 100%;
	}
	
	article {
		/* border: 1px solid red; */
		flex: 1;
	}
	
	section {
		/* border: 1px solid red; */
		flex: 100%;
	}
	
	@media screen and (max-width: 768px) {
		#main {
			flex-direction: column;
		}
		article {
			flex: none; /* 去除等分佈局 */
			width: 100%; /* 寬度 100% */
		}
	}
	
	div.journey_name {
		margin-bottom: 15px;
		font-size: 20px;
	}
	
	table.journey_detail {
		border-collapse: separate;
		border-spacing: 10px;
		width: 100%;
	}
	
	table.journey_detail td {
		vertical-align: top; /* 文字垂直置頂 */
	}
	
	button.btn_more {
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
	
	div.score {
		margin-left: 10px;
	}
	
	div.score div {
		border: 1px solid black;
		width: 320px;
		margin-top: 8px;
		margin-bottom: 8px;
	}
	
	div.journey_price_div {
		color: #787878;
		margin-left: 10px;
	}
	
	span.journey_price_span {
		margin-left: 40px;
	}
	
	section {
		margin-top: 50px;
	}
	
	button.btn_submit {
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
	
	button.btn_reset {
		width: 100px;
		height: 30px;
		border-radius: 90px;
		border-color: transparent;
		color: white;
		background: #9C6644;
		margin-right: 15px;
	}
	
	button.btn_reset:active {
		outline: 2px solid #9C6644;
		box-shadow: 0 0 8px #a1a397
	}
	
	button.btn_addCart {
		width: 100px;
		height: 30px;
		border-radius: 90px;
		border-color: transparent;
		color: white;
		background: #6B705C;
		margin-right: 15px;
	}
	
	button.btn_addCart:active {
		outline: 2px solid #6B705C;
		box-shadow: 0 0 8px #a1a397
	}
	
	table.wantToOrder {
		border-collapse: separate;
		border-spacing: 7px;
	}
	
	table.wantToOrder td {
		padding-right: 25px;
	}
	
	div.nth_days b {
		margin-left: 10px;
		margin-right: 15px;
	}
	
	div.nth_days i {
		margin-right: 5px;
	}
	
	div.nth_days i:hover {
		border-radius: 90px;
		background-color: #1a1919;
		color: white;
		box-shadow: 0 0 8px #a1a397
	}
</style>


<body>

<!--     <header id="header"></header> -->

    <main id="main">
        <article>
            <div class="journey_name">
                <span>行程名稱</span>
                <span>陽明山一日遊</span>
            </div>
            <br>
            
            <div class="nth_days">
                <b>第 1 天</b>
                <i class="fa-solid fa-circle-chevron-left"></i>
                <i class="fa-solid fa-circle-chevron-right"></i>
            </div>
            <hr>


            <table class="journey_detail">
                <tr>
                    <th>時間</th>
                    <th>美食景點</th>
                    <th>地址</th>
                </tr>
                <tr>
                    <td>08:00~10:00</td>
                    <td>AAAAA店家</td>
                    <td>104台北市中山區南京東路三段219號</td>
                </tr>
                <tr>
                    <td>11:00~13:00</td>
                    <td>BBBBB店家</td>
                    <td>104台北市中山區南京東路三段219號號號號號號號</td>
                </tr>
                <tr>
                    <td>14:00~16:00</td>
                    <td>CCCCC店家</td>
                    <td>104台北市中山區南京東路三段219號</td>
                </tr>
            </table>

            <br>
            <br>
            <div class="score">
                <label>最新評價</label>
                <div>......</div>
                <button type="button" class="btn_more">查看更多</button>
            </div>

            <br>
            <br>
            <div class="journey_price_div">
                <span>金額</span>
                <span class="journey_price_span">NT$420</span>
            </div>

        </article>

        <article>
            地圖
        </article>
        

        <section>
            <hr>
            <br>
            <table class="wantToOrder">
                <tr>
                    <td>出發日期</td>
                    <td>
                        <input type="date" name="" id="">
                    </td>
                </tr>
                <tr>
                    <td>旅遊人數</td>
                    <td>
                        <select>
                            <option value="0" selected>請選擇人數</option>
                            <option value="1">1人</option>
                            <option value="2">2人</option>
                            <option value="3">3人</option>
                            <option value="4">4人</option>
                            <option value="5">5人</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>已報名人數</td>
                    <td>10人</td>
                </tr>
                <tr>
                    <td>成團人數</td>
                    <td>20人</td>
                </tr>
                <tr>
                    <td>人數上限</td>
                    <td>50人</td>
                </tr>
            </table>

            <br>
            <div>
                <button type="reset" class="btn_reset" onclick="redirectToJourneySelect()">取消</button>
                <button type="button" class="btn_addCart">加入購物車</button>
                <button type="submit" class="btn_submit">前往結帳</button>
            </div>


        </section>
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


        function redirectToJourneySelect() {
            window.location.href = "./journey_select.html";
        }
    </script>

    

</body>
</html>


<body>

</body>
</html>