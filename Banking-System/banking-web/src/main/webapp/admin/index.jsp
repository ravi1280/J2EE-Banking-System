<%@ page import="javax.naming.InitialContext" %>
<%@ page import="lk.jiat.ee.service.AccountService" %>
<%@ page import="lk.jiat.ee.entity.Account" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.util.List" %>
<%@ page import="lk.jiat.ee.bean.ReportServiceBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    List<Account> accounts = null;
    double totalBalance = 0;
    double totalInterest = 0;

    try {
        InitialContext initialContext = new InitialContext();
        AccountService accountService = (AccountService) initialContext.lookup("lk.jiat.ee.service.AccountService");
        ReportServiceBean reportService = (ReportServiceBean) initialContext.lookup("java:global/Banking-System-Ear/timers-module/ReportServiceBean");
        accounts = accountService.getAllAccounts();
        for (Account acc : accounts) {
            totalBalance += acc.getBalance();
            totalInterest += acc.getInterestRate();
        }
        pageContext.setAttribute("accounts", accounts);
        pageContext.setAttribute("totalBalance", totalBalance);
        pageContext.setAttribute("totalAccounts", accounts.size());
        pageContext.setAttribute("avgInterestRate", accounts.size() > 0 ? totalInterest / accounts.size() : 0);

        pageContext.setAttribute("S_period", reportService.getPeriod());
        pageContext.setAttribute("S_totalTransactions", reportService.getTotalTransactions());
        pageContext.setAttribute("S_totalTransactionAmount", reportService.getTotalTransactionAmount());
        pageContext.setAttribute("S_depositCount", reportService.getDepositCount());
        pageContext.setAttribute("S_withdrawalCount", reportService.getWithdrawalCount());
        pageContext.setAttribute("S_uniqueCustomers", reportService.getUniqueCustomers());
        pageContext.setAttribute("S_reportAccounts", reportService.getTotalAccounts());
        pageContext.setAttribute("S_highestTransaction", reportService.getHighestTransaction());

        System.out.println(reportService.getPeriod());

    } catch (NamingException e) {
        throw new RuntimeException(e);
    }




%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <!-- Bootstrap 5 -->
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
        .dashboard-table th {
            background-color: #0d6efd;
            color: white;
        }
    </style>
</head>
<body>

<div class="container mt-5">

    <h2 class="mb-4 text-primary">Welcome, ${pageContext.request.userPrincipal.name}</h2>

    <!-- Quick Actions -->
    <div class="mb-4 d-flex gap-3">
