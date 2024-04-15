package com.example.demo.login;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/login")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    @CrossOrigin
    public void login(@RequestBody LoginRequest loginRequest) {
        loginService.login(loginRequest);
    }
}
