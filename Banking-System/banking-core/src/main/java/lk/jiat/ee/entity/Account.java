package lk.jiat.ee.entity;

import jakarta.persistence.*;
import lk.jiat.ee.enums.AccountType;

import java.io.Serializable;
import java.util.List;


@Entity
@NamedQueries({
        @NamedQuery(name = "Account.FindByID", query = "select a from Account a where a.id=:id"),
        @NamedQuery(name = "Account.FindByCustomerID", query = "select a from Account a where a.customer.id=:id"),
        @NamedQuery(name = "Account.FindAll" ,query = "select a from Account a"),
        @NamedQuery(name = "Account.FindByAccountNo", query = "SELECT a FROM Account a WHERE a.accountNumber=:accountNumber")
})
@Table(name = "accounts")
public class Account implements Serializable {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType = AccountType.SAVINGS;

    private double balance;

    private double interestRate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    // Constructors
    public Account() {}

    public Account(String accountNumber, String accountType, double balance, double interestRate,Customer customer) {
        this.accountNumber = accountNumber;
        this.accountType = AccountType.valueOf(accountType);
        this.balance = balance;
        this.interestRate = interestRate;
        this.customer = customer;
    }
}
