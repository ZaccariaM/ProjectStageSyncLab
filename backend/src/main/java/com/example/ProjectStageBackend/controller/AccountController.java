package com.example.ProjectStageBackend.controller;

import com.example.ProjectStageBackend.resource.AccountDTO;
import com.example.ProjectStageBackend.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
    private AccountService accountService;

    public AccountController (AccountService accountService){this.accountService = accountService;}

    //save new account
    @PostMapping("/save")
    public ResponseEntity<AccountDTO> save(@RequestBody AccountDTO acc){
        //parsing?
        return ResponseEntity.ok(this.accountService.save(acc));
    }

    //update account
//    @PutMapping("/update")
//    public ResponseEntity<AccountDTO> update(@RequestBody AccountDTO acc){
//        //parsing?
//
//    }

    //delete account
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestHeader Long id){
        //parsing?
        //id or username
        return ResponseEntity.ok(this.accountService.delete(id));
    }

}
