<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.lazytravel.customerservice.entity.*"%>
<%@ page import="com.lazytravel.customerservice.dao.*"%>
<%@ page import="com.lazytravel.customerservice.service.*"%>
<%@ page import="com.lazytravel.customer.entity.Customer"%>
<%@page import="com.lazytravel.customerservice.entity.CSMail"%>

<%
Customer customer = (Customer) session.getAttribute("customer");
if (customer == null) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
}
Integer customerId = (Integer) customer.getCustomerId();

CSMailService CSMailSvc = new CSMailServiceImpl();
List<CSMail>  list = CSMailSvc.getCSMailByCustomerId(customerId);

pageContext.setAttribute("list", list);

%>



<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>客戶信箱</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">

</head>

<body>
<header id="header"></header>

    <div class="container py-5">
        <h2 class="text-center mb-4">信箱</h2>
 
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">信件編號</th>
                        <th scope="col">客戶時間</th>
                        <th scope="col">客戶問題</th>
                        <th scope="col">客服時間</th>
                        <th scope="col">客服回答</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                   <c:forEach var="csMail" items="${list}">
                    <tr>
                        <td>${csMail.getMailId()}</td>
				        <td>${csMail.createTime}</td>
				        <td>${csMail.questions}</td>
				        <td>${csMail.RECEIVED_TIME}</td>
				        <td>${csMail.answer}</td>
                    </tr>
                   </c:forEach>
                    <!-- 可以添加更多類似上面結構的表格行 -->
                </tbody>
            </table>
             <div class="text-center mt-4">
	             <form method="post" action="<%=request.getContextPath()%>/customerService/CSMail.do" >
					<button type="submit" class="btn btn-secondary btn-modify" style="background-color: #6B705C">新增</button> 
					<input type="hidden" name="action" value="toFrontContent" />             
				</form>
            </div>
        </div>
    </div>

    <footer id="footer"></footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script>
        $(function () {
            $("#header").load("../components/html/header.jsp");
            $("#footer").load("../components/html/footer.jsp");
        });
        
//         function toMyAdd(){
// 			window.location.href = "frontContent.jsp";
// 		}
    </script>
</body>

</html>