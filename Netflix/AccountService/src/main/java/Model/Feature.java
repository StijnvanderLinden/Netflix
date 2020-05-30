package main.java.Model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Feature extends PanacheEntity {

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Profile profile;
    @ManyToOne(cascade = CascadeType.PERSIST)
    public Video video;
    public int seconds;
    public boolean favorite;

    public Feature(Profile profile, Video video, int seconds) {
        this.profile = profile;
        this.video = video;
        this.seconds = seconds;
    }

    public Feature(Profile profile, Video video, boolean favorite) {
        this.profile = profile;
        this.video = video;
        this.favorite = favorite;
    }

    public static Profile findByUsername(String username){
        return find("username", username).firstResult();
    }

    public static void create(Profile profile, Video video, int seconds){
        Feature time = new Feature(profile, video, seconds);
        Feature.persist(time);
    }

    public static void favorite(boolean toggle){

    }

    @Override
    public String toString() {
        return "Feature{" +
            "profile=" + profile +
            ", video=" + video +
            ", seconds=" + seconds +
            '}';
    }
}
