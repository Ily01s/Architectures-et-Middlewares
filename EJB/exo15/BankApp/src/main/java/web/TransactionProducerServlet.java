package web;

import java.io.IOException;
import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import ejb.ObjectFactory;


@WebServlet("/SendTransaction")
public class TransactionProducerServlet extends HttpServlet {

    @Resource(lookup = "java:/jms/queue/TransactionQueue")
    private Queue transactionQueue;

    @Resource(lookup = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String transactionDetails = request.getParameter("details");

        try (Session session = ObjectFactory.createJmsSession()) {
            MessageProducer producer = ObjectFactory.createJmsProducer(session, "TransactionQueue");
            TextMessage message = session.createTextMessage(transactionDetails);
            producer.send(message);
            response.getWriter().println("Transaction envoyée : " + transactionDetails);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Échec de l'envoi de la transaction : " + e.getMessage());
        }
    }

}
