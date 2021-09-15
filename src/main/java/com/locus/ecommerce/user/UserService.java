package com.locus.ecommerce.user;

import com.locus.ecommerce.auth.AuthService;
import com.locus.ecommerce.exception.ApiRequestException;
import com.locus.ecommerce.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthService authService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRepository userRepository;



    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserProfile() {
        return authService.getCurrentUser();
    }

    public void addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());

        if (userByEmail.isPresent()) {
            throw new ApiRequestException("User With Email Already Exists");
        }

        Optional<User> userByPhone = userRepository.findByPhone(user.getPhone());
        System.out.println(userByPhone.toString());
        if (userByPhone.isPresent()) {
            throw new ApiRequestException("User With Phone Already Exists");
        }
        user.setStatus(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        roleService.addRoleToUser(user.getEmail(),"ROLE_CUSTOMER");
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new ApiRequestException("User Not Found");
        }
        ;
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String name, String email, String phone, String address, String city,
                           String postcode) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiRequestException("User Not Found"));

        if (name != null && name.length() > 0 && !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }
        if (email != null && !Objects.equals(user.getEmail(), email)) {
            Optional<User> existingUser = userRepository.findByEmail(email);
            if (existingUser.isPresent()) {
                throw new ApiRequestException("User With Email Already Exists");
            }
            user.setEmail(email);
        }
        if (phone != null && phone.length() > 0 && !Objects.equals(user.getPhone(), phone)) {
            user.setPhone(phone);
        }
        if (address != null && address.length() > 0 && !Objects.equals(user.getAddress(), address)) {
            user.setAddress(address);
        }
        if (city != null && city.length() > 0 && !Objects.equals(user.getCity(), city)) {
            user.setCity(city);
        }
        if (postcode != null && postcode.length() > 0 && !Objects.equals(user.getPostcode(), postcode)) {
            user.setPostcode(postcode);
        }
    }
}
