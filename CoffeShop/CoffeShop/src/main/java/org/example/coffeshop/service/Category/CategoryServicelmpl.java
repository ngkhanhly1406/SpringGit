package org.example.coffeshop.service.Category;

import org.example.coffeshop.dao.CategoryRepository;
import org.example.coffeshop.entity.Category;
import org.example.coffeshop.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServicelmpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServicelmpl(CategoryRepository theCategoryRepository) {
        this.categoryRepository = theCategoryRepository;
    }

    @Override
    public void save(Category theCategory) {
        categoryRepository.save(theCategory);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAllByOrderByName();
    }

    @Override
    public Category findById(int theId) {
        Optional<Category> result = categoryRepository.findById(theId);
        Category theCategory = null;
        if (result.isPresent()) {
            theCategory = result.get();
        }
        else {
            throw new RuntimeException("Menu not find id" + theId);
        }
        return theCategory;
    }

    @Override
    public void deleteById(int theId) {
        categoryRepository.deleteById(theId);
    }
}
