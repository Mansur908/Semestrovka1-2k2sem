package ru.itis.demo.security.details;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.demo.models.User;

import java.util.Collection;
import java.util.Collections;

@Builder
public class UserDetailsImpl implements UserDetails {

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());
        return Collections.singleton(authority);
    }

    public Long getId() {
        return user.getId();
    }

    public String getName() {
        return user.getName();
    }

    public String getImageName() {
        return user.getImage();
    }

    public String getRole(){
        return user.getRole().toString();
    }

    public String getState(){
        return user.getState().toString();
    }

    public void setRole(String role){
        if (role.equals("User"))
            user.setRole(User.Role.USER);
        else
            user.setRole(User.Role.ADMIN);
    }

    @Override
    public String getPassword() {
        return user.getHashPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }
}
