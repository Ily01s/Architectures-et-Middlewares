package ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
    activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/OperationQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
    }
)
public class OperationMDB implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String operationDetails = textMessage.getText();
                System.out.println("Dernière opération reçue : " + operationDetails);
                // Logique métier supplémentaire peut être ajoutée ici (par exemple, sauvegarde dans une base)
            } else {
                System.out.println("Message non supporté : " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
