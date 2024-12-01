package web;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/SendOperation")
public class OperationProducerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operationDetails = request.getParameter("details");
        if (operationDetails == null || operationDetails.isEmpty()) {
            response.getWriter().println("Veuillez fournir les détails de l'opération en tant que paramètre 'details'.");
            return;
        }

        try {
            Context context = new InitialContext();
            ConnectionFactory factory = (ConnectionFactory) context.lookup("java:/ConnectionFactory");
            try (Connection connection = factory.createConnection()) {
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Queue queue = (Queue) context.lookup("java:/jms/queue/OperationQueue");
                MessageProducer producer = session.createProducer(queue);

                TextMessage message = session.createTextMessage(operationDetails);
                producer.send(message);
                response.getWriter().println("Opération envoyée : " + operationDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Erreur lors de l'envoi de l'opération : " + e.getMessage());
        }
    }
}
