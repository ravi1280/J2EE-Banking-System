package lk.jiat.ee;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.entity.Customer;
import lk.jiat.ee.service.AccountService;

import java.util.List;

@Stateless
public class AccountSessionBean implements AccountService {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveAccount(Account account) {
        em.persist(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = em.createNamedQuery("Account.FindAll", Account.class)
                .getResultList();
        return accounts;

    }

    @Override
    public Account getAccountByID(Long id) {
        Account  account =  em.createNamedQuery("Account.FindByID",Account.class)
                .setParameter("id", id)
                .getSingleResult();
        return account;
    }

    @Override
    public Account getAccountByNumber(String number) {
        Account  account =  em.createNamedQuery("Account.FindByAccountNo",Account.class)
                .setParameter("accountNumber", number)
                .getSingleResult();
        return account;
    }

    @Override
    public void updateAccount(Account account) {
        em.merge(account);
    }
}
