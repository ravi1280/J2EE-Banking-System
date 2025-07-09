package lk.jiat.ee.bean;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.jiat.ee.entity.Customer;
import lk.jiat.ee.service.CustomerService;

@Stateless
public class CustomerSessionBean implements CustomerService {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Customer getCustomerByID(String username, String password) {
        return null;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        System.out.println("bean email call "+email);
      return new Customer();
    }

    @Override
    public boolean exists(String username, String password) {
        return false;
    }

    @Override
    public void createCustomer(Customer customer) {
        em.persist(customer);

    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    @Override
    public void deleteCustomer(String username, String password) {

    }
}
