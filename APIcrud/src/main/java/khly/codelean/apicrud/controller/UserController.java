package khly.codelean.apicrud.controller;

import khly.codelean.apicrud.dto.request.UserCreationRequest;
import khly.codelean.apicrud.entity.User;
import khly.codelean.apicrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    User createUser(@RequestBody UserCreationRequest request){
        return userService.createUser(request);
    }


}
