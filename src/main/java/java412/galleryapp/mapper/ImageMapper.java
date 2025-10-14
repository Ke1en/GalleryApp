package java412.galleryapp.mapper;

import java412.galleryapp.dto.ImageResponseDto;
import java412.galleryapp.entity.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public ImageResponseDto mapToImageResponseDto(Image image) {
        return new ImageResponseDto(image.getId(), image.getImageUrl(), image.getCreateDate(), image.getTags());
    }

}