<%--        <a href="${pageContext.request.contextPath}/admin/addCustomers.jsp" class="btn btn-primary">Add Customer</a>--%>
        <a href="${pageContext.request.contextPath}/admin/addCustomers.jsp" class="btn btn-outline-primary">Add Customer</a>
    </div>

    <!-- Dashboard Cards -->
    <div class="row mb-5">
        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Total Accounts</h5>
                    <p class="fs-4">${totalAccounts}</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Total Balance (LKR)</h5>
                    <p class="fs-4"><fmt:formatNumber value="${totalBalance}" type="number" minFractionDigits="2" /></p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Avg. Interest Rate</h5>
                    <p class="fs-4"><fmt:formatNumber value="${avgInterestRate}" type="number" minFractionDigits="2" />%</p>
                </div>
            </div>
        </div>
    </div>

    <!-- Account Table -->
    <div class="card shadow">
        <div class="card-body">
            <h4 class="card-title mb-3">All Accounts</h4>
            <div class="table-responsive">
                <table class="table table-bordered dashboard-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Account Number</th>
                        <th>Account Type</th>
                        <th>Balance (LKR)</th>
                        <th>Interest Rate</th>
                        <th>Customer Name</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="accounts" items="${accounts}">
                        <tr>
                            <td>${accounts.id}</td>
                            <td>${accounts.accountNumber}</td>
                            <td>${accounts.accountType}</td>
                            <td><fmt:formatNumber value="${accounts.balance}" type="number" minFractionDigits="2"/></td>
                            <td><fmt:formatNumber value="${accounts.interestRate}" type="number" minFractionDigits="2"/></td>
                            <td>${accounts.customer.fullName}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/manageAccount.jsp?aid=${accounts.id}" class="btn btn-sm btn-primary">
                                    Manage
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Monthly System Report -->
    <div class="mt-5 mb-5">
        <div class="bg-white p-4 rounded card shadow">
            <h4 class="mb-4 text-primary d-flex align-items-center">
                <i class="fas fa-chart-pie me-2"></i> Monthly System Report – <span class="ms-2 text-dark">${S_period}</span>
            </h4>

            <div class="row g-4">
                <!-- Total Transactions -->
                <div class="col-md-3">
                    <div class="card border-0 shadow-sm bg-light">
                        <div class="card-body text-center">
                            <i class="fas fa-receipt fa-2x text-primary mb-2"></i>
                            <h6 class="text-muted">Total Transactions</h6>
                            <p class="fs-4 fw-bold text-dark">${S_totalTransactions}</p>
                        </div>
                    </div>
                </div>

                <!-- Total Amount -->
                <div class="col-md-3">
                    <div class="card border-0 shadow-sm bg-light">
                        <div class="card-body text-center">
                            <i class="fas fa-coins fa-2x text-warning mb-2"></i>
                            <h6 class="text-muted">Total Amount (LKR)</h6>
                            <p class="fs-4 fw-bold text-dark">
                                <fmt:formatNumber value="${S_totalTransactionAmount}" type="number" minFractionDigits="2" />
                            </p>
                        </div>
                    </div>
                </div>

                <!-- Highest Transaction -->
                <div class="col-md-3 ">
                    <div class="card border-0 shadow-sm bg-light">
                        <div class="card-body text-center">
                            <i class="fas fa-arrow-up fa-2x text-danger mb-2"></i>
                            <h6 class="text-muted">Highest Transaction</h6>
                            <p class="fs-4 fw-bold text-dark">
                                <fmt:formatNumber value="${S_highestTransaction}" type="number" minFractionDigits="2" />
                            </p>
                        </div>
                    </div>
                </div>

                <!-- Unique Customers -->
                <div class="col-md-3">
                    <div class="card border-0 shadow-sm bg-light">
                        <div class="card-body text-center">
                            <i class="fas fa-users fa-2x text-success mb-2"></i>
                            <h6 class="text-muted">Unique Customers</h6>
                            <p class="fs-4 fw-bold text-dark">${S_uniqueCustomers}</p>
                        </div>
                    </div>
                </div>

                <!-- Deposits -->
                <div class="col-md-3">
                    <div class="card border-0 shadow-sm bg-light">
                        <div class="card-body text-center">
                            <i class="fas fa-piggy-bank fa-2x text-success mb-2"></i>
                            <h6 class="text-muted">Deposits</h6>
                            <p class="fs-4 fw-bold text-dark">${S_depositCount}</p>
                        </div>
                    </div>
                </div>

                <!-- Withdrawals -->
                <div class="col-md-3">
                    <div class="card border-0 shadow-sm bg-light">
                        <div class="card-body text-center">
                            <i class="fas fa-money-bill-wave fa-2x text-danger mb-2"></i>
                            <h6 class="text-muted">Withdrawals</h6>
                            <p class="fs-4 fw-bold text-dark">${S_withdrawalCount}</p>
                        </div>
                    </div>
                </div>

                <!-- Reported Accounts -->
                <div class="col-md-3">
                    <div class="card border-0 shadow-sm bg-light">
                        <div class="card-body text-center">
                            <i class="fas fa-file-invoice fa-2x text-secondary mb-2"></i>
                            <h6 class="text-muted">Reported Accounts</h6>
                            <p class="fs-4 fw-bold text-dark">${S_reportAccounts}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
