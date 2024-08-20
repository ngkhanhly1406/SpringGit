package khly.codelean.testspringmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@Controller
public class TestspringmvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestspringmvcApplication.class, args);
    }

    @GetMapping("/testspringmvc")
    public String testspringmvc(@RequestParam(name="name", re) el) {}
}
