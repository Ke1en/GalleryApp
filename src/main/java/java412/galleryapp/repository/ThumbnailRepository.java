package java412.galleryapp.repository;

import java412.galleryapp.entity.Thumbnail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ThumbnailRepository extends JpaRepository<Thumbnail, UUID> {
    @Query(value = "SELECT t.* FROM thumbnails t WHERE t.image_id IN (" +
            "SELECT i.id FROM images i " +
            "JOIN image_tags it ON i.id = it.image_id " +
            "JOIN tags tg ON it.tag_id = tg.id " +
            "WHERE tg.name IN :tagNames " +
            "GROUP BY i.id " +
            "HAVING COUNT(DISTINCT tg.id) = :tagCount" +
            ")", nativeQuery = true)
    List<Thumbnail> findThumbnailsByTags(@Param("tagNames") List<String> tagNames, @Param("tagCount") int tagCount);
}
