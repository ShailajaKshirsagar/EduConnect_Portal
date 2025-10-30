package com.educonnect.controller;

import com.educonnect.dto.ApiUsageDto;
import com.educonnect.entity.User;
import com.educonnect.repository.UserRepo;
import com.educonnect.service.ApiUsageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiUsage")
@Tag(name = "Api Usage")
public class ApiUsageController {

    @Autowired
    private ApiUsageService apiUsageService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/getApiUsageCount/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT', 'FACULTY')")
    @SecurityRequirement(name = "basicAuth")
    public ResponseEntity<ApiUsageDto> getUsage(
            @PathVariable Long userId,
            Authentication authentication) {

        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        String username = authentication.getName();
        User currentUser = userRepo.findByUsername(username);

        ApiUsageDto usage = apiUsageService.getUsage(userId, currentUser);
        return ResponseEntity.ok(usage);
    }
}
