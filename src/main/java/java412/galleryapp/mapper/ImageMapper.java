package java412.galleryapp.mapper;

import java412.galleryapp.dto.ImageBase64ResponseDto;
import java412.galleryapp.dto.ImageResponseDto;
import java412.galleryapp.entity.Image;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ImageMapper {

    public ImageResponseDto mapToImageResponseDto(Image image) {
        return new ImageResponseDto(image.getId(), image.getName(), image.getDescription(), image.getImage());
    }

    public ImageBase64ResponseDto mapToImageBase64ResponseDto(Image image) {

        String base64Image = Base64.getEncoder().encodeToString(image.getImage());

        return new ImageBase64ResponseDto(image.getId(), image.getName(), image.getDescription(), base64Image);

    }

}
