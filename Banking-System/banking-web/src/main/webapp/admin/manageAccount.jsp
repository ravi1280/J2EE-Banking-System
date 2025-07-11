<%@ page import="javax.naming.InitialContext" %>
<%@ page import="lk.jiat.ee.service.AccountService" %>
<%@ page import="lk.jiat.ee.entity.Account" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
  String accountId = request.getParameter("aid");
  try {
    InitialContext initialContext = new InitialContext();
    AccountService accountService = (AccountService) initialContext.lookup("lk.jiat.ee.service.AccountService");
    Account account = accountService.getAccountByID(Long.parseLong(accountId));
    pageContext.setAttribute("account", account);
  } catch (NamingException e) {
    throw new RuntimeException(e);
  }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Admin | Manage Account</title>
  <!-- Bootstrap 5 CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Google Font -->
  <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
  <style>
    body {
      background-color: #f4f7fa;
      font-family: 'Roboto', sans-serif;
    }
    .form-container {
      max-width: 600px;
      margin: 50px auto;
      background-color: white;
      border-radius: 10px;
      box-shadow: 0 4px 20px rgba(0, 123, 255, 0.1);
      padding: 30px;
    }
    h1 {
      color: #0d6efd;
    }
    .btn-primary {
      background-color: #0d6efd;
      border-color: #0d6efd;
    }
  </style>
</head>
<body>

<div class="container">
  <div class="form-container">
    <h1 class="mb-4">Manage Account</h1>
    <form method="POST" action="${pageContext.request.contextPath}/updateAccount">
      <div class="mb-3">
        <label for="account_number" class="form-label">Account Number</label>
        <input type="text" class="form-control" name="account_number" value="${account.accountNumber}" readonly>
      </div>

      <div class="mb-3">
        <label for="account_type" class="form-label">Account Type</label>
        <select class="form-select" name="account_type" id="account_type">
          <option value="SAVINGS" ${account.accountType == 'SAVINGS' ? 'selected' : ''}>Savings</option>
          <option value="CURRENT" ${account.accountType == 'CURRENT' ? 'selected' : ''}>Current</option>
          <option value="FIXED_DEPOSIT" ${account.accountType == 'FIXED_DEPOSIT' ? 'selected' : ''}>Fixed Deposit</option>
        </select>
      </div>

      <div class="mb-3">
        <label for="balance" class="form-label">Balance</label>
        <input type="number" class="form-control" name="balance" step="0.01" value="${account.balance}" readonly>
      </div>

      <div class="mb-3">
        <label for="interestRate" class="form-label">Interest Rate (%)</label>
        <input type="number" class="form-control" name="interestRate" step="0.01" value="${account.interestRate}" required>
      </div>

      <input type="hidden" name="account_id" value="${account.id}">
      <button type="submit" class="btn btn-primary w-100">Update Account</button>
    </form>
  </div>
</div>

</body>
</html>
