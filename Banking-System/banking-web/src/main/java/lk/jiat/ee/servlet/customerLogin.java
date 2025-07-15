package lk.jiat.ee.servlet;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.entity.Customer;
import lk.jiat.ee.exceptions.AccountNotFoundException;
import lk.jiat.ee.exceptions.CustomerNotFoundException;
import lk.jiat.ee.service.AccountService;
import lk.jiat.ee.service.CustomerService;
import lk.jiat.ee.utils.Encyptions;

import java.io.IOException;

@WebServlet("/login")
public class customerLogin extends HttpServlet {

    @Inject
    private SecurityContext securityContext;

    @EJB
    private CustomerService customerService;

    @EJB
    private AccountService accountService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("email: "+email
        +" password: "+password);

        AuthenticationParameters parameters = AuthenticationParameters.withParams()
                .credential(new UsernamePasswordCredential(email, Encyptions.encrypt(password)));

        AuthenticationStatus status = securityContext.authenticate(request, response, parameters);
        System.out.println("helllo "+status.toString());

        if (status == AuthenticationStatus.SUCCESS) {
            String username = request.getUserPrincipal().getName();
            Customer currentUser = null;
            try {
                currentUser = customerService.getCustomerByEmail(username);
            } catch (CustomerNotFoundException e) {
                throw new RuntimeException(e);
            }

            Account userAccount = null;
            try {
                userAccount = accountService.getAccountByCustomerID(currentUser.getId());
                System.out.println(userAccount.getAccountNumber());
            } catch (AccountNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println(" User Account Number "+userAccount.getAccountNumber()+"User Name"+currentUser.getFullName());
            HttpSession session = request.getSession();
            session.setAttribute("sessionCustomer",currentUser );
            session.setAttribute("sessionAccount",userAccount);

            response.sendRedirect(request.getContextPath() + "/customer/index.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

    }
}
