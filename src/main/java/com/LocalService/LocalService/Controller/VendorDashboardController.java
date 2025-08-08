package com.LocalService.LocalService.Controller;

import com.LocalService.LocalService.DTO.VendorDashboardResponseDto;
import com.LocalService.LocalService.DTO.VendorIncomeDto;
import com.LocalService.LocalService.DTO.VendorServiceDto;
import com.LocalService.LocalService.DTO.VendorServiceResponseDto;
import com.LocalService.LocalService.Entity.BookingStatus;
import com.LocalService.LocalService.Service.BookingService;
import com.LocalService.LocalService.Service.VendorServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor/dashboard")
public class VendorDashboardController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private VendorServiceService serviceService;

    @PostMapping("/add")
    public ResponseEntity<VendorServiceResponseDto> addService(@Valid @RequestBody VendorServiceDto dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Logged in as: " + auth.getName());
        System.out.println("Authorities: " + auth.getAuthorities());
        VendorServiceResponseDto response = serviceService.addService(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pending-booking/{vendorId}")
    public ResponseEntity<List<VendorDashboardResponseDto>> getPendingBookings(@PathVariable Long vendorId) {
        return ResponseEntity.ok(bookingService.getPendingBookings(vendorId));
    }

    @GetMapping("/all-booking/{vendorId}")
    public ResponseEntity<List<VendorDashboardResponseDto>> getAllBookings(@PathVariable Long vendorId) {
        return ResponseEntity.ok(bookingService.getAllBookingsByVendor(vendorId));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<String> updateBookingStatus(@PathVariable Long bookingId,
                                                      @RequestParam BookingStatus status) {
        bookingService.updateBookingStatusByVendor(bookingId, status);
        return ResponseEntity.ok("Booking status updated to " + status);
    }

    @GetMapping("/service/{vendorId}")
    public ResponseEntity<List<VendorServiceResponseDto>> getVendorServices(@PathVariable Long vendorId) {
        return ResponseEntity.ok(serviceService.getServicesByVendor(vendorId));
    }

//    @GetMapping("/dashboard/income/{vendorId}")
//    public ResponseEntity<VendorIncomeDto> getIncome(@PathVariable Long vendorId) {
//        return ResponseEntity.ok(bookingService.getVendorIncomeAnalytics(vendorId));
//    }



}
