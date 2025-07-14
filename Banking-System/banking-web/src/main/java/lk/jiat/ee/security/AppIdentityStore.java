package lk.jiat.ee.security;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import lk.jiat.ee.entity.Admin;
import lk.jiat.ee.entity.Customer;
import lk.jiat.ee.exceptions.CustomerNotFoundException;
import lk.jiat.ee.service.AdminService;
import lk.jiat.ee.service.CustomerService;


import java.util.Set;


@ApplicationScoped
public class AppIdentityStore implements IdentityStore {
    @EJB
    private CustomerService customerService;

    @EJB
    private AdminService adminService;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        System.out.println(credential.getClass());

        if(credential instanceof UsernamePasswordCredential ){
            UsernamePasswordCredential upc = (UsernamePasswordCredential) credential;
            String email = upc.getCaller();
            String password = upc.getPasswordAsString();

            System.out.println(email+" validate "+password   );

            if (customerService.exists(email, password)) {
                Customer customer = null;
                try {
                    customer = customerService.getCustomerByEmail(email);
                } catch (CustomerNotFoundException e) {
                    throw new RuntimeException(e);
                }
                return new CredentialValidationResult(
                        customer.getEmail(),
                        Set.of("CUSTOMER") // Role
                );
            }
                if (adminService.exists(email, password)) {
                Admin admin = adminService.getAdminByEmail(email);
                    System.out.println(admin.getName());
                return new CredentialValidationResult(
                        admin.getEmail(),
                        Set.of("ADMIN") // Role
                );
            }


        }
        return CredentialValidationResult.INVALID_RESULT;

    }
}
