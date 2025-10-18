package java412.galleryapp.mapper;

import java412.galleryapp.dto.UserResponseDto;
import java412.galleryapp.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto mapToUserResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }

}
