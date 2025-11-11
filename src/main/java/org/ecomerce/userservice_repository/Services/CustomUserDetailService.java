package org.ecomerce.userservice_repository.Services;

import org.ecomerce.userservice_repository.Models.CustomUserDetails;
import org.ecomerce.userservice_repository.Models.User;
import org.ecomerce.userservice_repository.Repository.UserServicRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {
    private UserServicRepo userServicRepo;

    public CustomUserDetailService(UserServicRepo userServicRepo) {
        this.userServicRepo = userServicRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional= userServicRepo.findByEmail(username);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        User user = userOptional.get();
        //we got the user from database now we have to convert it into UserDetails object
        //now we need userDetails object
        return new CustomUserDetails(user);
    }
}
