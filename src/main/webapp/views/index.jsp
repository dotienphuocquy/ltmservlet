<%@ page import="model.bean.Video" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách video</title>
    <style>
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .btn {
            display: inline-block;
            padding: 8px 12px;
            margin: 10px auto;
            text-align: center;
            font-size: 14px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
	<%if(session.getAttribute("username") != null){ %>
    	<a href="Video?action=them" class="btn">Thêm Video</a>
    	<a href="Video?action=tiendo" class="btn">Tiến độ tải lên</a>
    	<a href="DangXuat" class="btn">Đăng Xuất</a>
    	<h2 style="text-align: center;">Danh sách Video của bạn</h2>
    	<h3 style="text-align: center;">Tên người dùng: <%=session.getAttribute("username") %></h3>
    <%} else { %>
    	<a href="DangNhap" class="btn">Đăng Nhập</a>
    	<h2 style="text-align: center;">Danh sách Video</h2>
    <%} %>
    <!-- Hiển thị thông báo -->
    <% 
    	String message = (String)request.getAttribute("message");
        if (message != null && message.equals("thanhcong")) { 
    %>
        <h3 style="text-align: center;">Xóa thành công</h3>
    <% 
        } 
    %>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Tên người đăng</th>
                <th>Tên video</th>
                <th>Ngày đăng</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Video> danhSachVideo = (List<Video>) request.getAttribute("danhsachvideo");
                if (danhSachVideo != null && !danhSachVideo.isEmpty()) {
                    for (Video vid : danhSachVideo) {
            %>
            <tr>
                <td><%= vid.getId() %></td>
                <td><%= vid.getUsername() %></td>
                <td><%= vid.getName() %></td>
                <td><%= vid.getCreated_at() %></td>
                <td>
					<form action="Video?action=xem" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="<%= vid.getId() %>">
                        <button type="submit" class="btn">Xem</button>
                    </form>
                 	<!-- Form sửa -->
                 	<%if(session.getAttribute("username") != null){ %>
                    <form action="Video?action=sua" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="<%= vid.getId() %>">
                        <button type="submit" class="btn">Sửa</button>
                    </form>
                    <!-- Form xóa -->
                    <form action="Video?action=xoa" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="<%= vid.getId() %>">
                        <button type="submit" class="btn" style="background-color: #dc3545;" onclick="return confirm('Bạn có chắc chắn muốn xóa?')">Xóa</button>
                    </form>
                    <%} %>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
               <td colspan="5" style="text-align: center;">Không có dữ liệu</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>