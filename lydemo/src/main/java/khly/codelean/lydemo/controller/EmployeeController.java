package khly.codelean.lydemo.controller;


import khly.codelean.lydemo.model.Employee;
import org.springframework.ui.Model;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@SpringBootApplication
@RequestMapping("/employees")
public class EmployeeController {

    private List<Employee> theEmployees;

    @PostConstruct
    private  void loadData(){

        Employee emp1 = new Employee(1,"pham","nam","nam@gmail.com");
        Employee emp2 = new Employee(2,"khanh","ly","ly@gmail.com");
        Employee emp3 = new Employee(3,"nhu","hai","hainhu@gmail.com");

        theEmployees = new ArrayList<>();

        theEmployees.add(emp1);
        theEmployees.add(emp2);
        theEmployees.add(emp3);

    }

    @GetMapping("/list")
    public String listEmployees(Model themodel){
        themodel.addAttribute("employees",theEmployees);

        return "list-employees";
    }

}