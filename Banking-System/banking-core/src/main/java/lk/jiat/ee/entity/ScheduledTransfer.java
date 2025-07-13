package lk.jiat.ee.entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Schedule.FindAll" ,query = "select s from ScheduledTransfer s"),
        @NamedQuery(name = "Schedule.FindAllActive", query = "SELECT s FROM ScheduledTransfer s WHERE s.active = true")
})
public class ScheduledTransfer implements Serializable {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    private String targetAccount;
    private String fromAccount;
    private double amount;

    private boolean active;

}
