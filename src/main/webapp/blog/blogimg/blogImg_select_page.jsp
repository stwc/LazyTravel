<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lazytravel.blog.entity.*"%>
<%@ page import="com.lazytravel.blog.dao.*"%>
<%@ page import="com.lazytravel.blog.service.*"%>

<html>
<head>
<title>Lazy Travel: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>

<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Lazy Travel: BlogImgHome</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Lazy Travel: Home</p>

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
  <li><a href='listAllBlogImg.jsp'>List</a> all BlogImgs.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="blogImg.do" >
        <b>輸入文章編號 (如41001):</b>
        <input type="text" name="blogImgId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="blogImgSvc" scope="page" class="com.lazytravel.blog.service.BlogImgServiceImpl" />
  <li>
     <FORM METHOD="post" ACTION="blogImg.do" >
    
       <b>選擇文章標題:</b>
       <select size="1" name="blogImgId">
         <c:forEach var="blogImg" items="${blogImgSvc.getAllBlogImgs()}" >
          <option value="${blogImg.getBlogImgId()}">${blogImg.getBlogImgId()}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="blogImg.do" >
       <b>選擇文章ID:</b>
       <select size="1" name="blogImgId">
         <c:forEach var="blogImg" items="${blogImgSvc.getAllBlogImgs()}" >
          <option value="${blogImg.getBlogImgId()}">${blogImg.blog.getBlogId()}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>會員管理</h3>

<ul>
  <li><a href='addBlogImg.jsp'>Add</a> a new blog.</li>
</ul>

</body>

</html>