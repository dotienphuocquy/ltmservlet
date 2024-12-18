<%@ page import="model.bean.Video" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sửa Video</title>
    <style>
    	/* CSS for Sửa Video Form */
			body {
			    font-family: Arial, sans-serif;
			    background-color: #f9f9f9;
			    margin: 0;
			    padding: 0;
			    display: flex;
			    justify-content: center;
			    align-items: center;
			    min-height: 100vh;
			}
			
			.container {
			    background-color: #ffffff;
			    padding: 20px;
			    border-radius: 8px;
			    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
			    width: 400px;
			}
			
			h2 {
			    text-align: center;
			    color: #333;
			    margin-bottom: 20px;
			}
			
			form {
			    display: flex;
			    flex-direction: column;
			    gap: 15px;
			}
			
			label {
			    font-weight: bold;
			    margin-bottom: 5px;
			    color: #555;
			}
			
			input[type="text"] {
			    padding: 10px;
			    border: 1px solid #ccc;
			    border-radius: 4px;
			    width: 100%;
			    font-size: 14px;
			    box-sizing: border-box;
			}
			
			input[type="text"][readonly] {
			    background-color: #f5f5f5;
			    color: #888;
			    cursor: not-allowed;
			}
			
			input[type="submit"] {
			    padding: 10px;
			    border: none;
			    border-radius: 4px;
			    background-color: #007bff;
			    color: #fff;
			    font-size: 16px;
			    cursor: pointer;
			    transition: background-color 0.3s;
			}
			
			input[type="submit"]:hover {
			    background-color: #0056b3;
			}
			
			.buttons {
			    display: flex;
			    justify-content: space-between;
			    align-items: center;
			}
			
			.back-btn {
			    text-decoration: none;
			    padding: 10px;
			    border: 1px solid #ccc;
			    border-radius: 4px;
			    background-color: #f5f5f5;
			    color: #333;
			    font-size: 14px;
			    text-align: center;
			    transition: background-color 0.3s;
			}
			
			.back-btn:hover {
			    background-color: #e0e0e0;
			}
			
			@media (max-width: 480px) {
			    .container {
			        width: 90%;
			        padding: 15px;
			    }
			
			    input[type="submit"], .back-btn {
			        font-size: 14px;
			        padding: 8px;
			    }
			}
    </style>
</head>
<body>
	<%Video video = (Video) request.getAttribute("video"); %>

    <div class="container">
        <!-- Form nhập thông tin -->
        <form action="Video?action=suavideo" method="post">
            <h2>Sửa Video: <%= video.getName()%></h2>
            
            <div>
                <label for="id">ID</label>
				<input 
				  type="text" 
				  id="id" 
				  name="id" 
				  value="<%=video.getId() %>" 
				  readonly 
				  style="background-color: #f5f5f5; color: #888; border: 1px solid #ddd; cursor: not-allowed;"
				  title="Trường này không thể chỉnh sửa">
            </div>  

            <div>
                <label for="name">Name</label>
                <input type="text" id="name" name="name" value="<%=video.getName() %>" required>
            </div>

            <div>
                <label for="username">Username</label>
				<input 
				  type="text" 
				  id="username" 
				  name="username" 
				  value="<%=video.getUsername() %>" 
				  readonly 
				  style="background-color: #f5f5f5; color: #888; border: 1px solid #ddd; cursor: not-allowed;"
				  title="Trường này không thể chỉnh sửa">
            </div>  

			<div>
                <label for="created_at">Created at</label>
				<input 
				  type="text" 
				  id="created_at" 
				  name="created_at" 
				  value="<%=video.getCreated_at() %>" 
				  readonly 
				  style="background-color: #f5f5f5; color: #888; border: 1px solid #ddd; cursor: not-allowed;"
				  title="Trường này không thể chỉnh sửa">
            </div>  
            
            
            <div class="buttons">
                <input type="submit" value="Lưu" onclick="return confirm('Bạn có chắc chắn muốn lưu?')">
				<a href="<%=request.getContextPath() %>" class="back-btn">Quay lại trang chủ</a>
			</div>
        </form>
    </div>
</body>
</html>
