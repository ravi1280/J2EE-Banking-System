<%@ page import="javax.naming.InitialContext" %>
<%@ page import="lk.jiat.ee.service.CustomerService" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="lk.jiat.ee.entity.Customer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    try {
        InitialContext initialContext = new InitialContext();
        CustomerService customerService = (CustomerService) initialContext.lookup("lk.jiat.ee.service.CustomerService");
        List<Customer> customers = customerService.getAllCustomers();
        pageContext.setAttribute("customers", customers);
    } catch (NamingException e) {
        throw new RuntimeException(e);
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin | Add Customer</title>
    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f4f7fa;
        }

        .container {
            margin-top: 40px;
        }

        .card-header {
            background-color: #0d6efd;
            color: white;
        }

        .btn-primary {
            background-color: #0d6efd;
        }

        table {
            margin-bottom: 30px;
        }
    </style>
</head>
<body>

<div class="container">
    <!-- Customer List -->
    <div class="card mb-4 shadow">
        <div class="card-header">
            <h4 class="mb-0">Customer List</h4>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered table-hover align-middle">
                    <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Full Name</th>
                        <th>Address</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>User Type</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="customers" items="${customers}">
                        <tr>
                            <td>${customers.id}</td>
                            <td>${customers.fullName}</td>
                            <td>${customers.address}</td>
                            <td>${customers.email}</td>
                            <td>${customers.phone}</td>
                            <td>${customers.userType.name()}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/addAccount.jsp?cid=${customers.id}" class="btn btn-sm btn-primary">
                                    Add Account
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Add Customer Form -->
    <div class="card shadow mb-4">
        <div class="card-header">
            <h4 class="mb-0">Add Customer</h4>
        </div>
        <div class="card-body">
            <form method="POST" action="${pageContext.request.contextPath}/addCustomer">
                <div class="mb-3">
                    <label for="fullName" class="form-label">Full Name</label>
                    <input type="text" class="form-control" name="fullName" id="fullName" required>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" name="email" id="email" required>
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" name="password" id="password" required>
                </div>

                <div class="mb-3">
                    <label for="address" class="form-label">Address</label>
                    <input type="text" class="form-control" name="address" id="address" required>
                </div>

                <div class="mb-3">
                    <label for="phone" class="form-label">Phone</label>
                    <input type="number" class="form-control" name="phone" id="phone" required>
                </div>

                <button type="submit" class="btn btn-primary w-100">Add Customer</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
