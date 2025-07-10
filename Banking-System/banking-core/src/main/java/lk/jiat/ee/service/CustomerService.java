package lk.jiat.ee.service;

import jakarta.ejb.Remote;
import lk.jiat.ee.entity.Customer;

import java.util.List;

@Remote
public interface CustomerService {


    Customer getCustomerByID(Long id);

    Customer getCustomerByEmail(String email);

    boolean exists(String username, String password);

    void createCustomer(Customer customer);
    List<Customer> getAllCustomers();

    void updateCustomer(Customer customer);

    void deleteCustomer(String username, String password);

}
