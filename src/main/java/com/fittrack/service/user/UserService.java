package com.fittrack.service.user;

import com.fittrack.dto.user.input.UserRegisterRequest;
import com.fittrack.dto.user.input.UserUpdateRequest;
import com.fittrack.dto.user.output.UserResponse;
import com.fittrack.exception.UserNotFoundException;
import com.fittrack.model.user.User;
import com.fittrack.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserResponse getUserProfileByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Utilizatorul nu a fost gasit!"));

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getRole(),
                user.getCreatedAt()
        );
    }

    public UserResponse updateUserProfile(String email, UserUpdateRequest request){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("Utilizatorul nu a fost gasit"));

        if(request.getFirstName()!=null&& !request.getFirstName().trim().isEmpty()){
            user.setFirstName(request.getFirstName());
        }
        if(request.getLastName()!=null&& !request.getLastName().trim().isEmpty()){
            user.setLastName(request.getLastName());
        }
        if(request.getPhone()!=null&& !request.getPhone().trim().isEmpty()){
            user.setPhoneNumber(request.getPhone());
        }

        User updatedUser=userRepository.save(user);

        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getEmail(),
                updatedUser.getFirstName(),
                updatedUser.getLastName(),
                updatedUser.getPhoneNumber(),
                updatedUser.getRole(),
                updatedUser.getCreatedAt()
        );
    }

}
