package lk.jiat.ee.entity;

import jakarta.persistence.*;
import lk.jiat.ee.enums.AccountType;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "accounts")
public class Account implements Serializable {
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

    public Account(String accountNumber, AccountType accountType, double balance, double interestRate) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.interestRate = interestRate;
    }
}
