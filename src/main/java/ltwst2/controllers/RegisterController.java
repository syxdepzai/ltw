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

@WebServlet(urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    IUserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Chuyển hướng đến trang đăng ký
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        // Lấy thông tin từ form đăng ký
        String username = req.getParameter("username");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Kiểm tra xem có trường nào bị bỏ trống không
        String alertMsg = "";
        if (username.isEmpty() || fullname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            alertMsg = "Vui lòng điền đầy đủ thông tin!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra xem username đã tồn tại chưa
        if (service.FindByUserName(username) != null) {
            alertMsg = "Username đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // Tạo user mới và thêm vào cơ sở dữ liệu
        UserModel newUser = new UserModel();
        newUser.setUsername(username);
        newUser.setFullname(fullname);
        newUser.setEmail(email);
        newUser.setPassword(password);  // Lưu ý: Bạn nên mã hóa mật khẩu trước khi lưu

        service.insert(newUser);

        // Sau khi đăng ký thành công, chuyển hướng về trang login
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
