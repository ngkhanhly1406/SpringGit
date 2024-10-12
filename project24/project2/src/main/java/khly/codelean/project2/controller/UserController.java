package khly.codelean.project2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import khly.codelean.project2.dao.CustomerRepository;
import khly.codelean.project2.entity.Category;
import khly.codelean.project2.entity.Customer;
import khly.codelean.project2.login.User;
import khly.codelean.project2.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@Controller
@RequestMapping("/user")
public class UserController {

    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    /*@PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }*/

    @PostMapping("/process_register")
    public String processRegister(User user, @RequestParam("phone") String phone, @RequestParam("address") String address) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Lưu người dùng
        userRepo.save(user);

        // Tạo và lưu khách hàng
        Customer customer = new Customer();
        customer.setName(user.getFirstName() + " " + user.getLastName());
        customer.setEmail(user.getEmail());
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setUser(user);

        customerRepo.save(customer);

        return "register_success";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("users", listUsers);
        return "admin/User/list";
    }









    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("user", new Category());
        return "admin/User/form";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + id)));
        // send over to our form
        return "admin/User/form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        userRepo.save(user);

        // use a redirect to prevent duplicate submissions
        return "redirect:/user/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userRepo.deleteById(id);
        return "redirect:/user/list";

    }
}
