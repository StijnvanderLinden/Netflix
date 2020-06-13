package Model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
    @NamedQuery(name = "feature", query = "select p from Feature as p where p.profile.id = :profile_id and p.video.id = :video_id")
})

@Entity
public class Feature extends PanacheEntity {

    @ManyToOne
    public Profile profile;
    @ManyToOne
    public Video video;
    public int seconds;
    public boolean favorite;

    @Override
    public String toString() {
        return "Feature{" +
            "profile=" + profile +
            ", video=" + video +
            ", seconds=" + seconds +
            '}';
    }
}
