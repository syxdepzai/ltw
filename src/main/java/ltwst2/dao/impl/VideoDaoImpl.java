package ltwst2.dao.impl;

import java.util.ArrayList;
import java.util.List;
import ltwst2.dao.IVideoDao;
import ltwst2.models.VideoModel;

public class VideoDaoImpl implements IVideoDao {
    private List<VideoModel> videos = new ArrayList<>();  // Lưu dữ liệu tạm thời

    @Override
    public List<VideoModel> findAll() {
        return videos;
    }

    @Override
    public VideoModel findById(int id) {
        return videos.stream().filter(video -> video.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void insert(VideoModel video) {
        videos.add(video);
    }

    @Override
    public void update(VideoModel video) {
        VideoModel existingVideo = findById(video.getId());
        if (existingVideo != null) {
            existingVideo.setTitle(video.getTitle());
            existingVideo.setDescription(video.getDescription());
            existingVideo.setVideoUrl(video.getVideoUrl());
            existingVideo.setStatus(video.getStatus());
        }
    }

    @Override
    public void delete(int id) {
        videos.removeIf(video -> video.getId() == id);
    }
}
