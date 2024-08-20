package org.example.coffeshop.service.Product;

import org.example.coffeshop.dao.ProductRepository;
import org.example.coffeshop.entity.Customer;
import org.example.coffeshop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServicelmpl implements ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductServicelmpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(Product theProduct) {
        productRepository.save(theProduct);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAllByOrderByName();
    }

    @Override
    public Product findById(int theId) {
        Optional<Product> result = productRepository.findById(theId);
        Product theProduct = null;
        if (result.isPresent()) {
            theProduct = result.get();
        }
        else {
            throw new RuntimeException("Menu not find id" + theId);
        }
        return theProduct;
    }

    @Override
    public void deleteById(int theId) {
        productRepository.deleteById(theId);
    }
}
