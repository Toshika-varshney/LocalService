package com.LocalService.LocalService.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequestDto {

    private Long bookingId;
    private String comment;
    @Min(1)
    @Max(5)
    private int rating;

}
