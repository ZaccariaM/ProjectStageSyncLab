package com.example.ProjectStageBackend.controller;

import com.example.ProjectStageBackend.service.LangChainService;
import com.example.ProjectStageBackend.service.MessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class LangChainController {
    private final LangChainService langChainService;
    private final MessageService messageService;

    public LangChainController(LangChainService langChainService, MessageService messageService) {
        this.langChainService = langChainService;
        this.messageService = messageService;
    }

    @GetMapping("/chat")
    public String chat(@RequestHeader("Authorization") String token, @RequestParam String prompt) {
        messageService.saveMessage(token, prompt, true);
        String reply = langChainService.chat(prompt);
        messageService.saveMessage(token, reply, false);
        return reply;
    }
}
