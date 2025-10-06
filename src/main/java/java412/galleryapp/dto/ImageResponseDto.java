package java412.galleryapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Schema(description = "DTO с основной информацией об изображении")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponseDto {

    @Schema(description = "Идентификатор изображения")
    private UUID id;

    @Schema(type = "byte", description = "Сжатое изображение в байтах")
    private byte[] image;

}
