<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String customerId = request.getParameter("cid");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin | Add Account</title>
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
        .form-label {
            font-weight: 500;
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
        <h1 class="mb-4">Add Customer Account</h1>
        <form method="POST" action="${pageContext.request.contextPath}/addAccount">
            <div class="mb-3">
                <label for="account_number" class="form-label">Account Number</label>
                <input type="number" class="form-control" id="account_number" name="account_number" required>
            </div>

            <div class="mb-3">
                <label for="account_type" class="form-label">Account Type</label>
                <select class="form-select" id="account_type" name="account_type" required>
                    <option value="SAVINGS">Savings</option>
                    <option value="CURRENT">Current</option>
                    <option value="FIXED_DEPOSIT">Fixed Deposit</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="balance" class="form-label">Balance</label>
                <input type="number" class="form-control" id="balance" name="balance" required>
            </div>

            <div class="mb-3">
                <label for="interestRate" class="form-label">Interest Rate (%)</label>
                <input type="number" class="form-control" id="interestRate" name="interestRate" required>
            </div>

            <input type="hidden" name="cid" value="<%= customerId %>">

            <button type="submit" class="btn btn-primary w-100">Add Account</button>
        </form>
    </div>
</div>

</body>
</html>
