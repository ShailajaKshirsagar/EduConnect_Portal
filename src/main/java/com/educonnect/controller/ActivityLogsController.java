package com.educonnect.controller;

import com.educonnect.entity.ActivityLogs;
import com.educonnect.service.ActivityLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
@Tag(name = "Activity Log Api",description = "API'S for Activity Logs")
public class ActivityLogsController
{
    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping("/getActivityLogs")
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT','FACULTY')")
    @SecurityRequirement(name = "basicAuth")
    @Operation(summary = "Get Activity Log for Authenticated user and Admin")
    public ResponseEntity<List<ActivityLogs>> getActivityLog(@RequestParam(required = false) Long userid, Authentication authentication){

        String username = authentication.getName();
        List<ActivityLogs> logs = activityLogService.getLogs(userid,username);
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }
}
