package ltwst2.services;

import java.util.List;
import ltwst2.models.VideoModel;

public interface IVideoService {
    List<VideoModel> findAll();
    VideoModel findById(int id);
    void insert(VideoModel video);
    void update(VideoModel video);
    void delete(int id);
}
