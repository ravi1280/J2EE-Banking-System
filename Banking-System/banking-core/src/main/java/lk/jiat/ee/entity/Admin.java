package lk.jiat.ee.entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Admin.FindByEmail", query = "select a from Admin a where a.email=:email"),
        @NamedQuery(name = "Admin.FindByEmailAndPassword", query = "select a from Admin a where a.email=:email and a.password=:password"),
})
@Cacheable(value = false)
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Name;
    @Column(unique=true)
    private String email;
    private String password;

    public Admin() {
    }

    public Admin(String name, String email, String password) {
        Name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
