package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.SaveVideo;
import model.bo.VideoBO;
import service.processing.SaveVideoProcessing;
import utils.Pair;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB - Bộ nhớ đệm khi tải file lớn
		maxFileSize = 1024L * 1024 * 1024 * 2, // 2 GB (2 x 1024 x 1024 x 1024 bytes)
		maxRequestSize = 1024L * 1024 * 1024 * 2 // 2 GB - Kích thước toàn bộ request
)
@WebServlet("/Video")
public class Video extends HttpServlet {
	@Override
	public void init() throws ServletException {
		super.init();
		for (int i = 0; i < 4; i++)
			new SaveVideoProcessing().start();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Đặt mã hóa cho request và response
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// Thiết lập kiểu nội dung phản hồi (nếu là HTML hoặc JSON)
		response.setContentType("text/html; charset=UTF-8");

		String action = request.getParameter("action");
		String fileName = request.getParameter("fileName");
		String username = (String) request.getSession().getAttribute("username");

		if (fileName != null) {
			String filePath = getServletContext().getRealPath("") + File.separator + "uploads" + File.separator
					+ fileName;
			File file = new File(filePath);
			if (file.exists()) {
				response.setContentType("video/mp4");
				response.setContentLength((int) file.length());
				try (FileInputStream in = new FileInputStream(file); OutputStream out = response.getOutputStream()) {

					byte[] buffer = new byte[1024];
					int bytesRead;
					while ((bytesRead = in.read(buffer)) != -1) {
						out.write(buffer, 0, bytesRead);
					}
				}
			} else {
				System.out.println("Khong tim thay file");

			}
		} else if (username != null) {
			if (action.equals("them")) {
				String message = request.getParameter("message");
				if (message != null) {
					request.setAttribute("message", message);
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/video/them.jsp");
				dispatcher.forward(request, response);
			} else if (action.equals("tiendo")) {
				response.setContentType("text/html");
				response.setIntHeader("Refresh", 5); // Refresh trang sau 5 giây

				response.getWriter().println("<a href=\"" + request.getContextPath() + "\">Quay lại trang chủ</a><br>");

				for (Entry<Pair<String,String>, AtomicReference<Double>> entry : SaveVideoProcessing.progressMap
						.entrySet()) {
					Pair<String, String> key = entry.getKey();
					AtomicReference<Double> value = entry.getValue();
					if (key.getKey().equals(username)) { // So sánh username trong Pair
						response.getWriter().println(
								"Video có ID là: " + key.getValue() + ", tiến độ: " + value + "%<br>");
					}
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Đặt mã hóa cho request và response
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// Thiết lập kiểu nội dung phản hồi (nếu là HTML hoặc JSON)
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("action");
		if (action.equals("xem")) {
			String id = request.getParameter("id");

			model.bean.Video vid = VideoBO.getVideoById(id);

			if (vid != null) {
				// Lưu thông tin video vào request scope và chuyển sang trang xem video
				request.setAttribute("video", vid);
				RequestDispatcher dispatcher = request.getRequestDispatcher("views/video/xem.jsp");
				dispatcher.forward(request, response);
			} else {
				response.getWriter().println("Không tìm thấy video với ID: " + id);
			}
		} else if (request.getSession().getAttribute("username") != null) {
			if (action.equals("them")) {

				String username = (String) request.getSession().getAttribute("username");

				// Nhận các Part từ request
				Part idPart = request.getPart("id");
				Part namePart = request.getPart("name");
				Part filePart = request.getPart("file");

				// Đọc nội dung của input text từ các Part
				String id = new BufferedReader(new InputStreamReader(idPart.getInputStream(), "UTF-8")).readLine();
				String name = new BufferedReader(new InputStreamReader(namePart.getInputStream(), "UTF-8")).readLine();

				String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

				InputStream tmpInputStream = null;
				long tmpSize = 0;

				try {
					tmpInputStream = filePart.getInputStream();
					tmpSize = filePart.getSize();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				if (tmpInputStream != null && tmpSize != 0) {
					SaveVideo tmp = new SaveVideo(id, username, name, uploadPath, tmpInputStream, tmpSize);

//				 	Xử lý ghi file trong background
					SaveVideoProcessing.queue.add(tmp);

					// Phản hồi ngay lập tức cho client
					response.getWriter().println("Video đang được tải lên và xử lý. Bạn có thể tắt trình duyệt.<br>");
					response.getWriter()
							.println("<a href=\"" + request.getContextPath() + "\">Quay lại trang chủ</a><br>");
					response.getWriter().println("<a href=\"Video?action=tiendo\">Xem tiến độ tải</a>");
				} else {
					// Phản hồi ngay lập tức cho client
					response.getWriter().println("Tải video thất bại.<br>");
					response.getWriter()
							.println("<a href=\"" + request.getContextPath() + "\">Quay lại trang chủ</a><br>");
				}
			} else if (action.equals("sua")) {
				String id = request.getParameter("id");

				model.bean.Video video = VideoBO.getVideoById(id);

				request.setAttribute("video", video);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/video/sua.jsp");
				dispatcher.forward(request, response);

			} else if (action.equals("xoa")) {
				String id = request.getParameter("id");

				VideoBO.xoa(id);

				String filePath = getServletContext().getRealPath("") + File.separator + "uploads" + File.separator
						+ id;

				File file = new File(filePath);

				if (file.exists())
					file.delete();

				response.sendRedirect(request.getContextPath());
			} else if (action.equals("suavideo")) {
				String id = request.getParameter("id");
				String name = request.getParameter("name");

				model.bean.Video video = new model.bean.Video(id, null, name);

				VideoBO.sua(video);

				response.sendRedirect(request.getContextPath());
			}
		}
	}
}
