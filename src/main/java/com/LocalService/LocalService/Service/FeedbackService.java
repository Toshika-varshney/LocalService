package com.LocalService.LocalService.Service;

import com.LocalService.LocalService.DTO.FeedbackRequestDto;
import com.LocalService.LocalService.DTO.FeedbackResponseDto;
import com.LocalService.LocalService.Entity.Feedback;

import java.util.List;

public interface FeedbackService {
    FeedbackResponseDto saveFeedback(FeedbackRequestDto feedback);
    List<FeedbackResponseDto> getFeedbackByUser(Long userId);
    List<FeedbackResponseDto> getFeedbackForService(Long serviceId);
}
