package com.LocalService.LocalService.Service;


import com.LocalService.LocalService.DTO.VendorServiceDto;
import com.LocalService.LocalService.DTO.VendorServiceResponseDto;

import java.util.List;
import java.util.Map;

public interface VendorServiceService {
    VendorServiceResponseDto addService(VendorServiceDto dto);

    List<VendorServiceResponseDto> getAllServices();
    List<VendorServiceResponseDto> getServicesByVendor(Long vendorId);
    VendorServiceResponseDto getServiceById(Long id);
    Map<String, List<String>> getCategoryWithServiceNames();
    List<VendorServiceResponseDto> getVendorsByServiceName(String serviceName);
}
