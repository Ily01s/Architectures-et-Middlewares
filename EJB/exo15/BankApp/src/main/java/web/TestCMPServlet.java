package web;

import ejb.AccountServiceBeanCMP;
import ejb.Account;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/TestCMP")
public class TestCMPServlet extends HttpServlet {

    @EJB
    private AccountServiceBeanCMP accountService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Exemple de création d'un compte
        accountService.createAccount("12345", "John Doe", 1000.0);

        // Récupération des comptes
        List<Account> accounts = accountService.getAllAccounts();

        // Affichage des comptes dans la réponse
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>Liste des comptes :</h1>");
        for (Account account : accounts) {
            response.getWriter().println("<p>ID: " + account.getId() + ", Owner: " + account.getOwner()
                    + ", Balance: " + account.getBalance() + "€</p>");
        }
        response.getWriter().println("</body></html>");
    }
}
