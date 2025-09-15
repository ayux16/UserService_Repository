package org.ecomerce.userservice_repository.Controllers;
import org.ecomerce.userservice_repository.DTO.*;
import org.ecomerce.userservice_repository.EXCEPTIONS.ValidTokenNotFoundException;
import org.ecomerce.userservice_repository.Models.Token;
import org.ecomerce.userservice_repository.Models.User;
import org.ecomerce.userservice_repository.Services.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserServiceInterface userService;

    UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public TokenDTO loginRequest(@RequestBody LoginRequestDTO loginRequestDTO) {
        Token token= userService.Login(
                loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword()
        );
        return TokenDTO.from(token);

    }
    /*
    returning a user after signup request might create unwanted data breech problem as it also contains password as well
    so instead of returning a user Class we created a dto with name and email and returning that
     */
    @PostMapping("/signup")
    public UserDTO signupRequest(@RequestBody SignupRequestDTO signupRequestDTO) {
        User user = userService.signup(
                signupRequestDTO.getFirstName(),
                signupRequestDTO.getLastName(),
                signupRequestDTO.getUsername(),
                signupRequestDTO.getEmail(),
                signupRequestDTO.getPassword(),
                signupRequestDTO.getPhone_Number()
        );
        return UserDTO.from(user);
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logoutRequest(@RequestBody LogoutRequestDTO requestDTO) {
        ResponseEntity<Void> responseEntity = null;
        try{
            userService.logout(requestDTO.getTokenValue());
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        }
        catch(ValidTokenNotFoundException e){
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping("/validate/{tokenValue}")
    public UserDTO validateRequest( @PathVariable String tokenValue){
       try {
           User user = userService.validateToken(tokenValue);
           return UserDTO.from(user);
       }
       catch(ValidTokenNotFoundException e){
          throw new RuntimeException("Invalid token");
       }
    }
}
