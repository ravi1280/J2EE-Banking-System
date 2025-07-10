package lk.jiat.ee.bean;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.jiat.ee.entity.Customer;
import lk.jiat.ee.service.CustomerService;

import java.util.List;

@Stateless
public class CustomerSessionBean implements CustomerService {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Customer getCustomerByID(Long id) {

        Customer  customer =  em.createNamedQuery("Customer.FindByID",Customer.class)
                .setParameter("id", id)
                .getSingleResult();
        return customer;

    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Customer  customer =  em.createNamedQuery("Customer.FindByEmail",Customer.class)
                .setParameter("email", email)
                .getSingleResult();
        return customer;
    }

    @Override
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

    @Override
    public void createCustomer(Customer customer) {
        em.persist(customer);

    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = em.createNamedQuery("Customer.FindAll", Customer.class)
                .getResultList();
        return customers;
    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    @Override
    public void deleteCustomer(String username, String password) {

    }
}
