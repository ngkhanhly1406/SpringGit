package khly.codelean.demo10988;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/home")
    public String showHome() {
        return "index";
    }
}
