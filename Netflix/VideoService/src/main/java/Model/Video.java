package main.java.Model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;

@Entity
public class Video extends PanacheEntity {

    public String title;
    public String description;
    public int duration;
    public String thumbnail;

    public static Video findByTitle(String title){
        return find("title", title).firstResult();
    }

    @Override
    public String toString() {
        return "Video{" +
            "title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", duration=" + duration +
            '}';
    }
}
