package khly.codelean.project2.controller;

import khly.codelean.project2.dao.CustomerRepository;
import khly.codelean.project2.dao.OrderRepository;
import khly.codelean.project2.entity.Order;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderController(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/list")
    public String listOrder(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "admin/Order/list";
    }

    @GetMapping("/add")
    public String addOrder(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("customers", customerRepository.findAll()); // Fetch customers for selection
        return "admin/Order/form";
    }

    @GetMapping("/edit/{id}")
    public String editOrder(@PathVariable("id") Long id, Model model) {
        model.addAttribute("order", orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Order Id:" + id)));
        model.addAttribute("customers", customerRepository.findAll()); // Fetch customers for selection
        return "admin/Order/form";
    }

    @PostMapping("/save")
    public String saveOrder(@ModelAttribute Order order) {
        orderRepository.save(order);
        return "redirect:/order/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        orderRepository.deleteById(id);
        return "redirect:/order/list";
    }
}
