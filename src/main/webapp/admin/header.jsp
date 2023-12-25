<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.lazytravel.admin.entity.Users" %>
<%
  Users users = (Users) session.getAttribute("users");
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

  <style>
    * {
      box-sizing: border-box;
    }

    body {
      margin: 0;
    }

    img {
      max-width: 100%;
    }
  </style>
</head>

<body>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">後臺管理</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/admin/index.html">首頁</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              平臺會員
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="<%=request.getContextPath()%>/admin/customer.jsp">會員資料</a></li>
              <li><a class="dropdown-item" href="orderList.html">會員訂單</a></li>
              <li><a class="dropdown-item" href="#">優惠券</a></li>
            </ul>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              美食景點
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">美食景點</a></li>
              <li><a class="dropdown-item" href="#">標籤</a></li>
            </ul>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              行程
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">行程管理</a></li>
              <li><a class="dropdown-item" href="#">旅行團</a></li>
              <li><a class="dropdown-item" href="#">行程評價</a></li>
            </ul>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              部落格
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">文章</a></li>
              <li><a class="dropdown-item" href="#">留言</a></li>
            </ul>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">客服</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              後臺權限
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="user.jsp">後臺使用者</a></li>
              <li><a class="dropdown-item" href="role.jsp">後臺角色</a></li>
              <li><a class="dropdown-item" href="func.jsp">後臺功能</a></li>
            </ul>
          </li>
        </ul>
        <form class="d-flex" method="post" action="">
          <div class="navbar-text me-3">
            歡迎後臺使用者：<span id="user" class="font-monospace text-primary"><%= users.getUsername() %></span>
          </div>
<%--          <form method="get" action="<%=request.getContextPath()%>/adminLogin.do">--%>
<%--            <input type="hidden" name="action" value="logout">--%>
<%--            <button class="btn btn-outline-secondary" type="submit">登出</button>--%>
<%--          </form>--%>
          <a href="<%=request.getContextPath()%>/adminLogin.do" class="btn btn-outline-secondary">登出</a>
          <input type="hidden" name="customer_id"  value="">
        </form>
      </div>
    </div>
  </nav>


  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    crossorigin="anonymous"></script>
</body>

</html>