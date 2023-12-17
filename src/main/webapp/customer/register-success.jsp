<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>會員註冊成功</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">

</head>

<body>
  <header id="header"></header>

  <div class="container d-flex justify-content-center mb-5">
    <div class="row border m-3 p-3 rounded-3 w-50">
      <h2 class="mb-4 text-center">註冊成功</h2>

      <div class="col-12 mb-3 text-center">
        將於<span class="text-danger">10秒</span>後自動返回登入頁面
      </div>
      <div class="col-12 mb-3 text-center">
        <a href="<%=request.getContextPath()%>/customer/login.jsp" class="btn text-light" style="background-color: #6B705C;">返回登入頁面</a>
      </div>

    </div>
  </div>

  <footer id="footer"></footer>


  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
  <script>
    $(function () {
      $("#header").load("../components/html/header.jsp");
      $("#footer").load("../components/html/footer.jsp");
    });



  </script>
</body>

</html>