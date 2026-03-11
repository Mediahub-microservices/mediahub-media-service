package com.mediahub.media_service.mapper;

import com.mediahub.media_service.domain.entity.Media;
import com.mediahub.media_service.dto.request.MediaRequestDto;
import com.mediahub.media_service.dto.response.MediaResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MediaMapper {

    public Media toEntity(MediaRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return Media.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .releaseYear(dto.getReleaseYear())
                .duration(dto.getDuration())
                .genre(dto.getGenre())
                .category(dto.getCategory())
                .rating(dto.getRating())
                .build();
    }

    public MediaResponseDto toDto(Media media) {
        if (media == null) {
            return null;
        }
        return MediaResponseDto.builder()
                .id(media.getId())
                .title(media.getTitle())
                .description(media.getDescription())
                .releaseYear(media.getReleaseYear())
                .duration(media.getDuration())
                .genre(media.getGenre())
                .category(media.getCategory())
                .rating(media.getRating())
                .createdAt(media.getCreatedAt())
                .updatedAt(media.getUpdatedAt())
                .build();
    }
}
