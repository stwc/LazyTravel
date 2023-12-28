<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>後臺 - 後臺角色</title>

  <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
  <link rel="icon" href="<%=request.getContextPath()%>/static/images/logo.ico" type="image/x-icon">

  <style>
      button.btn-modify {
          background-color: #9C6644;
          color: white;
      }

      a#add {
          background-color: #B7B7A4;
          color: white;
      }

      a#add:hover {
          background-color: #6B705C;
          color: white;
      }
  </style>
</head>

<body>
<div id="header"></div>

<div id="main" class="p-3">
  <div class="mx-3 mb-4 d-flex align-items-start">
    <h3 class="d-inline-block me-3">後臺角色</h3>
    <a href="role-add.html" id="add" class="btn">新增</a>
  </div>

  <div class="table-responsive mx-3">
    <table id="example" class="table table-striped" style="width:100%">
      <thead>
      <tr>
        <th scope="col">修改</th>
        <th scope="col">ID</th>
        <th scope="col">名稱</th>
        <th scope="col">說明</th>
      </tr>
      </thead>
      <tbody>
      <%--      <tr>--%>
      <%--        <td>--%>
      <%--          <form method="post" action="">--%>
      <%--            <button type="submit" class="btn-modify btn">修改</button>--%>
      <%--            <input type="hidden" name="customer_id" value="">--%>
      <%--            <input type="hidden" name="action" value="modify">--%>
      <%--          </form>--%>
      <%--        </td>--%>
      <%--        <td>1</td>--%>
      <%--        <td>管理員</td>--%>
      <%--        <td>擁有所有權限</td>--%>
      <%--      </tr>--%>
      </tbody>
    </table>
  </div>

</div>


<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
<script>
    function init() {
        $.ajax({
            url: "<%=request.getContextPath()%>/admin/role.do",
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
                        '<form method="post" action="role.do">' +
                        '<button type="submit" class="btn-modify btn">修改</button>' +
                        '<input type="hidden" name="customer_id" value="' + item.roleId + '">' +
                        '<input type="hidden" name="action" value="getOneModify">' +
                        '</form>' +
                        '</td>';

                    tmpArr.push(modifyBtn);
                    tmpArr.push(item.roleId);
                    tmpArr.push(item.roleName);
                    tmpArr.push(item.roleDescr);
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

        init()
    });
</script>
</body>

</html>