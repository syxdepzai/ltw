package ltwst2.services.impl;

import java.util.List;
import ltwst2.dao.IVideoDao;
import ltwst2.dao.impl.VideoDaoImpl;
import ltwst2.models.VideoModel;
import ltwst2.services.IVideoService;

public class VideoServiceImpl implements IVideoService {
    private IVideoDao videoDao = new VideoDaoImpl();

    @Override
    public List<VideoModel> findAll() {
        return videoDao.findAll();
    }

    @Override
    public VideoModel findById(int id) {
        return videoDao.findById(id);
    }

    @Override
    public void insert(VideoModel video) {
        videoDao.insert(video);
    }

    @Override
    public void update(VideoModel video) {
        videoDao.update(video);
    }

    @Override
    public void delete(int id) {
        videoDao.delete(id);
    }
}
