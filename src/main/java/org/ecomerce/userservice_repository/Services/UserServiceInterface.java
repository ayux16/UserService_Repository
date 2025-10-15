package org.ecomerce.userservice_repository.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ecomerce.userservice_repository.DTO.UserDTO;
import org.ecomerce.userservice_repository.Models.Token;
import org.ecomerce.userservice_repository.Models.User;

public interface UserServiceInterface {
    Token Login(String email, String password);
    User signup(String FirstName, String LastName, String username, String Email, String Password,Integer phoneNumber ) throws JsonProcessingException;
    void logout(String token);
    User validateToken(String Token);
}
