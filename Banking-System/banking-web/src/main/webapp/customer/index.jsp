<%@ page import="javax.naming.InitialContext" %>
<%@ page import="lk.jiat.ee.entity.Account" %>
<%@ page import="lk.jiat.ee.service.AccountService" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="lk.jiat.ee.service.CustomerService" %>
<%@ page import="lk.jiat.ee.entity.Customer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<%--%>
<%--    String accountId = request.getParameter("aid");--%>
<%--      try {--%>
<%--          InitialContext initialContext = new InitialContext();--%>
<%--          AccountService accountService = (AccountService) initialContext.lookup("lk.jiat.ee.service.AccountService");--%>
<%--          Account account = accountService.getAccountByID(Long.parseLong(accountId));--%>
<%--          pageContext.setAttribute("account", account);--%>
<%--          System.out.println(account.getAccountNumber());--%>
<%--  } catch (NamingException e) {--%>
<%--    throw new RuntimeException(e);--%>
<%--  }--%>
<%--%>--%>

<%
    try {
        InitialContext ic = new InitialContext();
        CustomerService customerService = (CustomerService) ic.lookup("lk.jiat.ee.service.CustomerService");
        AccountService accountService = (AccountService) ic.lookup("lk.jiat.ee.service.AccountService");

        // Get current user by username
        String username = request.getUserPrincipal().getName();
        Customer currentUser = customerService.getCustomerByEmail(username);

        if (currentUser != null) {
            // Get user's accounts
            Account userAccount = accountService.getAccountByID(currentUser.getId());
            pageContext.setAttribute("currentUser", currentUser);
            pageContext.setAttribute("userAccount", userAccount);
        }
    } catch (NamingException e) {
        pageContext.setAttribute("error", "Service unavailable. Please try again later.");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f4f7fa;
        }
        .card-title {
            color: #0d6efd;
        }
    </style>
</head>
<body>

<div class="container mt-5">

    <!-- Welcome Section -->
    <div class="card mb-4 shadow">
        <div class="card-body">
            <h3 class="card-title">Welcome, ${pageContext.request.userPrincipal.name}</h3>
            <p class="card-text">This is your personal dashboard. Use the buttons below to manage your banking activities.</p>
        </div>
    </div>

    <!-- Account Summary (placeholder values or JSTL loop) -->
    <div class="row mb-4">
        <div class="col-md-3">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Account Number</h5>
                    <p class="fs-4 text-primary">${userAccount.accountNumber}</p>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Account Type</h5>
                    <p class="fs-4">${userAccount.accountType.name()}</p>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Total Balance</h5>
<%--                    <p class="fs-4 text-success">${userAccount.balance}</p>--%>
                    <p class="fs-4 text-success"><fmt:formatNumber value="${userAccount.balance}" type="number" minFractionDigits="2" /></p>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Interest Rate</h5>
                    <p class="fs-4 text-primary">${userAccount.interestRate}%</p>
                </div>
            </div>
        </div>

    </div>

    <!-- Quick Action Buttons -->
    <div class="card shadow mb-5">
        <div class="card-body">
            <h5 class="card-title">Quick Actions</h5>
            <div class="d-flex flex-wrap gap-3 mt-3">
                <a href="${pageContext.request.contextPath}/customer/transactions.jsp" class="btn btn-outline-success">Transfer Funds</a>
                <a href="${pageContext.request.contextPath}/customer/viewTransaction.jsp" class="btn btn-outline-warning">View Transactions</a>
                <a href="${pageContext.request.contextPath}/customer/profile.jsp" class="btn btn-outline-secondary">Edit Profile</a>
            </div>
        </div>
    </div>

</div>

</body>
</html>
