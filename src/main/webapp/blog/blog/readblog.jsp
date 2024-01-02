<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.blog.entity.*"%>
<%@ page import="com.lazytravel.blog.dao.*"%>
<%@ page import="com.lazytravel.blog.service.*"%>
<%@ page import="com.lazytravel.customer.entity.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.Set"%>


<%
BlogService blogSvc = new BlogServiceImpl();
BlogClService blogClSvc = new BlogClServiceImpl();
BlogLikeService blogLikeSvc = new BlogLikeServiceImpl();
Blog blog = (Blog) request.getAttribute("blog");
BlogMsgService blogMsgSvc = new BlogMsgServiceImpl();
Customer customer = (Customer) session.getAttribute("customer");
Integer customerId = (customer != null) ? customer.getCustomerId() : 0;
List<BlogMsg> list = blogMsgSvc.getBlogMsgsByBlogId(blog.getBlogId());
pageContext.setAttribute("list", list);


// Blog blog = (Blog) request.getAttribute("blog");
// BlogService blogSvc = new BlogServiceImpl();
// List<Blog> list = blogSvc.getAllBlogs();
%>

<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous" />
<link rel="icon" href="../../static/images/logo.ico" type="image/x-icon" />
<style>
/* .row > div,
      .check {
        background: #ccc;
        border: 2px solid #aaa;
        margin-bottom: 5px;
      }  */
/* .title {
        color: #6b705c;
        font-feature-settings: "clig" off, "liga" off;
        letter-spacing: -1.1px;
        font: 700 55px/66px Inter, sans-serif;
      } */
.thumds {
	width: 18px;
}
</style>
</head>
<body>
	<header id="header"></header>
	<main>
		<div class="container">
			<div class="row">
				<div class="col my-2">
					<img class="card-img-top"
						src="<%=request.getContextPath()%>/blog/blog/BlogImgReader?blogId=${blog.blogId}"
						style="width: 80%; height: 80%;" />
				</div>
			</div>
			<div class="row">
				<div class="col">
					<h5 class="display-6 lead my-2">
						<strong>${blog.title}</strong>
					</h5>
				</div>
				<div id="status-${blog.blogId}"
					class="col-4 d-inline-flex justify-content-center align-items-center">
					
					
					<button type="button" class="blogLikeSubmit P-1"
												data-blogid="${blog.blogId}"
												style="border: none; background: none; padding: 0;">
												<c:choose>
													<c:when test="${not empty blog.blogLikes}">
														<c:set var="foundLike" value="false" />
														<c:forEach var="blogLike" items="${blog.blogLikes}">
															<c:if
 																test="${blogLike.customer.customerId eq customer.customerId and blogLike.blogLikeStatus eq '1' and foundLike eq 'false'}"> 
<!-- 																顯示已收藏的圖示 -->
																<img src="../../static/blogimages/已讚.svg" id="updateblogLike" class="thumds m-0 blogLike"
												alt="讚" />
																<c:set var="foundLike" value="true" />
															</c:if>
														</c:forEach>
<!-- 														如果找不到收藏，顯示未收藏的圖示 -->
														<c:if test="${foundLike eq 'false'}">
															<img src="../../static/blogimages/未讚.svg" id="updateblogLike" class="thumds m-0 blogLike"
												alt="讚" />
														</c:if>
													</c:when>
													<c:otherwise>
