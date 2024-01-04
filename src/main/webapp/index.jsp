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
<%@ page import="com.lazytravel.journey.entity.*"%>
<%@ page import="com.lazytravel.journey.dao.*"%>
<%@ page import="java.util.Random"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Collections"%>
<%@ page import="java.util.Comparator"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>首頁</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<link rel="icon" href="<%=request.getContextPath()%>/static/images/logo.ico" type="image/x-icon">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<style>
	* {
		box-sizing: border-box;
	}
	
	body {
		margin: 0;
	}
	
	main#main {
		padding: 50px 80px;
	}
	
	
	label.popularJourney,
	label.popularPic,
	label.popularBlog {
		font-size: 24px;
		font-weight: 600;
		color: rgb(113, 120, 95);
		margin-bottom: 15px;
	}
	
	label.popularJourney,
	label.popularPic,
	label.popularBlog {
		margin-left: 13px;
	}
	
	hr:not([zzz]) {
		height: 1.5px;
		background: rgba(0, 0, 0, 0.863);
		box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.368);
	}
	
	div.div_popularJourney {
	    border: 1px solid rgba(67, 65, 65, 0.2);
	    padding: 35px;
	    border-radius: 5px;
	}
	
	div.forEachJourney {
		margin: 5px 0px 30px 0px;
	}
	
	.thumds {
		width: 18px;
	}
		
	button.btn_button {
		width: 150px;
		height: 35px;
		border-radius: 90px;
		border-color: transparent;
		color: white;
		background: #CCD5AE;
		margin-right: 20px;
		margin-top: 20px;
	}
	
	button.btn_button:active {
		outline: 2px solid #CCD5AE;
		box-shadow: 0 0 8px #a1a397
	}
	
	button.btn_submit {
		width: 100px;
		height: 30px;
		border-radius: 90px;
		border-color: transparent;
		color: white;
		background: #9C6644;
	}
	
	button.btn_submit:active {
		outline: 2px solid #9C6644;
		box-shadow: 0 0 8px #a1a397
	}
	
	button.btn_blog {
		width: 150px;
		height: 35px;
		border-radius: 90px;
		border-color: transparent;
		color: white;
		background: #6B705C;
		margin-top: 20px;
	}
	
	button.btn_blog:active {
		outline: 2px solid #6B705C;
		box-shadow: 0 0 8px #a1a397
	}
	


</style>
</head>

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
	
	
	JourneyService journeySvc = new JourneyServiceImpl();
	List<Journey> journeyList = journeySvc.getAllByStatusIsMarketed();
    // 根據購買次數降冪排序
    Collections.sort(journeyList, Comparator.comparing(Journey::getScoreCount).reversed());
    pageContext.setAttribute("journeyList", journeyList);
    
%>

<body>

	<%@ include file="/components/html/header.jsp" %>

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
				<div class="col-2 justify-content-end d-flex mt-3 align-items-end">
					<button type="button" class="btn_button" onclick="ToSelect()">搜尋 美食景點</button>
