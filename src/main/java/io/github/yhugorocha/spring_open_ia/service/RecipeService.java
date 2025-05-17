package io.github.yhugorocha.spring_open_ia.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final ChatModel chatModel;

    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createRecipe(String ingredients,
                               String cuisine,
                               String dietaryRestrictions){

        var template = """
                Eu quero criar uma receita usando os seguintes ingredientes: {ingredients},
                O tipo de cozinha que eu prefiro é: {cuisine},
                Por favor, considere a dieta com estas restrições: {dietaryRestrictions},
                Inclua o título, a lista de ingredientes e como cozinhar.
                """;

        var promptTemplate = new PromptTemplate(template);

        promptTemplate.add("ingredients", ingredients);
        promptTemplate.add("cuisine", cuisine);
        promptTemplate.add("dietaryRestrictions", dietaryRestrictions);

        return chatModel.call(promptTemplate.create()).getResult().getOutput().getText();
    }

}
