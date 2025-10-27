package com.educonnect.dto;

import lombok.*;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDto {
    private long comment_id;
    private String content;
    private String authorname;
    private long authorid;
    private Instant createdat;
}
