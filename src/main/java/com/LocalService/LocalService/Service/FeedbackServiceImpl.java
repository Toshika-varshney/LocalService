package com.LocalService.LocalService.Service;

import com.LocalService.LocalService.DTO.FeedbackRequestDto;
import com.LocalService.LocalService.DTO.FeedbackResponseDto;
import com.LocalService.LocalService.Entity.Booking;
import com.LocalService.LocalService.Entity.Feedback;
import com.LocalService.LocalService.Exceptions.ResourceNotFoundException;
import com.LocalService.LocalService.Mappers.FeedbackMapper;
import com.LocalService.LocalService.Repository.BookingRepository;
import com.LocalService.LocalService.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public FeedbackResponseDto saveFeedback(FeedbackRequestDto feedbackRequestDto) {
        Booking booking = bookingRepository.findById(feedbackRequestDto.getBookingId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));


        Feedback feedback = feedbackMapper.toEntity(feedbackRequestDto);
        feedback.setUserId(booking.getUserId());
        feedback.setServiceId(booking.getVendorId());
        feedback.setCreatedAt(LocalDateTime.now());

        Feedback saved = feedbackRepository.save(feedback);
        return feedbackMapper.toDto(saved);
    }

    @Override
    public List<FeedbackResponseDto> getFeedbackByUser(Long userId) {
        return feedbackRepository.findByUserId(userId)
                .stream()
                .map(feedbackMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackResponseDto> getFeedbackForService(Long serviceId) {
        return feedbackRepository.findByServiceId(serviceId)
                .stream()
                .map(feedbackMapper::toDto)
                .collect(Collectors.toList());
    }
}
