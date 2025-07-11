package lk.jiat.ee.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.enums.AccountType;
import lk.jiat.ee.service.AccountService;

import java.io.IOException;

@WebServlet("/updateAccount")
public class updateAccount extends HttpServlet {
    @EJB
    private AccountService accountService;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1. Get parameters from form
            String accountIdStr = request.getParameter("account_id");
            String accountTypeStr = request.getParameter("account_type");
            String interestRateStr = request.getParameter("interestRate");

            // 2. Parse and fetch existing account
            Long accountId = Long.parseLong(accountIdStr);
            Account account = accountService.getAccountByID(accountId);

            // 3. Update account fields
            account.setAccountType(AccountType.valueOf(accountTypeStr)); // assuming enum
            account.setInterestRate(Double.parseDouble(interestRateStr));

            // 4. Save updated account
            accountService.updateAccount(account);

            // 5. Redirect to success or listing page
            response.sendRedirect(request.getContextPath() + "/admin/index.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp"); // or handle properly
        }
    }
}
