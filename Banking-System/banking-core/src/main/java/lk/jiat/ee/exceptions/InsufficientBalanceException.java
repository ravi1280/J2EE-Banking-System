package lk.jiat.ee.exceptions;

public class InsufficientBalanceException extends BankingSystemsException {

    public InsufficientBalanceException(double currentBalance, double requestedAmount) {

        super("Insufficient balance. Current: " + currentBalance + ", Requested: " + requestedAmount);
    }
}
