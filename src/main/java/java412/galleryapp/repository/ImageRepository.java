package java412.galleryapp.repository;

import java412.galleryapp.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
    @Query("SELECT i FROM images i JOIN i.tags t WHERE t.name IN :tagNames GROUP BY i.id HAVING COUNT(t.id) = :tagCount")
    List<Image> findAllByTags(@Param("tagNames") Set<String> tagNames, @Param("tagCount") long tagCount);
}
