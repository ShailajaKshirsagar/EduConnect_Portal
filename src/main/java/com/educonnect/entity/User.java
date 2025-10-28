package com.educonnect.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

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

    //for soft delete
    private boolean active = true;

    //for delete time
    private Instant deletedat;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String profilephotopath;

    public enum Role{
        STUDENT,
        FACULTY,
        ADMIN,
        HR
    }

}
