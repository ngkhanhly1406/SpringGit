package khly.codelean.project2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String showHome() {return "home";}
    @GetMapping("/shop-right-sidebar")
    public String Shopright() {return "shop-right-sidebar";}
    @GetMapping("/shop-left-sidebar")
    public String Shopleft() {return "shop-left-sidebar";}
    @GetMapping("/blog-left-sidebar")
    public String Blogleft() {return "blog-left-sidebar";}
    @GetMapping("/contact")
    public String ShowContact() {return "contact";}
    @GetMapping("/login")
    public String Login() {return "login";}
    @GetMapping("/register")
    public String Register() {return "register";}
    @GetMapping("/my-account")
    public String MyAccount() {return "my-account";}

}