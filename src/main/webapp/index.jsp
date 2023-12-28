<%@ page import="com.lazytravel.customer.entity.Customer" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.stream.Collectors" %>
<html>

<%
List<Integer> foodScapeIdList = new ArrayList<>();
foodScapeIdList.add(22001);
foodScapeIdList.add(22008);
foodScapeIdList.add(22022);
foodScapeIdList.add(22004);

// foodScapeIdList轉換為以,分隔的字串
String foodScapeIdList_Str = foodScapeIdList.stream().map(Object::toString).collect(Collectors.joining(","));

// 送資料
request.getSession().setAttribute("foodScapeIdList_Str", foodScapeIdList_Str);
%>

<body>
<h2>Hello World!</h2>
<ul>
	<% 
	    Customer customer = (Customer) session.getAttribute("customer");
	    if (customer != null) { 
	%>
	<li>Current customer id = <%= customer.getCustomerId() %></li>
	<% } %>
</ul>

		<!-- test 模擬送出customer選擇的景點 -->
        <br>
        <div class="div_btn">
        	<form method="post" action="<%=request.getContextPath()%>/journey/user/journeySelect.do">
	            <button type="submit" class="btn_submit">選擇的景點</button>
	            <input type="hidden" name="action" value="receiveFoodScapeId">
	    	</form>
        </div>
        <hr>
        
</body>
</html>
