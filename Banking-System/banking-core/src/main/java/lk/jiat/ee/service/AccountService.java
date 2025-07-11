package lk.jiat.ee.service;


import jakarta.ejb.Remote;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.entity.Customer;

import java.util.List;

@Remote
public interface AccountService {

    void saveAccount(Account account);
    List<Account> getAllAccounts();
    Account getAccountByID(Long id);
    Account getAccountByNumber(String number);
    void updateAccount(Account account);


}
