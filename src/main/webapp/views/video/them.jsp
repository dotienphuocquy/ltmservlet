<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Video</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f9;
        }
        h2 {
            color: #333;
        }
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: auto;
        }
        input, label {
            display: block;
            width: 100%;
            margin-bottom: 10px;
        }
        input[type="text"], input[type="file"] {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2 style="text-align: center;">Thêm Video Mới</h2>
    <form method="POST" action="Video?action=them" enctype="multipart/form-data">
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" placeholder="Nhập ID video" required>

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" placeholder="Nhập tên video" required>

        <label for="file">Chọn Video:</label>
        <input type="file" id="file" name="file" accept="video/*" required>

        <input type="submit" value="Thêm Video">
        <div>
		    <a href="<%=request.getContextPath() %>" >Quay lại trang chủ</a>
        </div>
    </form>
</body>
</html>
