package com.educonnect.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    private String username;

    private String password;

    private boolean active = true;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role{
        STUDENT,
        FACULTY,
        ADMIN,
        HR
    }

}
