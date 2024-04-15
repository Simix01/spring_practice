package com.example.demo.login;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserService;
import com.example.demo.registration.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public void login(LoginRequest loginRequest) {

        boolean isMailValid = emailValidator.test(loginRequest.getEmail());
        if(!isMailValid){
            throw new IllegalStateException("email is not valid");
        }

        Optional<AppUser> currentUser = appUserService.signIn(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
