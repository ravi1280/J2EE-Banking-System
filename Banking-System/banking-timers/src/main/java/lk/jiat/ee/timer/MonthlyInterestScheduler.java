package lk.jiat.ee.timer;


import jakarta.ejb.*;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.service.AccountService;

import java.util.List;

    @Singleton
    public class MonthlyInterestScheduler {

        @EJB
        private AccountService accountService;

    //    @Schedule(dayOfMonth = "Last", hour = "23", minute = "59", persistent = false)
    //    @Schedule(minute = "*/2", hour = "*", persistent = false)
        @TransactionAttribute(TransactionAttributeType.REQUIRED)
        public void updateMonthlyInterest() {
            List<Account> accounts  = accountService.getAllAccounts();

            for (Account account : accounts) {
                double interest = account.getBalance() * (account.getInterestRate() / 100.0);
                account.setBalance(account.getBalance() + interest);
                accountService.updateAccount(account);

            }
            System.out.println(" Monthly Interest Update Completed.");
        }

    }
