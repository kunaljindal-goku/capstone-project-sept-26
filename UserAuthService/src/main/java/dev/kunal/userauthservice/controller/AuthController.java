package dev.kunal.userauthservice.controller;

import dev.kunal.userauthservice.dto.AuthRequest;
import dev.kunal.userauthservice.dto.UserDto;
import dev.kunal.userauthservice.model.User;
import dev.kunal.userauthservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<UserDto> signUp(@RequestBody AuthRequest authRequest) {
        User user = authService.signUp(authRequest.getEmail(),
                authRequest.getPassword());
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setId(user.getId());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
