package com.appointee.Controllers;

import com.appointee.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("create")
    public String createUser(){
        return userService.createUser("nayeem", "ashraf");
    }
}
