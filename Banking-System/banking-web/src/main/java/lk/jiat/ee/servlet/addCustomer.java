package lk.jiat.ee.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.entity.Customer;
import lk.jiat.ee.exceptions.DuplicateCustomerException;
import lk.jiat.ee.service.CustomerService;
import lk.jiat.ee.utils.Encyptions;


import java.io.IOException;
import java.util.UUID;

@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN"}))
@WebServlet("/addCustomer")
public class addCustomer extends HttpServlet {

    @EJB
    private CustomerService customerService;



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        String encryptPassword = Encyptions.encrypt(password);
        String verificationCode = UUID.randomUUID().toString();
        Customer customer = new Customer(fullName, email, encryptPassword, phone, address);
        customer.setVerificationCode(verificationCode);


        try {
            customerService.createCustomer(customer);
        } catch (DuplicateCustomerException e) {
            throw new RuntimeException(e);
        }
        //roll back krnn customer add une nethnm

        response.sendRedirect(request.getContextPath()+"/admin/addCustomers.jsp");


//        VerificationMail verificationMail = new VerificationMail(email, verificationCode);
//        MailServiceProvider.getInstance().sendMail(verificationMail);
//
//        response.sendRedirect(request.getContextPath()+"/login.jsp");



    }
}
