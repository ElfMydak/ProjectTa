package Controller;

import Interfaces.CustomerProcessorInterface;
import Interfaces.RoomProcessorInterface;
import Service.CustomerProcessor;
import Service.RoomProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/deleteCustomer"})
public class DeleteCustomer extends HttpServlet {

    CustomerProcessorInterface customer = new CustomerProcessor();
    RoomProcessorInterface room = new RoomProcessor();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int number = Integer.parseInt(req.getParameter("room"));
        customer.deleteCustomer(number);
        room.toFree(room.findId(number));
        resp.sendRedirect(req.getContextPath() + "/mainPage");
    }
}
