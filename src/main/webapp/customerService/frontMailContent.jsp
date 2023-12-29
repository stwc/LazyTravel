<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.lazytravel.customerservice.entity.*"%>
<%@ page import="com.lazytravel.customerservice.dao.*"%>
<%@ page import="com.lazytravel.customerservice.service.*"%>
<%@ page import="com.lazytravel.customer.entity.Customer"%>
<%@page import="com.lazytravel.customerservice.entity.CSMail"%>

<%
CSMessageService csMsgSvc = new CSMessageServiceImpl();
// CSMail csMail = (CSMail) session.getAttribute("csMail");
// 獲取郵件ID，這是一個字串
String mailId = request.getParameter("mailId");
System.out.println(mailId);

if (mailId != null && !mailId.isEmpty()) {
    try {
        Integer mailIdint = Integer.valueOf(mailId);
        System.out.println(mailIdint);

        // 處理 mailIdint 並呼叫相應的方法
        List<CSMessage> list = csMsgSvc.getCSMessageByMailId(mailIdint);
        // 接下來的程式碼...
    } catch (NumberFormatException e) {
        // 轉換失敗，請在此處進行錯誤處理
        e.printStackTrace();
    }
}
// session.setAttribute("csMail", csMail);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>回訊息內容</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="icon" href="../static/images/logo.ico" type="image/x-icon">
</head>
<style>
.message-container {
	margin-bottom: 20px;
}

.message-box {
	margin-bottom: 10px;
	padding: 10px;
	border-radius: 8px;
}

.my-message {
	background-color: #faf5f2;
	text-align: right;
}

.other-message {
	background-color: #f0f0f0;
	text-align: left;
}
</style>


<body>
	<header id="header"></header>
	<div class="container py-5">
		<h2 class="text-center mb-4">訊息內容</h2>
		
		<section class="msg-container">
		</section>

<form METHOD="post" ACTION="${pageContext.request.contextPath}/customerService/CSMessage.do" name="form1">
		<div class="mb-3">
			<p>回覆內容</p>
			<textarea class="form-control" name="content" id="inputComment" rows="5"
				placeholder="請輸入回覆內容" required></textarea>
		</div>

	<div class="d-grid gap-1 col-1 mx-auto">
    <button type="submit" class="btn btn-secondary"
        style="background-color: #6B705C">回覆
        </button>
        <input type="hidden" name="mail_Id"  value="<%= request.getParameter("mailId")%>">
        <input type="hidden" name="createTime"  id="createTime">
        <input type="hidden" name="message_from" value="1">
        <input type="hidden" name="action" value="insert">
</div>
</form>


	<footer id="footer"></footer>
<!-- ============================================ -->
<script>
<!-- 帶入現在時間到buylistDate -->
document.addEventListener('DOMContentLoaded', function () {
    // 獲取當前日期和時間
    var currentDateTime = new Date();

    // 將日期格式化為 "yyyy-MM-dd HH:mm:ss"，這裡使用了自定義的 formatDate 函數
    var formattedDateTime = formatDate(currentDateTime);

    // 將格式化後的日期時間設置到輸入框的值中
    document.querySelector("input[name='createTime']").value = formattedDateTime;
});

// 自定義的日期格式化函數
function formatDate(date) {
    var year = date.getFullYear();
    var month = (date.getMonth() + 1).toString().padStart(2, '0');
    var day = date.getDate().toString().padStart(2, '0');
    var hours = date.getHours().toString().padStart(2, '0');
    var minutes = date.getMinutes().toString().padStart(2, '0');
    var seconds = date.getSeconds().toString().padStart(2, '0');

    return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
}
<!-- /帶入現在時間到buylistDate -->
</script>
<!-- ============================================ -->
<script>
    function redirectToFrontMailContent() {
        // 使用 JavaScript 將頁面 URL 跳轉到指定的網址
        window.location.href = '<%=request.getContextPath()%>/customerService/frontMailContent.jsp';
    }
</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script>
		$(function() {
			$("#header").load("../components/html/header.html");
			$("#footer").load("../components/html/footer.html");
		});
	</script>
	
	<script>
	// 將 JSON 數據轉換為 DOM
	function createMessageElement(message) {
	    const messageContainer = document.createElement('div');
	    messageContainer.classList.add('message-container');

	    const nameHeading = document.createElement('h5');
	    if (message.messageFrom) {
	        nameHeading.textContent = '客服';
	    } else {
	        nameHeading.textContent = message.customerName;
	        nameHeading.classList.add('text-end');
	    }

	    const messageBox = document.createElement('div');
	    messageBox.classList.add('message-box');
	    if (message.messageFrom) {
	        messageBox.classList.add('other-message');
	    } else {
	        messageBox.classList.add('my-message');
	    }

	    const contentParagraph = document.createElement('p');
	    contentParagraph.textContent = message.content;

	    const timeParagraph = document.createElement('p');
	    const createDate = new Date(message.createTime);
// 	    timeParagraph.textContent = createDate.toLocaleString('en-US', { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' });

	    messageBox.appendChild(contentParagraph);
	    messageBox.appendChild(timeParagraph);

	    messageContainer.appendChild(nameHeading);
	    messageContainer.appendChild(messageBox);

	    return messageContainer;
	}
	
		$.ajax({
			type: 'GET',
			url: "${pageContext.request.contextPath}/customerService/getCustomerList" + location.search,
			success: function(data) {
				console.info(data);
				
				// 找到目標容器
				const targetContainer = document.querySelector('section.msg-container');

				// 將轉換後的 DOM 添加到目標容器中
				data.forEach(message => {
				    const messageElement = createMessageElement(message);
				    targetContainer.appendChild(messageElement);
				});
			},
			error: function(msg) {
				console.error(data);
			},
		});
	</script>
</body>

</html>