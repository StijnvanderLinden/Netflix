package main.java.Model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.List;
import java.util.Random;
import javax.persistence.Entity;
import org.jetbrains.annotations.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Profile extends PanacheEntity {

    @NotNull
    @Size(min = 3, max = 50)
    public String username;
    public List<Video> videos;

    public Profile(
        @NotNull @Size(min = 3, max = 50) String username) {
        this.username = username;
    }

    public static Profile findRandom() {
        long countHeroes = Profile.count();
        Random random = new Random();
        int randomProfile = random.nextInt((int) countHeroes);
        return Profile.findAll().page(randomProfile, 1).firstResult();
    }

    public static Profile findByUsername(String username){
        return find("username", username).firstResult();
    }

    @Override
    public String toString() {
        return "Profile{" +
            "id=" + id +
            ", name='" + username + '\'' +
            '}';
    }
}
