package Controller;


import Interfaces.CustomerProcessorInterface;
import Model.Customer;
import Repository.CustomerModel;
import Service.CustomerProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/mainPage"})
public class Startup extends HttpServlet {

    CustomerProcessorInterface customer = new CustomerProcessor();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Customer> customerList = customer.getSortedArray();
        req.setAttribute("customers", customerList);
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
