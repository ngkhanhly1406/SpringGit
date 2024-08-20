package org.example.coffeshop.controller;


import org.example.coffeshop.entity.Customer;
import org.example.coffeshop.service.Customer.CustomerService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService theCustomerService) {
        customerService = theCustomerService;
    }

    @GetMapping("/list")
    public String listCustomers(Model theModel) {
        List<Customer> theCustomer = customerService.findAll();
        theModel.addAttribute("customer", theCustomer);
        return "customers/list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Customer theCustomer = new Customer();
        theModel.addAttribute("customer", theCustomer);
        return "customers/form-customers";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
        Customer theCustomer = customerService.findById(theId);
        theModel.addAttribute("customer", theCustomer);
        return "customers/form-customers";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
        customerService.save(theCustomer);
        return "redirect:/customer/list";
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {
        customerService.deleteById(theId);
        return "redirect:/customer/list";
    }
}
