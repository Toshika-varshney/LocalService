package com.LocalService.LocalService.Repository;

import com.LocalService.LocalService.Entity.Status;
import com.LocalService.LocalService.Entity.VendorRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VendorRequestRepository extends JpaRepository<VendorRequest,Long> {
    List<VendorRequest> findByRequestStatus(Status status);
    VendorRequest findByUserId(Long UserId);
    Optional<VendorRequest> findByUserIdAndRequestStatus(Long userId, Status requestStatus);
}
