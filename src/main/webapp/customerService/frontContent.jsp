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
	Integer mailId = (Integer) request.getAttribute("mailId");
	String customerName = (String) request.getAttribute("customerName");
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

    <div class="container mt-4 p-2 mt-2 ">
        <h3 class="text-center mb-2">客戶]回覆</h3>


        <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/customerService/CSMail.do" name="form1">
            <div class="mb-3">
                <label for="inputName" class="form-label" >會員姓名</label>
                <input type="text" readonly="readonly" class="form-control" id="inputName"  name="customer_name" value="${customer.customerName}" readonly="readonly">
            </div>

          
            <div class="mb-3">
                <label for="inputComment" class="form-label">問題內容</label>
                <textarea class="form-control" id="inputComment"  name="mailquestions" rows="5" placeholder="請輸入問題內容" required></textarea>
            </div>

          <br>
          <input type="hidden" name="messageFrom" value="0">
	<input type="hidden" name="action" value="frontinsert">
	<input type="submit" value="送出">
	<table id="table-1">

        </form>
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
      
    </script>
</body>

</html>
