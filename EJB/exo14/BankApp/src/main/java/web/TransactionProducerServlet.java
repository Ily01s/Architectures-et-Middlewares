package web;

import java.io.IOException;
import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

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

        System.out.println("Starting to send message...");
        try (Connection connection = connectionFactory.createConnection();
             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             MessageProducer producer = session.createProducer(transactionQueue)) {

                System.out.println("Connection and session created...");
            TextMessage message = session.createTextMessage(transactionDetails);
            producer.send(message);
            System.out.println("Message sent successfully.");
            response.getWriter().println("Transaction sent: " + transactionDetails);
        } catch (JMSException e) {
            e.printStackTrace();
            response.getWriter().println("Failed to send transaction: " + e.getMessage());
        }
        System.out.println("Exiting the servlet...");
    }
}
