<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>後臺 - 新增或修改後臺使用者</title>
  <link rel="icon" href="<%=request.getContextPath()%>/static/images/logo.ico" type="image/x-icon">

  <style>

  </style>
</head>

<body>
<div id="header"></div>

<div class="container">
  <form method="post" action="user.do" class="row m-3 p-3">
    <h2 class="mb-4">新增或修改後臺使用者</h2>
    <div class="col-md-2 mb-3">
      <label for="staticId" class="col-md-6 col-form-label">使用者ID</label>
      <input type="text" readonly class="form-control-plaintext" id="staticId" value="1">
    </div>
    <div class="col-md-10 mb-3">
      <label for="InputUsername" class="form-label">帳號</label>
      <input type="text" name="username" class="form-control" id="InputUsername" required>
    </div>
    <div class="col-md-6 mb-3">
      <label for="InputPassword1" class="form-label">密碼</label>
      <input type="password" name="user_passwd" class="form-control" id="InputPassword1"
             aria-describedby="passwordHelp1">
      <div id="passwordHelp1" class="form-text">密碼長度至少8碼以上</div>
    </div>
    <div class="col-md-6 mb-3">
      <label for="InputPassword2" class="form-label">確認密碼</label>
      <input type="password" class="form-control" id="InputPassword2" aria-describedby="passwordHelp2">
      <div id="passwordHelp2" class="form-text">請再次輸入密碼</div>
    </div>
    <div class="col-md-6 mb-3">
      <label for="inputRole" class="form-label">角色</label>
      <select name="sex" id="inputRole" class="form-select">
        <!--          <option selected>請選擇...</option>-->
        <!--          <option value="1">管理員</option>-->
      </select>
    </div>
    <div class="col-md-6 mb-3">
      <label for="inputStatus" class="form-label">狀態</label>
      <select name="status" id="inputStatus" class="form-select">
        <option value="0">停用</option>
        <option value="1">啟用</option>
      </select>
    </div>
    <div class="col-12 mb-3">
      <label for="staticCreateTime" class="col-md-6 col-form-label">建立時間</label>
      <input type="text" readonly class="form-control-plaintext" id="staticCreateTime" value="1970-01-01">
    </div>
    <div class="col-12 mb-3">
      <label for="staticUpdateTime" class="col-md-6 col-form-label">更新時間</label>
      <input type="text" readonly class="form-control-plaintext" id="staticUpdateTime" value="1970-01-01">
    </div>
    <div class="col-md-6 mt-3">
      <input type="hidden" name="action" value="signup">
      <button type="submit" id="btn-confirm" class="btn w-100" style="background-color: #6B705C;">
        <span class="text-light">確認</span>
      </button>
    </div>
    <div class="col-md-6 mt-3">
      <button type="submit" id="btn-cancel" class="btn w-100" style="background-color: #B7B7A4;">
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

    function getUserData(userId) {
        $.ajax({
            url: "<%=request.getContextPath()%>/admin/user.do",
            type: "GET",
            data: {"action": "getone", "id": userId},
            dataType: "json",
            success: function (data) {
                $("#staticId").val(userId);
                $("#InputUsername").val(data.username);

                $("#inputRole > option").each(function () {
                    if ($(this).val() === data.roleName) {
                        $("#inputRole").val($(this).val());
                        // console.log('roleId: ' +  $("#inputRole").val());
                        // console.log('this: ' + $(this).val());
                    }
                });

                $("#inputStatus > option").each(function () {
                    if ($(this).val() === data.userStatus) {
                        $("#inputStatus").val($(this).val());
                        // console.log('select: ' +  $("#inputStatus").val());
                        // console.log('this: ' + $(this).val());
                    }
                });

                $("#staticCreateTime").val(data.createTime);
                $("#staticUpdateTime").val(data.updateTime);

            },
            error: function () {
                console.log("Get user data failed.");
            },
        });
    }

    function init() {
        getRoles();

        let urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has("user_id")) {
            getUserData(urlParams.get("user_id"));
        }
    }

    $(function () {
        $("#header").load("<%=request.getContextPath()%>/admin/header.jsp");

        init()
    });
</script>
</body>

</html>