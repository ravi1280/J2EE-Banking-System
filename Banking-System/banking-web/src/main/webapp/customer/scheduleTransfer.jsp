<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  String email = request.getUserPrincipal().getName();
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Schedule Monthly Fund Transfer</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f4f7fa;
      font-family: 'Roboto', sans-serif;
      padding: 40px 0;
    }

    .form-card {
      background: #ffffff;
      padding: 30px;
      border-radius: 12px;
      box-shadow: 0 8px 24px rgba(0, 0, 0, 0.05);
      max-width: 600px;
      margin: auto;
    }

    .form-card h2 {
      font-weight: 600;
      color: #0d6efd;
      margin-bottom: 25px;
      text-align: center;
    }

    .form-check-label {
      font-weight: 500;
    }

    .btn-primary {
      width: 100%;
      font-weight: bold;
    }
  </style>
</head>
<body>

<div class="container">
  <div class="form-card">
    <h2><i class="fas fa-calendar-alt me-2"></i>Schedule Monthly Fund Transfer</h2>
    <form method="post" action="${pageContext.request.contextPath}/scheduleTransfer">
      <div class="mb-3">
        <label for="fromAccount" class="form-label">My Bank Account</label>
        <input type="text" class="form-control" id="fromAccount" name="fromAccount" required />
      </div>

      <div class="mb-3">
        <label for="targetAccount" class="form-label">Target Bank Account</label>
        <input type="text" class="form-control" id="targetAccount" name="targetAccount" required />
      </div>

      <div class="mb-3">
        <label for="amount" class="form-label">Amount (LKR)</label>
        <input type="number" class="form-control" id="amount" name="amount" step="0.01" required />
      </div>

      <div class="form-check mb-4">
        <input class="form-check-input" type="checkbox" id="active" name="active" checked>
        <label class="form-check-label" for="active">Enable Schedule</label>
      </div>

      <button type="submit" class="btn btn-primary">Save Schedule</button>
    </form>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- Font Awesome for icons (optional) -->
</body>
</html>
