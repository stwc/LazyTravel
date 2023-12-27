<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>後臺首頁</title>
  <link rel="icon" href="<%=request.getContextPath()%>/static/images/logo.ico" type="image/x-icon">

  <style>
      /* #calendar-div {
        width: 75%;
        height: 600px;
      } */
  </style>
</head>

<body>
<div id="header"></div>

<div class="container">
  <h3 class="my-3">後臺首頁</h3>
  <div class="row mb-4">
    <div class="col-12">
      <h4>行事曆</h4>
      <div id="calendar"></div>
    </div>
  </div>

  <div class="row gx-3">
    <div class="col-md-6">
      <h4>最新訂單</h4>
      <table class="table table-striped table-bordered">
        <thead>
        <tr>
          <th scope="col">日期</th>
          <th scope="col">訂單編號</th>
          <th scope="col">金額</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>2023/12/1</td>
          <td>20231201001</td>
          <td>$4500</td>
        </tr>
        <tr>
          <td>2023/12/1</td>
          <td>20231201001</td>
          <td>$4500</td>
        </tr>
        <tr>
          <td>2023/12/1</td>
          <td>20231201001</td>
          <td>$4500</td>
        </tr>
        </tbody>
      </table>
    </div>

    <div class="col-md-6">
      <h4>客服未回覆訊息</h4>
      <table class="table table-striped table-bordered">
        <thead>
        <tr>
          <th scope="col">日期</th>
          <th scope="col">信件主旨</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>2023/12/1</td>
          <td>訂單問題</td>
        </tr>
        <tr>
          <td>2023/12/1</td>
          <td>詢問行程</td>
        </tr>
        <tr>
          <td>2023/12/1</td>
          <td>取消訂單</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>


</div>

<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.10/index.global.min.js'></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script>
    function init() {

    }

    $(function () {
        $("#header").load("<%=request.getContextPath()%>/admin/header.jsp");

        init()
    });

    document.addEventListener('DOMContentLoaded', function () {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'dayGridMonth',
            locale: 'zh-tw',
            height: 'auto',
            contentHeight: 'auto',
            aspectRatio: 1.35,
            navLinks: true,
            headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay'
            },

            events: [{
                title: '練習CSS',
                start: '2023-12-10',
                end: '2023-12-12'
            }, {
                title: '做專題',
                start: '2023-12-14T14:00:00',
                end: '2023-12-14T16:00:00'
            }, {
                title: '練習JavaScript',
                start: '2023-12-15',
                allDay: false
            }]
        });
        calendar.render();
    });
</script>
</body>

</html>