<!-- 														顯示未收藏的圖示 -->
														<img src="../../static/blogimages/未讚.svg" id="updateblogLike" class="thumds m-0 blogLike" 
												alt="讚" />
													</c:otherwise>
												</c:choose>
												<input type="hidden" id="blogId" value="${blog.blogId}" />
											</button>
					
					
					<button type="button" class="blogClSubmit P-1"
												data-blogid="${blog.blogId}"
												style="border: none; background: none; padding: 0;">
												<c:choose>
													<c:when test="${not empty blog.blogCls}">
														<c:set var="foundFavorite" value="false" />
														<c:forEach var="blogCl" items="${blog.blogCls}">
															<c:if
																test="${blogCl.customer.customerId eq customer.customerId and blogCl.blogClStatus eq '1' and foundFavorite eq 'false'}">
																<!-- 顯示已收藏的圖示 -->
																<img src="../../static/blogimages/ON收藏.svg"
																	id="updateblogCl" class="thumds m-0 blogCl" alt="收藏" />
																<c:set var="foundFavorite" value="true" />
															</c:if>
														</c:forEach>
														<!-- 如果找不到收藏，顯示未收藏的圖示 -->
														<c:if test="${foundFavorite eq 'false'}">
															<img src="../../static/blogimages/UN收藏.svg"
																id="updateblogCl" class="thumds m-0 blogCl" alt="收藏" />
														</c:if>
													</c:when>
													<c:otherwise>
														<!-- 顯示未收藏的圖示 -->
														<img src="../../static/blogimages/UN收藏.svg"
															id="updateblogCl" class="thumds m-0 blogCl" alt="收藏" />
													</c:otherwise>
												</c:choose>
												<input type="hidden" id="blogId" value="${blog.blogId}" />
											</button>
					
					
					
					<img src="../../static/blogimages/瀏覽數.svg " class="thumds " alt="" />
					<p class="m-1 p-1">${blog.viewSum}</p>
				</div>
			</div>
			<!--         <div class="row-cols-auto d-inline-flex d-flex justify-content-center"> -->
			<div class="col-7">
				<c:set var="formattedDate">
					<fmt:formatDate value="${blog.blogDate}" pattern="yyyy-MM-dd HH:mm" />
				</c:set>
				<p class="h6">${formattedDate}</p>
			</div>

			<div
				class="col d-inline-flex justify-content-center align-items-center">
				<p class="m-0" style="width: 60px;">${blog.customer.nickname}</p>
				<img class="card-img-top"
					src="<%=request.getContextPath()%>/customer/ImageReader?id=${blog.customer.customerId}"
					style="width: 100px; height: 100px"
					alt="${blog.customer.customerName}" />
			</div>
			<hr />
			<div class="row">
				<div class="col">
					<p class="h6 my-3">${blog.content}</p>
				</div>
			</div>

			<hr />
			<div class="row">
				<div class="col">
					<p class="h1 my-5">
						<strong>留言區</strong>
					</p>
				</div>
			</div>
