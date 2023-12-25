	<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.List"%>
	<%@ page import="com.lazytravel.blog.entity.*"%>
	<%@ page import="com.lazytravel.blog.dao.*"%>
	<%@ page import="com.lazytravel.blog.service.*"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<%@ page import="java.util.Set"%>
	<%-- �����m�߱ĥ� EL ���g�k���� --%>
	
	<%
	BlogService blogSvc = new BlogServiceImpl();
	List<Blog> list = blogSvc.getAllBlogs();
	pageContext.setAttribute("list", list);
// 	 Blog blog = (Blog) session.getAttribute("blog");
// 	String img = request.getContextPath() + "/blog/blog/BlogImageReader?id=" + blog.getBlogId();
	
// 	if (session.getAttribute("customer") == null) {
//     response.sendRedirect(request.getContextPath() + "/blog/blog/blogfirst.jsp");
//     return;
//   }
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
						<h1 class="title">�峹�C��</h1>
					</div>
				</div>
	
				<div class="row ">
					<div class="col-9  d-inline-flex">
						<form class="input-group w-50 h-auto m-1" METHOD="post" ACTION="blog.do">
							<input type="text" class="form-control my-lg-auto" name="keyword"
								placeholder="��J���I/����" aria-label="Recipient's username"
								aria-describedby="button-addon2" />
								<input type="hidden" name="action" value="search">
							<button class="btn btn-outline-secondary my-auto" type="submit" style="background: #CCD5AE;border-color: transparent;color: white;border-radius: 90px;"
								id="button-addon2" >�j�M�ݬݧa</button>
						</form>
					</div>
	
					<div class="col-3 d-flex justify-content-end w-25 p-0">
						<div class="btn-group" role="group"
							aria-label="Basic radio toggle button group">
							<input type="radio" class="btn-check" name="btnradio"
								id="btnradio1" autocomplete="off" checked /> 
								<label
								class="btn btn-outline-primary" for="btnradio1"style="background: #CCD5AE;border-color: transparent;color: white;border-radius: 90px;">�峹�C��</label> 
								<input
								type="radio" class="btn-check" name="btnradio" id="btnradio2" style="background: #CCD5AE;border-color: transparent;color: white"
								autocomplete="off" /> 
								<label class="btn btn-outline-primary" for="btnradio2" style="background: #CCD5AE;border-color: transparent;color: white;border-radius: 90px;" onclick=" toMyBlog()">�ڪ��峹</label> 
								<input type="radio" style="background: #CCD5AE;border-color: transparent;color: white"
								class="btn-check" name="btnradio" id="btnradio3"
								autocomplete="off" /> <label class="btn btn-outline-primary" style="background: #CCD5AE;border-color: transparent;color: white;border-radius: 90px;"
								for="btnradio3">�峹����</label>
						</div>
					</div>
				</div>
			</div>
	
			<hr />
				<div class="container">
	    <div class="row" >
	        <c:forEach var="blog" items="${list}">
	            <div class="col-md-4">
	                <div class="card" style="width: 22rem;height: 450px; margin: 10px;">
	                <img class="card-img-top" src="<%=request.getContextPath()%>/blog/blog/BlogImgReader?blogId=${blog.blogId}" style=" width: 100%; height: 50%;" />
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
	                        <div class="d-flex justify-content-end align-items-center">
	                            <div class="col-md-3 pr-0">
	                                <p>${blog.customer.customerName}</p>
	                            </div>
	                            <div class="col-md-5 p-0">
	                            <img class="card-img-top" src="<%=request.getContextPath()%>/customer/ImageReader?customerId=${blog.customer.customerId}" style=" width: 50%; height: 50%; "alt="${blog.customer.customerName}" />
	                            </div>
	                            <div class="col-md-4 d-inline-flex align-items-center">
	                                <img src="../../static/blogimages/���g.svg" class="thumds" alt="�g" />
	                                <p class="m-1 p-1">${blog.likeSum}</p>
	                                <img src="../../static/blogimages/UN����.svg" class="thumds m-0" alt="����" />
	                                <p class="m-1 p-1">${blog.clSum}</p>
	                                <img src="../../static/blogimages/�s����.svg " class="thumds" alt="view" />
	                                <p class="m-1 p-1">${blog.viewSum}</p>
	                                <!-- <input type="hidden" name="blogId" value="${blog.blogId}">
	                                <input type="hidden" name="action" value="getOne_For_Update"> -->
	                            </div>
	                        </div>
	                        <form class=" row d-flex justify-content-end" METHOD="post" ACTION="blog.do" style="margin-bottom: 0px;">
	                        <input type="hidden" name="blogId" value="${blog.blogId}">
	                        <input type="hidden" name="action" value="getOne_For_Display">
 						   <input type="submit"  value="�ݧ�h" class="btn btn-primary m-1 p-1" style="background: #9C6644;border-color: transparent;color: white;width: 100px;height: 30px">
	                    </form>
	                    		</div>
	                </div>
	            </div>
	        </c:forEach>
	    </div>
	</div>
	<div id="searchResults" class="row"></div>
	
	
<!-- 				<div class="container-fluid"> -->
<!-- 					<div class="row justify-content-center"> -->
<!-- 						<div class="col-4"> -->
<!-- 							<nav aria-label="Page navigation example"> -->
<!-- 								<ul class="pagination p-3"> -->
<!-- 									<li class="page-item"><a class="page-link" href="#" >1</a></li> -->
<!-- 									<li class="page-item"><a class="page-link" href="#">2</a></li> -->
<!-- 									<li class="page-item"><a class="page-link" href="#">3</a></li> -->
<!-- 									<li class="page-item"><a class="page-link" href="#">�U�@��</a> -->
<!-- 									</li> -->
<!-- 								</ul> -->
<!-- 							</nav> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
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
			
			// JavaScript ��ơA�ΨӳB�z�I���ƥ�
	        function redirectToNextPage(blogId) {
	            // �ϥ� JavaScript �� window.location.href �i�歶������
	            window.location.href = '<%=request.getContextPath()%>/blog/blog/blog.do?blogId=' + blogId;
	        }
			function toMyBlog(){
				window.location.href = "myblog.jsp";
			}
	     
		</script>
	
	</body>
	</html>