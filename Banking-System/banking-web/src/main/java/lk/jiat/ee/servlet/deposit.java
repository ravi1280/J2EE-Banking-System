package lk.jiat.ee.servlet;


import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.entity.Transaction;
import lk.jiat.ee.enums.TransactionType;
import lk.jiat.ee.service.AccountService;
import lk.jiat.ee.service.TransactionService;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/deposit")
public class deposit extends HttpServlet {
    @EJB
    private TransactionService transactionService;

    @EJB
    private AccountService accountService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("deposit");
        String accountNumber = request.getParameter("accountNumber");
        String amount = request.getParameter("amount");
        String description = request.getParameter("description");
        System.out.println(accountNumber + " " + amount);

        Account account = accountService.getAccountByNumber(accountNumber);
        transactionService.deposit(accountNumber,Double.parseDouble(amount));

        Transaction transaction = new Transaction();
        transaction.setAmount(Double.parseDouble(amount));
        transaction.setDescription(description);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setAccount(account);

        transactionService.saveTransaction(transaction);

        response.sendRedirect(request.getContextPath() + "/customer/transactions.jsp");


    }
}
