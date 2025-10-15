package java412.galleryapp.mapper;

import java412.galleryapp.dto.ThumbnailResponseDto;
import java412.galleryapp.entity.Tag;
import java412.galleryapp.entity.Thumbnail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ThumbnailMapper {

    public ThumbnailResponseDto mapToThumbnailResponseDto(Thumbnail thumbnail, Set<Tag> tags) {
        return new ThumbnailResponseDto(thumbnail.getId(), thumbnail.getThumbnailUrl(), thumbnail.getCreateDate(), thumbnail.getImageId(), tags);
    }

}
