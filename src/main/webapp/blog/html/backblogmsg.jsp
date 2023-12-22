<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.blog.entity.*"%>
<%@ page import="com.lazytravel.blog.dao.*"%>
<%@ page import="com.lazytravel.blog.service.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
BlogMsgService blogMsgSvc = new BlogMsgServiceImpl();
List<BlogMsg> list = blogMsgSvc.getAllBlogMsgs();
pageContext.setAttribute("list",list);
%>


<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>後臺 - 後臺功能</title>

    <link
      rel="stylesheet"
      href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css" />
    <link rel="icon" href="../../static/images/logo.ico" type="image/x-icon" />

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
        <h3 class="d-inline-block me-3">部落格留言查詢</h3>
      </div>

      <div class="table-responsive mx-4">
        <table id="example" class="table table-striped" style="width: 100%">
          <thead>
            <tr>
              <th scope="col">留言ID</th>
              <th scope="col">會員名稱</th>
              <th scope="col">文章標題</th>
              <th scope="col">留言內容</th>
              <th scope="col">發布時間</th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="blog_msg" items="${list}">
            <tr>
              <td>${blog_msg.getBlogMsgId()}</td>
              <td>${blog_msg.customer.customerName}</td>
              <td>${blog_msg.blog.title}</td>
              <td>${blog_msg.content}</td>
              <td>${blog_msg.createTime}</td>
              <td>
                <form method="post" action="<%=request.getContextPath()%>/blog/blogmsg/blogMsg.do" style="margin-bottom: 0px;">
                    <button type="submit" name="action" value="getOne_For_Update" class="btn-modify btn">修改</button>
                    <button type="submit" name="action" value="delete" class="btn-modify btn">刪除</button>
                    <input type="hidden" name="blog_msg_id" value="${blog_msg.getBlogMsgId()}">
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
        $("#header").load("../../admin/header.html");
        new DataTable("#example");
      });
    </script>
  </body>
</html>
