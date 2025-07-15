<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="lk.jiat.ee.entity.Transaction" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="lk.jiat.ee.service.TransactionService" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="lk.jiat.ee.entity.Customer" %>
<%@ page import="lk.jiat.ee.service.CustomerService" %>
<%@ page import="lk.jiat.ee.entity.Account" %>
<%@ page import="lk.jiat.ee.service.AccountService" %>
<%@ page import="lk.jiat.ee.exceptions.AccountNotFoundException" %>
<%@ page import="lk.jiat.ee.exceptions.CustomerNotFoundException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
  try {
    String username = request.getUserPrincipal().getName();
    InitialContext ctx = new InitialContext();
    CustomerService customerService = (CustomerService) ctx.lookup("lk.jiat.ee.service.CustomerService");
    AccountService accountService = (AccountService) ctx.lookup("lk.jiat.ee.service.AccountService");
      Customer currentUser = null;
      try {
          currentUser = customerService.getCustomerByEmail(username);
      } catch (CustomerNotFoundException e) {
          throw new RuntimeException(e);
      }

      if (currentUser != null) {
        Account userAccount = null;
        try {
            userAccount = accountService.getAccountByCustomerID(currentUser.getId());
        } catch (AccountNotFoundException e) {
            throw new RuntimeException(e);
        }
        pageContext.setAttribute("currentUser", currentUser);
      pageContext.setAttribute("userAccount", userAccount);



      TransactionService transactionService = (TransactionService) ctx.lookup("lk.jiat.ee.service.TransactionService");
      List<Transaction> transactions = transactionService.getAllTransactionByAccountID(userAccount.getId());
      pageContext.setAttribute("transactions", transactions);

      double totalAmount = 0.0;
      for (Transaction t : transactions) {
        totalAmount += t.getAmount();
      }
      pageContext.setAttribute("totalAmount", totalAmount);
    }
  } catch (NamingException e) {
    throw new RuntimeException(e);
  }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Transaction Dashboard</title>
  <!-- Bootstrap 5 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Font Awesome -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">

  <style>
    body {
      font-family: 'Poppins', sans-serif;
      background-color: #f4f7fa;
      padding: 40px 0;
    }

    .card-title {
      color: #0d6efd;
    }

    .dashboard-table th {
      background-color: #0d6efd;
      color: white;
    }

    .summary-card {
      background-color: #ffffff;
      border-radius: 12px;
      box-shadow: 0 8px 24px rgba(0, 0, 0, 0.05);
      padding: 25px;
      transition: transform 0.2s ease;
    }

    .summary-card:hover {
      transform: translateY(-4px);
    }

    .summary-icon {
      font-size: 1.8rem;
    }

    .transaction-type {
      font-weight: 600;
      text-transform: capitalize;
    }

  </style>
</head>
<body>

<div class="container">
  <h2 class="text-primary mb-4">Transaction Dashboard</h2>

  <!-- Summary Cards -->
  <div class="row mb-4">
    <div class="col-md-6">
      <div class="summary-card d-flex justify-content-between align-items-center">
        <div>
          <h5>Total Transactions</h5>
          <p class="fs-4 mb-0"><c:out value="${fn:length(transactions)}"/></p>
        </div>
        <i class="fas fa-receipt text-info summary-icon"></i>
      </div>
    </div>
    <div class="col-md-6">
      <div class="summary-card d-flex justify-content-between align-items-center">
        <div>
          <h5>Total Amount (LKR)</h5>
          <p class="fs-4 mb-0">
            <fmt:formatNumber value="${totalAmount}" type="number" minFractionDigits="2"/>
          </p>
        </div>
        <i class="fas fa-coins text-warning summary-icon"></i>
      </div>
    </div>
  </div>

  <!-- Transaction Table -->
  <div class="card shadow">
    <div class="card-body">
      <h5 class="card-title mb-3"><i class="fas fa-table me-2"></i>Transaction History</h5>
      <div class="table-responsive">
        <table class="table table-bordered dashboard-table align-middle text-center">
          <thead>
          <tr>
            <th><i class="fas fa-hashtag"></i> ID</th>
            <th>Amount (LKR)</th>
            <th>Type</th>
            <th>Description</th>
            <th>Date & Time</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="t" items="${transactions}">
            <tr>
              <td>${t.id}</td>
              <td>${t.amount}</td>
              <td> ${t.type}</td>
              <td>${t.description}</td>
              <td>${t.timestamp}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
