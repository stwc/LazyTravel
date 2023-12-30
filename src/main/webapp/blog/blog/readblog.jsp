<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.blog.entity.*"%>
<%@ page import="com.lazytravel.blog.dao.*"%>
<%@ page import="com.lazytravel.blog.service.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<%@ page import="java.util.Set"%>


<%
Blog blog = (Blog) request.getAttribute("blog"); 
// Blog blog = (Blog) request.getAttribute("blog");
// BlogService blogSvc = new BlogServiceImpl();
// List<Blog> list = blogSvc.getAllBlogs();
// pageContext.setAttribute("list",list);
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
            <img class="card-img-top" src="<%=request.getContextPath()%>/blog/blog/BlogImgReader?blogId=${blog.blogId}" style=" width: 80%; height: 80%;" />
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h5 class="display-6 lead my-2">
              <strong>${blog.title}</strong>
            </h5>
          </div>
          <div
            class="col-4 d-inline-flex justify-content-center align-items-center">
            <img src="../../static/blogimages/«öÆg.svg" class="thumds" alt="" />
            <p class="m-1 p-1">${blog.likeSum}</p>
            <img
              src="../../static/blogimages/ÂsÄý¼Æ.svg "
              class="thumds"
              alt="" />
            <p class="m-1 p-1">${blog.viewSum}</p>
            <img
              src="../../static/blogimages/UN¦¬ÂÃ.svg"
              class="thumds" 
              alt="" />
          </div>
        </div>
<!--         <div class="row-cols-auto d-inline-flex d-flex justify-content-center"> -->
          <div class="col-7">
          <c:set var="formattedDate">
    							<fmt:formatDate value="${blog.blogDate}" pattern="yyyy-MM-dd HH:mm"/>
								</c:set>
            <p class="h6">${formattedDate}</p>
          </div>

          <div class="col d-inline-flex justify-content-center align-items-center" >
            <p class="m-0"style="width: 60px; ">${blog.customer.nickname}</p>
            <img class="card-img-top" src="<%=request.getContextPath()%>/customer/ImageReader?id=${blog.customer.customerId}" style="width:100px; height: 100px "alt="${blog.customer.customerName}" />
          </div>
<!--         </div> -->
        <hr />
        <div class="row">
          <div class="col">
            <p class="h6 my-3">
              ${blog.content}
            </p>
          </div>
        </div>


      </div>

      <hr />
    </main>
    <footer id="footer"></footer>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
      crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script>
      $(function () {
        $("#header").load("../../components/html/header.jsp");
        $("#footer").load("../../components/html/footer.jsp");
      });
      
      
    </script>
  </body>
</html>

</html>