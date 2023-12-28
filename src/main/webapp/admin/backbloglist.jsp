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
pageContext.setAttribute("list",list);
%>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>後臺 - 後臺功能</title>

    <link
      rel="stylesheet"
      href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css" />
    <link rel="icon" href="../static/images/logo.ico" type="image/x-icon" />

    <style>
      button.btn-modify {
        background-color: #9c6644;
        color: white;
      }

      a#add {
        background-color: #b7b7a4;
        color: white;
      }

      a#add:hover {
        background-color: #6b705c;
        color: white;
      }
    </style>
  </head>

  <body>
    <div id="header"></div>

    <div id="main" class="p-3">
      <div class="mx-3 mb-4 d-flex align-items-start">
        <h3 class="d-inline-block me-3">部落格文章查詢</h3>
      </div>

      <div class="table-responsive mx-4">
        <table id="example" class="table table-striped" style="width: 100%">
          <thead>
            <tr>
              <th scope="col">文章ID</th>
              <th scope="col">文章標題</th>
              <th scope="col">會員名稱</th>
              <th scope="col">發布時間</th>
              <th scope="col">文章狀態</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
          <c:forEach var="blog" items="${list}">
            <tr>
              <td>${blog.getBlogId()}</td>
              <td>${blog.title}</td>
              <td>${blog.customer.customerName}</td>
              <td>${blog.createTime}</td>
              <td>
    <c:if test="${blog.blogStatus eq 0}">
        下架中
    </c:if>
    <c:if test="${blog.blogStatus eq 1}">
        上架中
    </c:if>
</td>
              <td>
                <form method="post" action="<%=request.getContextPath()%>/blog/blog/blog.do" style="margin-bottom: 0px;">
                    <button type="submit" name="action" value="getOne_For_Update" class="btn-modify btn">修改</button>
                    <input type="hidden" name="blogId" value="${blog.getBlogId()}">	
                    <button type="submit" name="action" value="updownStatus" class="btn-modify btn" onclick="toggleStatus(${blog.getBlogId()})">
                    <c:if test="${blog.blogStatus eq 0}">上架</c:if>
                    <c:if test="${blog.blogStatus eq 1}">下架</c:if>
                    </button>
                    <input type="hidden" name="blogId" value="${blog.getBlogId()}">
                    
                </form>
              </td>
            </tr>
        </c:forEach>
          </tbody>
        </table>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
    <script>
      $(function () {
        $("#header").load("<%=request.getContextPath()%>/admin/header.jsp");
        new DataTable("#example");
      });
      
      function toggleStatus(blogId) {
          // 發送AJAX請求
          $.ajax({
              type: "POST",
              url: "<%=request.getContextPath()%>/blog/blog/blog.do",
              data: {
                  action: "updownStatus",
                  blogId: blogId
              },
              success: function (data) {
                  // 更新頁面上的狀態顯示，這裡假設你的HTML中有一個元素用於顯示狀態
                  var statusElement = $("#status_" + blogId);
                  var btnElement = $("#btn_" + blogId);
                  
                  if (data === "0") {
                      statusElement.text("下架中");
                      btnElement.text("上架");
                  } else if (data === "1") {
                      statusElement.text("上架中");
                      btnElement.text("下架");
                  }
              },
              error: function () {
                  alert("上下架操作失敗");
              }
          });
      }
    </script>
  </body>
</html>
