package ejb;

import javax.ejb.Remote;

@Remote
public interface AccountManager {
    void createAccount(String accountNumber, String owner, double balance);
    double getAccountBalance(String accountNumber);
}
