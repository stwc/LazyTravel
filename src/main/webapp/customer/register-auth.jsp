<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  Boolean isFailed = false;
  if (request.getAttribute("isFailed") != null)
    isFailed = (Boolean) request.getAttribute("isFailed");

  Boolean isExpired = false;
  if (request.getAttribute("isExpired") != null)
    isExpired = (Boolean) request.getAttribute("isExpired");

  Boolean notAuth = false;
  if (request.getAttribute("notAuth") != null)
    notAuth = (Boolean) request.getAttribute("notAuth");

  Boolean hideResend = false;
  if (request.getAttribute("hideResend") != null)
    hideResend = (Boolean) request.getAttribute("hideResend");
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>會員註冊信箱認證</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="icon" href="<%=request.getContextPath()%>/static/images/logo.ico" type="image/x-icon">

</head>

<body>
<header id="header"></header>

<div class="container d-flex justify-content-center mb-5">
  <form method="post" action="<%=request.getContextPath()%>/customer/register-auth.do"
        class="row border m-3 p-3 rounded-3 w-50">
    <h2 class="mb-4 text-center">電子郵件驗證</h2>
    <div class="alert alert-warning text-center <%= (isFailed) ? "" : "d-none" %>" role="alert">
      驗證碼輸入錯誤，請重新輸入
    </div>
    <div class="alert alert-warning text-center <%= (isExpired) ? "" : "d-none" %>" role="alert">
      驗證碼已過期，請點選再次發送驗證信
    </div>
    <div class="alert alert-warning text-center <%= (notAuth) ? "" : "d-none" %>" role="alert">
      帳號未驗證，請至註冊信箱收取驗證信，或點選再次發送驗證信
    </div>

    <div class="row">
      <div class="col mb-1 text-center">
        請於<span class="text-danger">10分鐘</span>內點選寄至信箱的驗證連結
      </div>
    </div>
    <%--    <div class="row">--%>
    <%--      <div class="col-md-8 mx-auto">--%>
    <%--        <label for="inputAuthCode" class="visually-hidden">驗證碼</label>--%>
    <%--        <input type="password" class="form-control" id="inputAuthCode" placeholder="驗證碼">--%>
    <%--      </div>--%>
    <%--    </div>--%>

    <div class="row g-1 px-3 d-flex justify-content-center">
      <%--      <div class="col-md-4 ">--%>
      <%--        <input type="hidden" name="action" value="signup">--%>
      <%--        <button type="submit" id="btn-confirm" class="btn w-100" style="background-color: #6B705C;"><span--%>
      <%--                class="text-light">確認</span></button>--%>
      <%--      </div>--%>
      <%--      <div class="col-md-4 ">--%>
      <%--        <button id="btn-sendcode" type="button" class="btn text-light w-100"--%>
      <%--                style="background-color: #B7B7A4;">再次發送驗證信--%>
      <%--        </button>--%>
      <%--      </div>--%>
      <div class="col-md-12 <%= (hideResend) ? "d-none" : "" %>">
        <input type="hidden" name="action" value="resend">
        <input type="hidden" name="customer_id" id="customerId">
        <button id="btn-resend" type="submit" class="btn text-light w-100"
                style="background-color: #6B705C;">再次發送驗證信
        </button>
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
        $("#header").load("<%=request.getContextPath()%>/components/html/header.jsp");
        $("#footer").load("<%=request.getContextPath()%>/components/html/footer.jsp");

        let urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has("id")) {
            $("#customerId").val(urlParams.get("id"))
        }

    });

</script>
</body>

</html>