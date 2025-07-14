package lk.jiat.ee.exceptions;

public class DuplicateCustomerException extends BankingSystemsException {
    public DuplicateCustomerException(String email) {

        super("A customer already exists with email: " + email);
    }
}
