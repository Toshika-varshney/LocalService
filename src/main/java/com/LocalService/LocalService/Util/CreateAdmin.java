package com.LocalService.LocalService.Util;

import com.LocalService.LocalService.Entity.Role;
import com.LocalService.LocalService.Entity.User;
import com.LocalService.LocalService.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CreateAdmin {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        String adminEmail="admin123@gmail.com";
        if(userRepository.findByEmail(adminEmail).isEmpty()){
            User admin=new User();
            admin.setName("Admin");
            admin.setRole(Role.ADMIN);
            admin.setCity("Bahjoi");
            admin.setEmail(adminEmail);
            admin.setPhone("9876541230");
            admin.setPassword(passwordEncoder.encode("admin123"));

            admin.setRegisteredDate(LocalDate.now());
            userRepository.save(admin);
            System.out.println("Admin created");

        }
    }

}
