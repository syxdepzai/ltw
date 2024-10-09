package ltwst2.dao;

import java.util.List;
import ltwst2.models.VideoModel;

public interface IVideoDao {
    List<VideoModel> findAll();
    VideoModel findById(int id);
    void insert(VideoModel video);
    void update(VideoModel video);
    void delete(int id);
}
