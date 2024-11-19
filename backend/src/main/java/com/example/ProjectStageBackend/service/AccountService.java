package com.example.ProjectStageBackend.service;

import com.example.ProjectStageBackend.model.AccountModel;
import com.example.ProjectStageBackend.repository.AccountRepository;
import com.example.ProjectStageBackend.resource.AccountDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {this.accountRepository=accountRepository;}

    //load account by username
//    public UserDetails loadAccountByUsername(String username) throws UsernameNotFoundException{
//        AccountModel account= accountRepository.findByUsername(username);
//        if(account==null){
//            throw new UsernameNotFoundException("Account not found");
//        }
//        return User.builder().username(account.getUsername()).password(new BCryptPasswordEncoder().encode(account.getPassword())).roles("USER").build();
//    }

    //edit existing account
    public AccountDTO update(@NotNull AccountDTO acc){
        AccountModel account=accountRepository.findById(acc.getId()).orElseThrow();
        account.setUsername(acc.getUsername());
        account.setEmail(acc.getEmail());
        account.setPassword(new BCryptPasswordEncoder().encode(acc.getPassword()));
        accountRepository.save(account);
        return acc;
    }

    //save new account
    public AccountDTO save(@NotNull AccountDTO acc){
        AccountModel account = AccountModel.builder().username(acc.getUsername()).email(acc.getEmail()).password(new BCryptPasswordEncoder().encode(acc.getPassword())).build();
        accountRepository.save(account);
        return acc;
    }

    //delete existing account
    public String delete(Long id){
        AccountModel account = accountRepository.findById(id).orElseThrow();
        accountRepository.delete(account);
        return "Account with id= "+id+" removed";
    }

}
