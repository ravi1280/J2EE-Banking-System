package lk.jiat.ee.service;

import jakarta.ejb.Remote;
import lk.jiat.ee.entity.ScheduledTransfer;

import java.util.List;
@Remote
public interface ScheduledTransferServissces {
    void save(ScheduledTransfer transfer);
    List<ScheduledTransfer> getAllActiveTransfers();
    void updateScheduledTransfer(ScheduledTransfer transfer);
}
