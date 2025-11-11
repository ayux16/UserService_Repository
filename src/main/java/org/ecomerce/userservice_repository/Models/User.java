package org.ecomerce.userservice_repository.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.ecomerce.userservice_repository.Models.ENUMS.ISVARIFIED;

import java.util.List;

@Getter
@Setter
@Entity
@Table (name="users")
public class User extends BaseModel{
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Integer phone_Number;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Roles> roles;
    @Enumerated(EnumType.STRING)
    private ISVARIFIED isvarified;
}
