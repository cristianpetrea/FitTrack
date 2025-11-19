package com.fittrack.controller.user;

import com.fittrack.dto.user.input.LoginRequest;
import com.fittrack.dto.user.input.UserRegisterRequest;
import com.fittrack.dto.user.output.LoginResponse;
import com.fittrack.dto.user.output.UserResponse;
import com.fittrack.service.user.AuthService;
import com.fittrack.service.user.RegisterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final RegisterService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRegisterRequest request){
        UserResponse response=userService.registerUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authUser(@Valid @RequestBody LoginRequest request){
        LoginResponse response=authService.authUser(request);
        return ResponseEntity.ok(response);
    }
}
