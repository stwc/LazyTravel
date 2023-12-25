<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lazytravel.customerservice.entity.*"%>
<%@ page import="com.lazytravel.customerservice.dao.*"%>
<%@ page import="com.lazytravel.customerservice.service.*"%>
<%@ include file="/admin/header.html"%>

<html>
<head>

<title>Lazy Travel: Home</title>



</head>

<body bgcolor='white'>


<h3>資料查詢:</h3>
	
<%-- 錯誤列表--%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllCSMessage.jsp'>List</a> all CSMessages.  <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="CSMessage.do" >
        <b>輸入訊息編號 (如1):</b>
        <input type="text" name="messageId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <jsp:useBean id="csMessageSvc" scope="page" class="com.lazytravel.customerservice.service.CSMessageServiceImpl" />
  
  <li>
     <FORM METHOD="post" ACTION="CSMessage.do" >
       <b>選擇會員ID(如1001):</b>
       <select size="1" name="messageId">
         <c:forEach var="CSMessage" items="${csMessageSvc.getAllCSMessages()}" >
          <option value="${CSMessage.getMessageId()}">${CSMessage.getMessageId()}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>

<h3>會員管理</h3>

<ul>
  <li><a href='addCSMessage.jsp'>Add</a> a new csMessag.</li>
</ul>

</body>

</html>