package lk.jiat.ee.service;


import jakarta.ejb.Remote;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.exceptions.AccountNotFoundException;

import java.util.List;

@Remote
public interface AccountService {

    void saveAccount(Account account);
    List<Account> getAllAccounts();
    Account getAccountByID(Long id) throws AccountNotFoundException;
    Account getAccountByCustomerID(Long id) throws AccountNotFoundException;
    Account getAccountByNumber(String number) throws AccountNotFoundException;
    void updateAccount(Account account);
    boolean isExists(String accountId);




}
