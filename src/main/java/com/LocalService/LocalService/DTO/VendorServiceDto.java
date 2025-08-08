package com.LocalService.LocalService.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorServiceDto {

//    @NotNull(message = "Vendor ID is required")
//    private Long vendorId;

    @NotBlank(message = "Service name is required")
    private String serviceName;

    @NotBlank(message = "Description is required")
    private String description;

    @Positive(message = "Price must be positive")
    private double price;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Available days are required")
    private String availableDays;

    @NotBlank(message = "Service timings are required")
    private String serviceTiming;

    @NotBlank(message = "Business address is required")
    private String businessAddress;
}
