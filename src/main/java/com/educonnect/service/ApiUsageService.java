package com.educonnect.service;

import com.educonnect.dto.ApiUsageDto;
import com.educonnect.entity.User;

public interface ApiUsageService {

    void incrementUsage(User user);

    ApiUsageDto getUsage(Long userid,User currentuser);
}
