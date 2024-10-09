package ltwst2.controllers.admin;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ltwst2.models.VideoModel;
import ltwst2.services.IVideoService;
import ltwst2.services.impl.VideoServiceImpl;

@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/add", "/admin/video/insert", "/admin/video/edit", "/admin/video/update", "/admin/video/delete" })
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IVideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        if (url.contains("videos")) {
            List<VideoModel> list = videoService.findAll();
            req.setAttribute("listVideos", list);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        } else if (url.contains("add")) {
            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
        } else if (url.contains("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            VideoModel video = videoService.findById(id);
            req.setAttribute("video", video);
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
        } else if (url.contains("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            videoService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("insert")) {
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String videoUrl = req.getParameter("videoUrl");
            int status = Integer.parseInt(req.getParameter("status"));

            VideoModel video = new VideoModel();
            video.setTitle(title);
            video.setDescription(description);
            video.setVideoUrl(videoUrl);
            video.setStatus(status);

            videoService.insert(video);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        } else if (url.contains("update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String videoUrl = req.getParameter("videoUrl");
            int status = Integer.parseInt(req.getParameter("status"));

            VideoModel video = new VideoModel();
            video.setId(id);
            video.setTitle(title);
            video.setDescription(description);
            video.setVideoUrl(videoUrl);
            video.setStatus(status);

            videoService.update(video);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }
}
