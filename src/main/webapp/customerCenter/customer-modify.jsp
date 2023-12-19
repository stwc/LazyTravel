<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.lazytravel.customer.entity.Customer" %>

<%
  Customer customer = (Customer) session.getAttribute("customer");

  // 會員更新失敗
  Boolean updateFailed = false;
  if (request.getAttribute("updateFailed") != null)
    updateFailed = (Boolean) request.getAttribute("updateFailed");
  // 還原輸入過的資料
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>會員中心 - 修改會員資料</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

  <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">


  <style>
      #preview {
          border: 1px solid lightgray;
          display: inline-block;
          width: 150px;
          min-height: 150px;
          position: relative;
      }

      #preview span.text {
          position: absolute;
          display: inline-block;
          left: 50%;
          top: 50%;
          transform: translate(-50%, -50%);
          z-index: -1;
          color: lightgray;
      }

      #preview img.preview_img {
          width: 100%;
      }
  </style>

</head>

<body>
<header id="header"></header>

<div class="container d-flex justify-content-center mb-5">
  <form method="post" action="customer.do" class="row border m-3 p-3 rounded-3" enctype="multipart/form-data">
    <h2 class="mb-4">修改會員資料</h2>
    <div class="alert alert-warning <%= (updateFailed) ? "" : "d-none" %>" role="alert">
      此Email信箱已有人使用，請重新輸入！
    </div>
    <div class="col-md-4 mb-3">
      <label for="staticId" class="col-sm-2 col-form-label">會員ID</label>
      <div class="col-sm-10">
        <input type="text" name="customer_id" readonly class="form-control-plaintext" id="staticId"
               value="<%= customer.getCustomerId() %>">
      </div>
    </div>
    <div class="col-md-4 mb-3">
      <label for="staticPoint" class="col-sm-2 col-form-label">會員金</label>
      <div class="col-sm-10">
        <input type="text" readonly class="form-control-plaintext" id="staticPoint"
               value="<%= customer.getCustomerPoint() %>">
      </div>
    </div>
    <div class="col-md-4 mb-3">
      <div id="preview">
        <span class="text">預覽圖</span>
      </div>
    </div>
    <div class="col-12 mb-3">
      <label for="inputAvatar" class="form-label">大頭貼</label>
      <input type="file" name="avatar" class="form-control" id="inputAvatar">
    </div>
    <div class="col-12 mb-3">
      <label for="inputEmail" class="form-label">Email 信箱</label>
      <input type="email" name="email" class="form-control" id="inputEmail" placeholder="name@gmail.com"
             aria-describedby="validationEmail" value="<%= customer.getEmail() %>" required>
      <div class="valid-feedback">
        格式正確
      </div>
      <div id="validationEmail" class="invalid-feedback">
        請輸入正確的Email格式
      </div>
    </div>
    <div class="col-md-5 mb-3">
      <label for="inputName" class="form-label">姓名</label>
      <input type="text" name="customer_name" class="form-control" id="inputName" aria-describedby="validationName"
             value="<%= customer.getCustomerName() %>" required>
      <div class="valid-feedback">
        格式正確
      </div>
      <div id="validationName" class="invalid-feedback">
        請輸入姓名
      </div>
    </div>
    <div class="col-md-5 mb-3">
      <label for="inputNickname" class="form-label">暱稱</label>
      <input type="text" name="nickname" class="form-control" id="inputNickname" aria-describedby="validationNickname"
             value="<%= customer.getNickname() %>"
             required>
      <div class="valid-feedback">
        格式正確
      </div>
      <div id="validationNickname" class="invalid-feedback">
        請輸入暱稱
      </div>
    </div>
    <div class="col-md-2 mb-3">
      <label for="inputSex" class="form-label">性別</label>
      <select name="sex" id="inputSex" class="form-select">
        <option value="0" <%= (customer.getSex().equals("0")) ? "selected" : ""%> >男</option>
        <option value="1" <%= (customer.getSex().equals("1")) ? "selected" : ""%> >女</option>
      </select>
    </div>
    <div class="col-md-4 mb-3">
      <label for="inputPhone" class="form-label">手機號碼</label>
      <input type="text" name="phone" class="form-control" id="inputPhone" aria-describedby="validationPhone"
             value="<%= customer.getPhone() %>" required>
      <div class="valid-feedback">
        格式正確
      </div>
      <div id="validationPhone" class="invalid-feedback">
        請輸入正確的手機號碼格式
      </div>
    </div>
    <div class="col-md-4 mb-3">
      <label for="inputIdno" class="form-label">身份證</label>
      <input type="text" name="idno" class="form-control" id="inputIdno" aria-describedby="validationIdno"
             value="<%= customer.getIdno() %>" required>
      <div class="valid-feedback">
        格式正確
      </div>
      <div id="validationIdno" class="invalid-feedback">
        請輸入正確的身份證格式
      </div>
    </div>
    <div class="col-md-4 mb-3">
      <label for="inputBirth" class="form-label">生日</label>
      <input type="date" name="birth" class="form-control" id="inputBirth" aria-describedby="validationBirth"
             value="<%= customer.getBirth() %>" required>
      <div class="valid-feedback">
        格式正確
      </div>
      <div id="validationBirth" class="invalid-feedback">
        請輸入生日，日期不得大於今天
      </div>
    </div>
    <div class="col-12 mb-3">
      <label for="inputAddress" class="form-label">地址</label>
      <input type="text" name="address" class="form-control" id="inputAddress" aria-describedby="validationAddress"
             value="<%= customer.getAddress() %>"
             required>
      <div class="valid-feedback">
        格式正確
      </div>
      <div id="validationAddress" class="invalid-feedback">
        請輸入地址
      </div>
    </div>
    <div class="col-md-6 mt-3">
      <input type="hidden" name="action" value="update">
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
        history.back();
    });

    function validateRequired() {
        let isRequired = true;

        // Email
        const email = $("#inputEmail");
        if (email.val() === "")
            email.removeClass("is-valid").addClass("is-invalid");
        else
            email.removeClass("is-invalid").addClass("is-valid");

        if (email.hasClass("is-invalid"))
            isRequired = false;

        // 姓名
        const name = $("#inputName");
        if (name.val() === "")
            name.removeClass("is-valid").addClass("is-invalid");
        else
            name.removeClass("is-invalid").addClass("is-valid");

        if (name.hasClass("is-invalid"))
            isRequired = false;

        // 暱稱
        const nickname = $("#inputNickname");
        if (nickname.val() === "")
            nickname.removeClass("is-valid").addClass("is-invalid");
        else
            nickname.removeClass("is-invalid").addClass("is-valid");

        if (nickname.hasClass("is-invalid"))
            isRequired = false;

        // 手機號碼
        const phone = $("#inputPhone");
        if (phone.val() === "")
            phone.removeClass("is-valid").addClass("is-invalid");
        else
            phone.removeClass("is-invalid").addClass("is-valid");

        if (phone.hasClass("is-invalid"))
            isRequired = false;

        // 身份證
        const idno = $("#inputIdno");
        if (idno.val() === "")
            idno.removeClass("is-valid").addClass("is-invalid");
        else
            idno.removeClass("is-invalid").addClass("is-valid");

        if (idno.hasClass("is-invalid"))
            isRequired = false;

        // 生日
        const birth = $("#inputBirth");
        if (birth.val() === "")
            birth.removeClass("is-valid").addClass("is-invalid");
        else
            birth.removeClass("is-invalid").addClass("is-valid");

        if (birth.hasClass("is-invalid"))
            isRequired = false;

        // 地址
        const address = $("#inputAddress");
        if (address.val() === "")
            address.removeClass("is-valid").addClass("is-invalid");
        else
            address.removeClass("is-invalid").addClass("is-valid");

        if (address.hasClass("is-invalid"))
            isRequired = false;

        return isRequired;
    }

    function validateCustomized() {
        let isValidated = true;

        // Email
        const email = $("#inputEmail");
        const emailRegex = /^\w+((-\w+)|(\.\w+))*@[A-Za-z0-9]+(([.\-])[A-Za-z0-9]+)*\.[A-Za-z]+$/;
        if (!email.val().match(emailRegex)) {
            email.removeClass("is-valid").addClass("is-invalid");
            isValidated = false;
        } else {
            email.removeClass("is-invalid").addClass("is-valid");
        }

        // 手機號碼
        const phone = $("#inputPhone");
        const phoneRegex = /^09[0-9]{8}$/;
        if (!phone.val().match(phoneRegex)) {
            phone.removeClass("is-valid").addClass("is-invalid");
            isValidated = false
        } else {
            phone.removeClass("is-invalid").addClass("is-valid");
        }

        // 身份證
        const idno = $("#inputIdno");
        const idnoRegex = /^[A-Z][1-2][0-9]{8}$/;
        if (!idno.val().match(idnoRegex)) {
            idno.removeClass("is-valid").addClass("is-invalid");
            isValidated = false
        } else {
            idno.removeClass("is-invalid").addClass("is-valid");
        }

        // 生日，日期不得大於等於今日
        const birth = $("#inputBirth");
        const birthDate = new Date(birth.val()).getTime();
        console.log(birthDate)
        if (birthDate >= (new Date(Date.now() - 1000 * 60 * 60 * 24)).getTime()) {
            birth.removeClass("is-valid").addClass("is-invalid");
            isValidated = false
        } else {
            birth.removeClass("is-invalid").addClass("is-valid");
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