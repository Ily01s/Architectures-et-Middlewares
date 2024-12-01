package ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;

@MessageDriven(
    activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/TransactionQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
    }
)
public class TransactionProcessorBean implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String transactionDetails = ((TextMessage) message).getText();
                System.out.println("Transaction received: " + transactionDetails);
                // Ajoutez ici la logique pour traiter la transaction.
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
