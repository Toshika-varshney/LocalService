package com.LocalService.LocalService.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message=" email is Required")
    private String email;
    @NotBlank(message = "password is Required")
    private String password;
}
