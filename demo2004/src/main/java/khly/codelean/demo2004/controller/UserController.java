package khly.codelean.demo2004.controller;

import ch.qos.logback.core.model.Model;
import khly.codelean.demo2004.model.User;
import khly.codelean.demo2004.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/account")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/edit")
    public String showEditForm(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findUserByUsername(username);
        model.addAttribute("user", user);
        return "account/edit";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User updatedUser, RedirectAttributes redirectAttributes) {
        userService.updateUser(updatedUser);
        redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin thành công!");
        return "redirect:/account/edit";
    }
}

