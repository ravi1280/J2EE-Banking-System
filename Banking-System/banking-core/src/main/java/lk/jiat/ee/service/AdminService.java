package lk.jiat.ee.service;

import jakarta.ejb.Remote;
import lk.jiat.ee.entity.Admin;
import lk.jiat.ee.entity.Customer;

@Remote
public interface AdminService {

    Admin getAdminByID(String username, String password);

    Admin getAdminByEmail(String email);

    boolean exists(String username, String password);
}
