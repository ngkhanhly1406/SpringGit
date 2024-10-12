package khly.codelean.project2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/my_account")
    public String myAccount() {
        return "my_account"; // Trả về view account
    }
}
