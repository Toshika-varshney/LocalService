package com.LocalService.LocalService.Service;

import com.LocalService.LocalService.DTO.AuthResponse;
import com.LocalService.LocalService.DTO.UserDto;
import com.LocalService.LocalService.DTO.UserResponseDto;
import com.LocalService.LocalService.Entity.Role;
import com.LocalService.LocalService.Entity.User;
import com.LocalService.LocalService.Exceptions.ResourceNotFoundException;
import com.LocalService.LocalService.Mappers.UserMapper;
import com.LocalService.LocalService.Repository.UserRepository;
import com.LocalService.LocalService.Security.CustomUserDetails;
import com.LocalService.LocalService.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User registerUser(UserDto dto) {
        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already exists");
        }
        if(userRepository.findByPhone(dto.getPhone()).isPresent()){
            throw new IllegalArgumentException("This phone no. already exists");
        }
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.USER);
        user.setRegisteredDate(LocalDate.now());
        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(String email, String password) {
        User user=userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("user not found with email :"+ email));

        if(!passwordEncoder.matches( password,user.getPassword())){
            throw new IllegalArgumentException("enter correct password");
        }
       String token =jwtUtil.generateToken(
               new CustomUserDetails(user),user.getId(),user.getRole().name()
       );
        return new AuthResponse(token,user.getRole().name(),user.getId());
    }

    @Override
    public void blockUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        user.setBlocked(true);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        user.setDeleted(true);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(); // You can add filtering for isDeleted = false if needed
    }

    @Override
    public List<User> getAllVendors() {
        return userRepository.findByRole(Role.VENDOR);
    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + username));
    }

}
