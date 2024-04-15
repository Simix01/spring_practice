package com.example.demo.appuser;

import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor  //annotazione per non dover implementare il costruttore di appUserRepository
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUp(AppUser appUser) {

        //SALVO UTENTE RICEVUTO COME METODO POST NEL DATABASE
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("email already in use");
        }
        String encodedPw = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPw);
        appUserRepository.save(appUser);

        //CREAZIONE TOKEN PER REGISTRAZIONE UTENTE
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(
                confirmationToken
        );

        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    public Optional<AppUser> signIn(String email, String password) {

        boolean userExists = appUserRepository.findByEmail(email).isPresent();
        if(!userExists)
            throw new IllegalStateException("user with this email does not exist");

        Optional<AppUser> user = appUserRepository.findByEmail(email);

        boolean isUserActive = user.get().isEnabled();
        if(!isUserActive)
            throw new IllegalStateException("user is not active");

        if(!bCryptPasswordEncoder.matches(password, user.get().getPassword()))
            throw new IllegalStateException("password does not match");
        else
            System.out.println("fatto");


        return user;
    }
}
