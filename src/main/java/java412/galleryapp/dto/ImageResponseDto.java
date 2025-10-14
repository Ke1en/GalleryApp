package java412.galleryapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java412.galleryapp.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Schema(description = "DTO с основной информацией об изображении")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponseDto {

    @Schema(description = "Идентификатор изображения")
    private UUID id;

    @Schema(description = "Путь к изображению")
    private String imageUrl;

    @Schema(description = "Время создания изображения")
    private LocalDateTime createDate;

    @Schema(description = "Список тегов изображения")
    private Set<Tag> tags;

}
