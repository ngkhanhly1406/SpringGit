package org.example.coffeshop.service.OrderDetail;

import org.example.coffeshop.entity.Customer;
import org.example.coffeshop.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    public void save(OrderDetail theOrderDetail);

    public List<OrderDetail> findAll();

    public OrderDetail findById(int theId);

    public void deleteById(int theId);
}
