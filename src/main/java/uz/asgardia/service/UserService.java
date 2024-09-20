package uz.asgardia.service;

import org.springframework.stereotype.Service;
import uz.asgardia.entity.User;
import uz.asgardia.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(User user){
        return userRepository.findByUserId(user.getId());
    }
}
