package Model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import org.jetbrains.annotations.NotNull;

@Entity
public class Profile extends PanacheEntity {

    @NotNull
    @Size(min = 3, max = 50)
    public String username;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    public Account account;

    public static Profile findByUsername(String username){
        return find("username", username).firstResult();
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
