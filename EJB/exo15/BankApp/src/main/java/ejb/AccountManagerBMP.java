package ejb;

import javax.ejb.Stateless;
import java.sql.*;

@Stateless
public class AccountManagerBMP implements AccountManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/BankApp";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    @Override
    public void createAccount(String accountNumber, String owner, double balance) {
        try (Connection conn = ObjectFactory.createJdbcConnection()) {
            String sql = "INSERT INTO accounts (accountNumber, owner, balance) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, accountNumber);
                stmt.setString(2, owner);
                stmt.setDouble(3, balance);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création du compte : " + e.getMessage(), e);
        }
    }


    @Override
    public double getAccountBalance(String accountNumber) {
        try (Connection conn = ObjectFactory.createJdbcConnection()) {
            String sql = "SELECT balance FROM accounts WHERE accountNumber = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, accountNumber);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getDouble("balance");
                    } else {
                        throw new RuntimeException("Compte introuvable");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du solde : " + e.getMessage(), e);
        }
    }

}
