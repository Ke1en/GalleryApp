package java412.galleryapp.mapper;

import java412.galleryapp.dto.ThumbnailResponseDto;
import java412.galleryapp.entity.Thumbnail;
import org.springframework.stereotype.Component;

@Component
public class ThumbnailMapper {

    public ThumbnailResponseDto mapToThumbnailResponseDto(Thumbnail thumbnail) {
        return new ThumbnailResponseDto(thumbnail.getId(), thumbnail.getThumbnailUrl(), thumbnail.getCreateDate(), thumbnail.getImageId());
    }

}
