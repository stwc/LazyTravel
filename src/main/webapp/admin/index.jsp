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
  <%--  <div class="row mb-4">--%>
  <%--    <div class="col-12">--%>
  <%--      <h4>行事曆</h4>--%>
  <%--      <div id="calendar"></div>--%>
  <%--    </div>--%>
  <%--  </div>--%>

  <div class="row mt-3">
    <div class="col-md-3">
      <h4>
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
             class="bi bi-person-fill" viewBox="0 0 16 16">
          <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
        </svg>
        平臺會員
      </h4>
      <ul class="mt-3">
        <li class="mb-2">
          <a href="<%=request.getContextPath()%>/admin/customer.jsp" class="">會員資料</a>
        </li>
        <li class="mb-2">
          <a href="<%=request.getContextPath()%>/admin/orderList.jsp" class="">會員訂單</a>
        </li>
        <li class="mb-2">
          <a href="<%=request.getContextPath()%>/admin/couponList.jsp" class="">優惠券</a>
        </li>
      </ul>
    </div>

    <div class="col-md-3">
      <h4 class="mt-2">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pin-map-fill" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M3.1 11.2a.5.5 0 0 1 .4-.2H6a.5.5 0 0 1 0 1H3.75L1.5 15h13l-2.25-3H10a.5.5 0 0 1 0-1h2.5a.5.5 0 0 1 .4.2l3 4a.5.5 0 0 1-.4.8H.5a.5.5 0 0 1-.4-.8z"/>
          <path fill-rule="evenodd" d="M4 4a4 4 0 1 1 4.5 3.969V13.5a.5.5 0 0 1-1 0V7.97A4 4 0 0 1 4 3.999z"/>
        </svg>
        美食景點
      </h4>
      <ul class="mt-3">
        <li class="mb-2">
          <a href="<%=request.getContextPath()%>/foodscape/jsp/foodscape.jsp" class="">美食景點</a>
        </li>
        <li class="mb-2">
          <a href="<%=request.getContextPath()%>/foodscape/jsp/opentime.jsp" class="">營業時間</a>
        </li>
      </ul>
    </div>

    <div class="col-md-3">
      <h4 class="mt-2">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar-date" viewBox="0 0 16 16">
          <path d="M6.445 11.688V6.354h-.633A12.6 12.6 0 0 0 4.5 7.16v.695c.375-.257.969-.62 1.258-.777h.012v4.61h.675zm1.188-1.305c.047.64.594 1.406 1.703 1.406 1.258 0 2-1.066 2-2.871 0-1.934-.781-2.668-1.953-2.668-.926 0-1.797.672-1.797 1.809 0 1.16.824 1.77 1.676 1.77.746 0 1.23-.376 1.383-.79h.027c-.004 1.316-.461 2.164-1.305 2.164-.664 0-1.008-.45-1.05-.82h-.684zm2.953-2.317c0 .696-.559 1.18-1.184 1.18-.601 0-1.144-.383-1.144-1.2 0-.823.582-1.21 1.168-1.21.633 0 1.16.398 1.16 1.23"/>
          <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5M1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4z"/>
        </svg>
        行程
      </h4>
      <ul class="mt-3">
        <li class="mb-2">
          <a href="<%=request.getContextPath()%>/journey/admin/journey_list.jsp" class="">行程管理</a>
        </li>
        <li class="mb-2">
          <a href="<%=request.getContextPath()%>/journey/admin/tourGroup_list.jsp" class="">旅行團</a>
        </li>
      </ul>
    </div>

    <div class="col-md-3">
      <h4 class="mt-2">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pen-fill" viewBox="0 0 16 16">
          <path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001"/>
        </svg>
        部落格
      </h4>
      <ul class="mt-3">
        <li class="mb-2">
          <a href="<%=request.getContextPath()%>/admin/backbloglist.jsp" class="">文章</a>
        </li>
      </ul>
    </div>

    <div class="col-md-3">
      <h4 class="mt-2">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-telephone-fill" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M1.885.511a1.745 1.745 0 0 1 2.61.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.678.678 0 0 0 .178.643l2.457 2.457a.678.678 0 0 0 .644.178l2.189-.547a1.745 1.745 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.634 18.634 0 0 1-7.01-4.42 18.634 18.634 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877L1.885.511z"/>
        </svg>
        客服
      </h4>
      <ul class="mt-3">
        <li class="mb-2">
          <a href="<%=request.getContextPath()%>/customerService/backContentMail.jsp" class="">客服</a>
        </li>
      </ul>
    </div>

    <div class="col-md-3">
      <h4 class="mt-2">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-kanban" viewBox="0 0 16 16">
          <path d="M13.5 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1h-11a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1zm-11-1a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h11a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z"/>
          <path d="M6.5 3a1 1 0 0 1 1-1h1a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1h-1a1 1 0 0 1-1-1zm-4 0a1 1 0 0 1 1-1h1a1 1 0 0 1 1 1v7a1 1 0 0 1-1 1h-1a1 1 0 0 1-1-1zm8 0a1 1 0 0 1 1-1h1a1 1 0 0 1 1 1v10a1 1 0 0 1-1 1h-1a1 1 0 0 1-1-1z"/>
        </svg>
        後臺管理
      </h4>
      <ul class="mt-3">
        <li class="mb-2">
          <a href="<%=request.getContextPath()%>/admin/user.jsp" class="">後臺使用者</a>
        </li>
      </ul>
    </div>

  </div>


</div>

<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.10/index.global.min.js'></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script>
    $(function () {
        $("#header").load("<%=request.getContextPath()%>/admin/header.jsp");
    });

    // document.addEventListener('DOMContentLoaded', function () {
    //     var calendarEl = document.getElementById('calendar');
    //     var calendar = new FullCalendar.Calendar(calendarEl, {
    //         initialView: 'dayGridMonth',
    //         locale: 'zh-tw',
    //         height: 'auto',
    //         contentHeight: 'auto',
    //         aspectRatio: 1.35,
    //         navLinks: true,
    //         headerToolbar: {
    //             left: 'prev,next today',
    //             center: 'title',
    //             right: 'dayGridMonth,timeGridWeek,timeGridDay'
    //         },
    //
    //         events: [{
    //             title: '練習CSS',
    //             start: '2023-12-10',
    //             end: '2023-12-12'
    //         }, {
    //             title: '做專題',
    //             start: '2023-12-14T14:00:00',
    //             end: '2023-12-14T16:00:00'
    //         }, {
    //             title: '練習JavaScript',
    //             start: '2023-12-15',
    //             allDay: false
    //         }]
    //     });
    //     calendar.render();
    // });
</script>
</body>

</html>