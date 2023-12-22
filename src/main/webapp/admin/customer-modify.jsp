<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.lazytravel.customer.entity.Customer" %>

<%
  Customer customer = (Customer) request.getAttribute("customer");
  String customerAvatar = request.getContextPath() + "/customer/ImageReader?id=" + customer.getCustomerId();
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>後臺 - 修改平臺會員</title>

  <link rel="icon" href="<%=request.getContextPath()%>/static/images/logo.ico" type="image/x-icon">

  <style>
      #preview {
          border: 1px solid lightgray;
          display: inline-block;
          width: 150px;
          min-height: 150px;
          position: relative;
      }

      /*#preview span.text {*/
      /*    position: absolute;*/
      /*    display: inline-block;*/
      /*    left: 50%;*/
      /*    top: 50%;*/
      /*    transform: translate(-50%, -50%);*/
      /*    z-index: -1;*/
      /*    color: lightgray;*/
      /*}*/

      #preview img.preview_img {
          width: 100%;
      }
  </style>
</head>

<body>
<div id="header"></div>

<div class="container mb-5">
  <form method="post" action="customer.do" class="row m-3 p-3">
    <h2 class="mb-4">修改會員資料</h2>
    <div class="row mb-3 mx-auto ps-md-5">
      <div class="col-sm-2 col-form-label fw-bold">大頭貼</div>
      <div id="preview" class="col-sm-10 m-2">
        <%--        <span class="text">預覽圖</span>--%>
        <img src="<%= customerAvatar %>" class="preview_img" alt="avatar">
      </div>
    </div>

    <div class="row mb-3 ps-md-5">
      <label for="inputStatus" class="col-sm-2 col-form-label fw-bold">狀態</label>
      <select name="customer_status" id="inputStatus" class="form-select col-sm-10 w-25">
        <option value="0" <%= (customer.getCustomerStatus().equals("0")) ? "selected" : ""%> >停用</option>
        <option value="1" <%= (customer.getCustomerStatus().equals("1")) ? "selected" : ""%> >啟用</option>
        <option value="2" <%= (customer.getCustomerStatus().equals("2")) ? "selected" : ""%> >未驗證</option>
      </select>
    </div>

    <div class="row mb-3 ps-md-5">
      <label for="staticId" class="col-sm-2 col-form-label fw-bold">會員ID</label>
      <div class="col-sm-10">
        <input type="text" name="customer_id" class="form-control-plaintext" id="staticId"
               value="<%= customer.getCustomerId() %>" readonly>
      </div>
    </div>

    <div class="row mb-3 ps-md-5">
      <label for="InputEmail" class="col-sm-2 col-form-label fw-bold">Email 信箱</label>
      <div class="col-sm-10">
        <input type="text" name="email" readonly class="form-control-plaintext" id="InputEmail"
               value="<%= customer.getEmail() %>">
      </div>
    </div>
    <div class="row mb-3 ps-md-5">
      <label for="inputName" class="col-sm-2 col-form-label fw-bold">姓名</label>
      <div class="col-sm-10">
        <input type="text" name="customer_name" readonly class="form-control-plaintext" id="inputName"
               value="<%= customer.getCustomerName() %>">
      </div>
    </div>
    <div class="row mb-3 ps-md-5">
      <label for="inputNickname" class="col-sm-2 col-form-label fw-bold">暱稱</label>
      <div class="col-sm-10">
        <input type="text" name="nickname" readonly class="form-control-plaintext" id="inputNickname"
               value="<%= customer.getNickname() %>">
      </div>
    </div>
    <div class="row mb-3 ps-md-5">
      <label for="inputSex" class="col-sm-2 col-form-label fw-bold">性別</label>
      <div class="col-sm-10">
        <input type="text" name="sex" readonly class="form-control-plaintext" id="inputSex"
               value="<%= (customer.getSex().equals("0") ? "男" : "女") %>">
      </div>
    </div>
    <div class="row mb-3 ps-md-5">
      <label for="staticPoint" class="col-sm-2 col-form-label fw-bold">會員金</label>
      <div class="col-sm-10">
        <input type="text" readonly class="form-control-plaintext" id="staticPoint"
               value="<%= customer.getCustomerPoint() %>">
      </div>
    </div>
    <div class="row mb-3 ps-md-5">
      <label for="inputPhone" class="col-sm-2 col-form-label fw-bold">手機號碼</label>
      <div class="col-sm-10">
        <input type="text" name="phone" readonly class="form-control-plaintext" id="inputPhone"
               value="<%= customer.getPhone() %>">
      </div>
    </div>
    <div class="row mb-3 ps-md-5">
      <label for="inputIdno" class="col-sm-2 col-form-label fw-bold">身份證</label>
      <div class="col-sm-10">
        <input type="text" name="idno" readonly class="form-control-plaintext" id="inputIdno"
               value="<%= customer.getIdno() %>">
      </div>
    </div>
    <div class="row mb-3 ps-md-5">
      <label for="inputDate" class="col-sm-2 col-form-label fw-bold">生日</label>
      <div class="col-sm-10">
        <input type="date" name="birth" readonly class="form-control-plaintext" id="inputDate"
               value="<%= customer.getBirth() %>">
      </div>
    </div>
    <div class="row mb-3 ps-md-5">
      <label for="inputAddress" class="col-sm-2 col-form-label fw-bold">地址</label>
      <div class="col-sm-10">
        <input type="text" name="address" readonly class="form-control-plaintext" id="inputAddress"
               value="<%= customer.getAddress() %>">
      </div>
    </div>
    <div class="row mb-3 ps-md-5">
      <label for="createTime" class="col-sm-2 col-form-label fw-bold">建立時間</label>
      <div class="col-sm-10">
        <input type="text" name="address" readonly class="form-control-plaintext" id="createTime"
               value="<%= customer.getCreateTime() %>">
      </div>
    </div>
    <div class="row mb-3 ps-md-5">
      <label for="updateTime" class="col-sm-2 col-form-label fw-bold">更新時間</label>
      <div class="col-sm-10">

        <input type="text" name="address" readonly class="form-control-plaintext" id="updateTime"
               value="<%= customer.getUpdateTime() %>">
      </div>
    </div>
    <div class="row">
      <div class="col-md-6 mt-3">
        <input type="hidden" name="action" value="changeStatus">
        <button type="submit" id="btn-confirm" class="btn w-100" style="background-color: #6B705C;">
          <span class="text-light">確認</span>
        </button>
      </div>
      <div class="col-md-6 mt-3">
        <button type="button" id="btn-cancel" class="btn w-100" style="background-color: #B7B7A4;">
          <span class="text-light">取消</span>
        </button>
      </div>
    </div>
  </form>
</div>


<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script>
    $(function () {
        $("#header").load("<%=request.getContextPath()%>/admin/header.jsp");

    });

    $("#btn-cancel").on("click", function () {
        window.location.replace("<%=request.getContextPath()%>/admin/customer.jsp");
    });
</script>
</body>

</html>