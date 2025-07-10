package lk.jiat.ee.servlet;

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
import lk.jiat.ee.utils.Encyptions;

import java.io.IOException;

@WebServlet("/login")
public class customerLogin extends HttpServlet {

    @Inject
    private SecurityContext securityContext;

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
            response.sendRedirect(request.getContextPath() + "/customer/index.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

    }
}
