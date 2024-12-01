package ejb;

import javax.ejb.Local;

@Local
public interface AccountServiceLocal {
    double getAccountBalance(String accountNumber);
}
