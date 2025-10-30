package com.educonnect.interceptor;

import com.educonnect.entity.ActivityLogs;
import com.educonnect.entity.User;
import com.educonnect.repository.ActivityLogRepo;
import com.educonnect.repository.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class ApiLoggingInterceptor implements HandlerInterceptor {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ActivityLogRepo activityLogRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        long startTime = (long) request.getAttribute("startTime");
        long duration = System.currentTimeMillis() - startTime;

        String username = "anonymous";
        Long userId = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() &&
                !"anonymousUser".equalsIgnoreCase(String.valueOf(authentication.getPrincipal()))) {

            username = authentication.getName();

            User user = userRepository.findByUsername(username);
            if (user != null) {
                userId = user.getId();
            }
        }

        ActivityLogs logs = ActivityLogs.builder()
                .userid(userId)
                .username(username)
                .endpoint(request.getRequestURI())
                .httpmethod(request.getMethod())
                .ipaddress(request.getRemoteAddr())
                .timestamp(Instant.now())
                .executiontime(duration + " ms")
                .build();

        activityLogRepository.save(logs);
    }
}
