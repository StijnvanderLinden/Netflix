package Service;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import Model.Account;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;

@ApplicationScoped
@Transactional(REQUIRED)
public class AccountService {

    @Transactional(SUPPORTS)
    public List<Account> findAllAccounts() {
        return Account.listAll();
    }

    @Transactional(SUPPORTS)
    public Account findAccountById(Long id) {
        System.out.println(id);
        return Account.findById(id);
    }

    public Account findAccountByName(String name) {
        return Account.findByUsername(name);
    }

    @Transactional(SUPPORTS)
    public Account findRandomAccount() {
        Account randomAccount = null;
        while (randomAccount == null) {
            randomAccount = Account.findRandom();
        }
        return randomAccount;
    }

    public Account persistAccount(@Valid Account account) {
        Account.persist(account);
        return account;
    }

    @Transactional(SUPPORTS)
    public Account updateAccount(@Valid Account account) {
        Account entity = Account.findById(account.id);
        entity.username = account.username;
        entity.profiles = account.profiles;
        return account;
    }

    @Transactional(SUPPORTS)
    public Account updateUsername(@Valid Account account) {
        Account entity = Account.findById(account.id);
        entity.username = account.username;
        return entity;
    }

    @Transactional(SUPPORTS)
    public void deleteAccount(Long id) {
        Account account = Account.findById(id);
        account.delete();
    }


}

