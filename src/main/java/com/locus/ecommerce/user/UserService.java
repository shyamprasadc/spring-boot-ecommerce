package com.locus.ecommerce.user;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return  userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());

        if(userByEmail.isPresent()){
            throw new IllegalStateException("User With Email Already Exists");
        }

        Optional<User> userByPhone = userRepository.findUserByPhone(user.getPhone());

        if(userByPhone.isPresent()){
            throw new IllegalStateException("User With Phone Already Exists");
        }

        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists) {
            throw new IllegalStateException("User Not Found");
        };
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId,String name,String email,String phone) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User Not Found"));

        if (name != null && name.length() > 0 && !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }
        if (email != null && !Objects.equals(user.getEmail(), email)) {
            Optional<User> existingUser = userRepository.findUserByEmail(email);
            if(existingUser.isPresent()){
                throw new IllegalStateException("User With Email Already Exists");
            }
            user.setEmail(email);
        }
    }
}
