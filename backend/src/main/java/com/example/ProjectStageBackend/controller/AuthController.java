package com.example.ProjectStageBackend.controller;

import com.example.ProjectStageBackend.mapper.AccountMapper;
import com.example.ProjectStageBackend.model.AccountModel;
import com.example.ProjectStageBackend.resource.AccountDTO;
import com.example.ProjectStageBackend.resource.MessageDTO;
import com.example.ProjectStageBackend.service.AccountService;
import com.example.ProjectStageBackend.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    private final AccountService accountService;
    private final MessageService messageService;

    public AuthController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    //login account
    @PostMapping("/login")
    public String login(@RequestBody AccountDTO credentials) {
        System.out.println("### credentials ###");
        System.out.println(credentials.getUsername());
        System.out.println(credentials.getPassword());
        System.out.println("### end credentials ###\n");

        AccountModel acc = AccountMapper.INSTANCE.dtoToModel(credentials);
        return accountService.verify(acc);
    }

    //save new account
    @PostMapping("/register")
    public String register(@RequestBody AccountDTO acc) {
        AccountModel accountModel = AccountMapper.INSTANCE.dtoToModel(acc);
        accountModel.setPassword(new BCryptPasswordEncoder(16).encode(accountModel.getPassword()));
        return AccountMapper.INSTANCE.modelToDto(accountService.save(accountModel)).toString();
    }

    //Logout is not necessary since it's not best practice for jwt
    //if you want something similar add a refresh token, shorten the login one expiration
    //send them both on the request and implement an api to refresh the login with the refresh one

    //update account
    @PutMapping("/update")
    //"FFIX" dunno it's probably bad
    public String update(@RequestBody AccountDTO acc) {
        AccountModel accountModel = AccountMapper.INSTANCE.dtoToModel(acc);
        return AccountMapper.INSTANCE.modelToDto(accountService.update(accountModel)).toString();
    }

    //delete account
    //"FFIX" same with the put it's probably bad
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestHeader Long id) {
        return ResponseEntity.ok(this.accountService.delete(id));
    }

    //message history list of the account
    @GetMapping("/history")
    public List<MessageDTO> messageHistory(@RequestHeader("Authorization") String token) {
        return messageService.messageHistory(token);
    }

    //add messages based on the account
    //directly on the langchain controller
}