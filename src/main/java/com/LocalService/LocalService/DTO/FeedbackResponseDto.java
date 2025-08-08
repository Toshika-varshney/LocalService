package com.LocalService.LocalService.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponseDto {
    private Long id;
    private String comment;

}
