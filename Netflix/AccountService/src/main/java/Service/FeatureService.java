package Service;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import Model.Feature;
import io.quarkus.panache.common.Parameters;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;

@ApplicationScoped
@Transactional(REQUIRED)
public class FeatureService {


    @Transactional(SUPPORTS)
    public Feature findFeatureById(Long id) {
        return Feature.findById(id);
    }

    public Feature findFeatureByProfileIdAndVideoId(Long profileId, Long videoId){
        Feature entity = Feature.find("profile_id = :profile and video_id = :video",
            Parameters.with("profile", profileId).and("video", videoId)).firstResult();
        return entity;
    }

    @Transactional(SUPPORTS)
    public List<Feature> getFeaturesByProfileId(Long id){
        List<Feature> features = Feature.find("profile_id", id).list();
        return features;
    }

    @Transactional(SUPPORTS)
    public List<Feature> getFavoritesByProfileId(Long id){
        List<Feature> entities = Feature.find("profile_id = :profile and favorite = true",
            Parameters.with("profile", id)).list();
        return entities;
    }

    public Feature persistFeature(@Valid Feature feature) {
        Feature.persist(feature);
        return feature;
    }

    public Feature updateFeature(@Valid Feature feature) {
        Feature entity = this.findFeatureByProfileIdAndVideoId(feature.profile.id, feature.video.id);
        if(entity == null){
            this.persistFeature(feature);
        }
        else{
            entity.seconds = feature.seconds;
            entity.favorite = feature.favorite;
        }
        return entity;
    }

    public void deleteFeature(Long id) {
        Feature feature = Feature.findById(id);
        feature.delete();
    }
}
