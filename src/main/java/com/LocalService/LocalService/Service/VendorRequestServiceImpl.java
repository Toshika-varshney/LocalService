package com.LocalService.LocalService.Service;

import com.LocalService.LocalService.DTO.VendorRequestDto;
import com.LocalService.LocalService.Entity.Role;
import com.LocalService.LocalService.Entity.Status;
import com.LocalService.LocalService.Entity.User;
import com.LocalService.LocalService.Entity.VendorRequest;
import com.LocalService.LocalService.Exceptions.ResourceNotFoundException;
import com.LocalService.LocalService.Mappers.VendorRequestMapper;
import com.LocalService.LocalService.Repository.UserRepository;
import com.LocalService.LocalService.Repository.VendorRequestRepository;
import com.LocalService.LocalService.Security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.LocalService.LocalService.Util.CookieUtil.extractTokenFromCookies;

@Service
public class VendorRequestServiceImpl implements VendorRequestService {

    @Autowired
    private VendorRequestRepository requestRepository;

    @Autowired
    private VendorRequestMapper vendorRequestMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest httpServletRequest;


    @Override
    public List<VendorRequest> pending() {
        return requestRepository.findByRequestStatus(Status.PENDING);

    }

    @Override
    public VendorRequest create(VendorRequestDto vendorRequestDto) {

        String token = extractTokenFromCookies( httpServletRequest);

        Long userId= jwtUtil.extractUserId(token);

        VendorRequest request = vendorRequestMapper.toEntity(vendorRequestDto,userId);
        request.setUserId(userId);
        request.setRequestStatus(Status.PENDING);
        request.setRequestedAt(LocalDateTime.now());
        return requestRepository.save(request);
    }

    @Override
    public VendorRequest approveRequest(Long requestId) {
        VendorRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found"));
        request.setRequestStatus(Status.APPROVED);
        requestRepository.save(request);


        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setRole(Role.VENDOR);
        userRepository.save(user);

        return request;
    }

    @Override
    public VendorRequest rejectRequest(Long requestId) {
        VendorRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found"));
        request.setRequestStatus(Status.REJECTED);
        return requestRepository.save(request);
    }

    @Override
    public VendorRequest findByUserId(Long UserId) {
        return requestRepository.findByUserId(UserId);
    }

    //
//    private String extractTokenfromHeader(){
//        String authHeader = httpServletRequest.getHeader("authorization");
//        if(authHeader !=null&& authHeader.startsWith("Bearer")){
//            return authHeader.substring(7);
//        }
//        throw new RuntimeException("JWT token is missing");
//    }
    }

