package java412.galleryapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO с основной информацией при регистрации пользователя")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDto {

    @Schema(description = "Имя пользователя")
    private String username;

    @Schema(description = "Пароль пользователя")
    private String password;

    @Schema(description = "Почтовый ящик пользователя")
    private String email;

}
