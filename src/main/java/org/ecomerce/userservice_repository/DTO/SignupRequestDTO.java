package org.ecomerce.userservice_repository.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Integer phone_Number;
}
