package lk.jiat.ee;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.service.AccountService;

@Stateless
public class AccountSessionBean implements AccountService {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveAccount(Account account) {
        em.persist(account);
    }
}
