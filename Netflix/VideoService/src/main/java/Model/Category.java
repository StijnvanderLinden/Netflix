package main.java.Model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class Category extends PanacheEntity {

    public String name;

    @ManyToMany(fetch = FetchType.LAZY)
    public List<Video> videos;

    @Override
    public String toString() {
        return "Category{" +
            "name='" + name + '\'' +
            ", videos=" + videos +
            '}';
    }
}
