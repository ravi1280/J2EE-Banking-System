<%--
  Created by IntelliJ IDEA.
  User: ravis
  Date: 7/9/2025
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<h1>Admin</h1>
<h1>Hello ,${pageContext.request.userPrincipal.name}</h1>
<hr/>
<a href="${pageContext.request.contextPath}/admin/addCustomers.jsp">Add Customers</a>
</body>
</html>
