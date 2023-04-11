package uz.onlinestor.onlinestoreback.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import uz.onlinestor.onlinestoreback.models.contragent.UserApp;

/**
 * DTO class for user requests by ROLE_USER
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private String phone;
    private String email;

    public UserApp toUser(){
        UserApp user = new UserApp();
        user.setId(id);
        user.setUsername(username);
        user.setPhone(phone);
        user.setEmail(email);

        return user;
    }

    public static UserDto fromUser(UserApp user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPhone(user.getPhone());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
