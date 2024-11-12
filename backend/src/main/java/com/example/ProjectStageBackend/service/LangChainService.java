package com.example.ProjectStageBackend.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.stereotype.Service;

@Service
public class LangChainService {
    private final ChatLanguageModel chatLanguageModel;

    public LangChainService(ChatLanguageModel chatLanguageModel){
        this.chatLanguageModel=chatLanguageModel;
    }

    public String chat(String prompt){
        return chatLanguageModel.generate(prompt);
    }
}
