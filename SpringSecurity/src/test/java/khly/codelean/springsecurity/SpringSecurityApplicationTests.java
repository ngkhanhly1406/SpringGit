package khly.codelean.springsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootTest
class SpringSecurityApplicationTests {
	public static void main(String[] args) {
		SpringApplication.run (
			SpringSecurityApplicationTests.class,args);}
			@GetMapping("/users")
			public String sayHello (@RequestParam(value = "my Name", defaultValue = "World") String name){
				return String.format("hello %s!", name);
			}


	}

