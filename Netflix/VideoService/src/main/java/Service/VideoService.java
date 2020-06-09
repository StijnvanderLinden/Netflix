package main.java.Service;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import main.java.Model.Video;

@ApplicationScoped
@Transactional(REQUIRED)
public class VideoService {
    @Transactional(SUPPORTS)
    public List<Video> findAllVideos() {
        return Video.listAll();
    }

    @Transactional(SUPPORTS)
    public Video findVideoById(Long id) {
        return Video.findById(id);
    }

    public Video persistVideo(@Valid Video profile) {
        Video.persist(profile);
        return profile;
    }

    public Video updateVideo(@Valid Video video) {
        Video entity = Video.findById(video.id);
        entity.title = video.title;
        entity.description = video.description;
        entity.duration = video.duration;
        return entity;
    }
    public void deleteVideo(Long id) {
        Video feature = Video.findById(id);
        feature.delete();
    }
}
