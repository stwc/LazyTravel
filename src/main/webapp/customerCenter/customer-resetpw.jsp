<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.lazytravel.customer.entity.Customer" %>

<%
  Customer customer = (Customer) session.getAttribute("customer");

  // 舊密碼錯誤
  Boolean isPwWrong = false;
  if (request.getAttribute("isPwWrong") != null)
    isPwWrong = (Boolean) request.getAttribute("isPwWrong");
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>會員中心 - 修改密碼</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

  <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">


  <style>

  </style>

</head>

<body>
<header id="header"></header>

<div class="container d-flex justify-content-center mb-5">
  <form method="post" action="customer.do" class="row border m-3 p-3 needs-validation rounded-3" novalidate>
    <h2 class="mb-4">修改密碼</h2>
    <div class="alert alert-warning <%= (isPwWrong) ? "" : "d-none" %>" role="alert">
      舊密碼不正確，請重新輸入！
    </div>
    <div class="col-12 mb-3">
      <label for="inputOldPassword" class="form-label">舊密碼</label>
      <input type="password" name="customer_old_passwd" class="form-control" id="inputOldPassword"
             placeholder="請輸入舊密碼"
             aria-describedby="oldPasswordHelp validationOldPassword">
      <div class="valid-feedback">
        格式正確
      </div>
      <div id="validationOldPassword" class="invalid-feedback">
        請輸入舊密碼
      </div>
    </div>
    <div class="col-12 mb-3">
      <label for="inputPassword1" class="form-label">新密碼</label>
      <input type="password" name="customer_passwd" class="form-control" id="inputPassword1"
             placeholder="密碼長度限制8~24位，只能用英文大寫、小寫或數字"
             aria-describedby="passwordHelp1 validationPassword1">
      <div class="valid-feedback">
        格式正確
      </div>
      <div id="validationPassword1" class="invalid-feedback">
        密碼長度限制8~24位，只能用英文大寫、小寫或數字
      </div>
    </div>
    <div class="col-12 mb-3">
      <label for="inputPassword2" class="form-label">確認新密碼</label>
      <input type="password" class="form-control" id="inputPassword2" placeholder="請再次輸入密碼"
             aria-describedby="passwordHelp2">
      <div class="valid-feedback validationPassword2">
        兩次輸入的密碼相符
      </div>
      <div id="validationPassword2" class="invalid-feedback">
        兩次輸入的密碼不相符，請重新輸入
      </div>
    </div>
    <div class="col-md-6 mt-3">
      <input type="hidden" name="action" value="resetpw">
      <button type="submit" id="btn-confirm" class="btn w-100" style="background-color: #6B705C;">
        <span class="text-light">確認</span>
      </button>
    </div>
    <div class="col-md-6 mt-3">
      <button type="button" id="btn-cancel" class="btn w-100" style="background-color: #B7B7A4;">
        <span class="text-light">取消</span>
      </button>
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
    });

    $("#btn-cancel").on("click", function () {
        window.location.replace("<%=request.getContextPath()%>/customerCenter/customer-center.jsp");
    });

    function validateRequired() {
        let isRequired = true;

        // Old Password
        const oldPasswd = $("#inputOldPassword");
        if (oldPasswd.val() === "")
            oldPasswd.removeClass("is-valid").addClass("is-invalid");
        else
            oldPasswd.removeClass("is-invalid").addClass("is-valid");

        if (oldPasswd.hasClass("is-invalid"))
            isRequired = false;

        // New Password1
        const password1 = $("#inputPassword1");
        if (password1.val() === "")
            password1.removeClass("is-valid").addClass("is-invalid");
        else
            password1.removeClass("is-invalid").addClass("is-valid");

        if (password1.hasClass("is-invalid"))
            isRequired = false;

        // New Password2
        const password2 = $("#inputPassword2");
        if (password2.val() === "")
            password2.removeClass("is-valid").addClass("is-invalid");
        else
            password2.removeClass("is-invalid").addClass("is-valid");

        if (password2.hasClass("is-invalid"))
            isRequired = false;

        return isRequired;
    }

    function validateCustomized() {
        let isValidated = true;

        // New Password
        const password1 = $("#inputPassword1");
        const password2 = $("#inputPassword2");
        // password1
        const passwordRegex = /^[a-zA-Z0-9]{8,24}$/;
        if (!password1.val().match(passwordRegex)) {
            password1.removeClass("is-valid").addClass("is-invalid");
            isValidated = false
        } else {
            password1.removeClass("is-invalid").addClass("is-valid");
        }
        // password2
        if (password2.val() !== password1.val()) {
            password2.removeClass("is-valid").addClass("is-invalid");
            isValidated = false
        } else {
            password2.removeClass("is-invalid").addClass("is-valid");
        }

        return isValidated;
    }

    $("#btn-confirm").on("click", function (e) {
        e.preventDefault();
        // console.log(e.target.closest("form"));

        if (validateRequired() && validateCustomized()) {
            console.log("Validate form succeeded!");
            e.target.closest("form").submit();
        } else {
            console.log("Validate form failed...");
        }
    });


</script>
</body>

</html>