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
  <li><a href='listAllCSMail.jsp'>List</a> all CSMails.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="CSMail.do" >
        <b>輸入訊息編號 (如41001):</b>
        <input type="text" name="mailId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="csMailSvc" scope="page" class="com.lazytravel.customerservice.service.CSMailServiceImpl" />
  <li>
     <FORM METHOD="post" ACTION="CSMail.do" >
    
       <b>選擇信件標題:</b>
       <select size="1" name="mailId">
         <c:forEach var="csMail" items="${csMailSvc.getAllCSMails()}" >
          <option value="${csMail.getMailId()}">${csMail.getTitle()}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="CSMail.do" >
       <b>選擇信件ID:</b>
       <select size="1" name="mailId">
         <c:forEach var="CSMail" items="${csMailSvc.getAllCSMails()}" >
          <option value="${CSMail.getMailId()}">${CSMail.getMailId()}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>會員管理</h3>

<ul>
  <li><a href='addCSMail.jsp'>Add</a> a new csMail.</li>
</ul>

</body>

</html>