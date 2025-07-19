package lk.jiat.ee.exceptions;

public class AccountNotFoundException extends BankingSystemsException {
    public AccountNotFoundException(String accountNumber) {
        super("Account not found: "+accountNumber);
    }

    public AccountNotFoundException(Long accountID) {
        super("Account not found: "+accountID);
    }
}
