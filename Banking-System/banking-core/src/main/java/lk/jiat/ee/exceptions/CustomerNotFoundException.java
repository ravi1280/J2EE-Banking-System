package lk.jiat.ee.exceptions;

public class CustomerNotFoundException extends BankingSystemsException {

    public CustomerNotFoundException(String email) {

        super("Customer not found: " + email);
    }
}
