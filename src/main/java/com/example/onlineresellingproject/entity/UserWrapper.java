package com.example.onlineresellingproject.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserWrapper implements UserDetails {

    private final UserEntity userEntity;

    public UserWrapper(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority(userEntity.getRole().toString())
        );
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userEntity.isNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userEntity.isNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userEntity.isNonCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return userEntity.isEnabled();
    }
}
