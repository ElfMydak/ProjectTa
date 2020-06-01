package Repository;

import HashTable.Table;
import Interfaces.ModelLayerCustomer;
import Model.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerModel implements ModelLayerCustomer {

    private  String url = "jdbc:mysql://localhost/hoteldb?serverTimezone=Europe/Moscow&useSSL=false";
    private  String username = "root";
    private String password = "root";

    @Override
    public Table<Integer, Customer> selectAll() {

        Table<Integer, Customer> customers = new Table<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
                while(resultSet.next()){
                    int customerId = resultSet.getInt(1);
                    String customerName = resultSet.getString(2);
                    int customerRoom = resultSet.getInt(3);
                    Date customerArrDate = resultSet.getDate(4);
                    Date customerDepDate = resultSet.getDate(5);
                    Customer customer = new Customer(customerId, customerName, customerRoom, customerArrDate, customerDepDate);
                    customers.add(customerRoom, customer);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return customers;
    }
    @Override
    public ArrayList<Customer> selectAllList() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
                while (resultSet.next()) {
                    customers.add(selectOne(resultSet.getInt(1)));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return customers;
    }

    @Override
    public Customer selectOne(int id) {

        Customer customer = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "SELECT * FROM customer WHERE customer_id = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if(resultSet.next()){
                        int customerId = resultSet.getInt(1);
                        String customerName = resultSet.getString(2);
                        int customerRoom = resultSet.getInt(3);
                        Date customerArrDate = resultSet.getDate(4);
                        Date customerDepDate = resultSet.getDate(5);
                        customer = new Customer(customerId, customerName, customerRoom, customerArrDate, customerDepDate);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return customer;
    }

    @Override
    public int insert(Customer customer) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "INSERT INTO customer (customer_name, customer_room, customer_arr_date, customer_dep_date)  Values (?, ?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, customer.getCustomerName());
                    preparedStatement.setInt(2, customer.getCustomerRoom());
                    preparedStatement.setDate(3, new Date(customer.getCustomerArrDate().getTime()));
                    preparedStatement.setDate(4, new Date(customer.getCustomerDepDate().getTime()));
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
    public int update(Customer customer) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "UPDATE customer SET customer_name = ?, customer_room = ?, customer_arr_date = ?, customer_dep_date = ? WHERE customer_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, customer.getCustomerName());
                    preparedStatement.setInt(2, customer.getCustomerRoom());
                    preparedStatement.setDate(3, new Date(customer.getCustomerArrDate().getTime()));
                    preparedStatement.setDate(4, new Date(customer.getCustomerDepDate().getTime()));
                    preparedStatement.setInt(5, customer.getCustomerId());

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

                String sql = "DELETE FROM customer WHERE customer_id = ?";
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
