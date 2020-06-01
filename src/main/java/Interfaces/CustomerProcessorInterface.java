package Interfaces;

import Model.Customer;

import java.util.ArrayList;
import java.util.Date;

public interface CustomerProcessorInterface {

    void addCustomer(String name, int room, Date depDate);

    ArrayList<Customer> getSortedArray();

    int getId(int room);

    void deleteCustomer(int room);
}
