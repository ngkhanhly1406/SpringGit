package org.example.coffeshop.controller;

import org.springframework.ui.Model;
import org.example.coffeshop.entity.Customer;
import org.example.coffeshop.entity.Order;
import org.example.coffeshop.service.Order.OrderService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService theOrderService) {
        orderService = theOrderService;
    }

    @GetMapping("/list")
    public String listOrders(Model theModel) {
        List<Order> theOrders = orderService.findAll();
        theModel.addAttribute("order", theOrders);
        return "orders/list-orders";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Order theOrder = new Order();
        theModel.addAttribute("order", theOrder);
        return "orders/form-orders";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("orderId") int theId, Model theModel) {
        Order theOrder = orderService.findById(theId);
        theModel.addAttribute("order", theOrder);
        return "orders/form-orders";
    }

    @PostMapping("/save")
    public String saveOrder(@ModelAttribute("order") Order theOrder) {
        orderService.save(theOrder);
        return "redirect:/order/list";
    }

    @PostMapping("/delete")
    public String deleteOrder(@RequestParam("orderId") int theId) {
        orderService.deleteById(theId);
        return "redirect:/order/list";
    }
}
