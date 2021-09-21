package com.locus.ecommerce.user;

import com.locus.ecommerce.address.AddressService;
import com.locus.ecommerce.auth.AuthService;
import com.locus.ecommerce.exception.ApiRequestException;
import com.locus.ecommerce.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private AddressService addressService;
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

    public void addNewUser(String name, String email, String phone, String password, String address, String city, String postcode) {
        Optional<User> userByEmail = userRepository.findByEmail(email);
        if (userByEmail.isPresent()) {
            throw new ApiRequestException("User With Email Already Exists");
        }

        Optional<User> userByPhone = userRepository.findByPhone(phone);
        if (userByPhone.isPresent()) {
            throw new ApiRequestException("User With Phone Already Exists");
        }

        User user = new User(name, email, phone, password);
        user.setStatus(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        roleService.addRoleToUser(user.getEmail(), "ROLE_CUSTOMER");
        addressService.addAddressOfUser(user.getEmail(), address, city, postcode);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new ApiRequestException("User Not Found");
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String name, String email, String phone) {
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
    }
}
