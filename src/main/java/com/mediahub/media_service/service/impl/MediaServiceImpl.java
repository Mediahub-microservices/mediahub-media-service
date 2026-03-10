package com.mediahub.media_service.service.impl;

import com.mediahub.media_service.domain.entity.Media;
import com.mediahub.media_service.domain.enums.Genre;
import com.mediahub.media_service.dto.request.MediaRequestDto;
import com.mediahub.media_service.dto.response.MediaResponseDto;
import com.mediahub.media_service.exception.DuplicateResourceException;
import com.mediahub.media_service.exception.ResourceNotFoundException;
import com.mediahub.media_service.repository.MediaRepository;
import com.mediahub.media_service.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    @Override
    @Transactional
    public MediaResponseDto addMedia(MediaRequestDto dto) {
        if (mediaRepository.existsByTitleIgnoreCase(dto.getTitle())) {
            throw new DuplicateResourceException("Un média avec ce titre existe déjà");
        }
        Media media = toEntity(dto);
        media = mediaRepository.save(media);
        return toDto(media);
    }

    @Override
    public MediaResponseDto getMediaById(Long id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Média introuvable avec l'id : " + id));
        return toDto(media);
    }

    @Override
    public Page<MediaResponseDto> getAllMedia(Pageable pageable) {
        return mediaRepository.findAll(pageable).map(this::toDto);
    }

    @Override
    @Transactional
    public MediaResponseDto updateMedia(Long id, MediaRequestDto dto) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Média introuvable avec l'id : " + id));
        if (mediaRepository.existsByTitleIgnoreCaseAndIdNot(dto.getTitle(), id)) {
            throw new DuplicateResourceException("Un média avec ce titre existe déjà");
        }
        media.setTitle(dto.getTitle());
        media.setDescription(dto.getDescription());
        media.setReleaseYear(dto.getReleaseYear());
        media.setDuration(dto.getDuration());
        media.setGenre(dto.getGenre());
        media.setCategory(dto.getCategory());
        media.setRating(dto.getRating());
        media = mediaRepository.save(media);
        return toDto(media);
    }

    @Override
    @Transactional
    public void deleteMedia(Long id) {
        if (!mediaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Média introuvable avec l'id : " + id);
        }
        mediaRepository.deleteById(id);
    }

    @Override
    public List<MediaResponseDto> searchMedia(String title, Genre genre) {
        boolean hasTitle = title != null && !title.isBlank();
        boolean hasGenre = genre != null;

        if (hasTitle && hasGenre) {
            return mediaRepository.findByTitleContainingIgnoreCaseAndGenre(title, genre).stream()
                    .map(this::toDto)
                    .toList();
        }
        if (hasTitle) {
            return mediaRepository.findByTitleContainingIgnoreCase(title).stream()
                    .map(this::toDto)
                    .toList();
        }
        if (hasGenre) {
            return mediaRepository.findByGenre(genre).stream()
                    .map(this::toDto)
                    .toList();
        }
        return mediaRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    private Media toEntity(MediaRequestDto dto) {
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

    private MediaResponseDto toDto(Media media) {
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
