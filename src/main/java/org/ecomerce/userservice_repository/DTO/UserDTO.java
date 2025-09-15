package org.ecomerce.userservice_repository.DTO;

import lombok.Getter;
import lombok.Setter;
import org.ecomerce.userservice_repository.Models.Roles;
import org.ecomerce.userservice_repository.Models.User;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String firstName;
    private String email;
    private List<Roles> roles;

    public static UserDTO from(User user) {
        if(user == null) {
            return null;
        }
        UserDTO userDto= new UserDTO();
        userDto.setFirstName(user.getFirstName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
