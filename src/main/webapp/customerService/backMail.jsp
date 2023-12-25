<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.customerservice.entity.*"%>
<%@ page import="com.lazytravel.customerservice.dao.*"%>
<%@ page import="com.lazytravel.customerservice.service.*"%>
<%@ include file="/admin/header.html"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
CSMailService csMailSvc = new CSMailServiceImpl();
List<CSMail> list = csMailSvc.getAllCSMails();
pageContext.setAttribute("list", list);
%>


<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>後臺 - 所有訊息資料</title>

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css" />
<link rel="icon" href="../../static/images/logo.ico" type="image/x-icon" />

<style>
button.btn-modify {
	background-color: #9c6644;
	color: white;
}

a#add {
	background-color: #b7b7a4;
	color: white;
}

a#add:hover {
	background-color: #6b705c;
	color: white;
}
</style>
</head>

<body>
	<div id="header"></div>

	<div id="main" class="p-3">
		<div class="mx-3 mb-4 d-flex align-items-start">
			<h3 class="d-inline-block me-3">所有訊息資料</h3>
		</div>

		<div class="table-responsive mx-4">
			<table id="example" class="table table-striped" style="width: 100%">
				<thead>
					<tr>
						<th scope="col">信件ID</th>
						<th scope="col">會員ID</th>
						<th scope="col">信件標題</th>
						<th scope="col">建立時間</th>
						<th scope="col">最後訊息時間</th>
						<th scope="col">信件狀態</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="csMail" items="${list}">
						<tr>
							<td>${csMail.getMailId()}</td>
							<td>${csMail.customer.customerId}</td>
							<td>${csMail.getTitle()}</td>
							<td>${csMail.createTime}</td>
							<td>${csMail.lastMsgTime}</td>
							<td>${csMail.csMailStatus}</td>
							<td>
					<form method="post" ACTION="<%=request.getContextPath()%>/customerService/CSMail.do" style="margin-bottom: 0px;">
                    <button type="submit" name="action" value="getOne_For_Update" class="btn-modify btn" class="btn-modify btn" style="background-color: #6B705C">查看詳情</button>
                    <input type="hidden" name="mailId"  value="${csMail.getMailId()}">
                </form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
	<script>
		$(function() {
			$("#header").load("../../admin/header.html");
			new DataTable("#example");
		});
	</script>
</body>
</html>