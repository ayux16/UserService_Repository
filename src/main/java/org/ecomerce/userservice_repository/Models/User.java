package org.ecomerce.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.ecomerce.Models.ENUMS.ISVARIFIED;

import java.util.List;

@Getter
@Setter
@Table (name="users")
public class User extends BaseModel{
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Integer phone_Number;
    @ManyToMany
    private List<Roles> roles;
    @Enumerated(EnumType.STRING)
    private ISVARIFIED isvarified;
}
