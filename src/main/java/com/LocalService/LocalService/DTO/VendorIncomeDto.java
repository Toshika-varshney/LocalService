package com.LocalService.LocalService.DTO;

import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorIncomeDto {
    private Double totalIncome;
    private Double projectedIncome;
    private Map<String, Double> monthlyIncome; // e.g., Jan: 1200.0
}

