package com.educonnect.serviceImpl;

import com.educonnect.dto.ApiUsageDto;
import com.educonnect.entity.ApiUsage;
import com.educonnect.entity.User;
import com.educonnect.repository.ApiUsageRepo;
import com.educonnect.repository.UserRepo;
import com.educonnect.service.ApiUsageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ApiUsageServiceImpl implements ApiUsageService {

    @Autowired
    private ApiUsageRepo apiUsageRepository;

    @Autowired
    private UserRepo userRepository;

    @Override
    @Transactional
    public void incrementUsage(User user) {

        LocalDate today = LocalDate.now();

        ApiUsage usage = apiUsageRepository.findByUserAndUsageDate(user,today).orElse(
                ApiUsage.builder()
                        .user(user)
                        .usagedate(today)
                        .apirequestcount(0L)
                        .build());
        usage.setApirequestcount(usage.getApirequestcount()+1);
        apiUsageRepository.save(usage);
    }

    @Override
    public ApiUsageDto getUsage(Long userid, User currentuser) {

        Long targetUserId;

        if(currentuser.getRole() == User.Role.ADMIN){
            targetUserId = (currentuser!=null)? userid : currentuser.getId();
        }else{
            targetUserId = currentuser.getId();
        }

        User targetuser = userRepository.findById(targetUserId).orElseThrow(()-> new RuntimeException("User not found"));
        LocalDate today = LocalDate.now();

        ApiUsage usage = apiUsageRepository.findByUserAndUsageDate(targetuser,today).orElse(
                ApiUsage.builder()
                        .user(targetuser)
                        .usagedate(today)
                        .apirequestcount(0L).build());

        return new ApiUsageDto(targetuser.getId(),targetuser.getUsername(), usage.getApirequestcount(), usage.getUsagedate());
    }
}
