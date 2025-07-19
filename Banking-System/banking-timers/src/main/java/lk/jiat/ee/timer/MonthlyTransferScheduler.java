package lk.jiat.ee.timer;

import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.entity.ScheduledTransfer;
import lk.jiat.ee.entity.Transaction;
import lk.jiat.ee.enums.TransactionType;
import lk.jiat.ee.service.AccountService;
import lk.jiat.ee.service.ScheduledTransferServissces;
import lk.jiat.ee.service.TransactionService;

import java.time.LocalDateTime;
import java.util.List;

@Singleton
public class MonthlyTransferScheduler {

    @EJB
    private ScheduledTransferServissces scheduledTransferServices;

    @EJB
    private TransactionService transactionService;
    @EJB
    private AccountService accountService;

    @Schedule(dayOfMonth = "Last", hour = "23", minute = "59", persistent = true)
    public void executeMonthlyTransfers() {

        List<ScheduledTransfer> transfers = scheduledTransferServices.getAllActiveTransfers();
        for (ScheduledTransfer st : transfers) {
            try {
                System.out.println(st.getCustomer().getFullName());

                Account account = accountService.getAccountByNumber(st.getFromAccount());

                transactionService.transfer(st.getFromAccount(),st.getTargetAccount(),st.getAmount());

                Transaction transaction = new Transaction();
                transaction.setAmount(st.getAmount());
                transaction.setDescription("Scheduled Monthly Transfer");
                transaction.setTimestamp(LocalDateTime.now());
                transaction.setType(TransactionType.TRANSFER);
                transaction.setAccount(account);

                transactionService.saveTransaction(transaction);

                st.setActive(false);
                scheduledTransferServices.updateScheduledTransfer(st);

            } catch (Exception e) {
                throw new RuntimeException("Transfer failed: " + e.getMessage());
            }
        }
    }



}
