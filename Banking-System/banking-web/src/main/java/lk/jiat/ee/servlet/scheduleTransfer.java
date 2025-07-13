package lk.jiat.ee.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.entity.Customer;
import lk.jiat.ee.entity.ScheduledTransfer;
import lk.jiat.ee.service.CustomerService;
import lk.jiat.ee.service.ScheduledTransferServissces;

import java.io.IOException;


@WebServlet("/scheduleTransfer")
public class scheduleTransfer extends HttpServlet {
    @EJB
    private CustomerService customerService;

    @EJB
    private ScheduledTransferServissces scheduledTransferService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getUserPrincipal().getName();
        Customer customer = customerService.getCustomerByEmail(email);
        System.out.println();

        String targetAccount = request.getParameter("targetAccount");
        String fromAccount = request.getParameter("fromAccount");
        double amount = Double.parseDouble(request.getParameter("amount"));
        boolean active = request.getParameter("active") != null;

        ScheduledTransfer transfer = new ScheduledTransfer();
        transfer.setCustomer(customer);
        transfer.setFromAccount(fromAccount);
        transfer.setTargetAccount(targetAccount);
        transfer.setAmount(amount);
        transfer.setActive(active);

        scheduledTransferService.save(transfer);

//        response.sendRedirect("customer/dashboard.jsp");
    }
}
