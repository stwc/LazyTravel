                                                                                                                                                                                                                                                                                                                       <%@page import="com.lazytravel.customerservice.entity.CSMail"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.customerservice.entity.*"%>
<%@ page import="com.lazytravel.customerservice.dao.*"%>
<%@ page import="com.lazytravel.customerservice.service.*"%>
<%@ include file="/admin/header.jsp"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>後臺 - 所有訊息內文</title>

    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css" />

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

<%
CSMailService csMailSvc = new CSMailServiceImpl();
List<CSMail> list = csMailSvc.getAllCSMails();
pageContext.setAttribute("list",list);
%>

<body>
<!--     <div id="header"></div> -->

    <div id="main" class="p-3">
        <div class="mx-3 mb-4 d-flex align-items-start">
            <h3 class="d-inline-block me-3">客服信箱</h3>
        </div>

        <div class="table-responsive mx-4">
            <table id="example" class="table table-striped" style="width: 100%">
                <thead>
                    <tr>
                        <th scope="col">信件ID</th>
                        <th scope="col">會員ID</th>
                        <th scope="col">客戶時間</th>
                        <th scope="col">客戶問題</th>
                        <th scope="col">客服時間</th>
                        <th scope="col">客服回答</th>
                        <th scope="col">信件狀態</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="csMail" items="${list}">
                        <tr>
                            <td>${csMail.getMailId()}</td>
                            <td>${csMail.customer.customerId}</td>
                            <td>${csMail.createTime}</td>
                            <td>${csMail.questions}</td>
                            <td>${csMail.RECEIVED_TIME}</td>
                            <td>${csMail.answer}</td>
                            <td>${csMail.csMailStatus}</td>
                            
                            <td>
                            	<form method="post" action="<%=request.getContextPath()%>/customerService/CSMail.do">
				                    <button type="submit" class="btn btn-secondary btn-modify" style="background-color: #6B705C">回覆</button> 
				                    <input type="hidden" name="mail_Id" value="${csMail.mailId}" />
				                    <input type="hidden" name="action" value="toBackContent" />             
				                  </form>
<%--                                 <a href="${pageContext.request.contextPath}/customerService/backContent.jsp?mailId=${csMail.getMailId()}" class="btn btn-secondary btn-modify" style="background-color: #6B705C">回覆</a> --%>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Bootstrap 5 JS -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    <!-- DataTables JS -->
    <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>

    <script>
        $(function() {
//             $("#header").load("../../admin/header.jsp");
            new DataTable("#example");
        });
    </script>
</body>

</html>