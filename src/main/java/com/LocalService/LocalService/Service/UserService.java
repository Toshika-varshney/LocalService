package com.LocalService.LocalService.Service;

import com.LocalService.LocalService.DTO.AuthResponse;
import com.LocalService.LocalService.DTO.UserDto;
import com.LocalService.LocalService.DTO.UserResponseDto;
import com.LocalService.LocalService.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService  {
    User registerUser(UserDto userDto);
    AuthResponse login(String email, String password);
    void blockUser(Long userId);

    void deleteUser(Long userId);

    List<User> getAllUsers();

    List<User> getAllVendors();
   User findByEmail(String username);

}
