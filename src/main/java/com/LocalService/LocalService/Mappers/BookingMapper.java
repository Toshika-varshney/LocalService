package com.LocalService.LocalService.Mappers;

import com.LocalService.LocalService.DTO.BookingRequestDto;
import com.LocalService.LocalService.DTO.BookingResponseDto;
import com.LocalService.LocalService.Entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking toEntity(BookingRequestDto dto);

    @Mapping(target = "serviceName", ignore = true)
    @Mapping(target = "vendorName", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target="businessName" , ignore=true)
    BookingResponseDto toDto(Booking booking);
}
