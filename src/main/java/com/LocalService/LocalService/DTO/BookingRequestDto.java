package com.LocalService.LocalService.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequestDto {

    private Long serviceId;

    @NotNull(message = "required BookingDate")
    private LocalDate bookingDate;

    @NotNull(message = "Timing is required")
    private LocalTime bookingTiming;

    @NotNull(message = "Address required")
    private String bookingAddress;

    private String notes;
}

