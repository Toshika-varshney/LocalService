package com.LocalService.LocalService.Entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;




@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "vendor_services")
public class VendorService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vendorId;
    private String vendorName;
    private String serviceName;        // Name of the service offered
    private String description;        // Description of the service
    private String contactNumber;      // Vendor's contact number (copied from User table)
    private String city;               // Vendor's city (copied from User table)
    private String businessAddress;    // Better naming than "serviceAddress"
    private Double price;
    private String category;
    private String availableDays;
    private String serviceTiming;      // Example: "10 AM - 6 PM"
    private LocalDateTime createdAt;
}

