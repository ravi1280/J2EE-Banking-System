package lk.jiat.ee.service;


import jakarta.ejb.Remote;
import lk.jiat.ee.entity.Account;

@Remote
public interface AccountService {

    void saveAccount(Account account);

}
