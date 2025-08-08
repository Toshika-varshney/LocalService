package com.LocalService.LocalService.Controller;

import com.LocalService.LocalService.DTO.VendorServiceDto;
import com.LocalService.LocalService.DTO.VendorServiceResponseDto;
import com.LocalService.LocalService.Service.VendorServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/vendor/services")
public class VendorServiceController {

    @Autowired
    private VendorServiceService serviceService;

    // ✅ 1. Add a new service by vendor
//    @PostMapping("/add")
//    public ResponseEntity<VendorServiceResponseDto> addService(@Valid @RequestBody VendorServiceDto dto) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("Logged in as: " + auth.getName());
//        System.out.println("Authorities: " + auth.getAuthorities());
//        VendorServiceResponseDto response = serviceService.addService(dto);
//        return ResponseEntity.ok(response);
//    }

    // ✅ 2. Get all available services (for users or admin)
    @GetMapping("/all")
    public ResponseEntity<List<VendorServiceResponseDto>> getAllServices() {
        return ResponseEntity.ok(serviceService.getAllServices());
    }

    // ✅ 3. Get services by a specific vendor
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<VendorServiceResponseDto>> getVendorServices(@PathVariable Long vendorId) {
        return ResponseEntity.ok(serviceService.getServicesByVendor(vendorId));
    }

    // ✅ 4. Get specific service by ID
    @GetMapping("/{serviceId}")
    public ResponseEntity<VendorServiceResponseDto> getServiceById(@PathVariable Long serviceId) {
        return ResponseEntity.ok(serviceService.getServiceById(serviceId));
    }
    // 5. get services category wise
    @GetMapping("/category-with-services")
    public ResponseEntity<Map<String, List<String>>> getCategoryWithServiceNames() {
        Map<String, List<String>> response = serviceService.getCategoryWithServiceNames();
        return ResponseEntity.ok(response);
    }
    // get all vendor providing similar service
    @GetMapping("/by-service-name/{serviceName}")
    public ResponseEntity<List<VendorServiceResponseDto>> getByServiceName(@PathVariable String serviceName) {
        List<VendorServiceResponseDto> list = serviceService.getVendorsByServiceName(serviceName);
        return ResponseEntity.ok(list);
    }
}
