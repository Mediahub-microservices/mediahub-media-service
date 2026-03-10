package com.mediahub.media_service.controller;

import com.mediahub.media_service.domain.enums.Genre;
import com.mediahub.media_service.dto.ApiResponse;
import com.mediahub.media_service.dto.request.MediaRequestDto;
import com.mediahub.media_service.dto.response.MediaResponseDto;
import com.mediahub.media_service.service.MediaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping
    public ResponseEntity<ApiResponse<MediaResponseDto>> createMedia(
            @Valid @RequestBody MediaRequestDto dto) {
        MediaResponseDto result = mediaService.addMedia(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(result, "Média créé avec succès"));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<MediaResponseDto>>> searchMedia(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Genre genre) {
        List<MediaResponseDto> results = mediaService.searchMedia(title, genre);
        return ResponseEntity.ok(
                ApiResponse.success(results, "Résultats de la recherche"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MediaResponseDto>> getMediaById(
            @PathVariable Long id) {
        MediaResponseDto result = mediaService.getMediaById(id);
        return ResponseEntity.ok(
                ApiResponse.success(result, "Média récupéré avec succès"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<MediaResponseDto>>> getAllMedia(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<MediaResponseDto> result = mediaService.getAllMedia(pageable);
        return ResponseEntity.ok(
                ApiResponse.success(result, "Liste des médias récupérée avec succès"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MediaResponseDto>> updateMedia(
            @PathVariable Long id,
            @Valid @RequestBody MediaRequestDto dto) {
        MediaResponseDto result = mediaService.updateMedia(id, dto);
        return ResponseEntity.ok(
                ApiResponse.success(result, "Média mis à jour avec succès"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMedia(
            @PathVariable Long id) {
        mediaService.deleteMedia(id);
        return ResponseEntity.ok(
                ApiResponse.success(null, "Média supprimé avec succès"));
    }
}
