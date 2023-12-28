<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  Boolean createFailed = false;
  if (request.getAttribute("createFailed") != null)
    createFailed = (Boolean) request.getAttribute("createFailed");
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>後臺 - 新增後臺使用者</title>
  <link rel="icon" href="<%=request.getContextPath()%>/static/images/logo.ico" type="image/x-icon">

  <style>

  </style>
</head>

<body>
<div id="header"></div>

<div class="container">
  <form method="post" action="user.do" class="row m-3 p-3">
    <h2 class="mb-4">新增後臺使用者</h2>
    <div id="alert-fail" class="alert alert-warning <%= (createFailed) ? "" : "d-none" %>" role="alert">
      使用者新增失敗，該帳號已存在
    </div>

    <div class="col-12 mb-3">
      <label for="InputUsername" class="form-label">帳號</label>
      <input type="text" name="username" class="form-control" id="InputUsername" aria-describedby="validationUsername"
             required>
      <div class="valid-feedback">
        格式正確
      </div>
      <div id="validationUsername" class="invalid-feedback">
        請輸入帳號
      </div>
    </div>
    <div class="col-md-6 mb-3">
      <label for="InputPassword1" class="form-label">密碼</label>
      <input type="password" name="user_passwd" class="form-control" id="InputPassword1"
             placeholder="密碼長度限制8~24位，只能用英文大寫、小寫或數字"
             aria-describedby="passwordHelp1 validationPassword1" required>
      <div class="valid-feedback">
        格式正確
      </div>
      <div id="validationPassword1" class="invalid-feedback">
        密碼長度限制8~24位，只能用英文大寫、小寫或數字
      </div>
    </div>
    <div class="col-md-6 mb-3">
      <label for="InputPassword2" class="form-label">確認密碼</label>
      <input type="password" class="form-control" id="InputPassword2" placeholder="請再次輸入密碼"
             aria-describedby="passwordHelp2 validationPassword2" required>
      <div class="valid-feedback validationPassword2">
        兩次輸入的密碼相符
      </div>
      <div id="validationPassword2" class="invalid-feedback">
        兩次輸入的密碼不相符，請重新輸入
      </div>
    </div>
    <div class="col-md-6 mb-3">
      <label for="inputRole" class="form-label">角色</label>
      <select name="role" id="inputRole" class="form-select" aria-describedby="validationRole">
        <option value="-1" selected>請選擇...</option>
        <!--          <option value="1">管理員</option>-->
      </select>
      <div class="valid-feedback">
        格式正確
      </div>
      <div id="validationRole" class="invalid-feedback">
        請選擇角色
      </div>
    </div>
    <div class="col-md-6 mb-3">
      <label for="inputStatus" class="form-label">狀態</label>
      <select name="status" id="inputStatus" class="form-select">
        <option value="0">停用</option>
        <option value="1" selected>啟用</option>
      </select>
    </div>
    <div class="col-md-6 mt-3">
      <input type="hidden" name="action" value="create">
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


<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script>

    function getRoles() {
        $.ajax({
            url: "<%=request.getContextPath()%>/admin/role.do",
            type: "GET",
            data: {"action": "getall"},
            dataType: "json",
            success: function (data) {
                let roles = $("#inputRole");
                data.forEach((item) => {
                    let option = '<option value="' + item.roleId + '">' + item.roleName + '</option>';
                    roles.append(option)
                });

            },
            error: function () {
                console.log("Get roles failed.");
            },
        });
    }

    function init() {
        getRoles();
    }

    function validateRequired() {
        let isRequired = true;

        // 帳號
        const username = $("#InputUsername");
        if (username.val() === "")
            username.removeClass("is-valid").addClass("is-invalid");
        else
            username.removeClass("is-invalid").addClass("is-valid");

        if (username.hasClass("is-invalid"))
            isRequired = false;

        // 角色
        const role = $("#inputRole");
        if (role.val() === "-1")
            role.removeClass("is-valid").addClass("is-invalid");
        else
            role.removeClass("is-invalid").addClass("is-valid");

        if (role.hasClass("is-invalid"))
            isRequired = false;

        // Password1
        const password1 = $("#InputPassword1");
        if (password1.val() === "")
            password1.removeClass("is-valid").addClass("is-invalid");
        else
            password1.removeClass("is-invalid").addClass("is-valid");

        if (password1.hasClass("is-invalid"))
            isRequired = false;

        // Password2
        const password2 = $("#InputPassword2");
        if (password2.val() === "")
            password2.removeClass("is-valid").addClass("is-invalid");
        else
            password2.removeClass("is-invalid").addClass("is-valid");

        if (password2.hasClass("is-invalid"))
            isRequired = false;

        return isRequired;
    }

    function validateCustomized() {
        console.log("validateCustomized")
        let isValidated = true;

        // Password
        const password1 = $("#InputPassword1");
        const password2 = $("#InputPassword2");
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


    $(function () {
        $("#header").load("<%=request.getContextPath()%>/admin/header.jsp");

        init()

        $("#btn-cancel").on("click", function () {
            window.location.replace("<%=request.getContextPath()%>/admin/user.jsp");
            // history.back();
        });

        $("#btn-confirm").on("click", function (e) {
            e.preventDefault();

            if (validateRequired() && validateCustomized()) {
                console.log("Validation succeeded.")
                e.target.closest("form").submit();
            } else {
                console.log("Validation failed.")
            }
        });
    });
</script>
</body>

</html>