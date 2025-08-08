package com.LocalService.LocalService.Controller;



import com.LocalService.LocalService.Entity.User;
import com.LocalService.LocalService.Entity.VendorRequest;
import com.LocalService.LocalService.Service.UserService;
import com.LocalService.LocalService.Service.VendorRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private VendorRequestService vendorRequestService;

    // Block a user
    @PutMapping("/block/{userId}")
    public ResponseEntity<String> blockUser(@PathVariable Long userId) {
        userService.blockUser(userId);
        return ResponseEntity.ok("User blocked successfully.");
    }

    // Delete a user
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User marked as deleted.");
    }

    // Get all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get all vendors
    @GetMapping("/vendors")
    public ResponseEntity<List<User>> getAllVendors() {
        return ResponseEntity.ok(userService.getAllVendors());
    }

    // Get pending vendor requests
    @GetMapping("/vendor-requests/pending")
    public ResponseEntity<List<VendorRequest>> getPendingVendorRequests() {
        return ResponseEntity.ok(vendorRequestService.pending());
    }

    // Approve a vendor request
    @PutMapping("/approve/{requestId}")
    public ResponseEntity<VendorRequest> approveVendorRequest(@PathVariable Long requestId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Logged in as: " + auth.getName());
        System.out.println("Authorities: " + auth.getAuthorities());
        return ResponseEntity.ok(vendorRequestService.approveRequest(requestId));
    }

    // Reject a vendor request
    @PutMapping("/reject/{requestId}")
    public ResponseEntity<VendorRequest> rejectVendorRequest(@PathVariable Long requestId) {
        return ResponseEntity.ok(vendorRequestService.rejectRequest(requestId));
    }
}
