package lk.jiat.ee.entity;

import jakarta.persistence.*;
import lk.jiat.ee.enums.TransactionType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private TransactionType type = TransactionType.WITHDRAWAL;

    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // Constructors
    public Transaction() {}

    public Transaction(double amount, TransactionType type, String description) {
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.description = description;
    }
}
