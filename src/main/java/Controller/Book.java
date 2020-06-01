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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = {"/book"})
public class Book extends HttpServlet {

    RoomProcessorInterface room = new RoomProcessor();
    CustomerProcessorInterface customer = new CustomerProcessor();
    int id;
    int number;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        number = Integer.parseInt(req.getParameter("number"));
        id = room.findId(number);

        getServletContext().getRequestDispatcher("/book.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String longDate = req.getParameter("date");
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(longDate);
            customer.addCustomer(name + " " + surname, number, date);
            room.toBook(id);
            resp.sendRedirect(req.getContextPath() + "/mainPage");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
