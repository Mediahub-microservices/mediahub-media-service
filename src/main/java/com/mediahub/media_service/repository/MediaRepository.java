package com.mediahub.media_service.repository;

import com.mediahub.media_service.domain.entity.Media;
import com.mediahub.media_service.domain.enums.Category;
import com.mediahub.media_service.domain.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {

    boolean existsByTitleIgnoreCase(String title);

    boolean existsByTitleIgnoreCaseAndIdNot(String title, Long id);

    List<Media> findByGenre(Genre genre);

    List<Media> findByTitleContainingIgnoreCase(String title);

    List<Media> findByTitleContainingIgnoreCaseAndGenre(String title, Genre genre);

    List<Media> findByCategory(Category category);

    List<Media> findByTitleContainingIgnoreCaseAndCategory(String title, Category category);
}
