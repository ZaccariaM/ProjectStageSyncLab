package com.example.ProjectStageBackend.controller;

import com.example.ProjectStageBackend.service.AccountService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
    private AccountService accountService;

    public AccountController (AccountService accountService){this.accountService = accountService;}



}
