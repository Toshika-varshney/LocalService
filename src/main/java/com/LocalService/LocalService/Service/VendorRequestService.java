package com.LocalService.LocalService.Service;

import com.LocalService.LocalService.DTO.VendorRequestDto;
import com.LocalService.LocalService.Entity.VendorRequest;

import java.util.List;

public interface VendorRequestService {
    List<VendorRequest> pending();
    VendorRequest create(VendorRequestDto vendorRequestDto);
    VendorRequest approveRequest(Long requestId);
    VendorRequest rejectRequest(Long requestId);
    VendorRequest findByUserId(Long UserId);
}