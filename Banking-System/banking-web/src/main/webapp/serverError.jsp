<%--
  Created by IntelliJ IDEA.
  User: ravis
  Date: 7/14/2025
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Server Error - 500</title>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #fef2f2;
            color: #7f1d1d;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            text-align: center;
        }

        .error-container {
            max-width: 600px;
            background-color: #fee2e2;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(185, 28, 28, 0.3);
            border: 2px solid #fca5a5;
        }

        h1 {
            font-size: 60px;
            margin: 0;
        }

        h2 {
            font-size: 24px;
            margin-top: 10px;
        }

        p {
            margin-top: 20px;
            font-size: 18px;
            color: #991b1b;
        }

        a.back-btn {
            display: inline-block;
            margin-top: 30px;
            background-color: #b91c1c;
            color: white;
            padding: 12px 24px;
            text-decoration: none;
            border-radius: 8px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        a.back-btn:hover {
            background-color: #7f1d1d;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1>500</h1>
    <h2>Internal Server Error</h2>
    <p>Sorry! Something went wrong on our end.<br>
        Please try again later or contact support.</p>

    <%
        if (exception != null && exception.getMessage() != null) {
    %>
    <p style="font-size: 16px; font-style: italic;">
        <%= exception.getMessage() %>
    </p>
    <%
        }
    %>

    <a href="${pageContext.request.contextPath}/index.jsp" class="back-btn">⬅ Back to Home</a>
</div>
</body>
</html>
