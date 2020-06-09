package Service;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import Model.Profile;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;

@ApplicationScoped
@Transactional(REQUIRED)
public class ProfileService {

    @Transactional(SUPPORTS)
    public List<Profile> findAllProfiles() {
        return Profile.listAll();
    }

    @Transactional(SUPPORTS)
    public Profile findProfileById(Long id) {
        return Profile.findById(id);
    }

    @Transactional(SUPPORTS)
    public Profile findProfileByName(String name) {
        return Profile.findByUsername(name);
    }

    @Transactional(SUPPORTS)
    public List<Profile> findProfilesByAccountId(long id) {
        List<Profile> profiles = Profile.find("account_id", id).list();
        System.out.println(profiles);
        return profiles;
    }

    public Profile persistProfile(@Valid Profile profile) {
        Profile.persist(profile);
        return profile;
    }

    public Profile updateUsername(@Valid Profile profile) {
        Profile entity = Profile.findById(profile.id);
        entity.username = profile.username;
        return entity;
    }

    public void deleteProfile(Long id) {
        Profile profile = Profile.findById(id);
        profile.delete();
    }
}
