package lk.jiat.ee.bean;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.jiat.ee.entity.Admin;
import lk.jiat.ee.entity.Customer;
import lk.jiat.ee.service.AdminService;

@Stateless
public class AdminSessionBean implements AdminService {

    @PersistenceContext
    private EntityManager em;
    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Admin getAdminByID(String username, String password) {
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Admin getAdminByEmail(String email) {
        Admin admin =  em.createNamedQuery("Admin.FindByEmail",Admin.class)
                .setParameter("email", email)
                .getSingleResult();
        return admin;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public boolean exists(String email, String password) {
        Admin admin = null;
        try {
            admin = em.createNamedQuery("Admin.FindByEmailAndPassword", Admin.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
            return admin != null;
        } catch (Exception e) {
            System.out.println("Admin Not Found");
            return false;
        }

    }
}
