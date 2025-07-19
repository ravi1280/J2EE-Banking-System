package lk.jiat.ee.timer;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.jiat.ee.bean.ReportServiceBean;
import lk.jiat.ee.entity.Transaction;
import lk.jiat.ee.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.List;

@Startup
@Singleton
public class MonthlyReportScheduler {
    @PersistenceContext
    private EntityManager em;

    @EJB
    private ReportServiceBean reportService;

    @PostConstruct
    public void runAtStartup() {
        System.out.println("🚀 [Startup] Generating initial report at: " + java.time.LocalDateTime.now());
        generateMonthlyReport();
    }

    @Schedule(dayOfMonth = "Last", hour = "23", minute = "59", persistent = true)
    public void generateMonthlyReport() {
        try {

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime startOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime startOfLastMonth = startOfMonth.minusMonths(1);
            LocalDateTime endOfLastMonth = startOfMonth.minusSeconds(1);

            List<Transaction> transactions = em.createQuery(
                            "SELECT t FROM Transaction t WHERE t.timestamp BETWEEN :start AND :end", Transaction.class)
                    .setParameter("start", startOfLastMonth)
                    .setParameter("end", endOfLastMonth)
                    .getResultList();


            double totalAmount = transactions.stream().mapToDouble(Transaction::getAmount).sum();
            int totalCount = transactions.size();


            long uniqueCustomers = transactions.stream()
                    .map(t -> t.getAccount().getCustomer().getId())
                    .distinct()
                    .count();

            long depositCount = transactions.stream()
                    .filter(t -> t.getType() == TransactionType.DEPOSIT)
                    .count();

            long withdrawalCount = transactions.stream()
                    .filter(t -> t.getType() == TransactionType.WITHDRAWAL)
                    .count();


            double maxTransaction = transactions.stream()
                    .mapToDouble(Transaction::getAmount)
                    .max()
                    .orElse(0.0);

            long accountCount = em.createQuery("SELECT COUNT(a) FROM Account a", Long.class).getSingleResult();
            String period ="Period: " + startOfLastMonth.toLocalDate() + " to " + endOfLastMonth.toLocalDate();

            reportService.updateReport(period,totalCount, totalAmount, depositCount, withdrawalCount,
                    uniqueCustomers, accountCount, maxTransaction);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
