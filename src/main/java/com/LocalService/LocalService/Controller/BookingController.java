package com.LocalService.LocalService.Controller;

import com.LocalService.LocalService.DTO.BookingRequestDto;
import com.LocalService.LocalService.DTO.BookingResponseDto;
import com.LocalService.LocalService.Service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    //  1.  user Create a new booking for selected service
    @PostMapping("/create")
    public ResponseEntity<BookingResponseDto> createBooking(@Valid @RequestBody BookingRequestDto dto) {
        BookingResponseDto response = bookingService.createBooking(dto);
        return ResponseEntity.ok(response);
    }

    //  2. Get all bookings of a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDto>> getUserBookings(@PathVariable Long userId) {
        List<BookingResponseDto> bookings = bookingService.getUserBooking(userId);
        return ResponseEntity.ok(bookings);
    }

    // 3. Get all bookings (for admin)
    @GetMapping("/all")
    public ResponseEntity<List<BookingResponseDto>> getAllBookings() {
        List<BookingResponseDto> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    // 4. Update booking status by vendor via vendordashboard
    @PutMapping("/{id}/status")
    public ResponseEntity<BookingResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        BookingResponseDto updated = bookingService.updateBookingStatus(id, status);
        return ResponseEntity.ok(updated);
    }
}

