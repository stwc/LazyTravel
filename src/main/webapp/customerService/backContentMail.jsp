<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.customerservice.entity.*"%>
<%@ page import="com.lazytravel.customerservice.dao.*"%>
<%@ page import="com.lazytravel.customerservice.service.*"%>
<%@ include file="/admin/header.html"%>
<!DOCTYPE html>
<html lang="en">
<%
CSMailService csMailSvc = new CSMailServiceImpl();
List<CSMail> list = csMailSvc.getAllCSMails();
pageContext.setAttribute("list", list);
%>

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>客服信箱</title>

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css" />
<link rel="icon" href="../../static/images/logo.ico" type="image/x-icon" />
</head>

<body>
<header id="header"></header>

    <div class="container py-5">
        <h2 class="text-center mb-4">客服信箱</h2>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">會員ID</th>
                        <th scope="col">會員姓名</th>
                        <th scope="col">發送日期</th>
                        <th scope="col">發送時間</th>
                        <th scope="col">信件標題</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>黃喵咪</td>
                        <td>2023-12-21</td>
                        <td>09:30 AM</td>
                        <td>標題內容</td>
                        <td><a href="#" class="btn btn-secondary" style="background-color: #6B705C">查看詳情</a></td>
                    </tr>
                    <!-- 可以添加更多類似上面結構的表格行 -->
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