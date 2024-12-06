package com.example.ProjectStageBackend.service;

import com.example.ProjectStageBackend.model.AccountModel;
import com.example.ProjectStageBackend.model.MessageModel;
import com.example.ProjectStageBackend.repository.AccountRepository;
import com.example.ProjectStageBackend.repository.MessageRepository;
import com.example.ProjectStageBackend.resource.MessageDTO;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private final AccountRepository accountRepository;
    private final MessageRepository messageRepository;
    private final JwtService jwtService;

    public MessageService(AccountRepository accountRepository, MessageRepository messageRepository, JwtService jwtService) {
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
        this.jwtService = jwtService;
    }

    //retrieve username from jwtToken
    //"FFIX" change it to id when implemented
    private String retrieveUsername(String token) {
        String jwtToken = token.substring(7);
        return jwtService.extractUsername(jwtToken);
    }

    //return all messages
    public List<MessageDTO> messageHistory(String token) {
        String username = retrieveUsername(token);
        AccountModel account = accountRepository.findByUsername(username);
        return messageRepository.findAllByAccountIdOrderByTimeAsc(account.getId());
    }

    //save one message
    public void saveMessage(String token, String message, Boolean sender) {
        String username = retrieveUsername(token);
        AccountModel account = accountRepository.findByUsername(username);
        MessageModel save = new MessageModel();
        save.setAccount(account);
        save.setMessage(message);
        save.setSender(sender);
        save.setTime(LocalDateTime.now());
        String test = messageRepository.save(save).toString();
        System.out.println("saving: "+ test);
    }
}
