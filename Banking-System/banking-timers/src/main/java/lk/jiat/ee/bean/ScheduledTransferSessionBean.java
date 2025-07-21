package lk.jiat.ee.bean;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.entity.ScheduledTransfer;
import lk.jiat.ee.service.ScheduledTransferServissces;

import java.util.List;

@Stateless
public class ScheduledTransferSessionBean implements ScheduledTransferServissces {
    @PersistenceContext
    private EntityManager em;


    @RolesAllowed({"CUSTOMER","ADMIN"})
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(ScheduledTransfer transfer) {
        em.persist(transfer);
    }

    @PermitAll
    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<ScheduledTransfer> getAllActiveTransfers() {
        List<ScheduledTransfer> scheduledTransfer = em.createNamedQuery("Schedule.FindAllActive", ScheduledTransfer.class)
                .getResultList();
        return scheduledTransfer;

    }

    @PermitAll
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateScheduledTransfer(ScheduledTransfer transfer) {
        em.merge(transfer);
    }
}
