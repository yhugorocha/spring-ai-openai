package io.github.yhugorocha.spring_open_ia.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String chat(String prompt) {
        return chatModel.call(prompt);
    }

    public String chatWithOptions(String prompt) {
        ChatResponse chatResponse = chatModel.call(
                new Prompt(
                        prompt,
                        OpenAiChatOptions.builder()
                                .model("gpt-4o")
                                .temperature(0.4)
                                .build()
                ));

        return chatResponse.getResult().getOutput().getText();
    }
}
