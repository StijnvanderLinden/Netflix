package main.java.Model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class Video extends PanacheEntity {

    public String title;
    @Column(length = 10000)
    public String description;
    public int duration;
    public String thumbnail;
    @Column(length = 10000)
    public String url;
    @ManyToMany(fetch = FetchType.LAZY)
    public List<Category> categories;

    public static Video findByTitle(String title){
        return find("title", title).firstResult();
    }

    @Override
    public String toString() {
        return "Video{" +
            "title='" + title + '\'' +
            '}';
    }
}
