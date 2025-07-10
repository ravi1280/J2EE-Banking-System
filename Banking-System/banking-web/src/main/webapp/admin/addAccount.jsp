<%--
  Created by IntelliJ IDEA.
  User: ravis
  Date: 7/11/2025
  Time: 1:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin | Add Account</title>
</head>
<body>
<h1>Add Account</h1>
<hr/>
<%
    String customerId = request.getParameter("cid");
%>

<form method="POST" action="${pageContext.request.contextPath}/addAccount">
    <table>
        <tr>
            <th>Account Number</th>
            <td><input type="number" name="account_number" required></td>
        </tr>
        <tr>
            <th>Account Type</th>
            <td>
                <select name="account_type" required>
                    <option value="SAVINGS">Savings</option>
                    <option value="CURRENT">current</option>
                    <option value="FIXED_DEPOSIT">fixed deposit</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>Balance</th>
            <td><input type="number" name="balance" required></td>
        </tr>
        <tr>
            <th>InterestRate</th>
            <td><input type="number" name="interestRate" required></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="hidden" name="cid" value="<%= customerId %>">
                <input type="submit" value="Add Customer Account">
            </td>
        </tr>
    </table>

</form>


</body>
</html>
