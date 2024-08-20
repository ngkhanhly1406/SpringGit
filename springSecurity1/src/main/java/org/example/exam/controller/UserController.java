package org.example.exam.controller;

import org.example.exam.entity.User;
import org.example.exam.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService theUserService) {userService = theUserService;}

    @GetMapping("/list")
    public String listUsers(Model theModel) {
        List<User> theUsers = userService.findAll();
        theModel.addAttribute("users", theUsers);
        return "users/list-users";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        User theUser = new User();
        theModel.addAttribute("user", theUser);
        return "users/user-form";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") int theId, Model theModel) {
        User theUser = userService.findById(theId);
        theModel.addAttribute("user", theUser);
        return "users/user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User theUser) {
        userService.save(theUser);
        return "redirect:/users/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("userId") int theId) {
        userService.deleteById(theId);
        return "redirect:/users/list";
    }


}
