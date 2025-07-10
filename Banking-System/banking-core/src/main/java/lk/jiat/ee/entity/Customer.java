package lk.jiat.ee.entity;

import jakarta.persistence.*;
import lk.jiat.ee.enums.Status;
import lk.jiat.ee.enums.UserType;

import java.io.Serializable;
import java.util.List;


@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.FindByEmail", query = "select c from Customer c where c.email=:email"),
        @NamedQuery(name = "Customer.FindByID", query = "select c from Customer c where c.id=:id"),
        @NamedQuery(name = "Customer.FindAll" ,query = "select c from Customer c"),
        @NamedQuery(name = "Customer.FindByEmailAndPassword", query = "select c from Customer c where c.email=:email and c.password=:password"),
})
@Cacheable(value = false)
public class Customer implements Serializable {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    private String phone;

    private String address;

    private String verificationCode;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts;

    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.CUSTOMER;

    @Enumerated(EnumType.STRING)
    private Status status = Status.INACTIVE;

    // Constructors
    public Customer() {}

    public Customer(String fullName, String email,String password, String phone, String address) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }
}
