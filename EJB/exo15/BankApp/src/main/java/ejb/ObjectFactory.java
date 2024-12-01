package ejb;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.DriverManager;

public class ObjectFactory {

    // Méthode pour créer une connexion JDBC
    public static Connection createJdbcConnection() {
        String DB_URL = "jdbc:mysql://localhost:3306/BankApp";
        String USER = "root";
        String PASSWORD = "password";
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de la connexion JDBC : " + e.getMessage(), e);
        }
    }

    // Méthode pour créer une Session JMS
    public static Session createJmsSession() {
        try {
            Context context = new InitialContext();
            ConnectionFactory factory = (ConnectionFactory) context.lookup("java:/ConnectionFactory");
            javax.jms.Connection connection = factory.createConnection(); // Utilisation de javax.jms.Connection
            connection.start(); // Démarrer la connexion JMS
            return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de la session JMS : " + e.getMessage(), e);
        }
    }

    // Méthode pour créer un Producteur JMS
    public static MessageProducer createJmsProducer(Session session, String queueName) {
        try {
            Context context = new InitialContext();
            Queue queue = (Queue) context.lookup("java:/jms/queue/" + queueName);
            return session.createProducer(queue);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création du Producteur JMS : " + e.getMessage(), e);
        }
    }
}
