package lk.jiat.ee.bean;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lk.jiat.ee.bean.annotation.CustomerValid;
import lk.jiat.ee.entity.Customer;
import lk.jiat.ee.exceptions.CustomerNotFoundException;
import lk.jiat.ee.exceptions.DuplicateCustomerException;
import lk.jiat.ee.service.CustomerService;

import java.util.List;


//@CustomerValid
@Stateless
public class CustomerSessionBean implements CustomerService {
    @PersistenceContext
    private EntityManager em;

    @PermitAll
    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Customer getCustomerByID(Long id) throws CustomerNotFoundException {
        try {
            return em.createNamedQuery("Customer.FindByID", Customer.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new CustomerNotFoundException("" + id);
        }
    }

    @RolesAllowed({"ADMIN"})
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createCustomer(Customer customer) throws DuplicateCustomerException {
        boolean exists = exists(customer.getEmail(), customer.getPassword());
        if (exists) {
            throw new DuplicateCustomerException(customer.getEmail());
        }
        em.persist(customer);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Customer getCustomerByEmail(String email) throws CustomerNotFoundException  {
        try {
            return em.createNamedQuery("Customer.FindByEmail", Customer.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new CustomerNotFoundException("Email: " + email);
        }
    }


    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public boolean exists(String email, String password) {

        try {
            Customer customer = em.createNamedQuery("Customer.FindByEmailAndPassword", Customer.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();

            return customer != null;
        } catch (Exception e) {
            System.out.println("Customer not found");
           return false;
        }
    }



    @PermitAll
    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Customer> getAllCustomers() {
        List<Customer> customers = em.createNamedQuery("Customer.FindAll", Customer.class)
                .getResultList();
        return customers;
    }
    @RolesAllowed({"ADMIN","CUSTOMER"})
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateCustomer(Customer customer) {
        em.merge(customer);
    }

    @RolesAllowed({"ADMIN"})
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteCustomer(String username, String password) throws CustomerNotFoundException {
        Customer customer = getCustomerByEmail(username);
        em.remove(customer);
    }
}
