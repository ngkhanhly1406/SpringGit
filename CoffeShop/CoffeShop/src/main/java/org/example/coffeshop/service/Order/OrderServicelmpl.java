package org.example.coffeshop.service.Order;

import org.example.coffeshop.dao.OrderRepository;
import org.example.coffeshop.entity.Customer;
import org.example.coffeshop.entity.Order;
import org.example.coffeshop.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServicelmpl implements OrderService{

    private final OrderRepository orderRepository;
    @Autowired
    public OrderServicelmpl(OrderRepository theOrderRepository) {
        this.orderRepository = theOrderRepository;
    }

    @Override
    public void save(Order theOrder) {
        orderRepository.save(theOrder);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAllByOrderByName();
    }

    @Override
    public Order findById(int theId) {
        Optional<Order> result = orderRepository.findById(theId);
        Order theOrder = null;
        if (result.isPresent()) {
            theOrder = result.get();

        }
        else {
            throw new RuntimeException("Menu not find id" + theId);
        }
        return theOrder;
    }

    @Override
    public void deleteById(int theId) {
        orderRepository.deleteById(theId);
    }
}
