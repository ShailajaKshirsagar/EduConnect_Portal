package com.educonnect.service;

import com.educonnect.entity.ActivityLogs;

import java.util.List;

public interface ActivityLogService {

    List<ActivityLogs> getLogs(Long userid, String username);
}
