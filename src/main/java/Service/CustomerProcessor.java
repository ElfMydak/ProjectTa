package Service;

import Interfaces.CustomerProcessorInterface;
import Interfaces.ModelLayerCustomer;
import Model.Customer;
import Repository.CustomerModel;
import Sort.MergeSort;

import java.util.ArrayList;
import java.util.Date;

public class CustomerProcessor implements CustomerProcessorInterface {

    ModelLayerCustomer model = new CustomerModel();

    @Override
    public void addCustomer(String name, int room, Date depDate){
        Customer customer = new Customer(name, room, new Date(), depDate);
        model.insert(customer);
    }

    @Override
    public ArrayList<Customer> getSortedArray(){
        MergeSort sort = new MergeSort(model.selectAllList());
        sort.sortGivenArray();
        return sort.getSortedArray();
    }

    @Override
    public int getId(int room){
        return model.selectAll().get(room).getCustomerId();
    }

    @Override
    public void deleteCustomer(int room){
        model.delete(getId(room));
    }
}
