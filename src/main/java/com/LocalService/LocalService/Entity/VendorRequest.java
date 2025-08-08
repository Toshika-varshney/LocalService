package com.LocalService.LocalService.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="vendor_request")
@Entity
public class VendorRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    private String businessName;//uski dukan ya bussiness ka kya naam h kis naam se frontened pe dikhegi

    private String description;
    @Enumerated(EnumType.STRING)
    private Status requestStatus;
    private LocalDateTime requestedAt;
}
