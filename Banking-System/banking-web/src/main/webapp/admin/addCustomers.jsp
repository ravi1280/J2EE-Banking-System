<%@ page import="javax.naming.InitialContext" %>
<%@ page import="lk.jiat.ee.service.CustomerService" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="lk.jiat.ee.entity.Customer" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ravis
  Date: 7/10/2025
  Time: 6:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin | Add Customer</title>
</head>
<body>
<%
    try {
        InitialContext initialContext = new InitialContext();
        CustomerService customerService = (CustomerService) initialContext.lookup("lk.jiat.ee.service.CustomerService");
        List<Customer> customers = customerService.getAllCustomers();
        pageContext.setAttribute("customers",customers);
    } catch (NamingException e) {
        throw new RuntimeException(e);
    }

%>
<table>
    <tr>
        <th>id</th>
        <th>Full Name</th>
        <th>Address</th>
        <th>Email</th>
        <th>Phone</th>
        <th>User Type</th>
        <th>Add Account</th>

    </tr>
    <c:forEach var="customers" items="${customers}">
        <tr>
            <td>${customers.id}</td>
            <td>${customers.fullName}</td>
            <td>${customers.address}</td>
            <td>${customers.email}</td>
            <td>${customers.phone}</td>
            <td>${customers.userType.name()}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/addAccount.jsp?cid=${customers.id}">Add Account</a>
            </td>

        </tr>
    </c:forEach>
</table>

<hr/>

<form method="POST" action="${pageContext.request.contextPath}/addCustomer" >
    <table>

        <tr>
            <th>Full Name</th>
            <td><input type="text" name="fullName" /></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><input type="email" name="email" /></td>
        </tr>

        <tr>
            <th>Password</th>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <th>Address</th>
            <td><input type="text" name="address" /></td>
        </tr>
        <tr>
            <th>Phone</th>
            <td><input type="number" name="phone" /></td>
        </tr>
        <tr>
            <td><input type="submit" name="Add Customer" /></td>
        </tr>
    </table>
</form>
</body>
</html>
