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


 

<h3>圖片查詢:</h3>
	
<%-- 錯誤列表--%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="img" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAlICSImg.jsp'>List</a> all CSImgs.  <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="CSImg.do" >
        <b>輸入訊息編號:</b>
        <input type="text" name="ImgId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <jsp:useBean id="csImgSvc" scope="page" class="com.lazytravel.customerservice.service.CSImgServiceImpl" />
  
  <li>
     <FORM METHOD="post" ACTION="CSImg.do" >
       <b>選擇圖片ID:</b>
       <select size="1" name="ImgId">
         <c:forEach var="CSImg" items="${csImgSvc.getAllCSImgs()}" >
          <option value="${CSImg.getImgId()}">${CSImg.getImgId()}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>

<h3>會員管理</h3>

<ul>
  <li><a href='addCSImg.jsp'>Add</a> a new csImg.</li>
</ul>

</body>

</html>