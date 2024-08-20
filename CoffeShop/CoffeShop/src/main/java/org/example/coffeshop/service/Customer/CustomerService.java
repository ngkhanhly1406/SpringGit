package org.example.coffeshop.service.Customer;

import org.example.coffeshop.entity.Customer;

import java.util.List;

public interface CustomerService {

    public void save(Customer theCustomer);

    public List<Customer> findAll();

    public Customer findById(int theId);

    public void deleteById(int theId);
}
