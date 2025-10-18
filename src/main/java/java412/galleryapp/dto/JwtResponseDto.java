package java412.galleryapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO для ответа при логине пользователя")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDto {

    @Schema(description = "Токен пользователя")
    private String token;

}
