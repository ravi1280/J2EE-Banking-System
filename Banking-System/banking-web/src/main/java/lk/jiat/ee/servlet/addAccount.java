package lk.jiat.ee.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.entity.Customer;
import lk.jiat.ee.service.AccountService;
import lk.jiat.ee.service.CustomerService;

import java.io.IOException;

@WebServlet("/addAccount")
public class addAccount extends HttpServlet {

    @EJB
    private CustomerService customerService;

    @EJB
    private AccountService accountService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        String accountNumber = request.getParameter("account_number");
        String accountType = request.getParameter("account_type");
        String balance = request.getParameter("balance");
        String interestRate = request.getParameter("interestRate");
        String cid = request.getParameter("cid");

        Customer customer = customerService.getCustomerByID(Long.parseLong(cid));

        Account account = new Account(accountNumber,accountType ,Double.parseDouble(balance),Double.parseDouble(interestRate),customer);

        accountService.saveAccount(account);

        response.sendRedirect(request.getContextPath() + "/admin/index.jsp");

        } catch (Exception e) {
            System.out.println(e.getMessage());
//            response.sendRedirect(request.getContextPath() + "/admin/addAccount.jsp?error=true");
        }

    }
}
