package org.example.coffeshop.dao;

import org.example.coffeshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Đổi 'name' thành thuộc tính tồn tại trong Order, ví dụ: 'productName'


    default List<Order> findAllByOrderByName() {
        return null;
    }
}
