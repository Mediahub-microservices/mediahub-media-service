package com.mediahub.media_service.domain.entity;

import com.mediahub.media_service.domain.enums.Category;
import com.mediahub.media_service.domain.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "medias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Size(min = 10)
    @Column(nullable = false, length = 2000)
    private String description;

    private Integer releaseYear;

    private Integer duration;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private Double rating;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
