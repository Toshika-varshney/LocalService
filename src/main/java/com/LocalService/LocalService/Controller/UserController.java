package com.LocalService.LocalService.Controller;

import com.LocalService.LocalService.DTO.AuthResponse;
import com.LocalService.LocalService.DTO.LoginRequest;
import com.LocalService.LocalService.DTO.UserDto;
import com.LocalService.LocalService.DTO.UserResponseDto;
import com.LocalService.LocalService.Entity.User;
import com.LocalService.LocalService.Security.JwtUtil;
import com.LocalService.LocalService.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.LocalService.LocalService.Util.CookieUtil.extractTokenFromCookies;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<User> register (@Valid @RequestBody UserDto dto){
        return ResponseEntity.ok(userService.registerUser(dto));
    }
//    @PostMapping(value = "/login", produces = "application/json")
//    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
//        return ResponseEntity.ok(userService.login(request.getEmail(), request.getPassword()));
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request,
                                              HttpServletResponse response) {
        AuthResponse authResponse = userService.login(request.getEmail(), request.getPassword());

        Cookie cookie = new Cookie("token", authResponse.getToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // true for HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60); // 1 day

        response.addCookie(cookie);

        //  Return the actual authResponse, not a new one with null
        return ResponseEntity.ok(authResponse);
    }


    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getLoggedInUser(HttpServletRequest request) {
        String token = extractTokenFromCookies(request);
        if (token == null) {
            return ResponseEntity.status(401).build();
        }

        String username = jwtUtil.extractUsername(token);
         User user = userService.findByEmail(username);

        return ResponseEntity.ok(new UserResponseDto());
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0); // expires now

        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }



}
