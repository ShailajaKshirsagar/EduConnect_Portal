package com.educonnect.serviceImpl;

import com.educonnect.entity.ActivityLogs;
import com.educonnect.entity.User;
import com.educonnect.repository.ActivityLogRepo;
import com.educonnect.repository.UserRepo;
import com.educonnect.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {

    @Autowired
    private ActivityLogRepo activityLogRepository;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<ActivityLogs> getLogs(Long userid, String username) {

        User currentUser = userRepo.findByUsername(username);
        String role = String.valueOf(currentUser.getRole());

        if (role != null &&
                (role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("ROLE_ADMIN"))) {
            if (userid != null) {
                return activityLogRepository.findByUserId(userid);
            } else {
                return activityLogRepository.findAll();
            }
        } else {
            return activityLogRepository.findByUserId(currentUser.getId());
        }
    }
}
