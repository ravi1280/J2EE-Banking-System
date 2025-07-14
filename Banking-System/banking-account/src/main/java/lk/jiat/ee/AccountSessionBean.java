package lk.jiat.ee;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.entity.Customer;
import lk.jiat.ee.exceptions.AccountNotFoundException;
import lk.jiat.ee.service.AccountService;

import java.util.List;

@Stateless
public class AccountSessionBean implements AccountService {
    @PersistenceContext
    private EntityManager em;

    @RolesAllowed({"ADMIN"})
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveAccount(Account account) {
        em.persist(account);
    }


    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Account> getAllAccounts() {
        List<Account> accounts = em.createNamedQuery("Account.FindAll", Account.class)
                .getResultList();
        return accounts;

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Account getAccountByID(Long id) throws AccountNotFoundException {
        try {
            return em.createNamedQuery("Account.FindByID", Account.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new AccountNotFoundException(id);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Account getAccountByNumber(String number) throws AccountNotFoundException {
        try {
            return em.createNamedQuery("Account.FindByAccountNo", Account.class)
                    .setParameter("accountNumber", number)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new AccountNotFoundException(number);

        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateAccount(Account account) {
        em.merge(account);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public boolean isExists(String accountId) {
        try {
            Account account = em.createNamedQuery("Account.FindByAccountNo", Account.class)
                    .setParameter("accountNumber", accountId)
                    .getSingleResult();

            return account != null;
        } catch (Exception e) {
            System.out.println("Account not found");
            return false;
        }
    }


}
