import javax.jms.*;
import javax.naming.InitialContext;

public class JmsProducer {
    public static void main(String[] args) {
        try {
            // Initialiser le contexte JNDI pour rechercher les ressources JMS
            InitialContext ctx = new InitialContext();
            ConnectionFactory factory = (ConnectionFactory) ctx.lookup("java:/ConnectionFactory");
            Queue queue = (Queue) ctx.lookup("java:/jms/queue/TransactionQueue");

            // Créer une connexion JMS
            Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);

            // Envoyer un message texte
            TextMessage message = session.createTextMessage("Transaction test via Producteur autonome");
            producer.send(message);

            System.out.println("Message envoyé avec succès !");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
