package lk.jiat.ee.service;

import jakarta.ejb.Remote;
import lk.jiat.ee.entity.Customer;

@Remote
public interface CustomerService {

    Customer getCustomerByID(String username, String password);

    Customer getCustomerByEmail(String email);

    boolean exists(String username, String password);

    void createCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(String username, String password);

}
