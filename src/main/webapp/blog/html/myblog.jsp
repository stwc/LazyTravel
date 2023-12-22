<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.blog.entity.*"%>
<%@ page import="com.lazytravel.blog.dao.*"%>
<%@ page import="com.lazytravel.blog.service.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
BlogService blogSvc = new BlogServiceImpl();
List<Blog> list = blogSvc.getAllBlogs();
pageContext.setAttribute("list", list);
%>


<html>

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

.dashed-border {
	border: 2px dashed #757d86; /* 虛線的寬度、樣式和顏色 */
	padding: 10px; /* 可以添加一些內邊距以使內容不貼近邊框 */
}
</style>
</head>
<body>
	<header id="header"></header>

	<div class="container">
		<div class="row my-3">
			<div class="col">
				<h1 class="title">我的文章列表</h1>
			</div>
		</div>

		<div class="row ">
			<div class="col-9  d-inline-flex">
				<form class="dropdown my-auto " method="post" action="blog.do">
					<button class="btn btn-secondary dropdown-toggle" type="button"
						name="blogid" id="dropdownMenuButton1" data-bs-toggle="dropdown"
						aria-expanded="false">想來點甚麼嗎?</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
						<c:forEach var="tag" items="${list[0].getTags()}">
							<li><a class="dropdown-item" href="#" data-id="${tag.tagId}">${tag.tagName}</a></li>
						</c:forEach>
					</ul>
				</form>


				<div class="input-group w-50 h-auto m-1">
					<!-- 					<form method="post" action="your_search_action"> -->
					<input type="text" class="form-control my-lg-auto"
						placeholder="輸入景點/美食" aria-label="Recipient's username"
						aria-describedby="button-addon2" />
					<button class="btn btn-outline-secondary my-auto" type="submit"
						id="button-addon2">搜尋看看吧</button>
					<!-- 					</form> -->
				</div>
			</div>


			<div class="col-3 d-flex justify-content-end w-25 p-0">
				<div class="btn-group" role="group"
					aria-label="Basic radio toggle button group">
					<input type="radio" class="btn-check" name="btnradio"
						id="btnradio1" autocomplete="off" /> <label
						class="btn btn-outline-primary" for="btnradio1">文章列表</label> <input
						type="radio" class="btn-check" name="btnradio" id="btnradio2"
						autocomplete="off" checked /> <label
						class="btn btn-outline-primary" for="btnradio2">我的文章</label> <input
						type="radio" class="btn-check" name="btnradio" id="btnradio3"
						autocomplete="off" /> <label class="btn btn-outline-primary"
						for="btnradio3">文章收藏</label>
				</div>
			</div>
		</div>



		<hr />
		<div class="container">
			<div class="row">
				<c:forEach var="blog" items="${list}">
					<div class="col-md-4">
						<div class="card" style="width: 22rem">
							<img src="../../static/blogimages/大王拉麵.jpg" class="card-img-top"
								alt="..." />

							<div class="card-body p">
								<h5 class="card-title">${blog.title}</h5>
								<div class="d-inline-flex">
									<c:forEach var="tag" items="${blog.getTags()}"> ${tag.tagName}
								</c:forEach>
									<p class="h6"></p>
								</div>
								<div class="d-grid gap-2 d-md-flex justify-content-md-end"></div>
								<p class="h6">${blog.createTime}</p>
								<p class="card-text">${blog.content}</p>
								<div class="d-flex justify-content-end">
									<div class="col-10"></div>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/blog/blog/blog.do"
										style="margin-bottom: 0px;">
										<button type="submit" value="修改">
											<img src="../../static/blogimages/編輯筆.svg" class="thumds"
												alt="編輯" />
										</button>
										<input type="hidden" name="blogId" value="${blog.blogId}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>



		</div>

		<div class="container-fluid m-3">
			<div class="row justify-content-center">
				<div class="col-4">
					<nav aria-label="Page navigation example">
						<ul class="pagination">
							<li class="page-item"><a class="page-link" href="#">Previous</a>
							</li>
							<li class="page-item"><a class="page-link" href="#">1</a></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">Next</a>
							</li>
						</ul>
					</nav>
				</div>
			</div>
		</div>

		<footer id="footer"></footer>

		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
			crossorigin="anonymous"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
		<script>
			$(function() {
				$("#header").load("../../components/html/header.html");
				$("#footer").load("../../components/html/footer.html");
			});
		</script>
</body>
</html>
