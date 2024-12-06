package com.example.ProjectStageBackend.service;

import com.example.ProjectStageBackend.model.AccountDetail;
import com.example.ProjectStageBackend.model.AccountModel;
import com.example.ProjectStageBackend.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceDetails implements org.springframework.security.core.userdetails.UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountModel acc = accountRepository.findByUsername(username);
        if (acc == null) {
            System.out.println("Username not found");   //for debugging
            throw new UsernameNotFoundException("Username not found");
        }

        return new AccountDetail(acc);
    }
}
