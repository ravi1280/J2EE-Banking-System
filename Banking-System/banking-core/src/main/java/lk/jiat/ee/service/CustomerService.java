package lk.jiat.ee.service;

import jakarta.ejb.Remote;
import lk.jiat.ee.entity.Customer;
import lk.jiat.ee.exceptions.CustomerNotFoundException;
import lk.jiat.ee.exceptions.DuplicateCustomerException;

import java.util.List;

@Remote
public interface CustomerService {


    Customer getCustomerByID(Long id) throws CustomerNotFoundException;

    Customer getCustomerByEmail(String email) throws CustomerNotFoundException;

    boolean exists(String username, String password);

    void createCustomer(Customer customer) throws DuplicateCustomerException;
    List<Customer> getAllCustomers();

    void updateCustomer(Customer customer);

    void deleteCustomer(String username, String password) throws CustomerNotFoundException;

}
