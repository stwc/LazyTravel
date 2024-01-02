<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  // 已經登入過就無法進來登入頁面，重導回首頁
  if (session.getAttribute("customer") != null) {
    String location = (String) session.getAttribute("location");
    if (location == null)
      response.sendRedirect(request.getContextPath() + "/index.jsp"); // 回首頁
    else
      response.sendRedirect(request.getContextPath() + location); // 回之前的頁面
    return;
  }

  Boolean loginFailed = false;
  if (request.getAttribute("loginFailed") != null)
    loginFailed = (Boolean) request.getAttribute("loginFailed");

  Boolean isBanned = false;
  if (request.getAttribute("isBanned") != null)
    isBanned = (Boolean) request.getAttribute("isBanned");
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>會員登入</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="icon" href="<%=request.getContextPath()%>/static/images/logo.ico" type="image/x-icon">

  <style>
      /* #box-login {
        border: 1px solid aqua;
        position: absolute;
        top: 45%;
        left: 50%;
        transform: translate(-50%, -50%);
        max-width: 480px;
      } */
  </style>
</head>

<body>

<header id="header"></header>

<div id="box-login" class="container-sm shadow p-3 my-5 bg-body-tertiary rounded-4 w-50 mb-5">
  <div class="d-flex flex-column justify-content-center align-items-center">
    <div class="h1 mt-1 mb-3">會員登入</div>

    <form method="post" action="<%=request.getContextPath()%>/login.do" class="w-75">
      <div class="alert alert-warning <%= (loginFailed) ? "" : "d-none" %>" role="alert">
        Email信箱或密碼輸入錯誤，請重新輸入！
      </div>
      <div class="alert alert-danger <%= (isBanned) ? "" : "d-none" %>" role="alert">
        該帳號已被停權！
      </div>
      <div class="mb-3">
        <label for="inputEmail" class="form-label">Email信箱</label>
        <input type="text" name="email" class="form-control" id="inputEmail" placeholder="請輸入Email..." required>
      </div>
      <div class="mb-3">
        <label for="inputPassword" class="form-label">密碼</label>
        <input type="password" name="customer_passwd" class="form-control" id="inputPassword"
               placeholder="請輸入密碼..." required>
      </div>
      <div class="d-grid gap-2 mt-4">
        <button type="submit" id="btn-login" class="btn mb-3" style="background-color: #6B705C;">
          <span class="text-light">登入</span>
        </button>
      </div>
    </form>

    <div class="w-75 d-flex justify-content-around mb-2">
      <a href="<%=request.getContextPath()%>/customer/forgotpw.jsp" id="forget-pw"
         class="text-decoration-none text-secondary">
        <svg xmlns="http://www.w3.org/2000/svg"
             width="16" height="16" fill="currentColor" class="bi bi-question-circle-fill" viewBox="0 0 16 16">
          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM5.496 6.033h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286a.237.237 0 0 0 .241.247zm2.325 6.443c.61 0 1.029-.394 1.029-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94 0 .533.425.927 1.01.927z"/>
        </svg>
        忘記密碼
      </a>
      <a href="<%=request.getContextPath()%>/customer/register.jsp" id="signup"
         class="text-decoration-none text-secondary">
        <svg xmlns="http://www.w3.org/2000/svg"
             width="16" height="16" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
          <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Zm.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0Zm-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z"/>
          <path d="M2 13c0 1 1 1 1 1h5.256A4.493 4.493 0 0 1 8 12.5a4.49 4.49 0 0 1 1.544-3.393C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4Z"/>
        </svg>
        註冊會員
      </a>
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
        $("#header").load("<%=request.getContextPath()%>/components/html/header.jsp");
        $("#footer").load("<%=request.getContextPath()%>/components/html/footer.jsp");

        let path = window.location.pathname.split("/");
        console.log(path)
        if (path[2] === "customer.do") {
            <%--window.location.href = "<%=request.getContextPath()%>/login.jsp";--%>
            let new_url = "<%=request.getContextPath()%>/login.jsp";
            window.history.pushState(null, "", new_url);
        }
    });

    document.getElementById("btn-login").onclick = () => {
        // console.log("test");
    };

</script>

</body>

</html>
