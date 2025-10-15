package java412.galleryapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java412.galleryapp.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Schema(description = "DTO с основной информацией об миниатюре")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThumbnailResponseDto {

    @Schema(description = "Идентификатор миниатюры")
    private UUID id;

    @Schema(description = "Путь к миниатюре")
    private String thumbnailUrl;

    @Schema(description = "Время создания миниатюры")
    private LocalDateTime createDate;

    @Schema(description = "id оригинального изображения")
    private UUID imageId;

    @Schema(description = "Тэги оригинального изображения")
    private Set<Tag> imageTags;

}
