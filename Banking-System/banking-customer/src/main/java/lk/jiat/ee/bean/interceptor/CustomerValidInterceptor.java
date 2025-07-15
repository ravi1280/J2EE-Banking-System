package lk.jiat.ee.bean.interceptor;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import lk.jiat.ee.bean.annotation.CustomerValid;
import lk.jiat.ee.entity.Customer;

@CustomerValid
@Interceptor
public class CustomerValidInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        System.out.println("CustomerValidInterceptor.intercept start");

        Object[] params = context.getParameters();
        for (Object param : params) {
            if (param instanceof Customer) {
                Customer customer = (Customer) param;
                if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
                    throw new IllegalArgumentException("Email is required");
                }
                // Other validations here
                System.out.println("✅ Interceptor: Customer is valid");
            }
        }
        return context.proceed();
    }


}

