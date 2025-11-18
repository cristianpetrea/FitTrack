package com.fittrack.dto.user.output;


import com.fittrack.model.user.UserRole;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class UserResponse {

    UUID id;
    String email;
    String firstName;
    String lastName;
    String phone;
    UserRole role;
    LocalDateTime createdAt;

}
