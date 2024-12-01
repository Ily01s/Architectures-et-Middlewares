package ejb;

import javax.ejb.Stateless;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
 

@Stateless
public class AccountServiceBean implements AccountServiceLocal {
    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Override
    public double getAccountBalance(String accountNumber) {
        // Simuler la récupération du solde
        if (accountNumber == null || accountNumber.isEmpty()) {
            return 0.0;
        }
        // Pour la démonstration, le solde est calculé en fonction du numéro de compte
        return Math.abs(accountNumber.hashCode() % 10000);
    }
}
