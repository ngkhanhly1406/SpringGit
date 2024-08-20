package org.example.coffeshop.service.Order;

import org.example.coffeshop.entity.Customer;
import org.example.coffeshop.entity.Order;

import java.util.List;

public interface OrderService {

    public void save(Order theOrder);

    public List<Order> findAll();

    public Order findById(int theId);

    public void deleteById(int theId);
}
