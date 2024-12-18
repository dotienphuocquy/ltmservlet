<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .container {
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        padding: 20px 40px;
        width: 300px;
        text-align: center;
    }

    h1 {
        color: #333;
        margin-bottom: 20px;
    }

    .form-group {
        margin-bottom: 15px;
        text-align: left;
    }

    .form-group label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
        color: #333;
    }

    .form-group input {
        width: 100%;
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }

    .form-group input:focus {
        border-color: #007bff;
        outline: none;
    }

    .buttons {
        margin-top: 15px;
    }

    .buttons button {
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        margin-right: 5px;
        font-size: 14px;
    }

    .buttons button[type="submit"] {
        background-color: #007bff;
        color: white;
        transition: background-color 0.3s;
    }

    .buttons button[type="submit"]:hover {
        background-color: #0056b3;
    }

    .buttons button[type="reset"] {
        background-color: #6c757d;
        color: white;
        transition: background-color 0.3s;
    }

    .buttons button[type="reset"]:hover {
        background-color: #5a6268;
    }

    .error {
        color: red;
        margin-bottom: 15px;
    }

    a {
        text-decoration: none;
        color: #007bff;
        margin-bottom: 15px;
        display: inline-block;
        transition: color 0.3s;
    }

    a:hover {
        color: #0056b3;
    }
</style>
</head>
<body>
    <div class="container">
        <% if (request.getAttribute("error") != null) { %>
            <div class="error"><%= request.getAttribute("error") %></div>
        <% } %>
        <h2>Đăng nhập hệ thống</h2>
        <div>
            <form action="DangNhap" method="post">
                <div class="form-group">
                    <label for="username">Tên đăng nhập:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Mật khẩu:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="buttons">
                    <button type="submit">Đăng nhập</button>
                    <button type="reset">Hủy</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
