package com.educonnect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ApiUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long apiusageid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    private long apirequestcount;

    private LocalDate usagedate;

    private Instant createdat;

    private Instant updatedat;

    @PrePersist
    public void prePersist() {
        createdat = Instant.now();
        updatedat = createdat;
    }

    @PreUpdate
    public void preUpdate() {
        updatedat = Instant.now();
    }
}
