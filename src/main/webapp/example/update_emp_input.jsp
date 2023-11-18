<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lazytravel.example.entity.Customer"%>

<%
	Customer customer = (Customer) request.getAttribute("customer");
%>

<html>

<head>

	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>會員資料修改 - update_emp_input.jsp</title>

	<style>
	  table#table-1 {
		background-color: #CCCCFF;
		border: 2px solid black;
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

	<style>
	  table {
		width: 550px;
		background-color: white;
		margin-top: 1px;
		margin-bottom: 1px;
	  }
	  table, th, td {
		border: 0px solid #CCCCFF;
	  }
	  th, td {
		padding: 1px;
	  }
	</style>

</head>

<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		<h3>會員資料修改 - update_emp_input.jsp</h3></td><td>
		<h4><a href="select_page.jsp"><img src="../static/images/logo.png" width="100" height="100" border="0" alt="">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="customer.do" name="form1">
	<table>
		<tr>
			<td>會員ID:<font color=red><b>*</b></font></td>
			<td><%=customer.getCustomerId()%></td>
		</tr>
		<tr>
			<td>會員姓名:</td>
			<td><input type="TEXT" name="customer_name" value="<%= (customer==null)? "張家豪" : customer.getCustomerName()%>" size="45"/></td>
		</tr>
		<tr>
			<td>暱稱:</td>
			<td><input type="TEXT" name="nickname" value="<%= (customer==null)? "阿豪" : customer.getNickname()%>" size="45"/></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><input type="TEXT" name="email" value="<%= (customer==null)? "haohao88@gmail.com" : customer.getEmail()%>" size="45"/></td>
		</tr>
		<tr>
			<td>密碼:</td>
			<td><input type="password" name="customer_passwd" value="<%= (customer==null)? "123456" : customer.getCustomerPasswd()%>" size="45"/></td>
		</tr>
		<tr>
			<td>性別:</td>
			<td><select name="sex">
				<option value="0" <%=(customer.getSex().equals("0"))? "selected" : ""%> >男</option>
				<option value="1" <%=(customer.getSex().equals("1"))? "selected" : ""%> >女</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>生日:</td>
			<td><input name="birth" type="date" value="<%= (customer==null)? "2017-06-01" : customer.getBirth()%>" size="45"></td>
		</tr>
		<tr>
			<td>身份證:</td>
			<td><input type="TEXT" name="idno" value="<%= (customer==null)? "A123456987" : customer.getIdno()%>" size="45"/></td>
		</tr>
		<tr>
			<td>電話:</td>
			<td><input type="TEXT" name="phone" value="<%= (customer==null)? "0988878987" : customer.getPhone()%>" size="45"/></td>
		</tr>
		<tr>
			<td>地址:</td>
			<td><input type="TEXT" name="address" value="<%= (customer==null)? "臺北市松山區南京東路4段2號" : customer.getAddress()%>" size="45"/></td>
		</tr>
		<tr>
			<td>會員金:</td>
			<td><input type="TEXT" name="customer_point" value="<%= (customer.getCustomerPoint()==null)? "0" : customer.getCustomerPoint()%>" size="45"/></td>
		</tr>
		<tr>
			<td>狀態:</td>
			<td>
				<select name="customer_status">
					<option value="1" <%=(customer.getCustomerStatus().equals("1"))? "selected" : ""%> >啟用</option>
					<option value="0" <%=(customer.getCustomerStatus().equals("0"))? "selected" : ""%> >停權</option>
				</select>
			</td>
		</tr>
	</table>
	<br>
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="customer_id" value="<%=customer.getCustomerId()%>">
	<input type="submit" value="送出修改">
</FORM>

</body>

</html>
