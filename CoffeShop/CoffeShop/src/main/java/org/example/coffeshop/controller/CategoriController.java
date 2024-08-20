package org.example.coffeshop.controller;

import org.example.coffeshop.entity.Category;
import org.example.coffeshop.entity.Customer;
import org.example.coffeshop.service.Category.CategoryService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@Controller
@RequestMapping("/category")
public class CategoriController {

    private final CategoryService categoryService;

    public CategoriController(CategoryService theCategoryService) {
        this.categoryService = theCategoryService;
    }

    @GetMapping("/list")
    public String listCategory(Model theModel) {
        List<Category> theCategory = categoryService.findAll();
        theModel.addAttribute("category", theCategory);
        return "categorys/list-categorys";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Category theCategory = new Category();
        theModel.addAttribute("category", theCategory);
        return "categorys/form-categorys";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("categoryId") int theId, Model theModel) {
        Category theCategory = categoryService.findById(theId);
        theModel.addAttribute("category", theCategory);
        return "categorys/form-categorys";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("customer") Category theCategory) {
        categoryService.save(theCategory);
        return "redirect:/category/list";
    }

    @PostMapping("/delete")
    public String deleteCategory(@RequestParam("categoryId") int theId) {
        categoryService.deleteById(theId);
        return "redirect:/category/list";
    }
}
