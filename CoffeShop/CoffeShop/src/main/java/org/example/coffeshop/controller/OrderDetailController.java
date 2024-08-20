package org.example.coffeshop.controller;

import org.example.coffeshop.entity.Customer;
import org.example.coffeshop.entity.OrderDetail;
import org.example.coffeshop.service.OrderDetail.OrderDetailService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@Controller
@RequestMapping("/customer")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService theOrderDetailService) {
        this.orderDetailService = theOrderDetailService;
    }

    @GetMapping("/list")
    public String listOrderDetails(Model theModel) {
        List<OrderDetail> theOrderDetail = orderDetailService.findAll();
        theModel.addAttribute("order", theOrderDetail);
        return "orders/list-orders";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        OrderDetail theOrderDetail = new OrderDetail();
        theModel.addAttribute("order", theOrderDetail);
        return "orders/form-orders";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("orderDetailId") int theId, Model theModel) {
        OrderDetail theOrderDetail = orderDetailService.findById(theId);
        theModel.addAttribute("order", theOrderDetail);
        return "orders/form-orders";
    }

    @PostMapping("/save")
    public String saveOrderDetail(@ModelAttribute("orderDetail") OrderDetail theOrderDetail) {
        orderDetailService.save(theOrderDetail);
        return "redirect:/order/list";
    }

    @PostMapping("/delete")
    public String deleteOrderDetail(@RequestParam("orderDetailId") int theId) {
        orderDetailService.deleteById(theId);
        return "redirect:/order/list";
    }
}
