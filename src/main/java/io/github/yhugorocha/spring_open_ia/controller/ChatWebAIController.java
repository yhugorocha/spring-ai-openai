package io.github.yhugorocha.spring_open_ia.controller;

import io.github.yhugorocha.spring_open_ia.service.ChatService;
import io.github.yhugorocha.spring_open_ia.service.RecipeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatWebAIController {

    private final ChatService chatService;
    private final RecipeService recipeService;

    public ChatWebAIController(ChatService chatService, RecipeService recipeService) {
        this.chatService = chatService;
        this.recipeService = recipeService;
    }

    @GetMapping("ask-ai")
    public String chat(@RequestParam String prompt) {
        return chatService.chat(prompt);
    }

    @GetMapping("ask-ai-options")
    public String chatWithOptions(@RequestParam String prompt){
        return chatService.chatWithOptions(prompt);
    }

    @GetMapping("recipe-creator")
    public String chat(@RequestParam String ingredients,
                       @RequestParam(defaultValue = "todas") String cuisine,
                       @RequestParam(defaultValue = "nenhuma") String dietaryRestrictions){
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
    }

}
