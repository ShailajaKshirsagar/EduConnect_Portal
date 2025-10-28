package com.educonnect.service;

import com.educonnect.dto.UserRequestDto;

import java.nio.file.AccessDeniedException;

public interface CustomUserService {
    String saveUser(UserRequestDto userdto);

    String softDeleteUser(long id, String username) throws AccessDeniedException;
}
