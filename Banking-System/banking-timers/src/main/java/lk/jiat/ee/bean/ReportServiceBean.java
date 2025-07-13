package lk.jiat.ee.bean;

import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;

@Singleton
public class ReportServiceBean {

    private int totalTransactions;
    private double totalTransactionAmount;
    private long depositCount;
    private long withdrawalCount;
    private long uniqueCustomers;
    private int totalAccounts;
    private double highestTransaction;
    private String period;

    @Lock(LockType.WRITE)
    public void updateReport(String period, int total, double amount, long deposits, long withdrawals,
                             long customers, long accounts, double maxTx) {
        this.period = period;
        this.totalTransactions = total;
        this.totalTransactionAmount = amount;
        this.depositCount = deposits;
        this.withdrawalCount = withdrawals;
        this.uniqueCustomers = customers;
        this.totalAccounts = Integer.parseInt(String.valueOf(accounts));
        this.highestTransaction = maxTx;
    }

    @Lock(LockType.READ)
    public int getTotalTransactions() { return totalTransactions; }

    @Lock(LockType.READ)
    public double getTotalTransactionAmount() { return totalTransactionAmount; }

    @Lock(LockType.READ)
    public long getDepositCount() { return depositCount; }

    @Lock(LockType.READ)
    public long getWithdrawalCount() { return withdrawalCount; }

    @Lock(LockType.READ)
    public long getUniqueCustomers() { return uniqueCustomers; }

    @Lock(LockType.READ)
    public int getTotalAccounts() { return totalAccounts; }

    @Lock(LockType.READ)
    public double getHighestTransaction() { return highestTransaction; }

    @Lock(LockType.READ)
    public String getPeriod() { return period; }
}
