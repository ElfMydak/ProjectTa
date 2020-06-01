package Controller;

import Interfaces.ModelLayerRoom;
import Model.Room;
import Repository.RoomModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/rooms"})
public class Rooms extends HttpServlet {

    ModelLayerRoom modelRoom = new RoomModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Room> rooms = modelRoom.selectAll();

        req.setAttribute("rooms", rooms);
        getServletContext().getRequestDispatcher("/rooms.jsp").forward(req, resp);
    }
}
