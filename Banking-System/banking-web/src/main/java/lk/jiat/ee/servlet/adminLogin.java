package lk.jiat.ee.servlet;


import jakarta.annotation.security.RolesAllowed;
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


@WebServlet("/adminLogin")
public class adminLogin extends HttpServlet {
    @Inject
    private SecurityContext securityContext;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AuthenticationParameters parameters = AuthenticationParameters.withParams()
                .credential(new UsernamePasswordCredential(email, password));

        AuthenticationStatus status = securityContext.authenticate(request, response, parameters);

        if (status == AuthenticationStatus.SUCCESS) {
//            System.out.println("Authentication failed");
           response.sendRedirect(request.getContextPath() + "/admin/index.jsp");
        }
        else {
            response.sendRedirect(request.getContextPath() + "/adminLogin.jsp");
        }

    }
}
