<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Bootstrap demo</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
  <script src="https://kit.fontawesome.com/cb6bf56872.js" crossorigin="anonymous"></script>

</head>

<body>
  <div class="container-fluid sticky-top " style="background-color: #6B705C">

    <!-- ===========  Header  =========== -->
    <header class="justify-content-around">
      <nav class="navbar">
        <div class="container-fluid">
          <a class="navbar-brand logo_link_trasition" href="#" style="color: white; font-weight: 600;">
            <img src="<%=request.getContextPath()%>/static/images/logo.png" width="70" height="70" id="header-logo"
              class="d-inline-block align-text-center" alt="logo">
            LazyTravel
          </a>

          <div class="navbar nav-item-area">
            <ul class="navbar-nav d-flex flex-row">
              <li class="nav-item dropdown mx-4">
                <a href="#" class="nav-link  link_trasition dropdown-toggle" data-bs-toggle="dropdown"
                  style="color: white;">首頁</a>
                <ul class="dropdown-menu" style="position: absolute">
                  <li><a class="dropdown-item" href="#">行程篩選</a></li>
                  <li><a class="dropdown-item" href="#">熱門行程</a></li>
                  <li><a class="dropdown-item" href="#">熱門景點</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown mx-4">
                <a href="#" class="nav-link link_trasition dropdown-toggle" data-bs-toggle="dropdown"
                  style="color: white;">部落格 BLOG</a>
                <ul class="dropdown-menu" style="position: absolute">
                  <li><a class="dropdown-item" href="#">熱門文章</a></li>
                  <li><a class="dropdown-item" href="#">撰寫文章</a></li>
                  <li><a class="dropdown-item" href="#">我的文章</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown mx-4">
                <a href="#" class="nav-link link_trasition dropdown-toggle" data-bs-toggle="dropdown"
                   style="color: white;">會員中心</a>
                <ul class="dropdown-menu" style="position: absolute">
                  <li><a class="dropdown-item" href="<%=request.getContextPath()%>/customer/customorCenter/customer-center.html">會員資料</a></li>
                  <li><a class="dropdown-item" href="#">歷史訂單</a></li>
                  <li><a class="dropdown-item" href="#">優惠券</a></li>
                  <li><a class="dropdown-item" href="#">我的評價</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown mx-4">
                <a href="#" class="nav-link link_trasition dropdown-toggle" data-bs-toggle="dropdown"
                  style="color: white;">客服</a>
                <ul class="dropdown-menu" style="position: absolute">
                  <li><a class="dropdown-item" href="#">常見問題 F&Q</a></li>
                  <li><a class="dropdown-item" href="#">聯絡我們</a></li>
                </ul>
              </li>


              <li class="nav-item mx-4"><a class="nav-link link_trasition" href="<%=request.getContextPath()%>/customer/login.jsp"
                  style="color: white;">登入/註冊</a></li>
              <li class="nav-item mx-4"><a class="fa-solid fa-envelope nav-link link_trasition" href="#"
                  style="color: white;"></a></li>
              <li class="nav-item mx-4"><a class="fa-regular fa-heart nav-link link_trasition" href="#"
                  style="color: white;"></a></li>
            </ul>
          </div>
        </div>
      </nav>

    </header>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>