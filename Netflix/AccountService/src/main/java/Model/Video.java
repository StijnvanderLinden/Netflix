package Model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
public class Video extends PanacheEntity {

    public String title;
    public String description;
    @ElementCollection
    public List<String> cast;
    public int duration;

    public static Video findByTitle(String title){
        return find("title", title).firstResult();
    }

    public static void create(Video video){
        Video.persist(video);
    }

    @Override
    public String toString() {
        return "Video{" +
            "title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", cast=" + cast +
            ", duration=" + duration +
            '}';
    }
}
