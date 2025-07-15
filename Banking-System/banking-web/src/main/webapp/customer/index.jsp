<%@ page import="javax.naming.InitialContext" %>
<%@ page import="lk.jiat.ee.entity.Account" %>
<%@ page import="lk.jiat.ee.service.AccountService" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="lk.jiat.ee.service.CustomerService" %>
<%@ page import="lk.jiat.ee.entity.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="lk.jiat.ee.exceptions.CustomerNotFoundException" %>
<%@ page import="lk.jiat.ee.exceptions.AccountNotFoundException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
    <!-- Bootstrap & Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">

    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f0f2f5;
        }

        .sidebar {
            height: 100vh;
            background-color: #0d6efd;
            color: white;
            padding: 20px;
        }

        .sidebar h4 {
            color: #fff;
        }

        .sidebar a {
            color: #cfd8dc;
            display: block;
            padding: 10px 0;
            text-decoration: none;
        }

        .sidebar a:hover {
            color: #fff;
        }

        .dashboard {
            padding: 30px;
        }

        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.05);
        }

        .card .bi {
            font-size: 1.8rem;
        }

        .btn-custom {
            border-radius: 30px;
            padding: 10px 20px;
        }

        @media (max-width: 768px) {
            .sidebar {
                height: auto;
            }
        }
    </style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-2 sidebar">
            <h4>Secure Bank</h4>
            <a href="#"><i class="bi bi-house-door me-2"></i> Dashboard</a>
            <a href="${pageContext.request.contextPath}/customer/transactions.jsp"><i class="bi bi-arrow-left-right me-2"></i> Transfer Funds</a>
            <a href="${pageContext.request.contextPath}/customer/viewTransaction.jsp"><i class="bi bi-eye me-2"></i> View Transactions</a>
            <a href="${pageContext.request.contextPath}/customer/scheduleTransfer.jsp"><i class="bi bi-clock-history me-2"></i> Schedule Transfer</a>
            <a href="${pageContext.request.contextPath}/logout"><i class="bi bi-box-arrow-right me-2"></i> Logout</a>
        </div>

        <!-- Dashboard -->
        <div class="col-md-10 dashboard">
            <h3 class="mb-4">Welcome, ${sessionCustomer.fullName}</h3>

            <div class="row g-4 mb-4">
                <div class="col-md-3">
                    <div class="card p-3 bg-light">
                        <div class="d-flex align-items-center">
                            <i class="bi bi-credit-card text-primary me-3"></i>
                            <div>
                                <h6 class="mb-0">Account Number</h6>
                                <p class="mb-0">${sessionAccount.accountNumber}</p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="card p-3 bg-light">
                        <div class="d-flex align-items-center">
                            <i class="bi bi-wallet2 text-info me-3"></i>
                            <div>
                                <h6 class="mb-0">Account Type</h6>
                                <p class="mb-0">${sessionAccount.accountType.name()}</p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="card p-3 bg-light">
                        <div class="d-flex align-items-center">
                            <i class="bi bi-cash-stack text-success me-3"></i>
                            <div>
                                <h6 class="mb-0">Balance</h6>
                                <p class="mb-0 text-success fw-bold">
                                    <fmt:formatNumber value="${sessionAccount.balance}" type="number" minFractionDigits="2"/>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="card p-3 bg-light">
                        <div class="d-flex align-items-center">
                            <i class="bi bi-percent text-warning me-3"></i>
                            <div>
                                <h6 class="mb-0">Interest Rate</h6>
                                <p class="mb-0">${sessionAccount.interestRate}%</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card p-4 mb-4">
                <h5 class="mb-3">Latest Updates</h5>

                <!-- Notifications -->
                <ul class="list-group list-group-flush mb-4">
                    <li class="list-group-item">🔔 Your last transaction of LKR 5,000 was successful.</li>
                    <li class="list-group-item">🔒 A scheduled transfer is set for July 30th.</li>
                </ul>


                <!-- Tips -->
                <div class="alert alert-info">
                    💡 <strong>Tip:</strong> Use strong, unique passwords and change them periodically for better account protection.
                </div>
            </div>

            <!-- Quick Actions -->
            <div class="card p-4">
                <h5 class="mb-3">Quick Actions</h5>
                <div class="d-flex flex-wrap gap-3">
                    <a href="${pageContext.request.contextPath}/customer/transactions.jsp" class="btn btn-outline-primary btn-custom">
                        <i class="bi bi-arrow-left-right me-2"></i>Transfer Funds
                    </a>
                    <a href="${pageContext.request.contextPath}/customer/viewTransaction.jsp" class="btn btn-outline-secondary btn-custom">
                        <i class="bi bi-eye me-2"></i>View Transactions
                    </a>
                    <a href="${pageContext.request.contextPath}/customer/scheduleTransfer.jsp" class="btn btn-outline-dark btn-custom">
                        <i class="bi bi-clock-history me-2"></i>Schedule Transfer
                    </a>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>