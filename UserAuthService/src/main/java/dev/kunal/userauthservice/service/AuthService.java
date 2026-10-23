package dev.kunal.userauthservice.service;

import dev.kunal.userauthservice.dto.AuthRequest;
import dev.kunal.userauthservice.model.Role;
import dev.kunal.userauthservice.model.User;
import dev.kunal.userauthservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

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

    public String login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()
                || !passwordEncoder.matches(password, optionalUser.get().getPasswordHash())) {
            return null;
        }
        return jwtService.generateToken(optionalUser.get());
    }
}
