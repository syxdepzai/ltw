package ltwst2.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ltwst2.models.CategoryModel;
import ltwst2.services.ICategoryService;
import ltwst2.services.impl.CategoryServiceImpl;
import static ltwst2.utils.Constant.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/edit", "/admin/category/update", "/admin/category/delete", "/admin/category/search" })
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Đường dẫn thư mục lưu trữ file upload
	private static final String UPLOAD_DIRECTORY = "uploads";

	public ICategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("categories")) {
			List<CategoryModel> list = cateService.findALL();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		} else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			CategoryModel category = cateService.findById(id);
			req.setAttribute("cate", category);

			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			cateService.delete(Integer.parseInt(id));
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("insert")) {
			CategoryModel category = new CategoryModel();
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);

			// Đường dẫn tuyệt đối đến thư mục upload
			String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			String fname = "";
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
					// Lấy tên file và extension
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					// Lưu file vào thư mục upload
					part.write(uploadPath + File.separator + fname);
					category.setImages(fname);
				} else {
					// Nếu không có ảnh upload, sử dụng ảnh mặc định
					category.setImages("avata.png");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			category.setCategoryname(categoryname);
			category.setStatus(statuss);

			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");

		} else if (url.contains("update")) {
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			CategoryModel category = new CategoryModel();
			category.setCategoryid(categoryid);
			category.setCategoryname(categoryname);
			category.setStatus(statuss);
			CategoryModel cateold = cateService.findById(categoryid);
			String fileold = cateold.getImages();
			// Đường dẫn tuyệt đối đến thư mục upload
			String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			String fname = "";
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
					// Lấy tên file và extension
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					// Lưu file vào thư mục upload
					part.write(uploadPath + File.separator + fname);
					category.setImages(fname);
				} else {
					// Nếu không có ảnh upload, sử dụng ảnh mặc định
					category.setImages(fileold);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			cateService.update(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
}
