package lk.jiat.ee;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lk.jiat.ee.annotation.ValidDeposit;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.entity.Transaction;
import lk.jiat.ee.annotation.ValidateWithdrawal;
import lk.jiat.ee.exceptions.AccountNotFoundException;
import lk.jiat.ee.exceptions.InsufficientBalanceException;
import lk.jiat.ee.exceptions.InvalidDepositAmountException;
import lk.jiat.ee.service.AccountService;
import lk.jiat.ee.service.TransactionService;

import java.util.List;

@Stateless
public class TransactionSessionBean implements TransactionService {
    @PersistenceContext
    private EntityManager em;

    @EJB
    private AccountService accountService;

//    @ValidateWithdrawal
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void withdraw(String AccountNumber, double amount) {

        try {
           Account account =  accountService.getAccountByNumber(AccountNumber);

                account.setBalance(account.getBalance() - amount);
                accountService.updateAccount(account);

        }  catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());

        }

    }

//    @ValidDeposit
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deposit(String AccountNumber, double amount) {

        try {
            Account account =  accountService.getAccountByNumber(AccountNumber);

            if (amount > 0) {
                account.setBalance(account.getBalance() + amount);
                accountService.updateAccount(account);
            }

        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void transfer(String sourceAccountNumber, String destinationAccountNumber, double amount) {
        try {
            withdraw(sourceAccountNumber, amount);
            deposit(destinationAccountNumber, amount);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveTransaction(Transaction transaction) {

        em.persist(transaction);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Transaction> getAllTransactionByAccountID(Long id) {
        List<Transaction> transactions = em.createNamedQuery("Transaction.FindByID", Transaction.class)
                .setParameter("id", id)
                .getResultList();
        return transactions;
    }
}
