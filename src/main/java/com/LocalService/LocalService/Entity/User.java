package com.LocalService.LocalService.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Table(name="users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,unique = true,length = 10)
    private String phone;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    private String address;
    @Column(nullable = false,name= "registered_date")
    private LocalDate registeredDate;
    private String profileImageUrl;
    private boolean isBlocked;
    private boolean isDeleted;
}
