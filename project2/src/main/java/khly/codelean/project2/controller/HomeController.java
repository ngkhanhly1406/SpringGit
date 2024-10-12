package khly.codelean.project2.controller;


import khly.codelean.project2.dao.*;
import khly.codelean.project2.entity.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpServletRequest;



@Controller
public class HomeController {

    private final UserRepository userRepo;

    private final ProductRepository productRepository;

    private final FAQRepository faqRepository;

    private final PostsRepository postsRepository;

    private final OrderRepository orderRepository;

    public HomeController(UserRepository userRepo, ProductRepository productRepository, FAQRepository faqRepository, PostsRepository postsRepository, OrderRepository orderRepository) {
        this.userRepo = userRepo;
        this.productRepository = productRepository;
        this.faqRepository = faqRepository;
        this.postsRepository = postsRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/")
    public String showHome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            String username = auth.getName();
            model.addAttribute("username", username);
        }
        return "home";
    }

    @GetMapping("/product")
    public String showProduct(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "shop-list-no-sidebar";
    }

    @GetMapping("/faq")
    public String viewFAQs(Model model) {
        model.addAttribute("faqs", faqRepository.findAll());
        return "faq";
    }

    @GetMapping("/blog")
    public String showBlog(Model model) {
        model.addAttribute("posts", postsRepository.findAll());
        return "blog-no-sidebar";
    }

    @GetMapping("/contact")
    public String showContact() {
        return "contact";
    }


    @GetMapping("/product-detail")
    public String showProductDetail() {
        return "product-details-thumbnail-right";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @GetMapping("/my-account")
    public String showAccount(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        return "my-account";
    }



    /*@GetMapping("/order-confirmation/{orderId}")
    public String orderConfirmation(@PathVariable Long orderId, Model model) {
        // Lấy thông tin đơn hàng từ cơ sở dữ liệu
        Order order = orderRepository.findOrderById(orderId); // Giả sử bạn có một phương thức để lấy đơn hàng
        model.addAttribute("order", order);
        return "order-confirmation"; // Trả về trang order-confirmation.html
    }*/

}
