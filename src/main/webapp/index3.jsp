<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.lazytravel.foodscape.controller.*"%>
<%@ page import="com.lazytravel.blog.entity.*"%>
<%@ page import="com.lazytravel.blog.dao.*"%>
<%@ page import="com.lazytravel.blog.service.*"%>
<%@ page import="com.lazytravel.customer.entity.*"%>
<%@page import="com.lazytravel.foodscape.dao.*"%>
<%@page import="com.lazytravel.foodscape.entity.*"%>
<%@page import="com.lazytravel.foodscape.service.*"%>
<%@ page import="java.util.Random" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Collections"%>

<%
BlogService blogSvc = new BlogServiceImpl();
BlogClService blogClSvc = new BlogClServiceImpl();
BlogLikeService blogLikeSvc = new BlogLikeServiceImpl();
// List<BlogCl> clList = blogClSvc.getAllBlogCls();
List<Blog> list = blogSvc.getAllBlogs();
Blog blog = (Blog) session.getAttribute("blog");

// pageContext.setAttribute("clList", clList);
Customer customer = (Customer) session.getAttribute("customer");
Integer customerId = (customer != null) ? customer.getCustomerId() : 0;


// 使用 Collections.shuffle 方法打亂資料集合
Collections.shuffle(list);

// 取得前三筆資料
List<Blog> randomThree = list.subList(0, Math.min(list.size(), 3));
pageContext.setAttribute("randomThree", randomThree);


FoodScape foodscape = (FoodScape) request.getAttribute("foodscape");
FoodScapeService foodscapeService = new FoodScapeServiceImpl();
List<FoodScape> pic = foodscapeService.getAllFoodScapes();
pageContext.setAttribute("pic", pic);


%>




