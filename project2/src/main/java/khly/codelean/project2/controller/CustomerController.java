package khly.codelean.project2.controller;

import khly.codelean.project2.dao.CustomerRepository;
import khly.codelean.project2.entity.Category;
import khly.codelean.project2.entity.Customer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/list")
    public String listCustomer(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "admin/Customer/list";
    }

    @GetMapping("/add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "admin/Customer/form";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("customer", customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Customer Id:" + id)));
        // send over to our form
        return "admin/Customer/form";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerRepository.deleteById(id);
        return "redirect:/customer/list";

    }
}
