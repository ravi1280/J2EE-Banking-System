package lk.jiat.ee.servlet;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.entity.Customer;
import lk.jiat.ee.enums.AccountType;
import lk.jiat.ee.service.AccountService;
import lk.jiat.ee.service.CustomerService;
import lk.jiat.ee.service.TransactionService;

import java.io.IOException;
import java.util.List;


@WebServlet("/test")
public class Test extends HttpServlet {

    @EJB
    private AccountService accountService;
    @EJB
    private TransactionService transactionService;

    @EJB
    private CustomerService customerService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet");


    }
}