<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>首頁</title>
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
.thumds {
	width: 18px;
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

	








<div class="container">
	<div class="row">
				<div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel" data-interval="1000">
    <div class="carousel-inner">
        <c:forEach var="foodpic" items="${pic}" varStatus="loop">
            <div class="carousel-item ${loop.first ? 'active' : ''}">
                <img src="<%=request.getContextPath()%>/FoodScapeImgReader?foodScapeId=${foodpic.foodScapeId}" class="d-block w-100" style="width: 500px; height: 400px;" alt="img">
            </div>
        </c:forEach>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>
	</div>
	
	<div class="row">
		<div class="col-10"></div>
			<div class="col-2 justify-content-end d-flex mt-3">
					<button type="button" onclick="ToSelect()"style="width: 130px;background: #6B705C;" class="btn btn-success">來去看看美食與景點吧</button>
				</div>
		
	</div>
</div>


    <br>
    <br>
    <br>
    <hr>

    <div class="container">
      </div>


    <br>
    <br>
    <br>
    
    <hr>
    <div class="container">
    <div class="row">
    <p class="h4">最新文章</p>
    </div>
			<div class="row">
				<c:forEach var="blog" items="${randomThree}">

					<c:if test="${blog.blogStatus ne 0}">
						<div class="col-md-4" id="blogCard-${blog.blogId}">
							<div class="card"
								style="width: 22rem; height: 450px; margin: 10px;">
								<img class="card-img-top"
									src="<%=request.getContextPath()%>/blog/blog/BlogImgReader?blogId=${blog.blogId}"
									style="width: 351px; height: 160px;" />
								<div class="card-body p">
									<h5 class="card-title"style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${blog.title}</h5>
									<div class="d-inline-flex">
										<p class="h6"></p>
									</div>
									<div class="d-grid gap-2 d-md-flex justify-content-md-end"></div>
									<c:set var="formattedDate">
										<fmt:formatDate value="${blog.blogDate}"
											pattern="yyyy-MM-dd HH:mm" />
									</c:set>
									<p class="h6">${formattedDate}</p>
									<p class="card-text">${fn:substring(blog.content, 0, 20)}${fn:length(blog.content) > 20 ? '...' : ''}</p>
									<div class="d-flex justify-content-end align-items-center">
										<div class="col-md-4 p-0">
											<p>${blog.customer.nickname}</p>
										</div>
										<div class="col-md-4 p-0 top-50 start-50">
											<img class="card-img-top"
												src="<%=request.getContextPath()%>/customer/ImageReader?id=${blog.customer.customerId}"
												style="width: 70%; height: 80px;"
												alt="${blog.customer.nickname}" />
										</div>
										<div class="col-md-4 d-inline-flex align-items-center">
										
										
										
										
										
										<button type="button" class="blogLikeSubmit "
												data-blogid="${blog.blogId}"
												style="border: none; background: none; padding: 5px;">
												<c:choose>
													<c:when test="${not empty blog.blogLikes}">
														<c:set var="foundLike" value="false" />
														<c:forEach var="blogLike" items="${blog.blogLikes}">
															<c:if
 																test="${blogLike.customer.customerId eq customer.customerId and blogLike.blogLikeStatus eq '1' and foundLike eq 'false'}"> 
<!-- 																顯示已收藏的圖示 -->
																<img src="static/blogimages/已讚.svg" id="updateblogLike" class="thumds m-0 blogLike"
												alt="讚" />
																<c:set var="foundLike" value="true" />
															</c:if>
														</c:forEach>
<!-- 														如果找不到收藏，顯示未收藏的圖示 -->
														<c:if test="${foundLike eq 'false'}">
															<img src="static/blogimages/未讚.svg" id="updateblogLike" class="thumds m-0 blogLike"
												alt="讚" />
														</c:if>
													</c:when>
													<c:otherwise>
<!-- 														顯示未收藏的圖示 -->
														<img src="static/blogimages/未讚.svg" id="updateblogLike" class="thumds m-0 blogLike" 
												alt="讚" />
													</c:otherwise>
												</c:choose>
												<input type="hidden" id="blogId" value="${blog.blogId}" />
											</button>
												
												
												
												
											<button type="button" class="blogClSubmit P-1"
												data-blogid="${blog.blogId}"
												style="border: none; background: none; padding: 10px;">
												<c:choose>
													<c:when test="${not empty blog.blogCls}">
														<c:set var="foundFavorite" value="false" />
														<c:forEach var="blogCl" items="${blog.blogCls}">
															<c:if
																test="${blogCl.customer.customerId eq customer.customerId and blogCl.blogClStatus eq '1' and foundFavorite eq 'false'}">
																<!-- 顯示已收藏的圖示 -->
																<img src="static/blogimages/ON收藏.svg"
																	id="updateblogCl" class="thumds m-0 blogCl" alt="收藏" />
																<c:set var="foundFavorite" value="true" />
															</c:if>
														</c:forEach>
														<!-- 如果找不到收藏，顯示未收藏的圖示 -->
														<c:if test="${foundFavorite eq 'false'}">
															<img src="static/blogimages/UN收藏.svg"
																id="updateblogCl" class="thumds m-0 blogCl" alt="收藏" />
														</c:if>
													</c:when>
													<c:otherwise>
														<!-- 顯示未收藏的圖示 -->
														<img src="static/blogimages/UN收藏.svg"
															id="updateblogCl" class="thumds m-0 blogCl" alt="收藏" />
													</c:otherwise>
												</c:choose>
												<input type="hidden" id="blogId" value="${blog.blogId}" />
											</button>

											<img src="static/blogimages/瀏覽數.svg " class="thumds "
												alt="view" />
											<p class="m-1 p-1">${blog.viewSum}</p>
										</div>
									</div>
									<form class=" row d-flex justify-content-end" METHOD="post"
										ACTION="blog/blog/blog.do" style="margin-bottom: 0px;">
										<input type="hidden" name="blogId" value="${blog.blogId}">
										<input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="看更多"
											class="btn btn-primary m-1 p-1"
											style="background: #9C6644; border-color: transparent; color: white; width: 100px; height: 30px">
									</form>
								</div>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>
			<div class="row">
			<div class="col-10"></div>
			<div class="col-2 justify-content-end d-flex">
					<button type="button" onclick="ToBlogFirst()"style="width: 130px;background: #6B705C;" class="btn btn-success">來去文章列表</button>
				</div>
				</div>
		</div>
   
    
  </main>

  <footer id="footer"></footer>


<script>



function ToSelect() {
    // 使用 window.location.href 將頁面導航到 myblog.jsp
    window.location.href ="<%=request.getContextPath()%>"+ '/foodscape/jsp/selectpage.jsp';
};
function ToBlogFirst() {
    // 使用 window.location.href 將頁面導航到 myblog.jsp
    window.location.href ="<%=request.getContextPath()%>"+ '/blog/blog/blogfirst.jsp';
};


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
                    var imageUrl = '/LazyTravel/foodscape/food/' + imagePath; // 替换为实际路径
                    $('#imageContainer').append('<img src="' + imageUrl + '" alt="Random Image">');
                });
            }
        });
    }

    // 每隔五秒调用一次loadRandomImages方法
    setInterval(loadRandomImages, 1500);

    // 页面加载时初始化一次
    loadRandomImages();
