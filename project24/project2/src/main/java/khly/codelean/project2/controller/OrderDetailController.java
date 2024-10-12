package khly.codelean.project2.controller;

import khly.codelean.project2.dao.OrderDetailRepository;
import khly.codelean.project2.dao.OrderRepository;
import khly.codelean.project2.entity.OrderDetail;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@Controller
@RequestMapping("/orderdetail")
public class OrderDetailController {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;

    public OrderDetailController(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/list")
    public String listOrderDetail(Model model) {
        model.addAttribute("orderdetails", orderDetailRepository.findAll());
        return "admin/OrderDetail/list";
    }

    @GetMapping("/add")
    public String addOrderDetail(Model model) {
        model.addAttribute("orderdetail", new OrderDetail());
        model.addAttribute("orders", orderRepository.findAll()); // Fetch list of orders
        return "admin/OrderDetail/form";
    }

    @GetMapping("/edit/{id}")
    public String editOrderDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("orderdetail", orderDetailRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid OrderDetail Id:" + id)));
        model.addAttribute("orders", orderRepository.findAll()); // Fetch list of orders
        return "admin/OrderDetail/form";
    }

    @PostMapping("/save")
    public String saveOrderDetail(@ModelAttribute OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
        return "redirect:/orderdetail/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrderDetail(@PathVariable("id") Long id) {
        orderDetailRepository.deleteById(id);
        return "redirect:/orderdetail/list";
    }
}
