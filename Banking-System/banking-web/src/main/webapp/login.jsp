<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer | Login</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #007bff, #00c6ff);
            font-family: 'Roboto', sans-serif;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .login-box {
            background: #fff;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }

        .login-title {
            color: #007bff;
            margin-bottom: 30px;
            font-weight: 600;
        }

        .btn-blue {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-blue:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="login-box">
    <h2 class="text-center login-title">Customer Login</h2>
    <form method="POST" action="${pageContext.request.contextPath}/login">
        <div class="mb-3">
            <label for="email" class="form-label">Email address</label>
            <input type="email" class="form-control" name="email" id="email" required />
        </div>

        <div class="mb-4">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" name="password" id="password" required />
        </div>

        <button type="submit" class="btn btn-blue w-100">Login</button>
    </form>
</div>

</body>
</html>
