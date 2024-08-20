package org.example.coffeshop.service.Product;

import org.example.coffeshop.entity.Customer;
import org.example.coffeshop.entity.Product;

import java.util.List;

public interface ProductService {

    public void save(Product theProduct);

    public List<Product> findAll();

    public Product findById(int theId);

    public void deleteById(int theId);
}