<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/blog/blogmsg/blogMsg.do"
							name="form2" >
			<c:forEach var="blogMsg" items="${list}">
				<div class="row ">
					<div class="col-3 d-inline-flex  align-items-center ">
						<p class="h5 m-0 pe-2">${blogMsg.customer.nickname}</p>
						<img
							src="<%=request.getContextPath()%>/customer/ImageReader?id=${blogMsg.customer.customerId}"
							style="width: 50px; height: 50px;"
							alt="${blogMsg.customer.nickname}" />
					</div>
					<div class="col-5 align-items-center text-center">
					
					<c:set var="formattedDate">
    				<fmt:formatDate value="${blogMsg.createTime}" pattern="yyyy-MM-dd HH:mm"/>
					</c:set>
				
						<p class="h5 text-black-50 m-0 mt-3">${formattedDate}</p>
					</div>
					<div class="row mt-2">	
						<div class="col d-inline-flex">${blogMsg.content}</div>
						<c:if test="${blogMsg.customer.customerId == customer.customerId}">
												<input type="hidden" name="action" value="delete" />
												<input type="hidden" name="blogId" value="${blog.blogId}">
												<input type="hidden" name="blogMsgId"
													value="${blogMsg.blogMsgId}" />
												<button type="submit" class="btn" style="width: 100px; height: 50px; background: #6B705C; border-color: transparent; color: white; border-radius: 90px;">刪除留言</button>
											</c:if>
					</div>
				</div>
				<hr>
			</c:forEach>
			</FORM>

			<div class="row mt-3">
				<div class="col d-inline-flex p-2">
				<c:choose>
					<c:when test="${not empty  customer.getNickname()}">
					<p class="h5 p-2 pe-2"><%=customer.getNickname()%></p>
					</c:when>
				<c:otherwise>
				<p class="h5 p-2 pe-2">遊客</p>
				</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${not empty  customer.getNickname()}">
					<img
							src="<%=request.getContextPath()%>/customer/ImageReader?id=${customer.getCustomerId()}"
							style="width: 50px; height: 50px;"
							alt="${customer.nickname}"/>
					</c:when>
				<c:otherwise>
				<img
							src="<%=request.getContextPath()%>/static/images/logo.png"
							style="width: 50px; height: 50px;"
							alt="遊客"/>
				</c:otherwise>
				</c:choose>
			</div>

				<form METHOD="post"
									ACTION="<%=request.getContextPath()%>/blog/blogmsg/blogMsg.do"
									name="form1" >
			<div class="row">
				<div class="col-12">
					<div class="form-group">
					<input type="hidden" name="blogId" value="${blog.blogId}">
					<input type="hidden" name="customerId" value="${customer.customerId}">
					<input type="hidden" name="createTime" value="<%=new java.sql.Timestamp(System.currentTimeMillis())%>">
					<input type="hidden" name="action" value="insert">
					
						<input type="text" class="form-control" style="height: 50px"  name="content"
							id="exampleInput" placeholder="來留個言吧"  required/>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<button type="submit" id="msgSubmit"  class="btn btn-success m-2 float-end" >
						送出</button>
				</div>
			</div>
					</form>
		</div>
		</div>


	</main>
	<footer id="footer"></footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script>
		$(function() {
			$("#header").load("../../components/html/header.jsp");
			$("#footer").load("../../components/html/footer.jsp");
		});
		
		$(document).ready(function () {
			$(document).on('click', '#msgSubmit', function (e){
				e.stopPropagation();
		    	var customerId = parseInt("<%=customerId%>", 10);
			
		        // 檢查用戶是否已登入
		        if (customerId === 0 || customerId === "") {
		        	swal("請先登入才能留言。", "", "warning");
		            console.log(customerId);
		            return false;
		        }
			})
		})
		
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
		                		var updateblogCl = $('#status-' + blogId + ' .blogClSubmit .blogCl');
		                		console.log(updateblogCl);
		                		if (response.blogclstatus === '0' || response.blogclstatus === 'novalue') {
		                			updateblogCl.prop('src', "../../static/blogimages/ON收藏.svg");
		                        } else {
		                            updateblogCl.prop('src', "../../static/blogimages/UN收藏.svg");
		                        }
		                        // 處理成功的邏輯
		                        console.log('成功：', response);
		                    } else {
		                        // 處理失敗的邏輯
		                        console.error('錯誤：', response.error);
		                    }
		                }
//			                error: function (xhr, s, error) {
//			                    alert(xhr.status);
//			                    alert(xhr.readyState);
//			                }
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
		        	swal("請先登入才能按讚唷。", "", "warning");
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
		                		var updateblogLike = $('#status-' + blogId + ' .blogLikeSubmit .blogLike');
		                		console.log(updateblogLike)
		                		if (response.blogLikeStatus === '0' || response.blogLikeStatus === 'novalue') {
		                            updateblogLike.prop('src', "../../static/blogimages/已讚.svg");
		                        } else {
		                            updateblogLike.prop('src', "../../static/blogimages/未讚.svg");
		                        }
		                        // 處理成功的邏輯
		                        console.log('成功：', response);
		                    } else {
		                        // 處理失敗的邏輯
		                        console.error('錯誤：', response.error);
		                    }
		                }
//			                error: function (xhr, s, error) {
//			                    alert(xhr.status);
//			                    alert(xhr.readyState);
//			                }
		            });
		        }
		    });
		});
	</script>
</body>
</html>

</html>