package main.java;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
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

    public String getPassword() {
        return password;
    }

    public static Account findRandom() {
        long countHeroes = Hero.count();
        Random random = new Random();
        int randomHero = random.nextInt((int) countHeroes);
        return Account.findAll().page(randomHero, 1).firstResult();
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
