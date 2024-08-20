package org.example.coffeshop.service.Category;

import org.example.coffeshop.entity.Category;
import org.example.coffeshop.entity.Customer;

import java.util.List;

public interface CategoryService {

    public void save(Category theCategory);

    public List<Category> findAll();

    public Category findById(int theId);

    public void deleteById(int theId);
}
