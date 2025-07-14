package lk.jiat.ee.interceptor;


import jakarta.ejb.EJB;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import lk.jiat.ee.annotation.ValidateWithdrawal;
import lk.jiat.ee.entity.Account;
import lk.jiat.ee.exceptions.InsufficientBalanceException;
import lk.jiat.ee.service.AccountService;

@ValidateWithdrawal
@Interceptor
public class WithdrawalValidatorInterceptor {
    @EJB
    private AccountService accountService;

    @AroundInvoke
    public Object validateBalance(InvocationContext context) throws Exception {
        Object[] params = context.getParameters();

        String accountId = (String) params[0];
        double amount = (double) params[1];

        boolean ExistAccount = accountService.isExists(accountId);
        Account account = accountService.getAccountByNumber(accountId);

        if (!ExistAccount || amount > account.getBalance()) {
            System.out.println("Insufficient balance for withdrawal or Account not exist");

            throw new InsufficientBalanceException(account.getBalance(),amount);
        }

        return context.proceed();

    }
}
