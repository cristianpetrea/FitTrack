package com.fittrack.service.user;


import com.fittrack.dto.user.input.LoginRequest;
import com.fittrack.dto.user.output.LoginResponse;
import com.fittrack.model.user.User;
import com.fittrack.repository.user.UserRepository;
import com.fittrack.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public LoginResponse authUser(LoginRequest loginRequest){

        User user=userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()->new BadCredentialsException("Email sau parola incorecta!"));

        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            throw new BadCredentialsException("Email sau parola incorecta!");
        }

        String jwt = tokenProvider.generateToken(user);

        return new LoginResponse(jwt);

    }
}
