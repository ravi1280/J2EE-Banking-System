package lk.jiat.ee.service;

import jakarta.ejb.Remote;
import lk.jiat.ee.entity.Transaction;

import java.util.List;

@Remote
public interface TransactionService {
    void withdraw(String AccountNumber, double amount);
    void deposit(String AccountNumber, double amount);
    void transfer(String sourceAccountNumber, String destinationAccountNumber, double amount);
    void saveTransaction(Transaction transaction);

   List<Transaction> getAllTransactionByAccountID(Long id);

}
