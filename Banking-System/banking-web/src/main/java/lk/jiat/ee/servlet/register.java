package lk.jiat.ee.servlet;

import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.entity.Customer;
import lk.jiat.ee.service.CustomerService;


import java.io.IOException;

@WebServlet("/register")
public class register extends HttpServlet {

    @EJB
    private CustomerService customerService;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String email = request.getParameter("email");
       String password = request.getParameter("password");
       String fullName = request.getParameter("fullName");
       String address = request.getParameter("address");
       String phone = request.getParameter("phone");

        System.out.println(email + " " + password+ " " + fullName + " " + address+ " " + phone);
        Customer customer = new Customer(fullName, email,password, phone, address);
        customerService.createCustomer(customer);

//       customerService.getCustomerByEmail(email);


//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String phone = request.getParameter("phone");
//        String address = request.getParameter("address");


    }
}
