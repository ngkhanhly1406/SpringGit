package khly.codelean.apicrud.service;

import khly.codelean.apicrud.dto.request.UserCreationRequest;
import khly.codelean.apicrud.entity.User;
import khly.codelean.apicrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        User user = new User();

        user.setTitle(request.getTitle());
        user.setContent(request.getContent());
        user.setRate(request.getRate());
        user.setCreated(request.getCreated());
        user.setAuthor(request.getAuthor());

        return userRepository.save(user);
    }
}

