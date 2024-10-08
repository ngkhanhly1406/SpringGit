package org.example.coffeshop.dao;

import org.example.coffeshop.entity.Category;
import org.example.coffeshop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByOrderByName();
}
