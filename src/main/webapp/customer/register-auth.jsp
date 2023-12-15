<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>會員註冊信箱認證</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">

</head>

<body>
  <header id="header"></header>

  <div class="container d-flex justify-content-center mb-5">
    <form method="post" action="customer.do" class="row border m-3 p-3 rounded-3 w-50">
      <h2 class="mb-4 text-center">驗證碼輸入</h2>

      <div class="row">
        <div class="col mb-3 text-center">
          請於<span class="text-danger">10分鐘</span>內輸入寄至信箱的驗證碼
        </div>
      </div>
      <div class="row">
        <div class="col-md-8 mx-auto">
          <label for="inputAuthCode" class="visually-hidden">驗證碼</label>
          <input type="password" class="form-control" id="inputAuthCode" placeholder="驗證碼">
        </div>
      </div>

      <div class="row g-3 px-3 d-flex justify-content-center">
        <div class="col-md-4 ">
          <input type="hidden" name="action" value="signup">
          <button type="submit" id="btn-confirm" class="btn w-100" style="background-color: #6B705C;"><span
              class="text-light">確認</span></button>
        </div>
        <div class="col-md-4 ">
          <button id="btn-sendcode" type="button" class="btn text-light w-100"
            style="background-color: #B7B7A4;">再次發送驗證信</button>
        </div>
      </div>
    </form>
  </div>

  <footer id="footer"></footer>


  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
  <script>
    $(function () {
      $("#header").load("../components/html/header.html");
      $("#footer").load("../components/html/footer.html");
    });



  </script>
</body>

</html>