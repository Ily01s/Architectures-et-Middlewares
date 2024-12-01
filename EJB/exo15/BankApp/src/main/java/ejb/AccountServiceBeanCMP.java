package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AccountServiceBeanCMP {

    @PersistenceContext
    private EntityManager entityManager;

    public void createAccount(String accountNumber, String owner, Double balance) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setOwner(owner);
        account.setBalance(balance);
        entityManager.persist(account);
    }

    public Account getAccount(Long id) {
        return entityManager.find(Account.class, id);
    }

    public List<Account> getAllAccounts() {
        return entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }

    public void updateBalance(Long id, Double newBalance) {
        Account account = entityManager.find(Account.class, id);
        if (account != null) {
            account.setBalance(newBalance);
            entityManager.merge(account);
        }
    }

    public void deleteAccount(Long id) {
        Account account = entityManager.find(Account.class, id);
        if (account != null) {
            entityManager.remove(account);
        }
    }
}
