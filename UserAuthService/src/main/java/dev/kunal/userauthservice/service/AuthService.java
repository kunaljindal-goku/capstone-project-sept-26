package dev.kunal.userauthservice.service;

import dev.kunal.userauthservice.dto.AuthRequest;
import dev.kunal.userauthservice.model.Role;
import dev.kunal.userauthservice.model.User;
import dev.kunal.userauthservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signUp(String email, String password) {
        if(userRepository.existsByEmail(email)) {
            return null;
        }

        User user = new User();
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }
}
