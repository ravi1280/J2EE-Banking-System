package lk.jiat.ee.interceptor;


import jakarta.ejb.EJB;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import lk.jiat.ee.annotation.ValidDeposit;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.exceptions.AccountNotFoundException;
import lk.jiat.ee.exceptions.InvalidDepositAmountException;
import lk.jiat.ee.service.AccountService;

@Interceptor
@ValidDeposit
public class DepositValidateInterceptors {
    @EJB
    private AccountService accountService;

    @AroundInvoke
    public Object validateBalance(InvocationContext context) throws Exception {
        System.out.println("WithdrawalValidatorInterceptor.validateBalance");
        Object[] params = context.getParameters();

        String accountId = (String) params[0];
        double amount = (double) params[1];

        Account account = accountService.getAccountByNumber(accountId);

        if (account == null) {
            throw new AccountNotFoundException(Long.parseLong(accountId));
        }

        if ( amount ==0) {
            throw new InvalidDepositAmountException(amount);
        }
        return context.proceed();

    }
}
