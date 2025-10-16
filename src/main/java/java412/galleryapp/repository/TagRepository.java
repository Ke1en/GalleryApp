package java412.galleryapp.repository;

import java412.galleryapp.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {
    @Query("SELECT COUNT(i) FROM images i JOIN i.tags t WHERE t.id = :tagId")
    int countImagesByTagId(@Param("tagId") UUID tagId); //TODO переделать под список
}
