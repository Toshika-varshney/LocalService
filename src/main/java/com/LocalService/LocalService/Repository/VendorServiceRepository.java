package com.LocalService.LocalService.Repository;

import com.LocalService.LocalService.Entity.VendorService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorServiceRepository extends JpaRepository<VendorService, Long> {
    List<VendorService> findByVendorId(Long vendorId);
    List<VendorService> findByServiceName(String serviceName);


}

