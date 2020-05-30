package main.java.Service;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import main.java.Model.Account;
import main.java.Model.Feature;
import main.java.Model.Profile;

@ApplicationScoped
@Transactional(REQUIRED)
public class FeatureService {

    @Transactional(SUPPORTS)
    public List<Feature> findAllFeatures() {
        return Profile.listAll();
    }

    @Transactional(SUPPORTS)
    public Feature findFeatureById(Long id) {
        return Profile.findById(id);
    }

    public Feature persistFeature(@Valid Feature profile) {
        Profile.persist(profile);
        return profile;
    }

//    public Feature updateFeature(@Valid Feature feature) {
//        Feature entity = Feature.findById(profile.id);
//        entity.username = feature.username;
//        return entity;
//    }

    public void deleteFeature(Long id) {
        Feature feature = Feature.findById(id);
        feature.delete();
    }
}
