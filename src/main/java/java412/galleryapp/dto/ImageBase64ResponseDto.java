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
public class ImageBase64ResponseDto {

    @Schema(description = "Идентификатор изображения")
    private UUID id;

    @Schema(description = "Наименование изображения")
    private String name;

    @Schema(description = "Описание изображения")
    private String description;

    @Schema(type = "String", description = "Изображение в Base64")
    private String image;

}
