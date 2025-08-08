package com.LocalService.LocalService.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorServiceResponseDto {
//    private Long id;
    private String serviceName;
    private String description;
    private double price;
    private String category;
    private String availableDays;
    private String serviceTiming;
    private String businessAddress;
    private String contactNumber;
    private String city;
    private String vendorName;
}

