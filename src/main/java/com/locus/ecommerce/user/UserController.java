package com.locus.ecommerce.user;

import com.locus.ecommerce.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/users")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(path = "/register")
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Long userId, @RequestBody Map<String, Object> reqBody) {
        String name = (String) reqBody.get("name");
        String email = (String) reqBody.get("email");
        String phone = (String) reqBody.get("phone");
        String address = (String) reqBody.get("address");
        String city = (String) reqBody.get("city");
        String postcode = (String) reqBody.get("postcode");

        userService.updateUser(userId, name, email, phone, address, city, postcode);
    }

}
