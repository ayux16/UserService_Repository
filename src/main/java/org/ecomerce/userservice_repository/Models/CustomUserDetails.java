package org.ecomerce.userservice_repository.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomUserDetails implements UserDetails{
//Authorities can be implemented later as per requirement
    //they are basicall roles and privileges assigned to user

    private String userName;
    private String password;
    private List<CustomGrantedAuthority> authorities;
    private boolean AccountNonExpired;
    private boolean AccountNonLocked;
    private boolean CredentialsNonExpired;
    private boolean Enabled;

    public CustomUserDetails(User user){
        this.userName = user.getUsername();
        this.password = user.getPassword();
        //authorities can be set here based on user roles/privilege
        this.AccountNonExpired= true; //set based on user status
        this.AccountNonLocked= true; //set based on user status
        this.CredentialsNonExpired= true; //set based on user status
        this.Enabled= true; //set based on user status

        //convert list of roles/privileges to list of CustomGrantedAuthority and assign to authorities
        List<Roles> roles = user.getRoles();
        this.authorities = new ArrayList<>();
        for(Roles role : roles){
           authorities.add(new CustomGrantedAuthority(role.getValue()));
        }

        //in the role we have value ... now we have to convert it int value... for this we have customgrantedauthority class
        //
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return AccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return AccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return CredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return Enabled;
    }
}
