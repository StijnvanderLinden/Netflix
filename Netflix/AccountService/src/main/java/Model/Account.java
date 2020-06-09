package Model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import org.jetbrains.annotations.NotNull;

@Entity
public class Account extends PanacheEntity {

    @NotNull
    @Size(min = 3, max = 50)
    public String username;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonbTransient
    public List<Profile> profiles;

    @Override
    public String toString() {
        String string = "Account{" +
            "id=" + id +
            ", name='" + username + '\'';
            return string;
    }
}
