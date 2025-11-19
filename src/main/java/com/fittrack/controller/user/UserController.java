package com.fittrack.controller.user;

import com.fittrack.dto.user.input.LoginRequest;
import com.fittrack.dto.user.input.UserRegisterRequest;
import com.fittrack.dto.user.output.LoginResponse;
import com.fittrack.dto.user.output.UserResponse;
import com.fittrack.service.user.AuthService;
import com.fittrack.service.user.RegisterService;
import com.fittrack.service.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final RegisterService registerService;
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRegisterRequest request){
        UserResponse response=registerService.registerUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authUser(@Valid @RequestBody LoginRequest request){
        LoginResponse response=authService.authUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getMyProfile(Authentication authentication) {

        String email = authentication.getName();

        UserResponse response = userService.getUserProfileByEmail(email);

        return ResponseEntity.ok(response);
    }
}
