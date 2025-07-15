package lk.jiat.ee.timer;

import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.entity.Customer;
import lk.jiat.ee.entity.Transaction;
import lk.jiat.ee.service.AccountService;
import lk.jiat.ee.service.CustomerService;
import lk.jiat.ee.service.TransactionPdfGenerator;
import lk.jiat.ee.service.TransactionService;

import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class MonthlyTransactionSave {
    @PersistenceContext
    private EntityManager em;

    @EJB
    private CustomerService customerService;
    @EJB
    private TransactionService transactionService;
    @EJB
    private AccountService accountService;

    @Schedule(dayOfMonth = "Last", hour = "23", minute = "59", persistent = false)
//        @Schedule(minute = "*/2", hour = "*", persistent = false)
    public void generateMonthlyReports() {
            System.out.println("Monthly reports generated");

        List<Customer> customers = customerService.getAllCustomers();
            List<Account>  accounts = accountService.getAllAccounts();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime startOfLastMonth = startOfMonth.minusMonths(1);
        LocalDateTime endOfLastMonth = startOfMonth.minusSeconds(1);

        for (Account account : accounts) {
            account.getCustomer().getFullName();
                    account.getId();

            List<Transaction> transactions = em.createQuery(
                            "SELECT t FROM Transaction t WHERE t.account.id = :accountId AND t.timestamp BETWEEN :start AND :end",
                            Transaction.class)
                    .setParameter("accountId",  account.getId())
                    .setParameter("start", startOfLastMonth)
                    .setParameter("end", endOfLastMonth)
                    .getResultList();
            try {

                new TransactionPdfGenerator().generateCustomerTransactionsPdf(account.getCustomer().getFullName(),account.getAccountNumber(), transactions);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
