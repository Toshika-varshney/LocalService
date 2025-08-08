package com.LocalService.LocalService.Mappers;

import com.LocalService.LocalService.DTO.FeedbackRequestDto;
import com.LocalService.LocalService.DTO.FeedbackResponseDto;
import com.LocalService.LocalService.Entity.Feedback;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface FeedbackMapper {
    Feedback toEntity(FeedbackRequestDto dto);

    FeedbackResponseDto toDto(Feedback feedback);

}
