package main.java.Model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.List;
import javax.persistence.Entity;
import org.jetbrains.annotations.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Profile extends PanacheEntity {

    @NotNull
    @Size(min = 3, max = 50)
    public String username;

    public Profile(
        @NotNull @Size(min = 3, max = 50) String username) {
        this.username = username;
    }

    public static Profile findByUsername(String username){
        return find("username", username).firstResult();
    }

    public static List<Profile> findByAccount(Account account){
        return find("id", account.id).list();
    }

    @Override
    public String toString() {
        return "Profile{" +
            "id=" + id +
            ", name='" + username + '\'' +
            '}';
    }
}
