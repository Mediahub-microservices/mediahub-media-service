package com.mediahub.media_service.service;

import com.mediahub.media_service.domain.enums.Genre;
import com.mediahub.media_service.dto.request.MediaRequestDto;
import com.mediahub.media_service.dto.response.MediaResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MediaService {

    MediaResponseDto addMedia(MediaRequestDto dto);

    MediaResponseDto getMediaById(Long id);

    Page<MediaResponseDto> getAllMedia(Pageable pageable);

    MediaResponseDto updateMedia(Long id, MediaRequestDto dto);

    void deleteMedia(Long id);

    List<MediaResponseDto> searchMedia(String title, Genre genre);
}
