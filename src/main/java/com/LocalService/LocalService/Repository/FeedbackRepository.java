package com.LocalService.LocalService.Repository;

import com.LocalService.LocalService.DTO.FeedbackResponseDto;
import com.LocalService.LocalService.Entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    List<Feedback> findByServiceId(Long serviceId);
    List<Feedback> findByUserId(Long userId);

}
