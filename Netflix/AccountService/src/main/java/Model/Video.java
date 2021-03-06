package Model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Video extends PanacheEntity {

    public String title;
    @Column(length = 10000)
    public String description;
    public int duration;
    public String thumbnail;
    @Column(length = 10000)
    public String url;

    @Override
    public String toString() {
        return "Video{" +
            "title='" + title + '\'' +
            '}';
    }
}
