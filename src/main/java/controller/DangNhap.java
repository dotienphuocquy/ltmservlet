package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bo.AdminBO;

/**
 * Servlet implementation class DangNhap
 */
@WebServlet("/DangNhap")
public class DangNhap extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DangNhap() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getSession().getAttribute("username") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/auth/dangnhap.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
		    response.getWriter().write("<h1>Bạn đã đăng nhập rồi!</h1>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(AdminBO.isValidAdmin(username, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			
			response.sendRedirect(request.getContextPath());
		}
		else {
			 // Gắn attribute vào request
	        request.setAttribute("error", "Tài khoản hoặc mật khẩu không chính xác");

	        // Chuyển tiếp request và response đến trang demo.jsp
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/auth/dangnhap.jsp");
	        dispatcher.forward(request, response);
		}
	}

}
