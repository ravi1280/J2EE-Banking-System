package lk.jiat.ee.bean;

import jakarta.ejb.Stateless;
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

    @Override
    public void save(ScheduledTransfer transfer) {
        em.persist(transfer);
    }

    @Override
    public List<ScheduledTransfer> getAllActiveTransfers() {
        List<ScheduledTransfer> scheduledTransfer = em.createNamedQuery("Schedule.FindAllActive", ScheduledTransfer.class)
                .getResultList();
        return scheduledTransfer;

    }

    @Override
    public void updateScheduledTransfer(ScheduledTransfer transfer) {
        em.merge(transfer);
    }
}
