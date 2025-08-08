package com.LocalService.LocalService.DTO;

import lombok.*;

import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeSummaryDto {
    private double totalIncome;
    private int totalBookings;
    private double incomeThisMonth;
    private Map<String, Double> monthlyIncomeMap;
}
