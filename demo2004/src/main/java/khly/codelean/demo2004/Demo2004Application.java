package khly.codelean.demo2004;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Demo2004Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo2004Application.class, args);
    }


    @GetMapping("/users")
    public String sayHello(@RequestParam (value ="myName", defaultValue = "world") String name) {
        return String.format( "Hello " , name);
    }

//    @GetMapping("/admim")
//    public String sayAdmin(@RequestParam (value ="myName", defaultValue = "world") String name) {
//        return String.format( "Hello admin " , name);
//    }
}



