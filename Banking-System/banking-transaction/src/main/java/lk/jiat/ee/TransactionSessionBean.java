package lk.jiat.ee;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.entity.Customer;
import lk.jiat.ee.entity.Transaction;
import lk.jiat.ee.service.TransactionService;

import java.util.List;

@Stateless
public class TransactionSessionBean implements TransactionService {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void withdraw(String AccountNumber, double amount) {
        try {
            Account account = em.createNamedQuery("Account.FindByAccountNo", Account.class)
                    .setParameter("accountNumber", AccountNumber)
                    .getSingleResult();
            if (account.getBalance() > 0 && account.getBalance() > amount) {
                account.setBalance(account.getBalance() - amount);
                em.merge(account);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deposit(String AccountNumber, double amount) {

        try {
            Account account = em.createNamedQuery("Account.FindByAccountNo", Account.class)
                    .setParameter("accountNumber", AccountNumber)
                    .getSingleResult();
            if (amount > 0) {
                account.setBalance(account.getBalance() + amount);
            }
            em.merge(account);
        } catch (NoResultException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void transfer(String sourceAccountNumber, String destinationAccountNumber, double amount) {
        try {
            withdraw(sourceAccountNumber, amount);
            deposit(destinationAccountNumber, amount);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void saveTransaction(Transaction transaction) {
        em.persist(transaction);
    }

    @Override
    public List<Transaction> getAllTransactionByAccountID(Long id) {
        List<Transaction> transactions = em.createNamedQuery("Transaction.FindByID", Transaction.class)
                .setParameter("id", id)
                .getResultList();
        return transactions;
    }
}
