package org.example.coffeshop.service.OrderDetail;

import org.example.coffeshop.dao.OrderDetailRepository;
import org.example.coffeshop.entity.Customer;
import org.example.coffeshop.entity.OrderDetail;
import org.example.coffeshop.service.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServicelmpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailServicelmpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }


    @Override
    public void save(OrderDetail theOrderDetail) {
        orderDetailRepository.save(theOrderDetail);
    }

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAllByOrderByName();
    }

    @Override
    public OrderDetail findById(int theId) {
        Optional<OrderDetail> result = orderDetailRepository.findById(theId);
        OrderDetail theOrderDetail = null;
        if (result.isPresent()) {
            theOrderDetail = result.get();

        }
        else {
            throw new RuntimeException("Menu not find id" + theId);
        }
        return theOrderDetail;
    }

    @Override
    public void deleteById(int id) {
        orderDetailRepository.deleteById(id);
    }
}
