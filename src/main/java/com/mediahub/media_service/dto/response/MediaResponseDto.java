package com.mediahub.media_service.dto.response;

import com.mediahub.media_service.domain.enums.Category;
import com.mediahub.media_service.domain.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaResponseDto {

    private Long id;
    private String title;
    private String description;
    private Integer releaseYear;
    private Integer duration;
    private Genre genre;
    private Category category;
    private Double rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
