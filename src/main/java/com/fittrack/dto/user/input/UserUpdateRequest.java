package com.fittrack.dto.user.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateRequest {

    String email;
    String lastName;
    String firstName;
    String phone;
}
