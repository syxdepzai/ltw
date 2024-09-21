package ltwst2.controllers;

import java.io.IOException;
import java.util.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ltwst2.models.UserModel;
import ltwst2.services.IUserService;
import ltwst2.services.impl.UserService;
import ltwst2.utils.EmailUtils;

@WebServlet(urlPatterns = { "/forgot-password" })
public class ForgotPasswordController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    IUserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String alertMsg = "";

        // Kiểm tra email có tồn tại trong hệ thống không
        UserModel user = userService.findByEmail(email);
        if (user != null) {
            // Tạo token ngẫu nhiên
            String token = UUID.randomUUID().toString();

            // Lưu token vào user (giả sử có một cột "reset_token" trong bảng Users)
            userService.updateResetToken(user.getId(), token);

            // Gửi email chứa link đặt lại mật khẩu
            String resetLink = req.getContextPath() + "/reset-password?token=" + token;
            EmailUtils.sendEmail(email, "Link đặt lại mật khẩu", "Nhấn vào đây để đặt lại mật khẩu: " + resetLink);

            alertMsg = "Một link đặt lại mật khẩu đã được gửi tới email của bạn.";
        } else {
            alertMsg = "Email không tồn tại trong hệ thống!";
        }

        req.setAttribute("alert", alertMsg);
        req.getRequestDispatcher("/views/forgot_password.jsp").forward(req, resp);
    }
}
