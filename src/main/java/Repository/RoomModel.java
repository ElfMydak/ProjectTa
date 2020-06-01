package Repository;

import BTree.BPlusTree;
import Interfaces.ModelLayerRoom;
import Model.Room;

import java.sql.*;
import java.util.ArrayList;

public class RoomModel implements ModelLayerRoom {

    private  String url = "jdbc:mysql://localhost/hoteldb?serverTimezone=Europe/Moscow&useSSL=false";
    private  String username = "root";
    private String password = "root";

    @Override
    public BPlusTree<Room, Integer> selectAllNumbers() {

        BPlusTree<Room, Integer>  rooms = new BPlusTree<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM room");
                while(resultSet.next()){
                    int roomNumber = resultSet.getInt(2);
                    Room room = selectOne(resultSet.getInt(1));
                    rooms.insert(room, roomNumber);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return rooms;
    }

    @Override
    public ArrayList<Room> selectAll(){
        ArrayList<Room> rooms = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM room");
                while(resultSet.next()){
                    Room room = selectOne(resultSet.getInt(1));
                    rooms.add(room);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return rooms;
    }

    @Override
    public void toBook(int id ){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "UPDATE room SET room_status = ? WHERE room_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setBoolean(1, true);
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    @Override
    public void toFree(int id){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "UPDATE room SET room_status = ? WHERE room_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setBoolean(1, false);
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    @Override
    public Room selectOne(int id) {

        Room room = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "SELECT * FROM room WHERE room_id = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if(resultSet.next()){
                        int roomId = resultSet.getInt(1);
                        int roomNumber = resultSet.getInt(2);
                        boolean roomStatus = resultSet.getBoolean(3);
                        String roomType = resultSet.getString(4);
                        int roomPrice = resultSet.getInt(5);
                        String roomDesc = resultSet.getString(6);
                        room = new Room(roomId, roomNumber, roomStatus, roomType, roomPrice, roomDesc);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return room;
    }

    @Override
    public int insert(Room room) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "INSERT INTO room (room_number, room_status, room_type, room_price, room_desc)  Values (?, ?, ?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, room.getRoomNumber());
                    preparedStatement.setBoolean(2, room.getRoomStatus());
                    preparedStatement.setString(3, room.getRoomType());
                    preparedStatement.setInt(4, room.getRoomPrice());
                    preparedStatement.setString(5, room.getRoomDescription());
                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }

    @Override
    public int update(Room room) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "UPDATE room SET room_number = ?, room_status = ?, room_type = ?, room_price = ?, room_desc = ? WHERE room_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, room.getRoomNumber());
                    preparedStatement.setBoolean(2, room.getRoomStatus());
                    preparedStatement.setString(3, room.getRoomType());
                    preparedStatement.setInt(4, room.getRoomPrice());
                    preparedStatement.setString(5, room.getRoomDescription());
                    preparedStatement.setInt(6, room.getRoomId());

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "DELETE FROM room WHERE room_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
}
