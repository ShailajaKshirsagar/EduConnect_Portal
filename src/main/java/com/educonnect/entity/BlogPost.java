package com.educonnect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "post_id")
    private long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    private Instant createdAt;

    private Instant updatedAt;

    @PrePersist
    public  void oncreate(){
        this.createdAt=Instant.now();
    }

    @PreUpdate
    public void onupdate(){
        this.updatedAt=Instant.now();
    }
}
