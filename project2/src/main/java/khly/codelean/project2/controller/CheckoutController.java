package khly.codelean.project2.controller;


import jakarta.servlet.http.HttpSession;
import khly.codelean.project2.cart.ShoppingCart;
import khly.codelean.project2.dao.CustomerRepository;
import khly.codelean.project2.dao.OrderRepository;
import khly.codelean.project2.dao.ProductRepository;
import khly.codelean.project2.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {


    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;


    public CheckoutController(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @RequestMapping("/payment")
    public String showCheckoutPage(HttpSession session, Model model, Principal principal) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            return "redirect:/cart/view";
        }

        if (principal == null) {
            return "redirect:/login";
        }

        String userEmail = principal.getName();
        Customer customer = customerRepository.findByUser_Email(userEmail);

        if (customer == null) {
            return "redirect:/login";
        }

        BigDecimal total = cart.getTotal();

        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        model.addAttribute("customer", customer);

        return "checkout";
    }


    @PostMapping("/cart-checkout")
    public String submitOrder(HttpSession session, Principal principal,
                              @RequestParam("address") String address,
                              @RequestParam("phone") String phone, Model model) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            model.addAttribute("error", "Giỏ hàng trống.");
            return "checkout";  // Quay lại trang checkout nếu giỏ hàng trống
        }

        // Lấy thông tin người dùng hiện tại từ Principal
        if (principal == null) {
            return "redirect:/login";
        }
        String userEmail = principal.getName();
        Customer customer = customerRepository.findByUser_Email(userEmail);
        if (customer == null) {
            return "redirect:/login";
        }

        // Cập nhật địa chỉ và số điện thoại
        customer.setAddress(address);
        customer.setPhone(phone);
        customerRepository.save(customer);

        // Tạo đơn hàng và lưu vào cơ sở dữ liệu
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(cart.getTotal());
        order.setStatus("Pending");

        List<OrderDetail> orderDetails = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            OrderDetail detail = new OrderDetail();
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            detail.setPrice(item.getProduct().getPrice());
            detail.setOrder(order);
            orderDetails.add(detail);
        }
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);

        session.removeAttribute("cart");

        return "redirect:/checkout/order-confirmation" ;
    }

    @GetMapping("/order-confirmation")
    public String orderConfirmation() {
        return "order-confirmation";
    }

}
