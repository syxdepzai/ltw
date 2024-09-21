package ltwst2.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ltwst2.models.UserModel;
import ltwst2.services.IUserService;
import ltwst2.services.impl.UserService;
import ltwst2.utils.PasswordUtils;

@WebServlet(urlPatterns = { "/reset-password" })
public class ResetPasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IUserService userService = new UserService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String token = req.getParameter("token");
		String newPassword = req.getParameter("new_password");

		String alertMsg = "";

		// Kiểm tra token có hợp lệ không
		UserModel user = userService.findByResetToken(token);
		if (user != null) {
			// Mã hóa mật khẩu mới
			String hashedPassword = PasswordUtils.hashPassword(newPassword);

			// Cập nhật mật khẩu mới vào cơ sở dữ liệu
			userService.updatePassword(user.getId(), hashedPassword);

			alertMsg = "Mật khẩu của bạn đã được đặt lại thành công!";
		} else {
			alertMsg = "Liên kết không hợp lệ hoặc đã hết hạn!";
		}

		req.setAttribute("alert", alertMsg);
		req.getRequestDispatcher("/views/reset_password.jsp").forward(req, resp);
	}
}
