package com.LocalService.LocalService.Service;

import com.LocalService.LocalService.DTO.BookingRequestDto;
import com.LocalService.LocalService.DTO.BookingResponseDto;
import com.LocalService.LocalService.DTO.VendorDashboardResponseDto;
import com.LocalService.LocalService.DTO.VendorIncomeDto;
import com.LocalService.LocalService.Entity.Booking;
import com.LocalService.LocalService.Entity.BookingStatus;
import com.LocalService.LocalService.Entity.VendorService;
import com.LocalService.LocalService.Entity.User;
import com.LocalService.LocalService.Exceptions.ResourceNotFoundException;
import com.LocalService.LocalService.Mappers.BookingMapper;
import com.LocalService.LocalService.Repository.BookingRepository;

import com.LocalService.LocalService.Repository.UserRepository;
import com.LocalService.LocalService.Repository.VendorServiceRepository;
import com.LocalService.LocalService.Security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VendorServiceRepository serviceRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Override
    public BookingResponseDto createBooking(BookingRequestDto requestDto) {

        String token=extractTokenFromHeader();
        Long userId= jwtUtil.extractUserId(token);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        VendorService service = serviceRepository.findById(requestDto.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));



        Booking booking= bookingMapper.toEntity(requestDto);
        booking.setStatus(BookingStatus.REQUESTED);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUserId(userId);
        booking.setVendorId(service.getVendorId());
        Booking saved=bookingRepository.save(booking);

        return mapToDtoWithDetails(saved);
    }

    @Override
    public List<BookingResponseDto> getUserBooking(Long userId) {
        return bookingRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDtoWithDetails)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingResponseDto> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::mapToDtoWithDetails)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponseDto updateBookingStatus(Long bookingId, String status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setStatus(BookingStatus.valueOf(status.toUpperCase()));
        Booking updated = bookingRepository.save(booking);

        return mapToDtoWithDetails(updated);
    }
    // ye manually kr rh hum kuki bas kuchh hi chiz chahiye table mapping m sab kuchh faltu ka uth kr aa jaat
    // or clean and clear code h sai h bhondu ram toshi
    private BookingResponseDto mapToDtoWithDetails(Booking booking) {
        BookingResponseDto dto = bookingMapper.toDto(booking);

        VendorService service = serviceRepository.findById(booking.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        User vendor = userRepository.findById(service.getVendorId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));

        dto.setServiceName(service.getServiceName());
        dto.setCity(service.getCity());
        dto.setVendorName(vendor.getName());

        return dto;
    }

    // Vendor Dashboard Methods

    @Override
    public List<VendorDashboardResponseDto> getPendingBookings(Long vendorId) {
        List<Booking> bookings = bookingRepository.findByVendorIdAndStatus(vendorId, BookingStatus.REQUESTED);
        return mapToVendorDtos(bookings);
    }

    @Override
    public List<VendorDashboardResponseDto> getAllBookingsByVendor(Long vendorId) {
        List<Booking> bookings = bookingRepository.findByVendorId(vendorId);
        return mapToVendorDtos(bookings);
    }

    @Override
    public void updateBookingStatusByVendor(Long bookingId, BookingStatus status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        booking.setStatus(status);
        bookingRepository.save(booking);
    }

    private List<VendorDashboardResponseDto> mapToVendorDtos(List<Booking> bookings) {
        return bookings.stream().map(booking -> {

            System.out.println("Looking for User ID: " + booking.getUserId());
            System.out.println("Looking for Service ID: " + booking.getServiceId());


            User user = userRepository.findById(booking.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            VendorService service = serviceRepository.findById(booking.getServiceId())
                    .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

            return VendorDashboardResponseDto.builder()
                    .bookingId(booking.getId())
                    .userName(user.getName())
                    .userContact(user.getPhone())
                    .serviceName(service.getServiceName())
                    .bookingDate(booking.getBookingDate())
                    .bookingTime(booking.getBookingTiming())
                    .bookingAddress(booking.getBookingAddress())
                    .status(booking.getStatus().toString())
                    .build();
        }).collect(Collectors.toList());
    }

    private String extractTokenFromHeader(){
        String authHeader= httpServletRequest.getHeader("Authorization");
        if(authHeader== null || !authHeader.startsWith("Bearer")){
            throw new RuntimeException("Missing or invalid Authorization header ");
        }
        return authHeader.substring(7);
    }
    //for income
//    public VendorIncomeDto getVendorIncomeAnalytics(Long vendorId) {
//        Double total = bookingRepository.getTotalIncome(vendorId, BookingStatus.COMPLETED);
//        Double projected = bookingRepository.getUpcomingIncome(vendorId, BookingStatus.APPROVED);
//        List<Object[]> monthly = bookingRepository.getMonthlyIncome(vendorId, BookingStatus.COMPLETED);
//
//
//
//
//        Map<String, Double> monthlyIncome = new LinkedHashMap<>();
//        for (Object[] row : monthly) {
//            Integer month = (Integer) row[0];
//            Double amount = (Double) row[1];
//            String monthName = Month.of(month).getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
//            monthlyIncome.put(monthName, amount);
//        }
//
//        return VendorIncomeDto.builder()
//                .totalIncome(total != null ? total : 0.0)
//                .projectedIncome(projected != null ? projected : 0.0)
//                .monthlyIncome(monthlyIncome)
//                .build();
//    }
//


}
