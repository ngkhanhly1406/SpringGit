package khly.codelean.project22;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Project22Application {

	public static void main(String[] args) {
		SpringApplication.run(Project22Application.class, args);
	}

	@GetMapping("/users")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("Users Hello %s!", name);
	}

	@GetMapping("/admin")
	public String sayAdmin(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("Admin Hello %s!", name);
	}
}