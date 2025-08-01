package lk.jiat.ee.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.authentication.mechanism.http.AutoApplySession;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@AutoApplySession
@ApplicationScoped
public class AuthMechanism implements HttpAuthenticationMechanism {
    @Inject
    private IdentityStore identityStore;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request,
                                                HttpServletResponse response,
                                                HttpMessageContext context) throws AuthenticationException {

        AuthenticationParameters authenticationParameters =context.getAuthParameters();
        if(authenticationParameters.getCredential() !=null){
            CredentialValidationResult result = identityStore.validate(authenticationParameters.getCredential());

            if(result.getStatus() == CredentialValidationResult.Status.VALID){
                AuthenticationStatus status = context.notifyContainerAboutLogin(result);

//                try {
//                    for (String role : result.getCallerGroups()) {
//                        if ("ADMIN".equals(role)) {
//                            response.sendRedirect(request.getContextPath() + "/admin/index.jsp");
//                            return AuthenticationStatus.SEND_CONTINUE;
//                        } else if ("CUSTOMER".equals(role)) {
//                            response.sendRedirect(request.getContextPath() + "/customer/index.jsp");
//                            return AuthenticationStatus.SEND_CONTINUE;
//                        }
//                    }
//                } catch (IOException e) {
//                    throw new RuntimeException("Redirection failed", e);
//                }

                return status;
            }else {
                return AuthenticationStatus.SEND_FAILURE;
            }
        }

        if (context.isProtected() && context.getCallerPrincipal() == null) {
            String requestURI = request.getRequestURI();

            try {

                if (requestURI.contains("/admin/")) {
                    response.sendRedirect(request.getContextPath() + "/adminLogin.jsp");
                } else if (requestURI.contains("/customer/")) {
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                } else {
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                }

                return AuthenticationStatus.SEND_CONTINUE;
            } catch (IOException e) {
                throw new RuntimeException("Redirect failed", e);
            }
        }

        return context.doNothing() ;
    }
}
