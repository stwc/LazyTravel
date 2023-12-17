<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.lazytravel.journey.dao.*"%>
<%@page import="com.lazytravel.journey.entity.*"%>

<%@ include file="/admin/header.html" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>後臺-旅行團(新增)</title>

<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
<link rel="icon" href="${pageContext.request.contextPath}/static/images/logo.ico" type="image/x-icon">
  

<style>
       *{
            box-sizing: border-box;
        }

        body{
            margin: 0;
        }

        main#main{
            padding: 50px 80px;
        }

        input{
            border: 1px solid #CCD5AE;   
        }

        input:focus{
            outline: 2px solid #CCD5AE;
            box-shadow: 0 0 8px #a1a397
        }

        select {
            border: 1px solid #CCD5AE;
            height: 27px;
        }

        select:focus {
            border:2px solid #CCD5AE;
            box-shadow: 0 0 8px #a1a397;
        }


        button.btn_submit{
            width: 100px;
            height: 30px;
            border-radius: 90px;
            border-color: transparent;
            color: white;
            background: #CCD5AE;
            
            margin-right: 15px;
        }

        button.btn_submit:active {
            outline: 2px solid #CCD5AE;
            box-shadow: 0 0 8px #a1a397
        }
        
        button.btn_reset{
            width: 100px;
            height: 30px;
            border-radius: 90px;
            border-color: transparent;
            color: white;
            background: #9C6644;
        }

        button.btn_reset:active {
            outline: 2px solid #9C6644;
            box-shadow: 0 0 8px #a1a397
        }

        div.div_btn{
            display: flex;
            justify-content: flex-end;
        }

        div.div_groupId label, div.div_groupId div{
            display: inline-block;
        }

        div.div_groupId label{
            margin-right: 35px;
        }

        div.div_groupId select{
            width: 181px;
        }

        div.div_tourGroup_journey label{
            margin-right: 5px;
        }

        div.div_tourGroup_journey input[type="number"]{
            width: 90px;
            margin-right: 5px;
        }

        div.div_tourGroup_journey select{
            width: 565px;
            margin-right: 5px;
        }

        div.div_tourGroup_date label{
            margin-right: 4px;
        }
        
        div.div_tourGroup_date input{
            margin-right: 100px;
        }

        div.div_price label{
            margin-right: 68px;
        }

        div.div_requiredMin label{
            margin-right: 36px;
        }

        div.div_requiredMax label{
            margin-right: 4px;
        }

        div.div_signDate label{
            margin-right: 20px;
        }

        div.div_signDate input{
            margin-right: 15px;
        }

        div.div_tourGroup_journey,
        div.div_tourGroup_date,
        div.div_price,
        div.div_requiredMin,
        div.div_requiredMax,
        div.div_signDate,
        div.div_btn{
            margin-bottom: 2px;
        }
</style>

<%
	TourGroup tourGroup = (TourGroup) request.getAttribute("tourGroup");

	JourneyService journeySvc = new JourneyServiceImpl();
	List<Journey> journeyList = journeySvc.getAll();
	pageContext.setAttribute("journey", journeyList);
	
	pageContext.getAttribute("journeyId");
	pageContext.getAttribute("journeyName");
%>

</head>

    <div id="header"></div>


    <main id="main">
        
        <p style="font-size: 20px;"><b>新增旅行團</b></p>
        <br>
        <br>
        
        <%-- 錯誤表列 --%>
        <c:if test="${not empty errorMsgs}">
        	<font style="color:red">請修正以下錯誤:</font>
        	<ul>
        		<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
        		</c:forEach>
        	</ul>
        </c:if>

		<form method="post" action="<%=request.getContextPath()%>/journey/admin/tourGroup.do">
			<div class="div_tourGroup_journey">
			    <label>行程ID / 名稱 :</label>
			    <select id="journeyOption" name="journey_id" onchange="updateHiddenField(this)">
			        <c:forEach var="journey" items="${journey}">
			            <option value="${journey.getJourneyId()}" ${journey.getJourneyId() == tourGroup.getJourney().getJourneyId() ? "selected" : ""}>
			                ${journey.getJourneyId()}  ${journey.getJourneyName()}
			            </option>
			        </c:forEach>
			    </select>
			    <input type="hidden" id="hiddenJourneyId" name="journey_id" value="<%= (tourGroup == null) ? "0" : tourGroup.getJourney().getJourneyId() %>">
			    <input type="hidden" id="hiddenJourneyName" name="journey_name" value="<%= (tourGroup == null) ? "0" : tourGroup.getJourney().getJourneyName() %>">
			</div>
	
	        <div class="div_tourGroup_date">
	            <label>旅行團開始日 :</label>
	            <input type="date" name="start_time" value="<%= (tourGroup == null) ? "" : tourGroup.getStartTime() %>">
	            <label>旅行團結束日 :</label>
	            <input type="date" name="end_time" value="<%= (tourGroup == null) ? "" : tourGroup.getEndTime() %>">
	        </div>
	
	        <div class="div_price">
	            <label>價格 :</label>
	            <input type="number" name="price" value="<%= (tourGroup == null) ? "0" : tourGroup.getPrice() %>">
	        </div>
	
	        <div class="div_requiredMin">
	            <label>成團人數 :</label>
	            <input type="number" name="min_required" value="<%= (tourGroup == null) ? "0" : tourGroup.getMinRequired() %>">
	        </div>
	
	        <div class="div_requiredMax">
	            <label>報名人數上限 :</label>
	            <input type="number" name="max_required" value="<%= (tourGroup == null) ? "0" : tourGroup.getMaxRequired() %>">
	        </div>
	
	        <div class="div_signDate">
	            <label>報名開始日 :</label>
	            <input type="datetime-local" name="signup_date" value="<%= (tourGroup == null) ? "" : tourGroup.getSignupDate() %>">
	            <label>報名截止日 :</label>
	            <input type="datetime-local" name="due_date" value="<%= (tourGroup == null) ? "" : tourGroup.getDueDate() %>">
	        </div>
	        
	        <!-- 下面欄位不能透過後台修改資料 -->
	        <input type="hidden" name="sign_num" value="<%= (tourGroup == null) ? "0" : tourGroup.getSignupNum() %>">
<%-- 			<input type="hidden" name="create_time" value="<%= tourGroup.getCreateTime() %>"> --%>
<%-- 			<input type="hidden" name="update_time" value="<%= tourGroup.getUpdateTime() %>"> --%>

	        <br>
	        <div class="div_btn">
				<button type="submit" name="tourGroup_add" class="btn_submit">送出</button>
				<input type="hidden" name="action" value="tourGroup_add">
				
				<button type="reset" class="btn_reset" onclick="redirectToTourGroupList()">取消</button>
			</div>
		</form>

    </main>

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
    <script>
        $(function () {
//             $("#header").load("header.html");
            new DataTable('#example');

        });
        
        function updateHiddenField(selectElement) {
            var selectedValue = selectElement.value;
            document.getElementById('hiddenJourneyId').value = selectedValue;
            document.getElementById('hiddenJourneyName').value = selectedValue;
        }

        var contextPath = "${pageContext.request.contextPath}";
        function redirectToTourGroupList() {
            window.location.href = contextPath + "/journey/admin/tourGroup_list.jsp";
        }
        
        
    </script>
</body>
  
</html>