</script>

<script>


    function loadRandomImages() {
        $.ajax({
            url: '/LazyTravel/scapeLoader', // 替换为你的Servlet的URL
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                // 清空之前的图片
                $('#imageContainer1').empty();

                // 将新的图片添加到页面
                data.forEach(function (imagePath) {
                    var imageUrl = '/LazyTravel/foodscape/scape/' + imagePath; // 替换为实际路径
                    $('#imageContainer1').append('<img src="' + imageUrl + '" alt="Random Image">');
                });
            }
        });
    }

    // 每隔五秒调用一次loadRandomImages方法
    setInterval(loadRandomImages, 1500);

    // 页面加载时初始化一次
    loadRandomImages();
</script>



  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  <script>
    $(function () {
		$("#header").load("<%=request.getContextPath()%>/components/html/header.jsp");
		$("#footer").load("<%=request.getContextPath()%>/components/html/footer.jsp");
    });
    
    $(document).ready(function () {
		$(document).on('click', '.blogClSubmit', function (e){
			e.stopPropagation();
	    	var customerId = parseInt("<%=customerId%>", 10);
	    	var blogId = $(this).data('blogid');
		
	        // 檢查用戶是否已登入
	        if (customerId === 0 || customerId === "") {
	        	swal("請先登入才能收藏。", "", "warning");
	            
	            console.log(customerId);
	        } else {
	            // 使用 AJAX 將收藏的資訊傳送到後端
	            var dataToSend = {
	                "action" : "toggleFavorite",
	                blogId: $(this).data('blogid'),
	                customerId: customerId
	            };

	            $.ajax({
	                type: "POST",
	                url: "<%=request.getContextPath()%>/blog/blog/blog.do",
	                data: JSON.stringify(dataToSend),
	                contentType: "application/json; charset=utf-8",
	                dataType: "json",
	                success: function (response) {
	                	if (response.result === 'success') {
	                		var updateblogCl = $('#blogCard-' + blogId + ' .blogClSubmit .blogCl');
	                		console.log(updateblogCl)
	                		if (response.blogclstatus === '0' || response.blogclstatus === 'novalue') {
	                            updateblogCl.prop('src', "static/blogimages/ON收藏.svg");
	                        } else {
	                            updateblogCl.prop('src', "static/blogimages/UN收藏.svg");
	                        }
	                        // 處理成功的邏輯
	                        console.log('成功：', response);
	                    } else {
	                        // 處理失敗的邏輯
	                        console.error('錯誤：', response.error);
	                    }
	                }
//		                error: function (xhr, s, error) {
//		                    alert(xhr.status);
//		                    alert(xhr.readyState);
//		                }
	            });
	        }
	    });
	});

	
	$(document).ready(function () {
		$(document).on('click', '.blogLikeSubmit', function (e){
			e.stopPropagation();
	    	var customerId = parseInt("<%=customerId%>", 10);
	    	var blogId = $(this).data('blogid');
	    	console.log(updateblogLike);
		
	        // 檢查用戶是否已登入
	        if (customerId === 0 || customerId === "") {
	        	swal("請先登入才能按讚。", "", "warning");
	            console.log(customerId);
	        } else {
	            // 使用 AJAX 將收藏的資訊傳送到後端
	            var dataToSend = {
	                "action" : "toggleLike",
	                blogId: $(this).data('blogid'),
	                customerId: customerId
	            };

	            $.ajax({
	                type: "POST",
	                url: "<%=request.getContextPath()%>/blog/blog/blog.do",
	                data: JSON.stringify(dataToSend),
	                contentType: "application/json; charset=utf-8",
	                dataType: "json",
	                success: function (response) {
	                	if (response.result === 'success') {
	                		var updateblogLike = $('#blogCard-' + blogId + ' .blogLikeSubmit .blogLike');
	                		console.log(updateblogLike)
	                		if (response.blogLikeStatus === '0' || response.blogLikeStatus === 'novalue') {
	                            updateblogLike.prop('src', "static/blogimages/已讚.svg");
	                        } else {
	                            updateblogLike.prop('src', "static/blogimages/未讚.svg");
	                        }
	                        // 處理成功的邏輯
	                        console.log('成功：', response);
	                    } else {
	                        // 處理失敗的邏輯
	                        console.error('錯誤：', response.error);
	                    }
	                }
//		                error: function (xhr, s, error) {
//		                    alert(xhr.status);
//		                    alert(xhr.readyState);
//		                }
	            });
	        }
	    });
	});
  </script>
</body>

</html>