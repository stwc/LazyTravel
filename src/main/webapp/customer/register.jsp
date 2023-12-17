<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>註冊會員</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="icon" href="<%=request.getContextPath()%>/static/images/logo.ico" type="image/x-icon">

</head>

<body>
  <header id="header"></header>

  <div class="container d-flex justify-content-center mb-5">
    <form method="post" action="<%=request.getContextPath()%>/customer/register.do" class="row border m-3 p-3 rounded-3">
      <h2 class="mb-4">註冊會員</h2>
      <div class="col-12 mb-3">
        <label for="inputEmail" class="form-label">Email 信箱</label>
        <input type="email" name="email" class="form-control" id="inputEmail" placeholder="name@gmail.com"
          aria-describedby="validationEmail" required>
        <div class="valid-feedback">
          格式正確
        </div>
        <div id="validationEmail" class="invalid-feedback">
          請輸入正確的Email格式
        </div>
      </div>
      <div class="col-md-6 mb-3">
        <label for="inputPassword1" class="form-label">密碼</label>
        <input type="password" name="customer_passwd" class="form-control" id="inputPassword1"
          placeholder="密碼長度限制8~24位，只能用英文大寫、小寫或數字" aria-describedby="passwordHelp1 validationPassword1" required>
        <!-- <div id="passwordHelp1" class="form-text">密碼長度至少8碼以上，只能用英文大寫、小寫或數字</div> -->
        <div class="valid-feedback">
          格式正確
        </div>
        <div id="validationPassword1" class="invalid-feedback">
          密碼長度限制8~24位，只能用英文大寫、小寫或數字
        </div>
      </div>
      <div class="col-md-6 mb-3">
        <label for="inputPassword2" class="form-label">確認密碼</label>
        <input type="password" class="form-control" id="inputPassword2" placeholder="請再次輸入密碼"
          aria-describedby="passwordHelp2 validationPassword2" required>
        <!-- <div id="passwordHelp2 validationPassword2" class="form-text">請再次輸入密碼</div> -->
        <div class="valid-feedback">
          兩次輸入的密碼相符
        </div>
        <div id="validationPassword2" class="invalid-feedback">
          兩次輸入的密碼不相符，請重新輸入
        </div>
      </div>
      <div class="col-md-5 mb-3">
        <label for="inputName" class="form-label">姓名</label>
        <input type="text" name="customer_name" class="form-control" id="inputName" aria-describedby="validationName" required>
        <div class="valid-feedback">
          格式正確
        </div>
        <div id="validationName" class="invalid-feedback">
          請輸入姓名
        </div>
      </div>
      <div class="col-md-5 mb-3">
        <label for="inputNickname" class="form-label">暱稱</label>
        <input type="text" name="nickname" class="form-control" id="inputNickname" aria-describedby="validationNickname" required>
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
          <option value="0" selected>男</option>
          <option value="1">女</option>
        </select>
      </div>
      <div class="col-md-4 mb-3">
        <label for="inputPhone" class="form-label">手機號碼</label>
        <input type="text" name="phone" class="form-control" id="inputPhone" aria-describedby="validationPhone"
          required>
        <div class="valid-feedback">
          格式正確
        </div>
        <div id="validationPhone" class="invalid-feedback">
          請輸入正確的手機號碼格式
        </div>
      </div>
      <div class="col-md-4 mb-3">
        <label for="inputIdno" class="form-label">身份證</label>
        <input type="text" name="idno" class="form-control" id="inputIdno" aria-describedby="validationIdno" required>
        <div class="valid-feedback">
          格式正確
        </div>
        <div id="validationIdno" class="invalid-feedback">
          請輸入正確的身份證格式
        </div>
      </div>
      <div class="col-md-4 mb-3">
        <label for="inputBirth" class="form-label">生日</label>
        <input type="date" name="birth" class="form-control" id="inputBirth" aria-describedby="validationBirth" required>
        <div class="valid-feedback">
          格式正確
        </div>
        <div id="validationBirth" class="invalid-feedback">
          請輸入生日
        </div>
      </div>
      <div class="col-12 mb-3">
        <label for="inputAddress" class="form-label">地址</label>
        <input type="textx" name="address" class="form-control" id="inputAddress" aria-describedby="validationAddress" required>
        <div class="valid-feedback">
          格式正確
        </div>
        <div id="validationAddress" class="invalid-feedback">
          請輸入地址
        </div>
      </div>
      <div class="col-12 mt-3">
<%--        <input type="hidden" name="action" value="insert">--%>
        <button type="submit" id="btn-signup" class="btn w-100" style="background-color: #6B705C;"><span
            class="text-light">註冊</span></button>
      </div>
    </form>
  </div>

  <footer id="footer"></footer>


  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<%--  <script src="<%=request.getContextPath()%>/customer/js/register.js"></script>--%>
  <script>
    // Loading header and footer
    $(function () {
      $("#header").load("<%=request.getContextPath()%>/components/html/header.jsp");
      $("#footer").load("<%=request.getContextPath()%>/components/html/footer.jsp");
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

      // Password1
      const password1 = $("#inputPassword1");
      if (password1.val() === "")
        password1.removeClass("is-valid").addClass("is-invalid");
      else
        password1.removeClass("is-invalid").addClass("is-valid");

      if (password1.hasClass("is-invalid"))
        isRequired = false;

      // Password2
      const password2 = $("#inputPassword2");
      if (password2.val() === "")
        password2.removeClass("is-valid").addClass("is-invalid");
      else
        password2.removeClass("is-invalid").addClass("is-valid");

      if (password2.hasClass("is-invalid"))
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
      const emailRegex = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
      if (!email.val().match(emailRegex)) {
        email.removeClass("is-valid").addClass("is-invalid");
        isValidated = false;
      } else {
        email.removeClass("is-invalid").addClass("is-valid");
      }

      // Password
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

      return isValidated;
    }



    $("#btn-signup").on("click", function (e) {
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