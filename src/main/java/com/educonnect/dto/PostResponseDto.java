package com.educonnect.dto;

import lombok.*;

import java.time.Instant;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {

    private long id;
    private String title;
    private String content;
    private String authorname;
    private long authorid;
    private Instant createdAt;
    private Instant updatedAt;
}
