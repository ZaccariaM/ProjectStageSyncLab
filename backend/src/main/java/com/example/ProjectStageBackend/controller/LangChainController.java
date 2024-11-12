package com.example.ProjectStageBackend.controller;

import com.example.ProjectStageBackend.service.LangChainService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http:/localhost:4200")
public class LangChainController {
    private final LangChainService langChainService;

    public LangChainController(LangChainService langChainService){
        this.langChainService=langChainService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "prompt", defaultValue = "Hello")String prompt){
        return langChainService.chat(prompt);
    }
}
