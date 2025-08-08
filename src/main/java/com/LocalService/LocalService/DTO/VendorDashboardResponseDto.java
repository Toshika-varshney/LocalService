package com.LocalService.LocalService.DTO;
// ye response vendor dash board k liye h

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorDashboardResponseDto {
    private Long bookingId;
    private String userName;
    private String userContact;
    private String serviceName;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private String bookingAddress;
    private String status;

}
