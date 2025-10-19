package com.educonnect.dto;

import com.educonnect.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto
{
    private String username;

    private String password;

    private boolean active = true;

    @Enumerated(EnumType.STRING)
    private User.Role role;

}
