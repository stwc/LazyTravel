<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.blog.entity.*"%>
<%@ page import="com.lazytravel.blog.dao.*"%>
<%@ page import="com.lazytravel.blog.service.*"%>
<%@ page import="com.lazytravel.customer.entity.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ page import="java.util.Set"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
BlogService blogSvc = new BlogServiceImpl();
List<Blog>  list = null;

Customer customer = (Customer) session.getAttribute("customer");

if (customer != null) {
    list = blogSvc.getBlogByCustomerId((Integer)customer.getCustomerId());
} else {
    // 如果會員未登入，導向登入頁面
    response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
    return;
}
pageContext.setAttribute("list", list);
%>


<html>

<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>我的文章列表</title>
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
						
					</div>


			<div class="col-3 d-flex justify-content-end w-25 p-0">
						<div class="btn-group" role="group"
							aria-label="Basic radio toggle button group">
							<input type="radio" class="btn-check" name="btnradio"
								id="btnradio1" autocomplete="off" checked /> 
								<label
								class="btn btn-outline-primary" for="btnradio1"style="background: #CCD5AE;border-color: transparent;color: white;border-radius: 90px;"onclick=" ToBlogFirst()">文章列表</label> 
								<input
								type="radio" class="btn-check" name="btnradio" id="btnradio2" style="background: #CCD5AE;border-color: transparent;color: white"
								autocomplete="off" /> 
								<label class="btn btn-outline-primary" for="btnradio2" style="background: #CCD5AE;border-color: transparent;color: white;border-radius: 90px;" onclick=" toMyBlog()">我的文章</label> 
								<input type="radio" style="background: #CCD5AE;border-color: transparent;color: white"
								class="btn-check" name="btnradio" id="btnradio3"
								autocomplete="off" /> <label class="btn btn-outline-primary" style="background: #CCD5AE;border-color: transparent;color: white;border-radius: 90px;" onclick="ToBlogCl()"
								for="btnradio3">文章收藏</label>
						</div>
			</div>
		</div>



		<hr />
		<div class="container">
			<div class="row">
				<c:forEach var="blog" items="${list}">
					<div class="col-md-4">
						<div class="card" style="width: 22rem;height: 425px; margin: 10px">
							<img class="card-img-top" src="<%=request.getContextPath()%>/blog/blog/BlogImgReader?blogId=${blog.blogId}" style=" width: 351px; height: 200px;" />

							<div class="card-body p">
								<h5 class="card-title">${blog.title}</h5>
								<div class="d-inline-flex">
									<p class="h6"></p>
								</div>
								<div class="d-grid gap-2 d-md-flex justify-content-md-end"></div>
								<c:set var="formattedDate">
    							<fmt:formatDate value="${blog.blogDate}" pattern="yyyy-MM-dd HH:mm"/>
								</c:set>
								<p class="h6">${formattedDate}</p>
								<p class="card-text">${fn:substring(blog.content, 0, 25)}${fn:length(blog.content) > 25 ? '...' : ''}</p>
								<div class="d-flex justify-content-end">
									<div class="col-10"></div>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/blog/blog/blog.do"
										style="position: absolute; bottom: 10px; right: 10px;">
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
				
				
				<div class="col-md-4">
    <div class="card" style="width: 22rem; height: 425px; margin: 10px; position: relative;">
        <img class="card-img-top" src="../../static/blogimages/九份景.jpg" style="width: 100%; height: 50%;" />

        <div class="card-body p">
    <p class="h5">來寫下一篇屬於你的文章吧</p>
    <form method="post" class="d-flex justify-content-end" action="<%=request.getContextPath()%>/blog/blog/blog.do" style="position: absolute; bottom: 10px; right: 10px;">
        <!-- 使用 JavaScript 實現跳轉 -->
        <button type="button" onclick="ToAdd()">
            <img src="../../static/blogimages/編輯筆.svg" class="thumds" alt="新增" />
        </button>
    </form>
        </div>
    </div>
</div>
			</div>



		</div>

		<iv>

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
			
			function ToAdd() {
		        // 使用 window.location.href 將頁面導航到 myblog.jsp
		        window.location.href = 'addblog2.jsp';
		    };
		    
		    function ToBlogFirst() {
		        // 使用 window.location.href 將頁面導航到 myblog.jsp
		        window.location.href = 'blogfirst.jsp';
		    };
		    
		    function ToBlogCl() {
		        // 使用 window.location.href 將頁面導航到 myblog.jsp
		        window.location.href = 'myblogcl.jsp';
		    };
		    
				</script>
</body>
</html>
