package java412.galleryapp.repository;

import java412.galleryapp.entity.Thumbnail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ThumbnailRepository extends JpaRepository<Thumbnail, UUID> {
    @Query("select t from thumbnails t where t.imageId in :imageIds")
    List<Thumbnail> findAllThumbnailsByImageId(List<UUID> imageId);
}
