<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin | Login</title>
    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #6a11cb, #2575fc);
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
            color: #6a11cb;
            margin-bottom: 30px;
            font-weight: 600;
        }

        .btn-purple {
            background-color: #6a11cb;
            border-color: #6a11cb;
        }

        .btn-purple:hover {
            background-color: #5913b0;
            border-color: #5913b0;
        }
    </style>
</head>
<body>

<div class="login-box">
    <h2 class="text-center login-title">Admin Login</h2>
    <form method="POST" action="${pageContext.request.contextPath}/adminLogin">
        <div class="mb-3">
            <label for="email" class="form-label">Email address</label>
            <input type="email" class="form-control" name="email" id="email" required />
        </div>

        <div class="mb-4">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" name="password" id="password" required />
        </div>

        <button type="submit" class="btn btn-purple w-100">Login</button>
    </form>
</div>

</body>
</html>
