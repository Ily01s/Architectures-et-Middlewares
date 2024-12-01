package web;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.Context;
import java.util.Properties;

public class JmsProducer {
    public static void main(String[] args) {
        try {

            // Initialiser le contexte JNDI pour rechercher les ressources JMS
            Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

            InitialContext ctx = new InitialContext(env);
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
