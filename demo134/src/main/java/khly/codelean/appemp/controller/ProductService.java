package khly.codelean.appemp.controller;

import khly.codelean.appemp.entity.Product;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductService {
    // create a mapping for "/hello"

    @GetMapping("/hello")
    public String sayHello(Model theModel) {

        theModel.addAttribute("theDate", new java.util.Date());

        return "helloworld";
    }


}

