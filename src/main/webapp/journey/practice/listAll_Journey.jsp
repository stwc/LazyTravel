<%-- <%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@ page import="java.util.List"%> --%>
<%-- <%@ page import="com.lazytravel.journey.entity.*"%> --%>
<%-- <%@ page import="com.lazytravel.journey.dao.*"%> --%>
<%-- <%-- 此頁練習採用 EL 的寫法取值 --%> --%>

<%-- <% --%>
// 	JourneyService journeySvc = new JourneyService();
// 	List<Journey> list = journeySvc.getAll();
// 	pageContext.setAttribute("list", list);
<%-- %> --%>


<!-- <html> -->

<!-- <head> -->
<!-- 	<title>所有行程資料</title> -->

<!-- 	<style> -->
/* 	  table#table-1 { */
/* 		background-color: #CCCCFF; */
/* 		border: 2px solid black; */
/* 		text-align: center; */
/* 	  } */
/* 	  table#table-1 h4 { */
/* 		color: red; */
/* 		display: block; */
/* 		margin-bottom: 1px; */
/* 	  } */
/* 	  h4 { */
/* 		color: blue; */
/* 		display: inline; */
/* 	  } */
<!-- 	</style> -->

<!-- 	<style> -->
/* 	  table { */
/* 		/*width: 800px;*/ */
/* 		background-color: white; */
/* 		margin-top: 5px; */
/* 		margin-bottom: 5px; */
/* 	  } */
/* 	  table, th, td { */
/* 		border: 1px solid #CCCCFF; */
/* 	  } */
/* 	  th, td { */
/* 		padding: 5px; */
/* 		text-align: center; */
/* 	  } */
<!-- 	</style> -->

<!-- </head> -->

<!-- <body> -->

<!-- <!-- 	<h4>此頁練習採用 EL 的寫法取值:</h4> --> -->
<!-- 	<table id="table-1"> -->
<!-- 		<tr><td> -->
<!-- 			<h3>所有行程資料</h3> -->
<!-- 			<h4><a href="select_Journey_page.jsp"><img src="../static/images/logo.png" width="100" height="100" border="0" alt="">回行程頁面</a></h4> -->
<!-- 		</td></tr> -->
<!-- 	</table> -->

<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<th>行程ID</th> -->
<!-- 			<th>行程名稱</th> -->
<!-- 			<th>價格</th> -->
<!-- 			<th>介紹文</th> -->
<!-- 			<th>建立時間</th> -->
<!-- 			<th>行程天數</th> -->
<!-- 			<th>購買次數</th> -->
<!-- 			<th>平均評分</th> -->
<!-- 			<th>評分次數</th> -->
<!-- 			<th>狀態</th> -->
<!-- 		</tr> -->
		
<%-- <%-- 		<%@ include file="page1.file" %> --%> --%>
<%-- <%-- 		<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%> --%>
<%-- 		<c:forEach var="journey" items="${list}"> --%>
<!-- 			<tr> -->
<%-- 				<td>${journey.journeyId}</td> --%>
<%-- 				<td>${journey.journeyName}</td> --%>
<%-- 				<td>${journey.price}</td> --%>
<%-- 				<td>${journey.content}</td> --%>
<%-- 				<td>${journey.createTime}</td> --%>
<%-- 				<td>${journey.days}</td> --%>
<%-- 				<td>${journey.buyCount}</td> --%>
<%-- 				<td>${journey.avgScore}</td> --%>
<%-- 				<td>${journey.scoreCount}</td> --%>
<%-- 				<td>${(journey.journeyStatus == 0) ? "未上架" : "已上架" }</td>     --%>
<!-- 				EL表達式中沒有equals()的用法，比較字串內容是否相同要用== -->
				
<!-- 				<td> -->
<%-- 				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/journey/journey.do" style="margin-bottom: 0px;"> --%>
<!-- 					 <input type="submit" value="修改"> -->
<%-- 					 <input type="hidden" name="journey_id"  value="${journey.journeyId}"> --%>
<!-- 					 <input type="hidden" name="action"	value="getOne_For_Update"> -->
<!-- 				  </FORM> -->
<!-- 				</td> -->
				
<!-- 				<td> -->
<%-- 			  	  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/journey/journey.do" style="margin-bottom: 0px;"> --%>
<!-- 				     <input type="submit" value="刪除"> -->
<%-- 				     <input type="hidden" name="journey_id"  value="${journey.journeyId}"> --%>
<!-- 				     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<%-- 		</c:forEach> --%>
<!-- 	</table> -->
<%-- <%--	<%@ include file="page2.file" %>--%> --%>

<!-- </body> -->

<!-- </html> -->