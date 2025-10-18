package java412.galleryapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Schema(description = "DTO с основной информацией об пользователе")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    @Schema(description = "Идентификатор пользователя")
    private UUID id;

    @Schema(description = "Имя пользователя")
    private String username;

    @Schema(description = "Почтовый ящик пользователя")
    private String email;

}
