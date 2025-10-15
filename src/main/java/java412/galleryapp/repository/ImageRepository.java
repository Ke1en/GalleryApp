package java412.galleryapp.repository;

import java412.galleryapp.entity.Image;
import java412.galleryapp.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
    @Query("SELECT t FROM images i JOIN i.tags t WHERE i.id = :imageId")
    Set<Tag> findTagsByImageId(@Param("imageId") UUID imageId);
}
