package com.LocalService.LocalService.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message="email is required")
    @Email
    private String email;
    @Pattern( regexp = "^\\d{10}$",message="phone no. is required")
    private String phone;
    @NotBlank(message="city is required")
    private String city;
    @NotBlank(message =" password is required")
    private String password;
    @NotNull(message = "address required")
    private String address;

}
