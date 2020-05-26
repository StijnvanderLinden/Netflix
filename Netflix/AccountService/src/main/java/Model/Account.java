package main.java.Model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.List;
import java.util.Random;
import javax.persistence.Entity;
import org.jetbrains.annotations.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Account extends PanacheEntity {

    @NotNull
    @Size(min = 3, max = 50)
    public String username;
    public String password;
    public List<Profile> profiles;

    public static Account findRandom() {
        long accounts = Account.count();
        Random random = new Random();
        int randomAccount = random.nextInt((int) accounts);
        return Profile.findAll().page(randomAccount, 1).firstResult();
    }

    public static Account findByUsername(String username){
        return find("username", username).firstResult();
    }

    @Override
    public String toString() {
        return "Account{" +
            "id=" + id +
            ", name='" + username + '\'' +
            '}';
    }
}
