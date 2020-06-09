package Model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import org.jetbrains.annotations.NotNull;

@Entity
public class Profile extends PanacheEntity {

    @NotNull
    @Size(min = 3, max = 50)
    public String username;

    @ManyToOne
    public Account account;

    public static Profile findByUsername(String username){
        return find("username", username).firstResult();
    }

    public static List<Profile> findByAccount(Account account){
        return find("account_id", account.id).list();
    }

    @Override
    public String toString() {
        return "Profile{" +
            "id=" + id +
            ", name='" + username + '\'' +
            ", account='" + account + '\'' +
            '}';
    }
}
