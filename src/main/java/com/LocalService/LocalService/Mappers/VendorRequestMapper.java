package com.LocalService.LocalService.Mappers;

import com.LocalService.LocalService.DTO.VendorRequestDto;
import com.LocalService.LocalService.Entity.VendorRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendorRequestMapper {
    VendorRequest toEntity(VendorRequestDto dto,Long userId);
    VendorRequestDto toDto(VendorRequest entity);
}
