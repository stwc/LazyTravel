<%@ page import="com.lazytravel.customer.entity.Customer" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<body>
<h2>Hello World!</h2>
<ul>
  <li>Current customer id = <%= ((Customer)session.getAttribute("customer")).getCustomerId() %></li>
</ul>

</body>
</html>
