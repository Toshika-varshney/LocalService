package com.LocalService.LocalService.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorRequestDto {

    @NotBlank(message = "business name required")
    private String businessName;
    @NotBlank(message = "Required description")
    private String description;
}
