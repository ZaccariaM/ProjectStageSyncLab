package com.example.ProjectStageBackend.service;

import com.example.ProjectStageBackend.model.AccountModel;
import com.example.ProjectStageBackend.repository.AccountRepository;
import com.example.ProjectStageBackend.resource.AccountDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private AccountService(AccountRepository accountRepository, JwtService jwtService,AuthenticationManager authenticationManager) {this.accountRepository=accountRepository; this.jwtService=jwtService;this.authenticationManager=authenticationManager;}

    //login account
    public String verify(AccountModel acc){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(acc.getUsername(), acc.getPassword()));
        if (authentication.isAuthenticated()){
            System.out.println("verify");
            return jwtService.generateToken(acc.getUsername());
        }else{
            return "Login failed";
        }
    }

    //edit existing account
    public AccountModel update(@NotNull AccountModel acc){
        AccountModel account=accountRepository.findById(acc.getId()).orElseThrow();
        account.setUsername(acc.getUsername());
        account.setEmail(acc.getEmail());
        account.setPassword(new BCryptPasswordEncoder(16).encode(acc.getPassword()));
        accountRepository.save(account);
        return acc;
    }

    //save new account
    public AccountModel save(AccountModel acc){
        return accountRepository.save(acc);
    }

    //delete existing account
    public String delete(Long id){
        AccountModel account = accountRepository.findById(id).orElseThrow();
        accountRepository.delete(account);
        return "Account with id= "+id+" removed";
    }
}
