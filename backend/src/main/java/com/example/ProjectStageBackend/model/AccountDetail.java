package com.example.ProjectStageBackend.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class AccountDetail implements UserDetails {
    private final AccountModel accountModel;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword(){
        return accountModel.getPassword();
    }

    @Override
    public String getUsername(){
        return accountModel.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
