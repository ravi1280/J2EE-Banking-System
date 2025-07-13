<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SecureBank | Home</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">

    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            background: linear-gradient(to right, #3a0ca3, #4361ee);
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Roboto', sans-serif;
        }

        .login-card {
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(16px);
            border-radius: 20px;
            padding: 40px;
            color: #fff;
            width: 90%;
            max-width: 500px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
            text-align: center;
            border: 1px solid rgba(255,255,255,0.2);
        }

        .login-card h1 {
            font-weight: bold;
            margin-bottom: 15px;
        }

        .login-card p {
            color: #ddd;
            margin-bottom: 30px;
        }

        .btn-custom {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 10px;
            font-weight: bold;
            font-size: 16px;
            margin-bottom: 15px;
            transition: 0.3s ease-in-out;
        }

        .btn-customer {
            background-color: #00b4d8;
            color: #fff;
        }

        .btn-admin {
            background-color: #7209b7;
            color: #fff;
        }

        .btn-custom:hover {
            transform: scale(1.03);
            opacity: 0.95;
        }

        .icon-circle {
            background-color: rgba(255, 255, 255, 0.2);
            width: 80px;
            height: 80px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 20px;
        }

        .icon-circle i {
            font-size: 36px;
            color: white;
        }

        @media (max-width: 576px) {
            .login-card {
                padding: 30px 20px;
            }

            .btn-custom {
                font-size: 15px;
            }
        }
    </style>
</head>
<body>

<div class="login-card">
    <div class="icon-circle mb-3">
        <i class="fas fa-university"></i>
    </div>
    <h1>Welcome to SecureBank</h1>
    <p>Your trusted digital banking platform. Choose your login to continue.</p>

    <a href="${pageContext.request.contextPath}/login.jsp" class="btn btn-custom btn-customer">
        <i class="fas fa-user me-2"></i> Customer Login
    </a>

    <a href="${pageContext.request.contextPath}/adminLogin.jsp" class="btn btn-custom btn-admin">
        <i class="fas fa-user-shield me-2"></i> Admin Login
    </a>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
