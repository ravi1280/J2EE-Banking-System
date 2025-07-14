package lk.jiat.ee.exceptions;

public class InvalidDepositAmountException extends BankingSystemsException {

    public InvalidDepositAmountException(double amount) {
        super("Invalid deposit amount: " + amount + ". Amount must be greater than 0.");
    }
}
