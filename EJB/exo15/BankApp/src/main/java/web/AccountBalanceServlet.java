package web;

import ejb.AccountServiceLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AccountBalance")
public class AccountBalanceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private AccountServiceLocal accountService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer le numéro de compte depuis les paramètres de la requête
        String accountNumber = request.getParameter("accountNumber");

        // Obtenir le solde du compte en utilisant l'EJB
        double balance = accountService.getAccountBalance(accountNumber);

        // Configurer la réponse HTTP
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Générer la réponse HTML
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html><head><title>Solde du compte</title></head><body>");
        response.getWriter().println("<h1>Solde du compte: " + balance + "€</h1>");
        response.getWriter().println("</body></html>");
    }
}
