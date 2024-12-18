<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.bean.Video" %>
<%
    Video video = (Video) request.getAttribute("video");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Xem Video</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 30px auto;
            background: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            border-radius: 8px;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        video {
            display: block;
            margin: 0 auto;
            width: 100%;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .info {
            text-align: center;
            margin-top: 20px;
            font-size: 16px;
            color: #555;
        }

        .info p {
            margin: 8px 0;
        }

        .info strong {
            color: #333;
        }

        /* Nút điều khiển video */
        video::-webkit-media-controls {
            border-radius: 8px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Xem Video: <%= video.getName() %></h2>
        <video controls>
            <source src="Video?fileName=<%= video.getId() %>" type="video/mp4">
            Trình duyệt của bạn không hỗ trợ phát video.
        </video>
        <div class="info">
            <p><strong>Tên người đăng:</strong> <%= video.getUsername() %></p>
            <p><strong>Ngày đăng:</strong> <%= video.getCreated_at() %></p>
        </div>
        <div>
		    <a href="<%=request.getContextPath() %>" >Quay lại trang chủ</a>
        </div>
    </div>

</body>
</html>
