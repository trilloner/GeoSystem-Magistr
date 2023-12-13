package com.geosystem.springbootbackend.controllers;

import com.geosystem.springbootbackend.dto.UserRequest;
import com.geosystem.springbootbackend.models.User;
import com.geosystem.springbootbackend.models.UserDetailsImpl;
import com.geosystem.springbootbackend.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody UserRequest user) throws Exception {
//        if (user.getId() != null && user.getId() != 0) {
//            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
//        }
//        if (user.getName() == null || user.getName().trim().length() == 0) {
//            return new ResponseEntity("missed param: name", HttpStatus.NOT_ACCEPTABLE);
//        }

        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/all")
    public List<User> showAll() {
        return userService.findAllUsers();
    }

    @GetMapping("/login")
    public User user(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        log.info("user logged " + userDetails.getUser());

        return userDetails.getUser();
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerNewUser(@RequestBody UserRequest user) {
        try {
            return ResponseEntity.ok(userService.saveUser(user));
        } catch (Exception ex) {
            log.error("User can`t be added: {}", ex.getMessage());
            return ResponseEntity.status(500).body(new User());
        }
    }
}