<!-- 					<button type="button" onclick="ToSelect()" style="width: 130px; background: #6B705C;" class="btn btn-success">搜尋美食/景點</button> -->
				</div>
			</div>
		</div>
		<br>
				
		
		<hr>
		<br>
		<label class="popularJourney">熱門行程</label>
		<div class="container">
			<div class="row">
				<c:forEach var="journey" items="${journeyList}" begin="0" end="2">
		            <div class="col-md-4 forEachJourney">
		            	<div class="jumbotron div_popularJourney" style="display: flex; flex-direction: column;">
		                    <p style="font-size: 24px; font-weight: 600; color: #CB997E;">${journey.journeyName}</p>
		                    <p style="font-weight: 600; color: rgb(152, 152, 152);">價格：NT$ ${journey.price} 元</p>
		                    <p>購買次數：${journey.scoreCount}</p>
					        
					        <form method="post" action="<%=request.getContextPath()%>/journey/user/journeySelect.do" style="align-self: flex-end;">
					        	<button type="submit" class="btn_submit">查看更多</button>
					            <input type="hidden" name="action" value="journeySelect_more">
					            <input type="hidden" name="loopIndex" value="${loop.index}" />
					            <input type="hidden" name="journeyId_${loop.index}" value="${journey.journeyId}">
					        </form>
		                </div>
		            </div>
		        </c:forEach>
			</div>
		</div>
		
		
		

        <hr>
        <br>
		<div class="container">
			<div class="row">
				<label class="popularBlog">最新文章</label>
				<p class="h4"></p>
			</div>
			<div class="row">
				<c:forEach var="blog" items="${randomThree}">

					<c:if test="${blog.blogStatus ne 0}">
						<div class="col-md-4" id="blogCard-${blog.blogId}">
							<div class="card" style="width: 22rem; height: 450px; margin: 10px;">
								<img class="card-img-top" src="<%=request.getContextPath()%>/blog/blog/BlogImgReader?blogId=${blog.blogId}" style="width: 351px; height: 160px;" />
								<div class="card-body p">
									<h5 class="card-title" style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${blog.title}</h5>
									<div class="d-inline-flex">
										<p class="h6"></p>
									</div>
									<div class="d-grid gap-2 d-md-flex justify-content-md-end"></div>
									<c:set var="formattedDate">
										<fmt:formatDate value="${blog.blogDate}" pattern="yyyy-MM-dd HH:mm" />
									</c:set>
									<p class="h6">${formattedDate}</p>
									<p class="card-text">${fn:substring(blog.content, 0, 20)}${fn:length(blog.content) > 20 ? '...' : ''}</p>
									<div class="d-flex justify-content-end align-items-center">
										<div class="col-md-4 p-0">
											<p>${blog.customer.nickname}</p>
										</div>
										<div class="col-md-4 p-0 top-50 start-50">
											<img class="card-img-top" src="<%=request.getContextPath()%>/customer/ImageReader?id=${blog.customer.customerId}" style="width: 70%; height: 80px;" alt="${blog.customer.nickname}" />
										</div>
										<div class="col-md-4 d-inline-flex align-items-center">

											<button type="button" class="blogLikeSubmit " data-blogid="${blog.blogId}" style="border: none; background: none; padding: 5px;">
												<c:choose>
													<c:when test="${not empty blog.blogLikes}">
														<c:set var="foundLike" value="false" />
														<c:forEach var="blogLike" items="${blog.blogLikes}">
															<c:if test="${blogLike.customer.customerId eq customer.customerId and blogLike.blogLikeStatus eq '1' and foundLike eq 'false'}">
																<!-- 顯示已收藏的圖示 -->
																<img src="static/blogimages/已讚.svg" id="updateblogLike" class="thumds m-0 blogLike" alt="讚" />
																<c:set var="foundLike" value="true" />
															</c:if>
														</c:forEach>
														<!-- 如果找不到收藏，顯示未收藏的圖示 -->
														<c:if test="${foundLike eq 'false'}">
															<img src="static/blogimages/未讚.svg" id="updateblogLike" class="thumds m-0 blogLike" alt="讚" />
														</c:if>
													</c:when>
													<c:otherwise>
														<!-- 顯示未收藏的圖示 -->
														<img src="static/blogimages/未讚.svg" id="updateblogLike" class="thumds m-0 blogLike" alt="讚" />
													</c:otherwise>
												</c:choose>
												<input type="hidden" id="blogId" value="${blog.blogId}" />
											</button>


											<button type="button" class="blogClSubmit P-1" data-blogid="${blog.blogId}" style="border: none; background: none; padding: 10px;">
												<c:choose>
													<c:when test="${not empty blog.blogCls}">
														<c:set var="foundFavorite" value="false" />
														<c:forEach var="blogCl" items="${blog.blogCls}">
															<c:if test="${blogCl.customer.customerId eq customer.customerId and blogCl.blogClStatus eq '1' and foundFavorite eq 'false'}">
																<!-- 顯示已收藏的圖示 -->
																<img src="static/blogimages/ON收藏.svg" id="updateblogCl" class="thumds m-0 blogCl" alt="收藏" />
																<c:set var="foundFavorite" value="true" />
															</c:if>
														</c:forEach>
														<!-- 如果找不到收藏，顯示未收藏的圖示 -->
														<c:if test="${foundFavorite eq 'false'}">
															<img src="static/blogimages/UN收藏.svg" id="updateblogCl" class="thumds m-0 blogCl" alt="收藏" />
														</c:if>
													</c:when>
													<c:otherwise>
														<!-- 顯示未收藏的圖示 -->
														<img src="static/blogimages/UN收藏.svg" id="updateblogCl" class="thumds m-0 blogCl" alt="收藏" />
													</c:otherwise>
												</c:choose>
												<input type="hidden" id="blogId" value="${blog.blogId}" />
											</button>

											<img src="static/blogimages/瀏覽數.svg " class="thumds" alt="view" />
											<p class="m-1 p-1">${blog.viewSum}</p>
										</div>
									</div>
									<form class=" row d-flex justify-content-end" METHOD="post" ACTION="blog/blog/blog.do" style="margin-bottom: 0px;">
										<input type="hidden" name="blogId" value="${blog.blogId}">
										<input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="查看更多" class="btn btn_submit m-1 p-1" style="background: #9C6644; border-color: transparent; color: white; width: 100px; height: 30px; border-radius: 90px;">
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
					<button type="button" class="btn_blog" onclick="ToBlogFirst()">查看所有文章</button>
<!-- 					<button type="button" onclick="ToBlogFirst()" style="width: 130px; background: #6B705C;" class="btn btn-success">來去文章列表</button> -->
				</div>
			</div>
		</div>


		
    </main>
    
    <%@ include file="/components/html/footer.jsp" %>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script>
    
	    var contextPath = "${pageContext.request.contextPath}";
	    function toFoodscapeSelectPage() {
	    	window.location.href = contextPath + "/foodscape/jsp/selectpage.jsp";
	    }    
    </script>
    
	<script>
		function ToSelect() {
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


	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script>
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