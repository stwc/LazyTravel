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
CSMailService CSMailSvc = new CSMailServiceImpl();
List<CSMail>  list = null;

Customer customer = (Customer) session.getAttribute("customer");

if (customer != null) {
    list = CSMailSvc.getCSMailByCustomerId((Integer)customer.getCustomerId());
} else {
    // 如果會員未登入，導向登入頁面
    response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
    return;
}
pageContext.setAttribute("list", list);
// CSMail csMail = (CSMail) session.getAttribute("csMail");
// session.setAttribute("csMailId", csMail.getMailId());
%>





<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>客服信箱</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">

</head>

<body>
<header id="header"></header>

    <div class="container py-5">
        <h2 class="text-center mb-4">客服信箱</h2>
 
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">信件編號</th>
                        <th scope="col">發送日期</th>
                        <th scope="col">發送時間</th>
                        <th scope="col">信件標題</th>
                        <th scope="col">內文</th>
                        
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                   <c:forEach var="csMail" items="${list}">
                    <tr>
                        <td>${csMail.getMailId()}</td>
				        <td>${csMail.createTime}</td>
				       <td>${csMail.lastMsgTime}</td>
				      <td>${csMail.getTitle()}</td>
				      <td>${csMessage.content}</td>
                        <td><a href="${pageContext.request.contextPath}/customerService/frontMailContent.jsp?mailId=${csMail.getMailId()}" class="btn btn-secondary" class="btn-modify btn" style="background-color: #6B705C">查看詳情</a>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customerService/CSMessage.do" style="margin-bottom: 0px;">
					 <input type="submit" value="查看詳情">
					 <input type="hidden" name="messageId"  value="${csMail.getMailId()}">
					 <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
					 </td>
                    </tr>
                   </c:forEach>
                    <!-- 可以添加更多類似上面結構的表格行 -->
                </tbody>
            </table>
             <div class="text-center mt-4">
                <a href="#" class="btn btn-secondary" onclick="toMyAdd()" style="background-color: #6B705C">回覆</a>
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
            $("#header").load("../components/html/header.html");
            $("#footer").load("../components/html/footer.html");
        });
        
        function toMyAdd(){
			window.location.href = "frontContent.jsp";
		}
    </script>
</body>

</html>