<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% Object customer = session.getAttribute("customer"); %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Bootstrap demo</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
  <!-- <link rel="stylesheet" href="../css/header-footer.css"> -->
  <script src="https://kit.fontawesome.com/cb6bf56872.js" crossorigin="anonymous"></script>

</head>

<body>
  <div class="container-fluid">
    <!-- ===========  footer  =========== -->
    <hr>

    <footer class="mx-3">
      <div class="row">
        <div class="col-md-3 mb-3">
          <h5>首頁</h5>
          <ul class="nav flex-column">
            <li class="nav-item mb-2"><a href="<%=request.getContextPath()%>/index.jsp" class="nav-link p-0 text-muted">Home</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">行程篩選</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">熱門行程</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">熱門景點</a></li>
          </ul>
        </div>

        <div class="col-md-3 mb-3">
          <h5>部落格 BLOG</h5>
          <ul class="nav flex-column">
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">熱門文章</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">撰寫文章</a></li>
            <li class="nav-item mb-2"><a href="<%=request.getContextPath()%>/blog/blog/myblog.jsp" class="nav-link p-0 text-muted">我的文章</a></li>
          </ul>
        </div>

        <div class="col-md-3 mb-3">
          <h5>會員中心</h5>
          <ul class="nav flex-column">
            <li class="nav-item mb-2"><a href="<%=request.getContextPath()%>/customerCenter/customer-center.jsp" class="nav-link p-0 text-muted">會員資料</a></li>
            <li class="nav-item mb-2"><a href="<%=request.getContextPath()%>/customerCenter/orderList.jsp" class="nav-link p-0 text-muted">歷史訂單</a></li>
            <li class="nav-item mb-2"><a href="<%=request.getContextPath()%>/customerCenter/coupon.jsp" class="nav-link p-0 text-muted">優惠券</a></li>
<%--            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">我的評價</a></li>--%>
          </ul>
        </div>

        <div class="col-md-3 mb-3">
          <h5>客服</h5>
          <ul class="nav flex-column">
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">常見問題 F&Q</a></li>
            <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">聯絡我們</a></li>
          </ul>
        </div>

        <div class="col-md-4 mb-3 ms-auto <%= (customer == null) ? "" : "d-none" %>">
          <form>
            <h5>還沒成為會員嗎? 趕快註冊加入，來場懶遊吧!!</h5>
            <p></p>
            <div class="d-flex flex-column flex-sm-row w-100 gap-2">
              <label for="newsletter1" class="visually-hidden">Email address</label>
              <input id="newsletter1" type="text" class="form-control" placeholder="Email address">
              <a href="<%=request.getContextPath()%>/customer/register.jsp" class="btn text-light" style="background-color: #6B705C;">Register</a>
            </div>
          </form>
        </div>
      </div>


      <div class="d-flex flex-column flex-sm-row justify-content-between py-3 my-3 border-top">
        <p>© 2023 LazyTravel Company, Inc. All rights reserved.</p>
        <ul class="list-unstyled d-flex">
          <li class="ms-3"><a class="link-dark" href="#"><svg class="bi" width="24" height="24">
                <use xlink:href="#twitter"></use>
              </svg></a></li>
          <li class="ms-3"><a class="link-dark" href="#"><svg class="bi" width="24" height="24">
                <use xlink:href="#instagram"></use>
              </svg></a></li>
          <li class="ms-3"><a class="link-dark" href="#"><svg class="bi" width="24" height="24">
                <use xlink:href="#facebook"></use>
              </svg></a></li>
        </ul>
      </div>
    </footer>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>