package com.LocalService.LocalService.Controller;

import com.LocalService.LocalService.DTO.FeedbackRequestDto;
import com.LocalService.LocalService.DTO.FeedbackResponseDto;
import com.LocalService.LocalService.Service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/submit")
    public ResponseEntity<FeedbackResponseDto> submitFeedback(@Valid @RequestBody FeedbackRequestDto dto){
        return ResponseEntity.ok(feedbackService.saveFeedback(dto));
    }
    @GetMapping("service/{serviceId}")
    public ResponseEntity<List<FeedbackResponseDto>> allFeedbackForService(@PathVariable Long serviceId){
        return ResponseEntity.ok(feedbackService.getFeedbackForService(serviceId));
    }
    @GetMapping("user/{userId} ")
    public ResponseEntity<List<FeedbackResponseDto>> allFeedbackByUser(@PathVariable Long userId){
        return ResponseEntity.ok(feedbackService.getFeedbackByUser(userId));
    }

}
