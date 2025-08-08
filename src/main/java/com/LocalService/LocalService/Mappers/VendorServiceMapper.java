package com.LocalService.LocalService.Mappers;


import com.LocalService.LocalService.DTO.VendorServiceDto;
import com.LocalService.LocalService.DTO.VendorServiceResponseDto;
import com.LocalService.LocalService.Entity.VendorService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface VendorServiceMapper {
    VendorService toEntity(VendorServiceDto dto, Long UserId, Long vendorId );

    @Mapping(target = "vendorName", ignore = true)
    @Mapping(target = "contactNumber", ignore = true)
    @Mapping(target = "city", ignore = true)
    VendorServiceResponseDto toDto(VendorService service);
}

