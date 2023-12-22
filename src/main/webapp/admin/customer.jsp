<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>後臺 - 平臺會員</title>

  <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
  <link rel="icon" href="<%=request.getContextPath()%>/static/images/logo.ico" type="image/x-icon">

  <style>
      button.btn-modify {
          background-color: #9C6644;
          color: white;
      }
  </style>
</head>

<body>
<div id="header"></div>

<div id="main" class="p-3">
  <h3 class="mx-3 mb-4">平臺會員</h3>

  <div class="table-responsive mx-3">
    <table id="example" class="table table-striped" style="width:100%">
      <thead>
      <tr>
        <th scope="col">修改</th>
        <th scope="col">ID</th>
        <th scope="col">姓名</th>
        <th scope="col">暱稱</th>
        <th scope="col">電話</th>
        <th scope="col">Email</th>
        <th scope="col">狀態</th>
        <th scope="col">建立時間</th>
        <th scope="col">更新時間</th>
      </tr>

      </thead>
      <tbody id="table-content">
      <!--          <tr>-->
      <!--            <td>-->
      <!--              <form method="post" action="">-->
      <!--                <button type="submit" class="btn-modify btn">修改</button>-->
      <!--                <input type="hidden" name="customer_id" value="">-->
      <!--                <input type="hidden" name="action" value="modify">-->
      <!--              </form>-->
      <!--            </td>-->
      <!--            <td>1</td>-->
      <!--            <td>王小明</td>-->
      <!--            <td>小明</td>-->
      <!--            <td>0910096787</td>-->
      <!--            <td>mrwang@gmail.com</td>-->
      <!--            <td>1</td>-->
      <!--            <td>2023-11-21 23:56:59.0</td>-->
      <!--            <td>2023-11-21 23:56:59.0</td>-->
      <!--          </tr>-->
      </tbody>
    </table>
  </div>

</div>


<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
<script>
    function init() {
        $.ajax({
            url: "<%=request.getContextPath()%>/admin/customer.do",
            type: "GET",
            data: {"action": "getall"},
            dataType: "json",
            beforeSend: function () {
                // $("ul.task_list").html(
                //         '<li style="text-align: center;"><i class="fas fa-spinner fa-spin fa-3x"></i></li>'
                // );
            },
            success: function (data) {
                let dataSet = [];
                data.forEach((item) => {
                    let tmpArr = [];
                    let modifyBtn =
                        '<td>' +
                        '<form method="post" action="customer.do">' +
                        '<button type="submit" class="btn-modify btn">修改</button>' +
                        '<input type="hidden" name="customer_id" value="' + item.customerId + '">' +
                        '<input type="hidden" name="action" value="getOneModify">' +
                        '</form>' +
                        '</td>';

                    let customerStatus;
                    switch (item.customerStatus) {
                        case "0":
                            customerStatus = "停用";
                            break;
                        case "1":
                            customerStatus = "啟用";
                            break;
                        case "2":
                            customerStatus = "未驗證";
                            break;
                    }

                    tmpArr.push(modifyBtn);
                    tmpArr.push(item.customerId);
                    tmpArr.push(item.customerName);
                    tmpArr.push(item.nickname);
                    tmpArr.push(item.phone);
                    tmpArr.push(item.email);
                    tmpArr.push(customerStatus);
                    tmpArr.push(item.createTime);
                    tmpArr.push(item.updateTime);
                    dataSet.push(tmpArr)
                });

                new DataTable('#example', {
                    data: dataSet
                });

            },
            error: function () {
                console.log("init error");
            },
        });
    }

    $(function () {
        $("#header").load("<%=request.getContextPath()%>/admin/header.jsp");
        // new DataTable('#example');

        init()
    });
</script>
</body>

</html>