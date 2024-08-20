package org.example.coffeshop.controller;

import org.example.coffeshop.entity.Customer;
import org.example.coffeshop.entity.Product;
import org.example.coffeshop.service.Product.ProductService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public String listProducts(Model theModel) {
        List<Product> theProduct = productService.findAll();
        theModel.addAttribute("product", theProduct);
        return "products/list-products";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Product theProduct = new Product();
        theModel.addAttribute("product", theProduct);
        return "products/form-products";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("productId") int theId, Model theModel) {
        Product theProduct = productService.findById(theId);
        theModel.addAttribute("product", theProduct);
        return "products/form-products";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("customer") Product theProduct) {
        productService.save(theProduct);
        return "redirect:/product/list";
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("productId") int theId) {
        productService.deleteById(theId);
        return "redirect:/product/list";
    }
}
