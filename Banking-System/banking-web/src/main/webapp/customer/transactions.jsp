<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="lk.jiat.ee.service.TransactionService" %>
<%@ page import="lk.jiat.ee.entity.Transaction" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  String username = request.getUserPrincipal().getName();
  System.out.println(username);
//  try {
//    InitialContext initialContext = new InitialContext();
//    TransactionService transactionService = (TransactionService) initialContext.lookup("lk.jiat.ee.service.TransactionService");
//    String idParam = request.getParameter("id");
//    Long id = Long.parseLong(idParam); // convert string to Long
//    List<Transaction> transactions = transactionService.getAllTransactionByAccountID(id);
//    pageContext.setAttribute("transactions", transactions);
//  } catch (NamingException e) {
//    throw new RuntimeException(e);
//  }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Transactions</title>

  <!-- Bootstrap 5 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Font Awesome -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">

  <style>
    body {
      background-color: #f8f9fa;
      font-family: 'Roboto', sans-serif;
      padding: 40px 0;
      color: #212529;
    }


    .card-form {
      border-radius: 12px;
      background-color: #ffffff;
      padding: 25px;
      box-shadow: 0 8px 24px rgba(0, 0, 0, 0.06);
      height: 100%;
      transition: transform 0.3s ease;
    }

    .card-form:hover {
      transform: translateY(-5px);
    }

    .form-control {
      background-color: #f1f3f5;
      border: 1px solid #ced4da;
      color: #212529;
    }

    .form-control:focus {
      border-color: #007bff;
      box-shadow: 0 0 0 0.2rem rgba(0,123,255,.25);
    }

    .form-control::placeholder {
      color: #6c757d;
    }

    .btn-modern {
      border-radius: 8px;
      font-weight: 600;
      padding: 10px;
      border: none;
    }

    .btn-withdrawal {
      background-color: #dc3545;
      color: white;
    }

    .btn-deposit {
      background-color: #28a745;
      color: white;
    }

    .btn-transfer {
      background-color: #007bff;
      color: white;
    }

    h2.section-title {
      font-weight: bold;
      margin-bottom: 40px;
    }

    h4.card-title {
      font-weight: 600;
      margin-bottom: 20px;
    }

    label.form-label {
      font-weight: 500;
      color: #212529;
    }
  </style>
</head>
<body>

<div class="container">
  <!-- Customer List -->

  <h2 class="text-center section-title "><i class="fas fa-coins me-2 text-primary"></i>Customer Transactions</h2>

  <div class="row g-4">

    <!-- Withdraw Form -->
    <div class="col-md-4">
      <div class="card-form">
        <h4 class="card-title"><i class="fas fa-money-bill-wave me-2 text-danger"></i>Withdraw</h4>
        <form method="POST" action="${pageContext.request.contextPath}/withdraw">
          <div class="mb-3">
            <label class="form-label">Account Number</label>
            <input type="text" name="accountNumber" class="form-control" placeholder="Your account number" required />
          </div>
          <div class="mb-3">
            <label class="form-label">Description</label>
            <input type="text" name="description" class="form-control" placeholder="description" required />
          </div>
          <div class="mb-3">
            <label class="form-label">Amount (LKR)</label>
            <input type="number" name="amount" class="form-control" placeholder="Eg: 5000" required />
          </div>
          <button type="submit" class="btn btn-withdrawal btn-modern w-100">Withdraw</button>
        </form>
      </div>
    </div>

    <!-- Deposit Form -->
    <div class="col-md-4">
      <div class="card-form">
        <h4 class="card-title"><i class="fas fa-piggy-bank me-2 text-success"></i>Deposit</h4>
        <form method="POST" action="${pageContext.request.contextPath}/deposit">
          <div class="mb-3">
            <label class="form-label">Account Number</label>
            <input type="text" name="accountNumber" class="form-control" placeholder="Your account number" required />
          </div>
          <div class="mb-3">
            <label class="form-label">Description</label>
            <input type="text" name="description" class="form-control" placeholder="description" required />
          </div>
          <div class="mb-3">
            <label class="form-label">Amount (LKR)</label>
            <input type="number" name="amount" class="form-control" placeholder="Eg: 10000" required />
          </div>
          <button type="submit" class="btn btn-deposit btn-modern w-100">Deposit</button>
        </form>
      </div>
    </div>

    <!-- Transfer Form -->
    <div class="col-md-4">
      <div class="card-form">
        <h4 class="card-title"><i class="fas fa-exchange-alt me-2 text-primary"></i>Transfer</h4>
        <form method="POST" action="${pageContext.request.contextPath}/transfer">
          <div class="mb-3">
            <label class="form-label">From Account</label>
            <input type="text" name="fromAccount" class="form-control" placeholder="Your account number" required />
          </div>
          <div class="mb-3">
            <label class="form-label">To Account</label>
            <input type="text" name="toAccount" class="form-control" placeholder="Recipient's account number" required />
          </div>
          <div class="mb-3">
            <label class="form-label">Description</label>
            <input type="text" name="description" class="form-control" placeholder="description" required />
          </div>
          <div class="mb-3">
            <label class="form-label">Amount (LKR)</label>
            <input type="number" name="amount" class="form-control" placeholder="Eg: 2500" required />
          </div>
          <button type="submit" class="btn btn-transfer btn-modern w-100">Transfer</button>
        </form>
      </div>
    </div>

  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
