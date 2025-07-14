<%--
  Created by IntelliJ IDEA.
  User: ravis
  Date: 7/14/2025
  Time: 3:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Access Denied</title>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #fdf6b2;
            color: #92400e;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            text-align: center;
        }

        .error-container {
            max-width: 600px;
            background-color: #fef3c7;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(146, 64, 14, 0.2);
            border: 2px solid #facc15;
        }

        h1 {
            font-size: 60px;
            margin: 0;
        }

        h2 {
            font-size: 26px;
            margin-top: 10px;
        }

        p {
            margin-top: 20px;
            font-size: 18px;
            color: #b45309;
        }

        a.back-btn {
            display: inline-block;
            margin-top: 30px;
            background-color: #b45309;
            color: white;
            padding: 12px 24px;
            text-decoration: none;
            border-radius: 8px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        a.back-btn:hover {
            background-color: #78350f;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1>403</h1>
    <h2>Access Denied</h2>
    <p>🚫 You are not authorized to access this page.<br>
        Please log in with the proper account or contact your administrator.</p>

    <a href="index.jsp" class="back-btn">🔐 Login</a>
</div>
</body>
</html>

