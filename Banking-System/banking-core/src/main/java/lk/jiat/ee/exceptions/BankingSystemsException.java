package lk.jiat.ee.exceptions;
import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BankingSystemsException extends Exception {

    public BankingSystemsException(String message) {

        super(message);
    }

    public BankingSystemsException(String message, Throwable cause) {
        super(message, cause);
    }
}
