package khly.codelean.khlymealdemo.controller;

import java.util.List;
import java.util.ArrayList;

import jakarta.annotation.PostConstruct;
import khly.codelean.khlymealdemo.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@SpringBootApplication
@RequestMapping("/employees")
class EmployeeController {
    private List<Employee> theEmployees;

    @PostConstruct
    private void loadData() {
        Employee emp1 = new Employee(1, "Dang", "Thi", "dangkimthi@gmail.com");
        Employee emp2 = new Employee(2, "Khanh", "Ly", "dangkimthi@gmail.com");
        Employee emp3 = new Employee(3, "Bao", "Nam", "dangkimthi@gmail.com");

        theEmployees = new ArrayList<>();
        theEmployees.add(emp1);
        theEmployees.add(emp2);
        theEmployees.add(emp3);
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {
        model.addAttribute("employees", theEmployees);
        return "list_employees";
    }

}
