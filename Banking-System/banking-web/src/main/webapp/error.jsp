<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>Error</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background-color: #fff1f2;
      color: #991b1b;
      text-align: center;
      padding: 80px 20px;
    }

    .error-box {
      display: inline-block;
      background-color: #ffe4e6;
      border: 2px solid #f87171;
      padding: 40px 50px;
      border-radius: 15px;
      box-shadow: 0 4px 15px rgba(153, 27, 27, 0.3);
      max-width: 600px;
      width: 100%;
    }

    h2 {
      font-size: 28px;
      margin-bottom: 20px;
    }

    .message {
      font-size: 18px;
      margin-top: 10px;
    }

    .back-link {
      margin-top: 30px;
      display: inline-block;
      background-color: #991b1b;
      color: #fff;
      padding: 12px 25px;
      text-decoration: none;
      font-weight: bold;
      border-radius: 8px;
      transition: background-color 0.3s ease;
    }

    .back-link:hover {
      background-color: #7f1d1d;
    }
  </style>
</head>
<body>
<div class="error-box">
  <h2>🚫 Oops! Something went wrong</h2>

  <div class="message">
    <%
      if (exception != null && exception.getMessage() != null) {
        out.print(exception.getMessage());
      } else {
        out.print("An unexpected error occurred. Please try again later.");
      }
    %>
  </div>

  <a href="index.jsp" class="back-link">⬅ Back to Home</a>
</div>
</body>
</html>
