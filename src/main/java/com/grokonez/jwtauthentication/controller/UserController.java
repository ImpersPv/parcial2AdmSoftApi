package com.grokonez.jwtauthentication.controller;

import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin()
@RestController
//@RequestMapping("/api/auth")

public class UserController {
    @Autowired
    private UserRepository userRepository;

    //get all
    @GetMapping("/users")
    public Page<User> getImcs(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}