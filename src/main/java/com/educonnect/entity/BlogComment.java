package com.educonnect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long comment_id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user-id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private BlogPost post;

    private Instant createdat;

    @PrePersist
    public void oncreate() {
        this.createdat = Instant.now();
    }
}
