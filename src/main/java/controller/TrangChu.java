package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.VideoBO;

/**
 * Servlet implementation class TrangChu
 */
@WebServlet("/TrangChu")
public class TrangChu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrangChu() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) request.getSession().getAttribute("username");
		
		if (username == null) {
			List<model.bean.Video> danhsachvideo = VideoBO.getAllVideo();

			request.setAttribute("danhsachvideo", danhsachvideo);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/index.jsp");
			dispatcher.forward(request, response);
		} else {
			List<model.bean.Video> danhsachvideo = VideoBO.getUserVideo(username);

			request.setAttribute("danhsachvideo", danhsachvideo);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/index.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
