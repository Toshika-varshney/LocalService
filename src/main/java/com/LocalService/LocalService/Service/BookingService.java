package com.LocalService.LocalService.Service;

import com.LocalService.LocalService.DTO.BookingRequestDto;
import com.LocalService.LocalService.DTO.BookingResponseDto;
import com.LocalService.LocalService.DTO.VendorDashboardResponseDto;
import com.LocalService.LocalService.DTO.VendorIncomeDto;
import com.LocalService.LocalService.Entity.BookingStatus;

import java.util.List;

public interface BookingService {
    BookingResponseDto createBooking(BookingRequestDto requestDto);
    List<BookingResponseDto> getUserBooking( Long userId);
    List<BookingResponseDto> getAllBookings();
    BookingResponseDto updateBookingStatus(Long bookingId, String status);


    // Vendor Dashboard Methods
    List<VendorDashboardResponseDto> getPendingBookings(Long vendorId);
    List<VendorDashboardResponseDto> getAllBookingsByVendor(Long vendorId);
    void updateBookingStatusByVendor(Long bookingId, BookingStatus status);
    // for income
//    public VendorIncomeDto getVendorIncomeAnalytics(Long vendorId);

}
