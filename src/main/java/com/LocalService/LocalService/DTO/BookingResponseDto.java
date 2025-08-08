package com.LocalService.LocalService.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponseDto {
    private Long id;
    private String serviceName;
    private String vendorName;
    private String businessName;
    private String city;
    private String bookingAddress;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private LocalDateTime createdAt;
    private String status;
    private String notes;
}
