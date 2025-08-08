package com.LocalService.LocalService.Service;


import com.LocalService.LocalService.DTO.VendorServiceDto;
import com.LocalService.LocalService.DTO.VendorServiceResponseDto;
import com.LocalService.LocalService.Entity.Status;
import com.LocalService.LocalService.Entity.VendorRequest;
import com.LocalService.LocalService.Entity.VendorService;
import com.LocalService.LocalService.Entity.User;
import com.LocalService.LocalService.Exceptions.ResourceNotFoundException;
import com.LocalService.LocalService.Mappers.VendorServiceMapper;
import com.LocalService.LocalService.Repository.UserRepository;
import com.LocalService.LocalService.Repository.VendorRequestRepository;
import com.LocalService.LocalService.Repository.VendorServiceRepository;
import com.LocalService.LocalService.Security.JwtUtil;
import com.LocalService.LocalService.Util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.LocalService.LocalService.Util.CookieUtil.extractTokenFromCookies;

@Service
public class VendorServiceServiceImpl implements VendorServiceService {

    @Autowired
    private VendorServiceRepository serviceRepository;

    @Autowired
    private VendorServiceMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest httpServletRequest;



    @Autowired
    private VendorRequestRepository vendorRequestRepository;
    @Override
    public VendorServiceResponseDto addService(VendorServiceDto dto) {

        String token =extractTokenFromCookies(httpServletRequest);
        System.out.println("Token extracted from cookie: " + token);

        if(token == null) {
            throw new IllegalArgumentException("Token not found in cookie");
        }
        Long UserId= jwtUtil.extractUserId(token);
        System.out.println("Extracted userId from token: " + UserId);
        VendorRequest vendor=vendorRequestRepository.findByUserIdAndRequestStatus(UserId,Status.APPROVED)
                .orElseThrow(()-> new ResourceNotFoundException("Approved vendor not found for this user"));
        Long vendorId = vendor.getId();
        User user=userRepository.findById(UserId)
                .orElseThrow(()->new ResourceNotFoundException("vendor not found"));

        VendorService entity = mapper.toEntity(dto,UserId,vendorId);
        entity.setVendorId(vendorId);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setContactNumber(user.getPhone());
        entity.setCity(user.getCity());
        entity.setVendorName(user.getName());
        VendorService saved = serviceRepository.save(entity);

        VendorServiceResponseDto response = mapper.toDto(saved);
        response.setVendorName(user.getName());
        response.setContactNumber(user.getPhone());
        response.setCity(user.getCity());

        return response;
    }

    @Override
    public List<VendorServiceResponseDto> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(this::mapWithVendorDetails)
                .collect(Collectors.toList());
    }

    @Override
    public List<VendorServiceResponseDto> getServicesByVendor(Long vendorId) {
        return serviceRepository.findByVendorId(vendorId).stream()
                .map(this::mapWithVendorDetails)
                .collect(Collectors.toList());
    }

    @Override
    public VendorServiceResponseDto getServiceById(Long id) {
        VendorService service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));
        return mapWithVendorDetails(service);
    }

    @Override

    public Map<String, List<String>> getCategoryWithServiceNames() {
        List<VendorService> services = serviceRepository.findAll();

        return services.stream()
                .collect(Collectors.groupingBy(
                        VendorService::getCategory,
                        Collectors.mapping(VendorService::getServiceName, Collectors.toSet())  // Unique service names
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream().toList()
                ));
    }

    @Override
    public List<VendorServiceResponseDto> getVendorsByServiceName(String serviceName) {
        List<VendorService> matchingServices = serviceRepository.findByServiceName(serviceName);

        return matchingServices.stream()
                .map(this::mapWithVendorDetails) // Contact number, city, vendor name set karega
                .collect(Collectors.toList());
    }


    private VendorServiceResponseDto mapWithVendorDetails(VendorService service) {
        VendorServiceResponseDto dto = mapper.toDto(service);
        User vendor = userRepository.findById(service.getVendorId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
        dto.setVendorName(vendor.getName());
        dto.setContactNumber(vendor.getPhone());
        dto.setCity(vendor.getCity());
        return dto;
    }

//    private String extractTokenFromHeader(){
//        String header = httpServletRequest.getHeader("Authorization");
//        if(header== null || !header.startsWith("Bearer")){
//            throw new IllegalArgumentException("Missing or invalid Authorization header");
//        }
//        return header.substring(7);
//
//    }
}
