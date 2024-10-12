package khly.codelean.project2.controller;

import jakarta.servlet.http.HttpSession;
import khly.codelean.project2.cart.ShoppingCart;
import khly.codelean.project2.dao.CustomerRepository;
import khly.codelean.project2.dao.OrderRepository;
import khly.codelean.project2.dao.ProductRepository;
import khly.codelean.project2.entity.*;
import khly.codelean.project2.login.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    public CartController(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }


    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable("productId") Long productId, HttpSession session) {
        // Kiểm tra xem người dùng đã đăng nhập chưa
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
        }*/

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            return "redirect:/error/404";
        }

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        cart.addItem(new CartItem(product, 1));
        return "redirect:/cart/view";
    }

    /*@PostMapping("/update/{productId}")
    public String updateQuantity(@PathVariable Long productId, @RequestBody Map<String, Integer> payload, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null) {
            int newQuantity = payload.get("quantity");
            cart.updateQuantity(productId, newQuantity);
            return "redirect:/cart/view"; // Chuyển hướng trở lại trang giỏ hàng
        }
        return "error/error";
    }*/





    @RequestMapping("/view")
    public String viewCart(Model model, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null) {
            model.addAttribute("cart", cart);
            // Tính tổng giá của giỏ hàng dựa trên từng sản phẩm và số lượng
            BigDecimal total = cart.getItems().stream()
                    .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            model.addAttribute("total", total);
        } else {
            model.addAttribute("cart", new ShoppingCart());
            model.addAttribute("total", BigDecimal.ZERO); // Khi giỏ hàng trống
        }
        return "cart"; // Trả về view "cart"
    }


    /*@PostMapping("/checkout")
    public String checkout(@SessionAttribute("cart") ShoppingCart cart, Principal principal, HttpSession session) {
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart/view"; // Nếu giỏ hàng trống, không thể checkout
        }

        // Lấy thông tin khách hàng dựa trên người dùng hiện tại
        Customer customer = customerRepository.findByUser_Email(principal.getName());

        if (customer == null) {
            return "redirect:/error/404"; // Nếu không tìm thấy khách hàng
        }

        // Tạo đơn hàng mới
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(cart.getTotalPrice());

        // Lưu các mục chi tiết đơn hàng
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(item.getProduct());
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setPrice(item.getTotalPrice());
            orderDetail.setOrder(order);
            orderDetails.add(orderDetail);
        }
        order.setOrderDetails(orderDetails);

        // Lưu đơn hàng vào cơ sở dữ liệu
        orderRepository.save(order);

        // Xóa giỏ hàng sau khi thanh toán thành công
        session.removeAttribute("cart");

        return "redirect:/order/confirmation";
    }*/
    @PostMapping("/update/{productId}")
    public String updateQuantity(@PathVariable Long productId, @RequestBody Map<String, Integer> payload, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null) {
            int newQuantity = payload.get("quantity");
            cart.updateQuantity(productId, newQuantity);
            return "redirect:/cart/view"; // Chuyển hướng trở lại trang giỏ hàng
        }
        return "error/error";
    }



    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null) {
            cart.removeItem(productId); // Xóa sản phẩm khỏi giỏ hàng
        }
        return "redirect:/cart/view"; // Chuyển hướng về trang giỏ hàng
    }


}

