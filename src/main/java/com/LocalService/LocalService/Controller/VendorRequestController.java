package com.LocalService.LocalService.Controller;

import com.LocalService.LocalService.DTO.VendorRequestDto;
import com.LocalService.LocalService.Entity.VendorRequest;
import com.LocalService.LocalService.Security.JwtUtil;
import com.LocalService.LocalService.Service.VendorRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-requests")

public class VendorRequestController {
    @Autowired
    private VendorRequestService requestService;


    @PostMapping("/create")
    public ResponseEntity<VendorRequest> createRequest(@RequestBody VendorRequestDto dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authorities: " + auth.getAuthorities());
        return ResponseEntity.ok(requestService.create(dto));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<VendorRequest>> getPending() {
        return ResponseEntity.ok(requestService.pending());
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<VendorRequest> approve(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.approveRequest(id));
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<VendorRequest> reject(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.rejectRequest(id));
    }

}
