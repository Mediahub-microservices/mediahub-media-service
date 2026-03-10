package com.mediahub.media_service.dto.request;

import com.mediahub.media_service.domain.enums.Category;
import com.mediahub.media_service.domain.enums.Genre;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaRequestDto {

    @NotBlank(message = "Le titre est obligatoire")
    private String title;

    @NotBlank(message = "La description est obligatoire")
    @Size(min = 10, message = "La description doit faire au moins 10 caractères")
    private String description;

    @Min(value = 1900, message = "L'année de sortie ne peut pas être antérieure à 1900")
    private Integer releaseYear;

    @Positive(message = "La durée doit être positive")
    private Integer duration;

    @NotNull(message = "Le genre est obligatoire")
    private Genre genre;

    @NotNull(message = "La catégorie est obligatoire")
    private Category category;

    @DecimalMin(value = "0.0", message = "La note doit être au moins 0")
    @DecimalMax(value = "10.0", message = "La note ne peut pas dépasser 10")
    private Double rating;
}
