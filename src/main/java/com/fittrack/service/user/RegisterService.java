package com.fittrack.service.user;

import com.fittrack.dto.user.input.UserRegisterRequest;
import com.fittrack.dto.user.output.UserResponse;
import com.fittrack.exception.EmailAlreadyExistsException;
import com.fittrack.model.user.User;
import com.fittrack.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse registerUser(UserRegisterRequest request){


        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Exista deja un cont cu acest email!");
        }

        if(request.getPassword().length()<6){
            throw new IllegalArgumentException("Parola trebuie sa aiba minim 6 caractere!");
        }

        User newUser=new User();

        String encodedPassword=passwordEncoder.encode(request.getPassword());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(encodedPassword);
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setPhoneNumber(request.getPhone());



        User savedUser=userRepository.save(newUser);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getPhoneNumber(),
                savedUser.getRole(),
                savedUser.getCreatedAt()
        );
    }


}
