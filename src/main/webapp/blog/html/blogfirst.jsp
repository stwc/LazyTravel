<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.blog.entity.*"%>
<%@ page import="com.lazytravel.blog.dao.*"%>
<%@ page import="com.lazytravel.blog.service.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Set"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
BlogService blogSvc = new BlogServiceImpl();
List<Blog> list = blogSvc.getAllBlogs();
pageContext.setAttribute("list", list);
%>


<html lang="en">
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
<link rel="icon" href="../../static/images/logo.png" type="image/x-icon" />
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
				<div class="col my-3">
					<h1 class="title">文章列表</h1>
				</div>
			</div>

			<div class="row ">
				<div class="col-9  d-inline-flex">
					<form class="input-group w-50 h-auto m-1" METHOD="post" ACTION="blog.do">
						<input type="text" class="form-control my-lg-auto" name="keyword"
							placeholder="輸入景點/美食" aria-label="Recipient's username"
							aria-describedby="button-addon2" />
						<button class="btn btn-outline-secondary my-auto" type="submit"
							id="button-addon2" value="search">搜尋看看吧</button>
					</form>
				</div>

				<div class="col-3 d-flex justify-content-end w-25 p-0">
					<div class="btn-group" role="group"
						aria-label="Basic radio toggle button group">
						<input type="radio" class="btn-check" name="btnradio"
							id="btnradio1" autocomplete="off" checked /> 
							<label
							class="btn btn-outline-primary" for="btnradio1"style="background: #CCD5AE;border-color: transparent;color: white">文章列表</label> 
							<input
							type="radio" class="btn-check" name="btnradio" id="btnradio2" style="background: #CCD5AE;border-color: transparent;color: white"
							autocomplete="off" /> 
							<label class="btn btn-outline-primary" for="btnradio2" style="background: #CCD5AE;border-color: transparent;color: white">我的文章</label> 
							<input type="radio" style="background: #CCD5AE;border-color: transparent;color: white"
							class="btn-check" name="btnradio" id="btnradio3"
							autocomplete="off" /> <label class="btn btn-outline-primary" style="background: #CCD5AE;border-color: transparent;color: white"
							for="btnradio3">文章收藏</label>
					</div>
				</div>
			</div>
		</div>

		<hr />
			<div class="container">
    <form class="row" METHOD="post" ACTION="blog.do">
        <c:forEach var="blog" items="${list}">
            <div class="col-md-4">
                <div class="card" style="width: 22rem; max-height: 450px; margin: 10px;">
                    <img src="../../static/blogimages/大王拉麵.jpg" class="card-img-top" alt="..." />
                    <div class="card-body p">
                        <h5 class="card-title">${blog.title}</h5>
                        <div class="d-inline-flex">
                            <p class="h6"></p>
                        </div>
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end"></div>
                        <p class="h6">${blog.createTime}</p>
                        <p class="card-text">${fn:substring(blog.content, 0, 50)}${fn:length(blog.content) > 50 ? '...' : ''}</p>
                        <div class="d-flex justify-content-end align-items-center">
                            <div class="col-md-3 pr-0">
                                <p>${blog.customer.customerName}</p>
                            </div>
                            <div class="col-md-5 p-0">
                                <img src="${blog.customer.avatar}" class="w-100" alt="${blog.customer.customerName}" />
                            </div>
                            <div class="col-md-4 d-inline-flex align-items-center">
                                <img src="../../static/blogimages/按讚.svg" class="thumds" alt="讚" />
                                <p class="m-1 p-1">${blog.likeSum}</p>
                                <img src="../../static/blogimages/UN收藏.svg" class="thumds m-0" alt="收藏" />
                                <p class="m-1 p-1">${blog.clSum}</p>
                                <img src="../../static/blogimages/瀏覽數.svg " class="thumds" alt="view" />
                                <!-- <p class="m-1 p-1">${blog.viewSum}</p> -->
                                <!-- <input type="hidden" name="blogId" value="${blog.blogId}">
                                <input type="hidden" name="action" value="getOne_For_Update"> -->
                            </div>
                        </div>
                    </div>
                    <!-- 添加按鈕 -->
                    <div class="d-flex justify-content-end">
                        <input type="submit" name="action" value="看更多" action="getOne_For_Display" class="btn btn-primary m-1 p-1" style="background: #CCD5AE;border-color: transparent;color: white;width: 100px;height: 30px">
                    
                    </div>
                </div>
            </div>
        </c:forEach>
    </form>
</div>
<div id="searchResults" class="row"></div>


			<div class="container-fluid">
				<div class="row justify-content-center">
					<div class="col-4">
						<nav aria-label="Page navigation example">
							<ul class="pagination p-3">
								<li class="page-item"><a class="page-link" href="#" >1</a></li>
								<li class="page-item"><a class="page-link" href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#">下一頁</a>
								</li>
							</ul>
						</nav>
					</div>
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
	<script>
		$(function() {
			$("#header").load("../../components/html/header.jsp");
			$("#footer").load("../../components/html/footer.jsp");
		});
		
		// JavaScript 函數，用來處理點擊事件
        function redirectToNextPage(blogId) {
            // 使用 JavaScript 的 window.location.href 進行頁面跳轉
            window.location.href = '<%=request.getContextPath()%>/blog/blog/blog.do?blogId=' + blogId;
        }
		
     
	</script>

</body>
</html>
